package ${basePackage}.modules.${moduleName}.dto;

import ${basePackage}.common.base.PageParam;
import ${basePackage}.modules.${moduleName}.entity.${table.className};

/**
 * <p>分页查询实体</p>
 * <p>Table: ${table.tableName} - ${table.remark?replace("表","")}</p>
 * @since ${.now}
 */
public class ${table.className}PageParam extends PageParam<${table.className}> {

}
