package com.mldong.modules.sys.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Condition;

import com.github.pagehelper.Page;
import com.mldong.common.base.CommonPage;
import com.mldong.common.base.WhereParam;
import com.mldong.common.base.YesNoEnum;
import com.mldong.common.tk.ConditionUtil;
import com.mldong.modules.sys.dto.SysUserPageParam;
import com.mldong.modules.sys.dto.SysUserParam;
import com.mldong.modules.sys.entity.SysUser;
import com.mldong.modules.sys.mapper.SysUserMapper;
import com.mldong.modules.sys.service.SysUserService;
/**
 * 用户管理业务
 * @author mldong
 *
 */
@Service
public class SysUserServiceImpl implements SysUserService{
	@Autowired
	private SysUserMapper sysUserMapper;
	@Override
	public int save(SysUserParam param) {
		Date now = new Date();
		SysUser user = new SysUser();
		BeanUtils.copyProperties(param, user);
		user.setCreateTime(now);
		user.setUpdateTime(now);
		user.setIsDeleted(YesNoEnum.NO);
		return sysUserMapper.insertSelective(user);
	}

	@Override
	public int update(SysUserParam param) {
		Date now = new Date();
		SysUser user = new SysUser();
		BeanUtils.copyProperties(param, user);
		user.setUpdateTime(now);
		return sysUserMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public int remove(List<Long> ids) {
		Date now = new Date();
		SysUser upUser = new SysUser();
		upUser.setIsDeleted(YesNoEnum.YES);
		upUser.setUpdateTime(now);
		Condition condition = new Condition(SysUser.class);
		condition.createCriteria().andIn("id", ids);
		return sysUserMapper.updateByConditionSelective(upUser, condition);
	}

	@Override
	public SysUser get(Long id) {
		return sysUserMapper.selectByPrimaryKey(id);
	}

	@Override
	public CommonPage<SysUser> list(SysUserPageParam param) {
		Page<SysUser> page =param.buildPage(true);
		List<WhereParam> queryModelList = param.getWhereParams();
		if(null == queryModelList || queryModelList.isEmpty()) {
			SysUser user = new SysUser();
			sysUserMapper.select(user);
		} else {
			sysUserMapper.selectByCondition(ConditionUtil.buildCondition(SysUser.class, queryModelList));		}
		return CommonPage.toPage(page);
	}

}
