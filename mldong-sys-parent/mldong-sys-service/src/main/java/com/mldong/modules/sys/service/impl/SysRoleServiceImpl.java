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
import com.mldong.modules.sys.dto.SysRoleParam;
import com.mldong.modules.sys.dto.SysRolePageParam;
import com.mldong.modules.sys.entity.SysRole;
import com.mldong.modules.sys.mapper.SysRoleMapper;
import com.mldong.modules.sys.service.SysRoleService;
/**
 * <p>业务接口实现层</p>
 * <p>角色</p>
 *
 * @since 2020-06-08 10:26:59
 */
@Service
public class SysRoleServiceImpl implements SysRoleService{
	@Autowired
	private SysRoleMapper sysRoleMapper;
	@Transactional(rollbackFor=Exception.class)
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
	@Transactional(rollbackFor=Exception.class)
	@Override
	public int update(SysRoleParam param) {
		Date now = new Date();
		SysRole sysRole = new SysRole();
		BeanUtils.copyProperties(param, sysRole);
		sysRole.setUpdateTime(now);
		return sysRoleMapper.updateByPrimaryKeySelective(sysRole);
	}
	@Transactional(rollbackFor=Exception.class)
	@Override
	public int remove(List<Long> ids) {
		Date now = new Date();
		SysRole upUser = new SysRole();
		upUser.setUpdateTime(now);
		Condition condition = new Condition(SysRole.class);
		condition.createCriteria().andIn("id", ids);
		// 更新时间
		sysRoleMapper.updateByConditionSelective(upUser, condition);
		// 逻辑删除
		return sysRoleMapper.deleteByCondition(condition);
	}

	@Override
	public SysRole get(Long id) {
		return sysRoleMapper.selectByPrimaryKey(id);
	}

	@Override
	public CommonPage<SysRole> list(SysRolePageParam param) {
		Page<SysRole> page =param.buildPage(true);
		List<WhereParam> whereParams = param.getWhereParams();
		if(null == whereParams || whereParams.isEmpty()) {
			SysRole sysRole = new SysRole();
			sysRoleMapper.select(sysRole);
		} else {
			sysRoleMapper.selectByCondition(ConditionUtil.buildCondition(SysRole.class, whereParams));		}
		return CommonPage.toPage(page);
	}

}
