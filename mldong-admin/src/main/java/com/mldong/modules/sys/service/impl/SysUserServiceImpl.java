package com.mldong.modules.sys.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tk.mybatis.mapper.entity.Condition;

import com.github.pagehelper.Page;
import com.mldong.common.base.CommonPage;
import com.mldong.common.base.IdsParam;
import com.mldong.common.base.WhereParam;
import com.mldong.common.base.YesNoEnum;
import com.mldong.common.config.GlobalProperties;
import com.mldong.common.exception.BizException;
import com.mldong.common.tk.ConditionUtil;
import com.mldong.common.tool.Md5Tool;
import com.mldong.common.tool.StringTool;
import com.mldong.modules.sys.dto.SysUserPageParam;
import com.mldong.modules.sys.dto.SysUserParam;
import com.mldong.modules.sys.entity.SysUser;
import com.mldong.modules.sys.enums.SysErrEnum;
import com.mldong.modules.sys.mapper.SysUserMapper;
import com.mldong.modules.sys.service.SysRbacService;
import com.mldong.modules.sys.service.SysUserService;
import com.mldong.modules.sys.vo.SysUserVo;
/**
 * 用户管理业务
 * @author mldong
 *
 */
@Service
public class SysUserServiceImpl implements SysUserService{
	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private GlobalProperties globalProperties;
	@Autowired
	private SysRbacService sysRbacService;
	@Transactional(rollbackFor=Exception.class)
	@Override
	public int save(SysUserParam param) {
		SysUser q = new SysUser();
		q.setUserName(param.getUserName());
		int count = sysUserMapper.selectCount(q);
		if(count>0) {
			// 用户名已存在
			throw new BizException(SysErrEnum.SYS80000007);
		}
		if(StringUtils.isNotEmpty(param.getPassword()) && StringUtils.isNotEmpty(param.getConfirmPassword())) {
			if(!param.getPassword().equals(param.getConfirmPassword())) {
				// 两密码不一致
				throw new BizException(SysErrEnum.SYS80000005);
			}
		} else {
			param.setPassword(globalProperties.getDefaultPassword());
		}
		
		Date now = new Date();
		SysUser user = new SysUser();
		BeanUtils.copyProperties(param, user);
		user.setCreateTime(now);
		user.setUpdateTime(now);
		user.setIsDeleted(YesNoEnum.NO);
		String salt = StringTool.getRandomString(8);
		String passwordEncry = Md5Tool.md5(user.getPassword(), salt);
		user.setPassword(passwordEncry);
		user.setSalt(salt);
		return sysUserMapper.insertSelective(user);
	}
	@Transactional(rollbackFor=Exception.class)
	@Override
	public int update(SysUserParam param) {
		
		Date now = new Date();
		SysUser user = new SysUser();
		BeanUtils.copyProperties(param, user);
		user.setUpdateTime(now);
		String password = param.getPassword();
		String confirmPassword = param.getConfirmPassword();
		if(StringUtils.isNotEmpty(password) && StringUtils.isNotEmpty(confirmPassword)) {
			// 不为空，两密码相等，可以允许修改
			if(password.equals(confirmPassword)) {
				String salt = StringTool.getRandomString(8);
				String passwordEncry = Md5Tool.md5(password, salt);
				user.setSalt(salt);
				user.setPassword(passwordEncry);
			}
		}
		// 用户名不能修改
		user.setUserName(null);
		if(globalProperties.getSuperAdminId().equals(param.getId())) {
			// 超级管理员被锁
			user.setIsLocked(null);
		}
		return sysUserMapper.updateByPrimaryKeySelective(user);
	}
	@Transactional(rollbackFor=Exception.class)
	@Override
	public int remove(List<Long> ids) {
		// 超级管理员不能被删除
		ids.remove(globalProperties.getSuperAdminId());
		if(ids.isEmpty()) {
			return 1;
		}
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
		List<WhereParam> whereParams = param.getWhereParams();
		if(null == whereParams || whereParams.isEmpty()) {
			SysUser user = new SysUser();
			sysUserMapper.select(user);
		} else {
			sysUserMapper.selectByCondition(ConditionUtil.buildCondition(SysUser.class, whereParams));
		}
		return CommonPage.toPage(page);
	}
	@Override
	public int resetPassword(IdsParam param) {
		String defaultPassword = globalProperties.getDefaultPassword();
		SysUser user = new SysUser();
		String salt = StringTool.getRandomString(8);
		String passwordEncry = Md5Tool.md5(defaultPassword, salt);
		user.setSalt(salt);
		user.setPassword(passwordEncry);
		Condition condition = new Condition(SysUser.class);
		condition.createCriteria().andIn("id", param.getIds());
		return sysUserMapper.updateByConditionSelective(user, condition);
	}
	@Override
	public SysUserVo getUserInfo(Long userId) {
		SysUser user = sysUserMapper.selectByPrimaryKey(userId);
		if(null == userId) {
			return null;
		}
		SysUserVo vo = new SysUserVo();
		BeanUtils.copyProperties(user, vo);
		vo.setAccessList(sysRbacService.loadUserAccessList(userId));
		vo.setMenuList(sysRbacService.loadUserMenuList(userId));
		return vo;
	}

}
