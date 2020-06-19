package ${basePackage}.modules.${moduleName}.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.mldong.common.validator.Groups;
import javax.validation.constraints.*;
<#if table.hasOtherDate>
import java.util.Date;
</#if>
<#if table.hasOtherCodedType>
import ${basePackage}.modules.${moduleName}.entity.${table.className};
</#if>
<#if table.hasOtherYesNoEnum>
import ${basePackage}.common.base.YesNoEnum;
</#if>

/**
 * <p>接收请求参数实体</p>
 * <p>Table: ${table.tableName} - ${table.remark?replace("表","")}</p>
 * @since ${.now}
 */
@ApiModel(description="${table.remark?replace("表","")}")
public class ${table.className}Param{

<#list table.columns as column>
    <#if column.primaryKey>
	@ApiModelProperty(value="${column.remark}-更新时必填")
	@NotNull(message="${column.remark}不能为空",groups={Groups.Update.class})
    private ${column.javaType} ${column.javaProperty};
    <#else>
    <#if "isDeleted,createTime,updateTime"?contains(column.javaProperty)==false>
    @ApiModelProperty(value = "${column.remark}",required=${column.nullable?string("false","true")})
    <#if column.codedType>
    private ${table.className}.${column.javaType} ${column.javaProperty};
    <#else>
    <#if column.javaType=="String" && column.nullable!=true>
    @NotBlank(message="${column.remark}不能为空",groups={Groups.Save.class,Groups.Update.class})
    </#if>
    private ${column.javaType} ${column.javaProperty};
    </#if>
    </#if>
    </#if>
</#list>
<#list table.columns as column>
<#if "isDeleted,createTime,updateTime"?contains(column.javaProperty)==false>
<#if column.codedType>
    /**
     * 获取${column.remark}
     *
     */
    public ${table.className}.${column.javaType} ${column.getterMethodName}(){
        return this.${column.javaProperty};
    }
	 /**
     * 设置${column.remark}
     *
     * @param ${column.javaProperty}
     */
    public void ${column.setterMethodName}(${table.className}.${column.javaType} ${column.javaProperty}){
        this.${column.javaProperty} = ${column.javaProperty};
    }
<#else>
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
</#if>
</#if>
</#list>
}