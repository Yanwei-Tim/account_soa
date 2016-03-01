package com.tianque.tableManage.service;

import java.util.List;

public interface TableManageService {
	public void createTable(String createTableSql);

	public void crateIndex(String indexSql);

	public boolean IsCreateTable(String tableName);

	public boolean IsCreateIndex(String tableName);

	public void dorpIndex(String tableName);

	/**
	 * 判断研判分析表是否存在，不存在创建
	 * 
	 * @param tableName
	 *            表名
	 * @param createSql
	 *            创建表的sql
	 * @param year
	 *            年份
	 * @param month
	 *            月份
	 */
	public boolean createAnalyseTable(String tableName, String createSql, int year);

	public boolean isTableExists(List<String> tableNames);

	public void createAnalyseIndex(String prefix, String field1, String field2);

	public void createAnalyseIndex(String tableName, String parame, int year,
                                   int month);

	/**
	 * 根据索引名称判断是否有索引有为true
	 * 
	 * @param indexName
	 * @return
	 */
	public boolean isCreateIndexByIndexName(String indexName);

	/***
	 * 判断某表的某字段是否创建
	 */
	public boolean tableColumnIsCreate(String tableName, String columnName);

}
