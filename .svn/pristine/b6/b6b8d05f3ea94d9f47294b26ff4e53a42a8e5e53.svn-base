package com.tianque.core.base;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapExecutor;

abstract public class AbstractBaseDao<T> extends SqlMapClientDaoSupport implements
		DruidSpringMonitor {
	public final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public void setSqlMapClientBase(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}

	protected void batchInsertDate(List datas, String sqlAlias) {
		List batchList = new ArrayList();
		for (int index = 0; index < datas.size(); index++) {
			batchList.add(datas.get(index));
			if (index != 0 && index % 500 == 0) {
				batchInsertFor500(batchList, sqlAlias);
				batchList.clear();
			}
		}
		if (batchList.size() > 0) {
			batchInsertFor500(batchList, sqlAlias);
		}

	}

	private void batchInsertFor500(final List datas, final String sqlAlias) {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			@Override
			public Object doInSqlMapClient(SqlMapExecutor excutor)
					throws SQLException {
				excutor.startBatch();
				for (Object data : datas) {
					excutor.insert(sqlAlias, data);
				}
				excutor.executeBatch();
				return null;
			}

		});
	}

	protected void batchUpdateDate(List datas, String sqlAlias) {
		List batchList = new ArrayList();
		for (int index = 0; index < datas.size(); index++) {
			batchList.add(datas.get(index));
			if (index != 0 && index % 500 == 0) {
				batchUpdateFor500(batchList, sqlAlias);
				batchList.clear();
			}
		}
		if (batchList.size() > 0) {
			batchInsertFor500(batchList, sqlAlias);
		}

	}

	private void batchUpdateFor500(final List datas, final String sqlAlias) {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			@Override
			public Object doInSqlMapClient(SqlMapExecutor excutor)
					throws SQLException {
				excutor.startBatch();
				for (Object data : datas) {
					excutor.update(sqlAlias, data);
				}
				excutor.executeBatch();
				return null;
			}

		});
	}
}
