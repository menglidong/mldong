package com.mldong.modules.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mldong.modules.sys.dto.SysUserResult;
import com.mldong.modules.sys.dto.SysUserWithRoleIdPageParam;
import com.mldong.modules.sys.entity.SysMenu;
import com.mldong.modules.sys.entity.SysRoleAccess;
import com.mldong.modules.sys.entity.SysUser;

public interface SysUserDao {
	/**
	 * 查询包含角色名称
	 * @return
	 */
	public List<SysUserResult> selectWithRoleName();
	/**
	 * 通过角色id查询列表
	 * @param param
	 * @return
	 */
	public List<SysUser> selectUserByRoleId(SysUserWithRoleIdPageParam param);
	/**
	 * 查询未加入指定角色的用户列表
	 * @param param
	 * @return
	 */
	public List<SysUser> selectUserNoInRole(SysUserWithRoleIdPageParam param);
	/**
	 * 获取用户资源权限标识
	 * @param userId
	 * @return
	 */
	public List<SysRoleAccess> selectUserAccess(@Param("userId")Long userId);
	/**
	 * 获取用户菜单权限
	 * @param userId
	 * @return
	 */
	public List<SysMenu> selectUserMenu(@Param("userId")Long userId);
}
