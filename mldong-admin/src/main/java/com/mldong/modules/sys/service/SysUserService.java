package com.mldong.modules.sys.service;

import java.util.List;

import com.mldong.common.base.CommonPage;
import com.mldong.common.base.IdsParam;
import com.mldong.modules.sys.dto.SysUserPageParam;
import com.mldong.modules.sys.dto.SysUserParam;
import com.mldong.modules.sys.entity.SysUser;
import com.mldong.modules.sys.vo.SysUserVo;
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
	 * @param param
	 * @return
	 */
	public CommonPage<SysUser> list(SysUserPageParam param);
	/**
	 * 重置密码
	 * @param param
	 * @return
	 */
	public int resetPassword(IdsParam param);
	/**
	 * 获取用户信息
	 * @return
	 */
	public SysUserVo getUserInfo(Long userId);
}
