package com.mldong.generator.config.model;
/**
 * 表配置实体
 * @author mldong
 *
 */
public class TableConfigModel {
	/**
	 * 表名称
	 */
	private String tableName;
	/**
	 * 表别名
	 */
	private String tableAlias;
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getTableAlias() {
		return tableAlias;
	}
	public void setTableAlias(String tableAlias) {
		this.tableAlias = tableAlias;
	}
	
}
