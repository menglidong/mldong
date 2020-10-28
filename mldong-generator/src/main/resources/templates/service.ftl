package ${basePackage}.modules.${moduleName}.service;

import java.util.List;

import ${basePackage}.common.base.CommonPage;
import ${basePackage}.modules.${moduleName}.dto.${table.className}Param;
import ${basePackage}.modules.${moduleName}.dto.${table.className}PageParam;
import ${basePackage}.modules.${moduleName}.entity.${table.className};
// START###################
<#list addContent as item>
<#if item_index==0>
${item}
</#if>
</#list>
// ###################END
/**
 * <p>业务接口层</p>
 * <p>${table.remark}</p>
 *
 * @since ${.now}
 */
public interface ${table.className}Service {
	/**
	 * 添加${table.remark}
	 * @param param
	 * @return
	 */
	public int save(${table.className}Param param);
	/**
	 * 更新${table.remark}
	 * @param param
	 * @return
	 */
	public int update(${table.className}Param param);
	/**
	 * 删除${table.remark}
	 * @param ids
	 * @return
	 */
	public int remove(List<Long> ids);
	/**
	 * 查询${table.remark}
	 * @param id
	 * @return
	 */
	public ${table.className} get(Long id);
	/**
	 * 分页查询${table.remark}列表
	 * @param param	分页查询参数
	 * @return
	 */
	public CommonPage<${table.className}> list(${table.className}PageParam param);
	// START###################
<#list addContent as item>
<#if item_index==1>
${item}
</#if>
</#list>
	// ###################END
}