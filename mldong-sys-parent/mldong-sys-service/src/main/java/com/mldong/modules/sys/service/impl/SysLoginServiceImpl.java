package com.mldong.modules.sys.service.impl;

import com.mldong.common.base.YesNoEnum;
import com.mldong.common.config.GlobalProperties;
import com.mldong.common.exception.BizException;
import com.mldong.common.token.TokenStrategy;
import com.mldong.common.tool.Md5Tool;
import com.mldong.common.web.RequestHolder;
import com.mldong.modules.sys.dao.SysUserDao;
import com.mldong.modules.sys.dto.SysLoginParam;
import com.mldong.modules.sys.entity.SysDept;
import com.mldong.modules.sys.entity.SysUser;
import com.mldong.modules.sys.entity.SysUserLoginTimes;
import com.mldong.modules.sys.entity.SysUserRole;
import com.mldong.modules.sys.enums.SysErrEnum;
import com.mldong.modules.sys.mapper.SysDeptMapper;
import com.mldong.modules.sys.mapper.SysUserLoginTimesMapper;
import com.mldong.modules.sys.mapper.SysUserMapper;
import com.mldong.modules.sys.service.SysLoginService;
import com.mldong.modules.sys.service.SysRbacService;
import com.mldong.modules.sys.vo.SysLoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 登录接口实现
 * @author mldong
 *
 */
@Service
public class SysLoginServiceImpl implements SysLoginService{
	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private SysUserLoginTimesMapper sysUserLoginTimesMapper;
	@Autowired
	private GlobalProperties globalProperties;
	@Autowired
	private TokenStrategy generateTokenStrategy;
	@Autowired
	private SysRbacService sysRbacService;
	@Autowired
	private SysUserDao sysUserDao;
	@Autowired
	private SysDeptMapper sysDeptMapper;
	@Transactional(rollbackFor=Exception.class)
	@Override
	public SysLoginVo login(SysLoginParam param) {
		String userName = param.getUserName();
		String password = param.getPassword();
		SysUser q = new SysUser();
		q.setUserName(userName);
		SysUser user = sysUserMapper.selectOne(q);
		if(null == user) {
			// 用户不存在
			throw new BizException(SysErrEnum.SYS80000001);
		}
		if(YesNoEnum.YES.equals(user.getIsLocked())) {
			//用户已被锁定
			throw new BizException(SysErrEnum.SYS80000004);
		}
		// 校验登录次数
		SysUserLoginTimes userLoginTimesQuery = new SysUserLoginTimes();
		userLoginTimesQuery.setUserId(user.getId());
		SysUserLoginTimes userLoginTimes = sysUserLoginTimesMapper.selectOne(userLoginTimesQuery);
		if(null == userLoginTimes || 
			(null != userLoginTimes && userLoginTimes.getTimes()<=globalProperties.getMaxErrLoginTimes())) {
			String passwordEncry = Md5Tool.md5(password, user.getSalt());
			if(!passwordEncry.equals(user.getPassword())) {
				// 用户名或者密码错误
				Date now = new Date();
				if(userLoginTimes == null) {
					userLoginTimes = new SysUserLoginTimes();
					userLoginTimes.setUserId(user.getId());
					userLoginTimes.setCreateTime(now);
					userLoginTimes.setUpdateTime(now);
					userLoginTimes.setTimes(1);
					sysUserLoginTimesMapper.insertSelective(userLoginTimes);
				} else {
					userLoginTimes.setUserId(null);
					userLoginTimes.setCreateTime(null);
					userLoginTimes.setUpdateTime(now);
					userLoginTimes.setTimes(userLoginTimes.getTimes()+1);
					sysUserLoginTimesMapper.updateByPrimaryKeySelective(userLoginTimes);
				}
				throw new BizException(SysErrEnum.SYS80000002);
			}
		} else {
			// 登录次数太多，账号已被锁定，请联系管理员
			throw new BizException(SysErrEnum.SYS80000003);
		}
		return createLoginVo(user);
	}
	@Transactional(rollbackFor=Exception.class)
	@Override
	public int logout(String token) {
		generateTokenStrategy.removeToken(token);
		return 1;
	}
	/**
	 * 创建登录返回vo
	 * @param user
	 * @return
	 */
	private SysLoginVo createLoginVo (SysUser user) {
		SysLoginVo vo = new SysLoginVo();
		Long userId = user.getId();
		String avatar = user.getAvatar();
		String userName = user.getUserName();
		String realName = user.getRealName();
		Map<String,Object> ext = new HashMap<>();
		ext.put("realName", realName);
		ext.put("deptId", user.getDeptId() == null ? 0L : user.getDeptId());
		ext.put("childDeptIds", "");
		ext.put("roleKey", sysUserDao.selectUserRoleKey(userId));
		if(!RequestHolder.isSuperAdmin()) {
			ext.put("dataScope", sysUserDao.selectUserDataScope(user.getId()));
			if(user.getDeptId()!=null) {
				List<SysDept> childDeptList = findChildDept(user.getDeptId());
				List<String> childDeptIds = childDeptList.stream().map(item -> {
					return item.getId().toString();
				}).collect(Collectors.toList());
				ext.put("childDeptIds", String.join(",", childDeptIds));
			}
		}
		// 创建token
		String token = generateTokenStrategy.generateToken(userId, userName, ext);
		vo.setAccessList(sysRbacService.loadUserAccessList(userId));
		vo.setAvatar(avatar);
		vo.setMenuList(sysRbacService.loadUserMenuList(userId));
		vo.setRealName(realName);
		vo.setUserId(userId);
		vo.setUserName(userName);
		vo.setToken(token);
		return vo;
	}

	/**
	 * 获取所有子部门
	 * @param parentId
	 * @return
	 */
	private List<SysDept> findChildDept(Long parentId) {
		List<SysDept> childDeptList = new ArrayList<>();
		SysDept q = new SysDept();
		q.setIsDeleted(YesNoEnum.NO);
		q.setIsEnabled(YesNoEnum.YES);
		q.setParentId(parentId);
		List<SysDept> deptList= sysDeptMapper.select(q);
		if(!deptList.isEmpty()) {
			childDeptList.addAll(deptList);
			deptList.forEach(item ->{
				childDeptList.addAll(findChildDept(item.getId()));
			});
		}
		return childDeptList;
	}
}
