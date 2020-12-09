package ${basePackage}.modules.${moduleName}.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
<#if (table.hasBigDecimalColumn)>
import java.math.BigDecimal;
</#if>
import javax.persistence.Id;
import javax.persistence.Table;
<#if logicDelete>
import tk.mybatis.mapper.annotation.LogicDelete;
</#if>
<#if table.hasOtherCodedType>
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import ${basePackage}.common.annotation.DictEnum;
import ${basePackage}.common.base.CodedEnum;
</#if>
import ${basePackage}.common.base.YesNoEnum;

/**
 * <p>实体类</p>
 * <p>Table: ${table.tableName} - ${table.remark?replace("表","")}</p>
 * @since ${.now}
 */
@Table(name="${table.tableName}")
@ApiModel(description="${table.remark?replace("表","")}")
public class ${table.className} implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1L;
<#list table.columns as column>
    <#if column.primaryKey>
	@Id
	@ApiModelProperty(value="${column.remark}", position = 1)
    private ${column.javaType} ${column.javaProperty};
    <#else>
    @ApiModelProperty(value = "${column.remark}", position = ${(column_index+1)*5})
    <#if column.javaProperty == "isDeleted">
	<#if logicDelete>
	@LogicDelete(isDeletedValue=YesNoEnum.Y,notDeletedValue=YesNoEnum.N)
	</#if>
    private ${column.javaType} ${column.javaProperty};
    <#else>
    private ${column.javaType} ${column.javaProperty};
    </#if>
    </#if>
</#list>

<#list table.columns as column>
    /**
     * 获取${column.remark}
     *
     */
    public ${column.javaType} ${column.getterMethodName}(){
        return this.${column.javaProperty};
    }
	 /**
     * 设置${column.remark}
     *
     * @param ${column.javaProperty}
     */
    public void ${column.setterMethodName}(${column.javaType} ${column.javaProperty}){
        this.${column.javaProperty} = ${column.javaProperty};
    }
</#list>

<#list table.columns as column>
    <#if column.codedType>
    <#if column.javaProperty!="isDeleted">
    @DictEnum(key="${table.tableName}_${column.columnName}",name="${column.codedRemark}")
    public enum ${column.javaType} implements CodedEnum {
	<#list column.codedTypes as coded>
		<#if coded_has_next>
		/**
		 * ${coded.remark}
		 */
		${coded.name}(${coded.value}, "${coded.remark}"),
		</#if>
		<#if !coded_has_next>
		/**
		 * ${coded.remark}
		 */
		${coded.name}(${coded.value}, "${coded.remark}");
		</#if>
	</#list>

		private int value;
		private String name;
		@JsonCreator
	    public static ${column.javaType} forValue(int value) {
	        return CodedEnum.codeOf(${column.javaType}.class, value).get();

	    }
		${column.javaType}(int value, String name) {
			this.value = value;
			this.name = name;
		}
		@JsonValue
		public int getValue() {
			return value;
		}
		public String getName() {
			return name;
		}
	
    }
    </#if>
    </#if>
</#list>
}