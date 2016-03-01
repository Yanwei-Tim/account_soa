package com.tianque.init.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tianque.init.ContextType;

public class SqlScriptExcuteUtil {
	protected final static Logger logger = LoggerFactory.getLogger(SqlScriptExcuteUtil.class);
	public static List<String> loadSqlFile(String fileName) throws Exception{
		FileReader sqlFileIn = new FileReader(fileName);
		BufferedReader bufferedReader = new BufferedReader(sqlFileIn);
		StringBuffer sqlSb = new StringBuffer();
		String str = null;
		while ((str = bufferedReader.readLine()) != null) {
			if (!str.startsWith("/*!") && !str.startsWith("--")
					&& str.indexOf("/*") <= 0 && str.trim().length()>0) {
				sqlSb.append("\n"+str);
			}
		}
		bufferedReader.close();
		sqlFileIn.close();
		String[] sqls = sqlSb.toString().split(";");
		List<String> sqlList = new ArrayList<String>();
		for (int i = 0; i < sqls.length; i++) {
			sqlList.add(new String(sqls[i].getBytes(), "utf-8"));
		}
		return sqlList;
	}

	public static void executeBatchSql(List<String> sqls, DataSource dataSource)
			throws SQLException {
		Connection con = dataSource.getConnection();
		con.setAutoCommit(false);
		Statement statement = con.createStatement();
		try{
			executeBatchSql(sqls, statement);
			con.commit();
		}catch (Exception e) {
			logger.error("异常信息", e);
			con.rollback();
		}finally{
			statement.close();
			con.close();
		}
	}


	private static void executeBatchSql(List<String> sqls, Statement statement)
			throws SQLException {
		for (int i = 0; i < sqls.size(); i = 0) {
			String sql = sqls.get(i);
			sqls.remove(i);
			if(sql==null || "".equals(sql.trim())){
				continue;
			}
			statement.addBatch(sql);
			if (i / 50 == 0) {
				try {
					statement.executeBatch();
				} catch (SQLException e) {
					logger.info("错误SQL：" + sql);
					if (sql.toLowerCase().trim().indexOf("drop")==-1) {
						throw new SQLException(sql, e);
					}
				}
			}
		}
	}
	
	public static DataSource getDataSource(final ContextType contextType) {
		logger.error("contextType:"+contextType);
		DataSource dataSource=new DataSource() {
			private String dirverClassName = (String)PropertiesLoader.get("jdbc.driverClassName"); 
	        private String url = (String)PropertiesLoader.get(contextType.name()+".url"); 
	        private String user = (String)PropertiesLoader.get(contextType.name()+".username"); 
	        private String pswd = (String)PropertiesLoader.get(contextType.name()+".password"); 
			@Override
			public Connection getConnection(String username, String password)
					throws SQLException {
				try {
					Class.forName(dirverClassName);
				} catch (ClassNotFoundException e) {
					logger.error("异常信息", e);
				}
				return DriverManager.getConnection(url,username,password);
			}
			
			@Override
			public Connection getConnection() throws SQLException {
				logger.error("dirverClassName:"+dirverClassName);
				logger.error("url:"+url);
				logger.error("user:"+user);
				logger.error("pswd:"+pswd);
				try {
					Class.forName(dirverClassName);
				} catch (ClassNotFoundException e) {
					logger.error("异常信息", e);
				}
				return DriverManager.getConnection(url,user,pswd);
			} 
			@Override
			public PrintWriter getLogWriter() throws SQLException {
				return null;
			}
			@Override
			public int getLoginTimeout() throws SQLException {
				return 0;
			}
			@Override
			public void setLogWriter(PrintWriter out) throws SQLException {
				
			}
			@Override
			public void setLoginTimeout(int seconds) throws SQLException {
				
			}
			@Override
			public boolean isWrapperFor(Class iface) throws SQLException {
				return false;
			}
			@Override
			public Class unwrap(Class iface) throws SQLException {
				return null;
			}

		};
		
		return dataSource;
	}

}
