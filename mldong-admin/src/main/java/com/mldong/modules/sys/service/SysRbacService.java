package com.mldong.modules.sys.service;

import java.util.List;

import com.mldong.common.base.CommonPage;
import com.mldong.common.base.IdAndIdsParam;
import com.mldong.modules.sys.dto.SysUserWithRoleIdPageParam;
import com.mldong.modules.sys.entity.SysMenu;
import com.mldong.modules.sys.entity.SysUser;
import com.mldong.modules.sys.vo.SysAccessTreeVo;
import com.mldong.modules.sys.vo.SysMenuTreeVo;

/**
 * 权限管理相关接口
 * @author mldong
 *
 */
public interface SysRbacService {
	/**
	 * 获取权限树
	 * @param userId 当前用户id
	 * @param roleId 角色id
	 * @return
	 */
	public SysAccessTreeVo listAccessTree(Long userId, Long roleId);
	/**
	 * 获取菜单树
	 * @param userId 当前用户id
	 * @param roleId 角色id
	 * @return
	 */
	public SysMenuTreeVo listMenuByRoleId(Long userId, Long roleId);
	/**
	 * 角色成员列表
	 * @param param
	 * @return
	 */
	public CommonPage<SysUser> listUserByRoleId(SysUserWithRoleIdPageParam param);
	/**
	 * 保存用户角色关系
	 * @param param
	 * @return
	 */
	public int saveUserRole(IdAndIdsParam param);
	/**
	 * 从角色中移除用户
	 * @param param
	 * @return
	 */
	public int deleteUserRole(IdAndIdsParam param);
	/**
	 * 查询未加入指定角色的用户列表
	 * @param param
	 * @return
	 */
	public CommonPage<SysUser> listUserNoInRole(SysUserWithRoleIdPageParam param);
	/**
	 * 保存角色权限资源关系
	 * @param param
	 * @return
	 */
	public int saveRoleAccess(IdAndIdsParam param);
	/**
	 * 删除角色权限资源关系
	 * @param param
	 * @return
	 */
	public int deleteRoleAccess(IdAndIdsParam param);
	/**
	 * 保存角色菜单关系
	 * @param param
	 */
	public int saveRoleMenu(IdAndIdsParam param);
	/**
	 * 删除角色菜单关系
	 * @param param
	 * @return
	 */
	public int deleteRoleMenu(IdAndIdsParam param);
	/**
	 * 判断用户是否有权限
	 * @param userId 用户id
	 * @param access 权限标识
	 * @return
	 */
	public boolean hasAccess(Long userId,String access);
	/**
	 * 加载用户权限资源标识
	 * @param userId
	 * @return
	 */
	public List<String> loadUserAccessList(Long userId);
	/**
	 * 加载用户菜单权限
	 * @param userId
	 * @return
	 */
	public List<SysMenu> loadUserMenuList(Long userId);
}
