package com.mldong.modules.sys.service;

import com.mldong.common.base.CommonPage;
import com.mldong.modules.sys.entity.SysUser;

public interface SysUserService {
	/**
	 * 添加用户
	 * @param param
	 * @return
	 */
	public int save(SysUser param);
	/**
	 * 更新用户
	 * @param param
	 * @return
	 */
	public int update(SysUser param);
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	public int delete(Long id);
	/**
	 * 查询用户
	 * @param id
	 * @return
	 */
	public SysUser get(Long id);
	/**
	 * 分页查询用户列表
	 * @param t
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public CommonPage<SysUser> list(SysUser t, int pageNum, int pageSize);

}
