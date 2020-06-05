package ${basePackage}.modules.${moduleName}.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Condition;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import ${basePackage}.common.base.CommonPage;
import ${basePackage}.common.base.YesNoEnum;
import ${basePackage}.modules.sys.dto.${table.className}Param;
import ${basePackage}.modules.sys.entity.${table.className};
import ${basePackage}.modules.sys.mapper.${table.className}Mapper;
import ${basePackage}.modules.sys.service.${table.className}Service;
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

	@Override
	public int update(${table.className}Param param) {
		Date now = new Date();
		${table.className} ${table.tableCameName} = new ${table.className}();
		BeanUtils.copyProperties(param, ${table.tableCameName});
		${table.tableCameName}.setUpdateTime(now);
		return ${table.tableCameName}Mapper.updateByPrimaryKeySelective(${table.tableCameName});
	}

	@Override
	public int remove(List<Long> ids) {
		Date now = new Date();
		${table.className} upUser = new ${table.className}();
		upUser.setIsDeleted(YesNoEnum.YES);
		upUser.setUpdateTime(now);
		Condition condition = new Condition(${table.className}.class);
		condition.createCriteria().andIn("id", ids);
		return ${table.tableCameName}Mapper.updateByConditionSelective(upUser, condition);
	}

	@Override
	public ${table.className} get(Long id) {
		return ${table.tableCameName}Mapper.selectByPrimaryKey(id);
	}

	@Override
	public CommonPage<${table.className}> list(${table.className}Param param, int pageNum, int pageSize) {
		Page<${table.className}> page = PageHelper.startPage(pageNum, pageSize,true);
		${table.className} ${table.tableCameName} = new ${table.className}();
		BeanUtils.copyProperties(param, ${table.tableCameName});
		${table.tableCameName}Mapper.select(${table.tableCameName});
		return CommonPage.toPage(page);
	}

}
