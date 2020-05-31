package com.mldong.modules.sys.dto;

import com.mldong.modules.sys.entity.SysUser;
/**
 * 自定义查询实体层
 * @author mldong
 *
 */
public class SysUserResult extends SysUser{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1872261088219530225L;
	private String roleName;
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
}
