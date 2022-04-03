package com.mldong.modules.sys.dao;

import com.mldong.modules.sys.dto.SysUserPageParam;
import com.mldong.modules.sys.dto.SysUserResult;
import com.mldong.modules.sys.dto.SysUserWithRoleIdPageParam;
import com.mldong.modules.sys.entity.SysMenu;
import com.mldong.modules.sys.entity.SysRoleAccess;
import com.mldong.modules.sys.entity.SysUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserDao {
	/**
	 * 查询包含角色/部门/岗位名称的实体
	 * @return
	 */
	public List<SysUserResult> selectWithExt(SysUserPageParam param);
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

	/**
	 * 获取用户数据范围集合
	 * @param userId
	 * @return
	 */
	public List<String> selectUserDataScope(@Param("userId")Long userId);

	/**
	 * 获取用户角色key
	 * @param userId
	 * @return
	 */
	public List<String> selectUserRoleKey(@Param("userId")Long userId);

	/**
	 * 获取某个角色key的所有用户id
	 * @param roleKey
	 * @return
	 */
	public List<Long> selectUserIdsByRoleKey(@Param("roleKey") String roleKey);
}
