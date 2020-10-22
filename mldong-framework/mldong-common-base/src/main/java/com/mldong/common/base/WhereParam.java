package com.mldong.common.base;

import com.mldong.common.tool.StringTool;
import io.swagger.annotations.ApiModelProperty;
/**
 * 自定义查询实体
 * @author mldong
 *
 */
public class WhereParam {
	/**
	 * 表别名-xml自定义查询需要
	 */
	@ApiModelProperty(value="表别名",required=false)
	private String tableAlias;
	/**
	 * 操作类型
	 */
	@ApiModelProperty(value="操作类型",required=true)
	private OperateTypeEnum operateType;
	/**
	 * 属性名
	 */
	@ApiModelProperty(value="属性名",required=true)
	private String propertyName;
	/**
	 * 属性值
	 */
	@ApiModelProperty(value="属性值",required=true)
	private Object propertyValue;

	public String getTableAlias() {
		return tableAlias;
	}

	public void setTableAlias(String tableAlias) {
		this.tableAlias = tableAlias;
	}

	public OperateTypeEnum getOperateType() {
		return operateType;
	}
	public void setOperateType(OperateTypeEnum operateType) {
		this.operateType = operateType;
	}
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public Object getPropertyValue() {
		return propertyValue;
	}
	public void setPropertyValue(Object propertyValue) {
		this.propertyValue = propertyValue;
	}
	
	public String getColumnName() {
		return (StringTool.isNotEmpty(tableAlias) ? (tableAlias + ".") : "") + StringTool.humpToLine(propertyName);
	}
}
