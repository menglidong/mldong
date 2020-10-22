package com.mldong.modules.cms.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tk.mybatis.mapper.entity.Condition;

import com.github.pagehelper.Page;
import com.mldong.common.base.CommonPage;
import com.mldong.common.base.WhereParam;
import com.mldong.common.base.YesNoEnum;
import com.mldong.common.tk.ConditionUtil;
import com.mldong.modules.cms.dto.CmsCategoryParam;
import com.mldong.modules.cms.dto.CmsCategoryPageParam;
import com.mldong.modules.cms.entity.CmsCategory;
import com.mldong.modules.cms.mapper.CmsCategoryMapper;
import com.mldong.modules.cms.service.CmsCategoryService;
/**
 * <p>业务接口实现层</p>
 * <p>栏目</p>
 *
 * @since 2020-10-22 11:30:30
 */
@Service
public class CmsCategoryServiceImpl implements CmsCategoryService{
	@Autowired
	private CmsCategoryMapper cmsCategoryMapper;
	@Transactional(rollbackFor=Exception.class)
	@Override
	public int save(CmsCategoryParam param) {
		Date now = new Date();
		CmsCategory cmsCategory = new CmsCategory();
		BeanUtils.copyProperties(param, cmsCategory);
		cmsCategory.setCreateTime(now);
		cmsCategory.setUpdateTime(now);
		cmsCategory.setIsDeleted(YesNoEnum.NO);
		return cmsCategoryMapper.insertSelective(cmsCategory);
	}
	@Transactional(rollbackFor=Exception.class)
	@Override
	public int update(CmsCategoryParam param) {
		Date now = new Date();
		CmsCategory cmsCategory = new CmsCategory();
		BeanUtils.copyProperties(param, cmsCategory);
		cmsCategory.setUpdateTime(now);
		return cmsCategoryMapper.updateByPrimaryKeySelective(cmsCategory);
	}
	@Transactional(rollbackFor=Exception.class)
	@Override
	public int remove(List<Long> ids) {
		Date now = new Date();
		CmsCategory upCmsCategory = new CmsCategory();
		upCmsCategory.setUpdateTime(now);
		Condition condition = new Condition(CmsCategory.class);
		condition.createCriteria().andIn("id", ids);
		// 更新时间
		cmsCategoryMapper.updateByConditionSelective(upCmsCategory, condition);
		// 逻辑删除
		return cmsCategoryMapper.deleteByCondition(condition);
	}

	@Override
	public CmsCategory get(Long id) {
		return cmsCategoryMapper.selectByPrimaryKey(id);
	}

	@Override
	public CommonPage<CmsCategory> list(CmsCategoryPageParam param) {
		Page<CmsCategory> page =param.buildPage(true);
		List<WhereParam> whereParams = param.getWhereParams();
		if(null == whereParams || whereParams.isEmpty()) {
			CmsCategory cmsCategory = new CmsCategory();
			cmsCategoryMapper.select(cmsCategory);
		} else {
			cmsCategoryMapper.selectByCondition(ConditionUtil.buildCondition(CmsCategory.class, whereParams));		}
		return CommonPage.toPage(page);
	}

}
