package com.tianque.tableManage.dao;

import java.util.List;

public interface TableManageDao {
	public void createTable(String createTableSql);

	public void crateIndex(String indexSql);

	public boolean IsCreateTable(String tableName);

	public boolean IsCreateIndex(String tableName);

	public void dorpIndex(String tableName);

	public boolean isTableExists(List<String> tableNames);

	// 判断数组中的索引是否存在
	public boolean IsCreateIndex(String[] indexArr);

	// 创建索引
	public void createIndexArr(List<String> list);

	/**
	 * 根据索引名称判断是否有索引
	 * 
	 * @param indexName
	 */
	public boolean isCreateIndexByIndexName(String indexName);

	/***
	 * 判断表中某字段是否创建
	 */
	public boolean tableColumnIsCreate(String tableName, String columnName);
}
