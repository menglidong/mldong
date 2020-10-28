package ${basePackage}.modules.${moduleName}.dto;

import ${basePackage}.common.base.PageParam;
import ${basePackage}.modules.${moduleName}.entity.${table.className};
// START###################
<#list addContent as item>
<#if item_index==0>
${item}
</#if>
</#list>
// ###################END
/**
 * <p>分页查询实体</p>
 * <p>Table: ${table.tableName} - ${table.remark?replace("表","")}</p>
 * @since ${.now}
 */
public class ${table.className}PageParam extends PageParam<${table.className}> {
    // START###################
<#list addContent as item>
<#if item_index==1>
${item}
</#if>
</#list>
    // ###################END
}
