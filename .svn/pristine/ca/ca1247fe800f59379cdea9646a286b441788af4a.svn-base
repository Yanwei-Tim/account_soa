package com.tianque.tableManage.dao;

import com.tianque.core.base.AbstractBaseDao;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("tableManageDao")
public class TableManageDaoImpl extends AbstractBaseDao implements
		TableManageDao {
	private Logger logger = Logger.getLogger(TableManageDaoImpl.class);

	public void createTable(String createTableSql) {
		getSqlMapClientTemplate().update("tableManage.createTable",
				createTableSql);

	}

	@Override
	public void crateIndex(String indexSql) {
		getSqlMapClientTemplate().update("tableManage.createIndex", indexSql);

	}

	@Override
	public boolean IsCreateTable(String tableName) {
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject(
				"tableManage.IsCreateTable", tableName);
		return count == 1 ? true : false;
	}

	@Override
	public boolean IsCreateIndex(String tableName) {
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject(
				"tableManage.isCreateIndex", tableName);
		return count == 1 ? true : false;
	}

	@Override
	public boolean IsCreateIndex(String[] indexArr) {
		Map map = new HashMap();
		map.put("tableIndex", indexArr);
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject(
				"tableManage.isCreateIndexArr", map);
		return count == 12 ? true : false;
	}

	@Override
	public void createIndexArr(List<String> list) {

		for (String createIndexString : list) {
			try {
				Map map = new HashMap();
				map.put("createIndexString", createIndexString);
				getSqlMapClientTemplate().insert("tableManage.createIndexArr",
						map);
			} catch (Exception e) {
				continue;
			}
		}
	}

	@Override
	public void dorpIndex(String tableName) {
		getSqlMapClientTemplate().update("tableManage.dropIndex", tableName);

	}

	@Override
	public boolean isTableExists(List<String> tableNames) {
		Map map = new HashMap();
		map.put("tableNames", tableNames);
		Object o = this.getSqlMapClientTemplate().queryForObject(
				"tableManage.isTableExists", map);
		if (o == null) {
			return false;
		}
		Integer num = (Integer) o;
		if (num != 12) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isCreateIndexByIndexName(String indexName) {
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject(
				"tableManage.isCreateIndexByIndexName", indexName);
		return count == 1 ? true : false;
	}

	@Override
	public boolean tableColumnIsCreate(String tableName, String columnName) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableName", tableName);
		map.put("columnName", columnName);
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject(
				"tableManage.tableColumnIsCreate", map);
		return count == 1 ? true : false;
	}
}
