package com.mldong.modules.sys.service;

import java.util.List;

import com.mldong.common.base.CommonPage;
import com.mldong.modules.sys.dto.SysUserParam;
import com.mldong.modules.sys.entity.SysUser;
/**
 * 用户管理业务接口
 * @author mldong
 *
 */
public interface SysUserService {
	/**
	 * 添加用户
	 * @param param
	 * @return
	 */
	public int save(SysUserParam param);
	/**
	 * 更新用户
	 * @param param
	 * @return
	 */
	public int update(SysUserParam param);
	/**
	 * 删除用户
	 * @param ids
	 * @return
	 */
	public int remove(List<Long> ids);
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
	public CommonPage<SysUser> list(SysUserParam t, int pageNum, int pageSize);

}
