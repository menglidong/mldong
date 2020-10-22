package com.mldong.modules.sys.dto;

import com.mldong.modules.sys.entity.SysUser;
import io.swagger.annotations.ApiModelProperty;

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
	@ApiModelProperty(value="角色名称")
	private String roleName;
	@ApiModelProperty(value="部门名称")
	private String deptName;
	@ApiModelProperty(value="岗位名称")
	private String postName;
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}
}
