package com.mldong.modules.sys.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Condition;

import com.github.pagehelper.Page;
import com.mldong.common.base.CommonPage;
import com.mldong.common.base.YesNoEnum;
import com.mldong.modules.sys.dto.SysRoleParam;
import com.mldong.modules.sys.dto.SysRolePageParam;
import com.mldong.modules.sys.entity.SysRole;
import com.mldong.modules.sys.mapper.SysRoleMapper;
import com.mldong.modules.sys.service.SysRoleService;
/**
 * <p>业务接口实现层</p>
 * <p>角色</p>
 *
 * @since 2020-06-06 06:19:21
 */
@Service
public class SysRoleServiceImpl implements SysRoleService{
	@Autowired
	private SysRoleMapper sysRoleMapper;
	@Override
	public int save(SysRoleParam param) {
		Date now = new Date();
		SysRole sysRole = new SysRole();
		BeanUtils.copyProperties(param, sysRole);
		sysRole.setCreateTime(now);
		sysRole.setUpdateTime(now);
		sysRole.setIsDeleted(YesNoEnum.NO);
		return sysRoleMapper.insertSelective(sysRole);
	}

	@Override
	public int update(SysRoleParam param) {
		Date now = new Date();
		SysRole sysRole = new SysRole();
		BeanUtils.copyProperties(param, sysRole);
		sysRole.setUpdateTime(now);
		return sysRoleMapper.updateByPrimaryKeySelective(sysRole);
	}

	@Override
	public int remove(List<Long> ids) {
		Date now = new Date();
		SysRole upUser = new SysRole();
		upUser.setIsDeleted(YesNoEnum.YES);
		upUser.setUpdateTime(now);
		Condition condition = new Condition(SysRole.class);
		condition.createCriteria().andIn("id", ids);
		return sysRoleMapper.updateByConditionSelective(upUser, condition);
	}

	@Override
	public SysRole get(Long id) {
		return sysRoleMapper.selectByPrimaryKey(id);
	}

	@Override
	public CommonPage<SysRole> list(SysRolePageParam param) {
		Page<SysRole> page =param.buildPage(true);
		SysRole sysRole = new SysRole();
		sysRoleMapper.select(sysRole);
		return CommonPage.toPage(page);
	}

}
