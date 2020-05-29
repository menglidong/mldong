package com.mldong.modules.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mldong.common.base.CommonPage;
import com.mldong.modules.sys.entity.SysUser;
import com.mldong.modules.sys.mapper.SysUserMapper;
import com.mldong.modules.sys.service.SysUserService;
@Service
public class SysUserServiceImpl implements SysUserService{
	@Autowired
	private SysUserMapper sysUserMapper;
	@Override
	public int save(SysUser param) {
		return sysUserMapper.insertSelective(param);
	}

	@Override
	public int update(SysUser param) {
		return sysUserMapper.updateByPrimaryKeySelective(param);
	}

	@Override
	public int delete(Long id) {
		return sysUserMapper.deleteByPrimaryKey(id);
	}

	@Override
	public SysUser get(Long id) {
		return sysUserMapper.selectByPrimaryKey(id);
	}

	@Override
	public CommonPage<SysUser> list(SysUser t, int pageNum, int pageSize) {
		Page<SysUser> page = PageHelper.startPage(pageNum, pageSize,true);
		sysUserMapper.select(t);
		return CommonPage.toPage(page);
	}

}
