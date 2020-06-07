package com.mldong.generator.core.model;

import java.util.List;

import com.mldong.common.util.StringUtil;

public class Table {
	/**
	 * 表名称
	 */
	private String tableName;
	/**
	 * 表驼峰名称
	 */
	private String tableCameName;
	/**
	 * 表类型
	 */
	private String tableType;
	/**
	 * 表别名
	 */
	private String tableAlias;
	/**
	 * 表注释
	 */
	private String remark;
	/**
	 * 实体名称
	 */
	private String entityName;
	/**
	 * 主键列表
	 */
	private List<Column> primaryKeys;
	/**
	 * 所有列,包括主键
	 */
	private List<Column> columns;
	private String catalog;
	private String schema;
	private String className;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableCameName() {
		return tableCameName;
	}

	public void setTableCameName(String tableCameName) {
		this.tableCameName = tableCameName;
	}

	public String getTableType() {
		return tableType;
	}

	public void setTableType(String tableType) {
		this.tableType = tableType;
	}

	public String getTableAlias() {
		return tableAlias;
	}

	public void setTableAlias(String tableAlias) {
		this.tableAlias = tableAlias;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public List<Column> getPrimaryKeys() {
		return primaryKeys;
	}

	public void setPrimaryKeys(List<Column> primaryKeys) {
		this.primaryKeys = primaryKeys;
	}

	public String getCatalog() {
		return catalog;
	}

	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	public String getClassName() {
		if (StringUtil.isEmpty(className)) {
			className = StringUtil.getCamelCaseString(tableName, true);
		}
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public boolean isHasParentId() {
		for (Column column : getColumns()) {
			if (column.getJavaProperty().equals("parentId")) {
				return true;
			}
		}
		return false;
	}

	public boolean isHasDateColumn() {
		for (Column column : getColumns()) {
			if (column.isDate()) {
				return true;
			}
		}
		return false;
	}

	public boolean isHasBigDecimalColumn() {
		for (Column column : getColumns()) {
			if (column.isBigDecimal()) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 是否有除YesNoEnum类型的其他类型
	 * @return
	 */
	public boolean isHasOtherCodedType() {
		return getColumns().stream().filter(item->{
			return item.isCodedType();
		}).count() >0;
	}
	/**
	 * 是否有其他YesNoEnum类型
	 * @return
	 */
	public boolean isHasOtherYesNoEnum() {
		return getColumns().stream().filter(item->{
			return "YesNoEnum".equals(item.getJavaType());
		}).count() >1;
	}
	/**
	 * 排除createTime与updateTime两个时间
	 * @return
	 */
	public boolean isHasOtherDate() {
		return getColumns().stream().filter(item->{
			return "Date".equals(item.getJavaType());
		}).count() > 2;
	}
}
