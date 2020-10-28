package ${basePackage}.modules.${moduleName}.mapper;
import ${basePackage}.modules.${moduleName}.entity.${table.className};
import ${basePackage}.common.base.BaseMapper;
// START###################
<#list addContent as item>
<#if item_index==0>
${item}
</#if>
</#list>
// ###################END
/**
 * <p>持久层</p>
 * <p>${table.remark}</p>
 *
 * @since ${.now}
 */
public interface ${table.className}Mapper extends BaseMapper<${table.className}> {
    // START###################
<#list addContent as item>
<#if item_index==1>
${item}
</#if>
</#list>
    // ###################END
}