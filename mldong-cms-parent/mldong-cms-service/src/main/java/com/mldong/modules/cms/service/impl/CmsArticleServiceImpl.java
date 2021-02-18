package com.mldong.modules.cms.service.impl;

import com.github.pagehelper.Page;
import com.mldong.common.base.CommonPage;
import com.mldong.common.base.WhereParam;
import com.mldong.common.base.YesNoEnum;
import com.mldong.common.dauth.DataScope;
import com.mldong.common.tk.ConditionUtil;
import com.mldong.modules.cms.dao.CmsArticleDao;
import com.mldong.modules.cms.dto.CmsArticlePageParam;
import com.mldong.modules.cms.dto.CmsArticleParam;
import com.mldong.modules.cms.dto.CmsArticleWithExt;
import com.mldong.modules.cms.entity.CmsArticle;
import com.mldong.modules.cms.mapper.CmsArticleMapper;
import com.mldong.modules.cms.service.CmsArticleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import java.util.Date;
import java.util.List;
/**
 * <p>业务接口实现层</p>
 * <p>文章</p>
 *
 * @since 2020-10-22 11:30:29
 */
@Service
public class CmsArticleServiceImpl implements CmsArticleService{
	@Autowired
	private CmsArticleMapper cmsArticleMapper;
	@Autowired
	private CmsArticleDao cmsArticleDao;
	@Transactional(rollbackFor=Exception.class)
	@Override
	public int save(CmsArticleParam param) {
		Date now = new Date();
		CmsArticle cmsArticle = new CmsArticle();
		BeanUtils.copyProperties(param, cmsArticle);
		cmsArticle.setCreateTime(now);
		cmsArticle.setUpdateTime(now);
		cmsArticle.setIsDeleted(YesNoEnum.NO);
		return cmsArticleMapper.insertSelective(cmsArticle);
	}
	@Transactional(rollbackFor=Exception.class)
	@Override
	public int update(CmsArticleParam param) {
		Date now = new Date();
		CmsArticle cmsArticle = new CmsArticle();
		BeanUtils.copyProperties(param, cmsArticle);
		cmsArticle.setUpdateTime(now);
		return cmsArticleMapper.updateByPrimaryKeySelective(cmsArticle);
	}
	@Transactional(rollbackFor=Exception.class)
	@Override
	public int remove(List<Long> ids) {
		Date now = new Date();
		CmsArticle upCmsArticle = new CmsArticle();
		upCmsArticle.setUpdateTime(now);
		Condition condition = new Condition(CmsArticle.class);
		condition.createCriteria().andIn("id", ids);
		// 更新时间
		cmsArticleMapper.updateByConditionSelective(upCmsArticle, condition);
		// 逻辑删除
		return cmsArticleMapper.deleteByCondition(condition);
	}

	@Override
	public CmsArticle get(Long id) {
		return cmsArticleMapper.selectByPrimaryKey(id);
	}

	@Override
	public CommonPage<CmsArticle> list(CmsArticlePageParam param) {
		Page<CmsArticle> page =param.buildPage(true);
		List<WhereParam> whereParams = param.getWhereParams();
		if(null == whereParams || whereParams.isEmpty()) {
			CmsArticle cmsArticle = new CmsArticle();
			cmsArticleMapper.select(cmsArticle);
		} else {
			cmsArticleMapper.selectByCondition(ConditionUtil.buildCondition(CmsArticle.class, whereParams));
		}
		if(param.getIncludeIds()!=null && !param.getIncludeIds().isEmpty()) {
			param.getIncludeIds().removeIf(id -> {
				return page.getResult().stream().filter(item -> {
					return item.getId().equals(id);
				}).count() > 0;
			});
			if(!param.getIncludeIds().isEmpty()) {
				Condition condition = new Condition(CmsArticle.class);
				condition.createCriteria().andIn("id", param.getIncludeIds());
				page.getResult().addAll(0, cmsArticleMapper.selectByCondition(condition));
			}
		}
		return CommonPage.toPage(page);
	}

	@Override
	public CommonPage<CmsArticleWithExt> listWithExt(CmsArticlePageParam param) {
		Page<CmsArticleWithExt> page =param.buildPage(true);
		cmsArticleDao.selectWithExt(param);
		return CommonPage.toPage(page);
	}

	@Override
	public CmsArticleWithExt getWithExt(Long id) {
		return cmsArticleDao.selectOneWithExt(id);
	}

	@Override
	public CommonPage<CmsArticleWithExt> listOnDataScope(CmsArticlePageParam param) {
		Page<CmsArticleWithExt> page =param.buildPage(true);
		cmsArticleDao.selectOnDataScope(param);
		return CommonPage.toPage(page);
	}
	@DataScope(deptAlias = "a", userAlias = "a")
	@Override
	public CommonPage<CmsArticleWithExt> listOnDataScope2(CmsArticlePageParam param) {
		Page<CmsArticleWithExt> page =param.buildPage(true);
		cmsArticleDao.selectWithExt(param);
		return CommonPage.toPage(page);
	}

}
