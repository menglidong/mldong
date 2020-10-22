package com.mldong.modules.sys.service.impl;

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
import com.mldong.modules.sys.dto.SysPostParam;
import com.mldong.modules.sys.dto.SysPostPageParam;
import com.mldong.modules.sys.entity.SysPost;
import com.mldong.modules.sys.mapper.SysPostMapper;
import com.mldong.modules.sys.service.SysPostService;
/**
 * <p>业务接口实现层</p>
 * <p>岗位</p>
 *
 * @since 2020-10-21 09:08:07
 */
@Service
public class SysPostServiceImpl implements SysPostService{
	@Autowired
	private SysPostMapper sysPostMapper;
	@Transactional(rollbackFor=Exception.class)
	@Override
	public int save(SysPostParam param) {
		Date now = new Date();
		SysPost sysPost = new SysPost();
		BeanUtils.copyProperties(param, sysPost);
		sysPost.setCreateTime(now);
		sysPost.setUpdateTime(now);
		sysPost.setIsDeleted(YesNoEnum.NO);
		return sysPostMapper.insertSelective(sysPost);
	}
	@Transactional(rollbackFor=Exception.class)
	@Override
	public int update(SysPostParam param) {
		Date now = new Date();
		SysPost sysPost = new SysPost();
		BeanUtils.copyProperties(param, sysPost);
		sysPost.setUpdateTime(now);
		return sysPostMapper.updateByPrimaryKeySelective(sysPost);
	}
	@Transactional(rollbackFor=Exception.class)
	@Override
	public int remove(List<Long> ids) {
		Date now = new Date();
		SysPost upSysPost = new SysPost();
		upSysPost.setUpdateTime(now);
		Condition condition = new Condition(SysPost.class);
		condition.createCriteria().andIn("id", ids);
		// 更新时间
		sysPostMapper.updateByConditionSelective(upSysPost, condition);
		// 逻辑删除
		return sysPostMapper.deleteByCondition(condition);
	}

	@Override
	public SysPost get(Long id) {
		return sysPostMapper.selectByPrimaryKey(id);
	}

	@Override
	public CommonPage<SysPost> list(SysPostPageParam param) {
		Page<SysPost> page =param.buildPage(true);
		List<WhereParam> whereParams = param.getWhereParams();
		if(null == whereParams || whereParams.isEmpty()) {
			SysPost sysPost = new SysPost();
			sysPostMapper.select(sysPost);
		} else {
			sysPostMapper.selectByCondition(ConditionUtil.buildCondition(SysPost.class, whereParams));		}
		return CommonPage.toPage(page);
	}

}
