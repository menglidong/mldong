package com.mldong.modules.sys.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

import com.mldong.common.base.PageParam;
import com.mldong.modules.sys.entity.SysUser;

public class SysUserWithRoleIdPageParam extends PageParam<SysUser> {
	@ApiModelProperty(value="角色id",required=true)
	@NotBlank(message="角色id")
	private Long roleId;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	
}
