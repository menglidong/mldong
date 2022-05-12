package com.mldong.modules.sys.service.impl;

import com.github.pagehelper.Page;
import com.mldong.common.base.*;
import com.mldong.common.base.constant.GlobalErrEnum;
import com.mldong.common.config.GlobalProperties;
import com.mldong.common.exception.BizException;
import com.mldong.common.tk.ConditionUtil;
import com.mldong.common.tool.Md5Tool;
import com.mldong.common.tool.StringTool;
import com.mldong.modules.sys.dao.SysUserDao;
import com.mldong.modules.sys.dto.*;
import com.mldong.modules.sys.entity.SysUser;
import com.mldong.modules.sys.enums.SysErrEnum;
import com.mldong.modules.sys.mapper.SysUserMapper;
import com.mldong.modules.sys.service.SysRbacService;
import com.mldong.modules.sys.service.SysUserService;
import com.mldong.modules.sys.vo.SysUserVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import java.util.Date;
import java.util.List;
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
	@Autowired
	private SysUserDao sysUserDao;
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
		if(globalProperties.getSuperAdminId().equals(param.getId())) {
			throw new BizException(new CommonError() {
				@Override
				public int getValue() {
					return GlobalErrEnum.GL99990100.getValue();
				}

				@Override
				public String getName() {
					return "超级管理员账号密码不可修改";
				}
			});
		}
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
			} else {
				// 两密码不一致
				throw new BizException(SysErrEnum.SYS80000005);
			}
		} else{
			user.setPassword(null);
			user.setSalt(null);
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
		upUser.setUpdateTime(now);
		Condition condition = new Condition(SysUser.class);
		condition.createCriteria().andIn("id", ids);
		// 更新时间
		sysUserMapper.updateByConditionSelective(upUser, condition);
		// 逻辑删除
		return sysUserMapper.deleteByCondition(condition);
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
		if(param.getIncludeIds()!=null && !param.getIncludeIds().isEmpty()) {
			param.getIncludeIds().removeIf(id -> {
				return page.getResult().stream().filter(item -> {
					return item.getId().equals(id);
				}).count() > 0;
			});
			if(!param.getIncludeIds().isEmpty()) {
				Condition condition = new Condition(SysUser.class);
				condition.createCriteria().andIn("id", param.getIncludeIds());
				page.getResult().addAll(0, sysUserMapper.selectByCondition(condition));
			}
		}
		return CommonPage.toPage(page);
	}
	@Override
	public int resetPassword(IdsParam param) {
		if(param.getIds().contains(globalProperties.getSuperAdminId()) || param.getIds().contains(20)) {
			throw new BizException(new CommonError() {
				@Override
				public int getValue() {
					return GlobalErrEnum.GL99990100.getValue();
				}

				@Override
				public String getName() {
					return "超级管理员账号密码不可重置";
				}
			});
		}
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

	@Override
	public CommonPage<SysUserResult> listWithExt(SysUserPageParam param) {
		Page<SysUserResult> page =param.buildPage(true);
		sysUserDao.selectWithExt(param);
		return CommonPage.toPage(page);
	}

	@Override
	public SysUser getProfile(Long userId) {
		SysUser user = sysUserMapper.selectByPrimaryKey(userId);
		if(null == userId) {
			return null;
		}
		user.setPassword(null);
		user.setSalt(null);
		return user;
	}
	@Value("${spring.profiles.active}")
	private String profiles;
	@Override
	public int updatePwd(SysUpdatePwdParam param) {
		if(globalProperties.getSuperAdminId().equals(param.getUserId())&&"demo".equals(profiles)) {
			// 演示站超级管理员不开放修改密码权限
			throw new BizException(GlobalErrEnum.GL99990013);
		}
		SysUser user = sysUserMapper.selectByPrimaryKey(param.getUserId());
		if(user == null) {
			// 用户不存在
			throw new BizException(SysErrEnum.SYS80000001);
		}
		if(!Md5Tool.md5(param.getPassword(),user.getSalt()).equals(user.getPassword())) {
			// 旧密码错误
			throw new BizException(SysErrEnum.SYS80000006);
		}
		String salt = StringTool.getRandomString(8);
		SysUser up = new SysUser();
		up.setId(user.getId());
		String passwordEncry = Md5Tool.md5(param.getNewPassword(), salt);
		up.setPassword(passwordEncry);
		up.setSalt(salt);
		return sysUserMapper.updateByPrimaryKeySelective(up);
	}

	@Override
	public int uploadAvatar(SysAvatarParam param) {
		SysUser user = new SysUser();
		user.setId(param.getUserId());
		user.setAvatar(param.getAvatar());
		return sysUserMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public int updateProfile(SysUpdateProfileParam param) {
		SysUser user = new SysUser();
		user.setId(param.getUserId());
		BeanUtils.copyProperties(param, user);
		return sysUserMapper.updateByPrimaryKeySelective(user);
	}
}
