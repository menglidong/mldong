package com.mldong.modules.sys.service.impl;

import java.util.Date;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Condition;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mldong.common.base.CommonPage;
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
		user.setIsDeleted(false);
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
		upUser.setIsDeleted(true);
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
	public CommonPage<SysUser> list(SysUserParam param, int pageNum, int pageSize) {
		Page<SysUser> page = PageHelper.startPage(pageNum, pageSize,true);
		SysUser user = new SysUser();
		BeanUtils.copyProperties(param, user);
		sysUserMapper.select(user);
		return CommonPage.toPage(page);
	}

}
