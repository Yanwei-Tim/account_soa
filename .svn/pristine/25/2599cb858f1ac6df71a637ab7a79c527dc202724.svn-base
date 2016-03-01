package com.tianque.init.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tianque.core.util.GlobalValue;
import com.tianque.init.ContextType;
import com.tianque.init.Initialization;
import com.tianque.init.util.PropertiesLoader;
import com.tianque.init.util.SqlScriptExcuteUtil;
import com.tianque.init.xml.XmlUtil;

public class DatabaseInitialization implements Initialization {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	private ContextType contextType;

	public DatabaseInitialization(ContextType contextType) {
		this.contextType = contextType;
		logger.error("contextType:" + contextType);
	}

	@Override
	public void init() throws Exception {
		logger.info("DatabaseInitialization  init!");
		try {
			clearOldObjects();
			createTableForSqls();
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("初始化错误：", e);
		}
		logger.info("数据表Rebuilder完成!");
	}

	private void createTableForSqls() {
		File sqlFile = new File(GlobalValue.SQLS_TABLE_PATH);
		File[] file = sqlFile.listFiles();
		try {
			List<String> sqls;
			for (File files : file) {
				if (!files.isDirectory()) {
					sqls = loadSqlFile(files.getPath());
					SqlScriptExcuteUtil.executeBatchSql(sqls, getDataSource());
				}
			}
			for (InputStream inputStream : XmlUtil.getSqlInputStreams()) {
				sqls = loadSqlFile(inputStream);
				SqlScriptExcuteUtil.executeBatchSql(sqls, getDataSource());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("初始化时读取sql文件出错：", e);
		}
	}

	public void alterTableCache() throws Exception {
		List<String> sqls = loadAlterTableCacheSql();
		SqlScriptExcuteUtil.executeBatchSql(sqls, getDataSource());
	}

	private List<String> loadAlterTableCacheSql() throws Exception {
		List<String> sqlList = new ArrayList<String>();

		DataSource dataSource = SqlScriptExcuteUtil.getDataSource(contextType);
		Connection connection = dataSource.getConnection();
		List<String> tables = getOracleObjectNames("table", connection);
		connection.close();
		for (String table : tables) {
			if (!table.toLowerCase().startsWith("c3p0")
					&& table.indexOf("$") < 0 && table.indexOf("=") < 0)
				sqlList.add("alter table " + table + " cache");
		}
		connection.close();
		return sqlList;
	}

	private void clearOldObjects() throws Exception {

		DataSource dataSource = SqlScriptExcuteUtil.getDataSource(contextType);
		Connection connection = dataSource.getConnection();

		deleteTables(connection, getOracleObjectNames("table", connection));
		deleteSequences(connection,
				getOracleObjectNames("sequence", connection));

		connection.close();

	}

	private void deleteTables(Connection connection, List<String> oracleObjects)
			throws SQLException {
		Statement deleteStatement = connection.createStatement();
		for (String tableName : oracleObjects) {
			if (!tableName.toLowerCase().startsWith("c3p0")
					&& tableName.indexOf("$") < 0 && tableName.indexOf("=") < 0)
				deleteStatement.addBatch("drop table " + tableName
						+ " cascade constraints");
		}
		deleteStatement.executeBatch();
		deleteStatement.close();
	}

	private void deleteSequences(Connection connection,
			List<String> oracleObjects) throws SQLException {
		Statement deleteStatement = connection.createStatement();
		for (String sequence : oracleObjects) {
			deleteStatement.addBatch("drop sequence " + sequence);
		}
		deleteStatement.executeBatch();
		deleteStatement.close();
	}

	private List<String> getOracleObjectNames(String objectType,
			Connection connection) throws SQLException {
		String sql = "select " + objectType + "_name from user_" + objectType
				+ "s ";
		List<String> oracleObjects = new ArrayList<String>();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		while (resultSet.next()) {
			oracleObjects.add(resultSet.getString(objectType + "_name"));
		}
		statement.close();
		return oracleObjects;
	}

	private DataSource getDataSource() {
		DataSource dataSource = new DataSource() {
			private String dirverClassName = (String) PropertiesLoader
					.get("jdbc.driverClassName");
			private String url = (String) PropertiesLoader.get(contextType
					.name()
					+ ".url");
			private String user = (String) PropertiesLoader.get(contextType
					.name()
					+ ".username");
			private String pswd = (String) PropertiesLoader.get(contextType
					.name()
					+ ".password");

			@Override
			public Connection getConnection(String username, String password)
					throws SQLException {
				try {
					Class.forName(dirverClassName);
				} catch (ClassNotFoundException e) {
					logger.error("异常信息", e);
				}
				return DriverManager.getConnection(url, username, password);
			}

			@Override
			public Connection getConnection() throws SQLException {
				try {
					Class.forName(dirverClassName);
				} catch (ClassNotFoundException e) {
					logger.error("异常信息", e);
				}
				return DriverManager.getConnection(url, user, pswd);
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

	private List<String> loadSqlFile(InputStream inputStream) throws Exception {
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(inputStream));
		StringBuffer sqlSb = new StringBuffer();
		String str = null;
		while ((str = bufferedReader.readLine()) != null) {
			if (!str.startsWith("/*!") && !str.startsWith("--")
					&& str.indexOf("/*") <= 0 && str.trim().length() > 0) {
				sqlSb.append("\n" + str);
			}
		}
		bufferedReader.close();
		String[] sqls = sqlSb.toString().split(";");
		List<String> sqlList = new ArrayList<String>();
		for (int i = 0; i < sqls.length; i++) {
			sqlList.add(new String(sqls[i].getBytes(), "utf-8"));
		}
		return sqlList;
	}

	private List<String> loadSqlFile(String fileName) throws Exception {
		return loadSqlFile(new FileInputStream(fileName));
	}

}
