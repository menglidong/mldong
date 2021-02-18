package ${basePackage}.modules.${moduleName}.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tk.mybatis.mapper.entity.Condition;

import com.github.pagehelper.Page;
import ${basePackage}.common.base.CommonPage;
import ${basePackage}.common.base.WhereParam;
import ${basePackage}.common.base.YesNoEnum;
import ${basePackage}.common.tk.ConditionUtil;
import ${basePackage}.modules.${moduleName}.dto.${table.className}Param;
import ${basePackage}.modules.${moduleName}.dto.${table.className}PageParam;
import ${basePackage}.modules.${moduleName}.entity.${table.className};
import ${basePackage}.modules.${moduleName}.mapper.${table.className}Mapper;
import ${basePackage}.modules.${moduleName}.service.${table.className}Service;

/**
 * <p>业务接口实现层</p>
 * <p>${table.remark}</p>
 *
 * @since ${.now}
 */
@Service
public class ${table.className}ServiceImpl implements ${table.className}Service{
	@Autowired
	private ${table.className}Mapper ${table.tableCameName}Mapper;
	@Transactional(rollbackFor=Exception.class)
	@Override
	public int save(${table.className}Param param) {
		Date now = new Date();
		${table.className} ${table.tableCameName} = new ${table.className}();
		BeanUtils.copyProperties(param, ${table.tableCameName});
		${table.tableCameName}.setCreateTime(now);
		${table.tableCameName}.setUpdateTime(now);
		${table.tableCameName}.setIsDeleted(YesNoEnum.NO);
		return ${table.tableCameName}Mapper.insertSelective(${table.tableCameName});
	}
	@Transactional(rollbackFor=Exception.class)
	@Override
	public int update(${table.className}Param param) {
		Date now = new Date();
		${table.className} ${table.tableCameName} = new ${table.className}();
		BeanUtils.copyProperties(param, ${table.tableCameName});
		${table.tableCameName}.setUpdateTime(now);
		return ${table.tableCameName}Mapper.updateByPrimaryKeySelective(${table.tableCameName});
	}
	@Transactional(rollbackFor=Exception.class)
	@Override
	public int remove(List<Long> ids) {
		Date now = new Date();
		${table.className} up${table.className} = new ${table.className}();
		up${table.className}.setUpdateTime(now);
		Condition condition = new Condition(${table.className}.class);
		condition.createCriteria().andIn("id", ids);
		// 更新时间
		${table.tableCameName}Mapper.updateByConditionSelective(up${table.className}, condition);
		// 逻辑删除
		return ${table.tableCameName}Mapper.deleteByCondition(condition);
	}

	@Override
	public ${table.className} get(Long id) {
		return ${table.tableCameName}Mapper.selectByPrimaryKey(id);
	}

	@Override
	public CommonPage<${table.className}> list(${table.className}PageParam param) {
		Page<${table.className}> page =param.buildPage(true);
		List<WhereParam> whereParams = param.getWhereParams();
		if(null == whereParams || whereParams.isEmpty()) {
			${table.className} ${table.tableCameName} = new ${table.className}();
			${table.tableCameName}Mapper.select(${table.tableCameName});
		} else {
			${table.tableCameName}Mapper.selectByCondition(ConditionUtil.buildCondition(${table.className}.class, whereParams));
        }
		if(param.getIncludeIds()!=null && !param.getIncludeIds().isEmpty()) {
			param.getIncludeIds().removeIf(id -> {
				return page.getResult().stream().filter(item -> {
					return item.getId().equals(id);
				}).count() > 0;
			});
			if(!param.getIncludeIds().isEmpty()) {
				Condition condition = new Condition(${table.className}.class);
				condition.createCriteria().andIn("id", param.getIncludeIds());
				page.getResult().addAll(0, ${table.tableCameName}Mapper.selectByCondition(condition));
			}
		}
		return CommonPage.toPage(page);
	}
}