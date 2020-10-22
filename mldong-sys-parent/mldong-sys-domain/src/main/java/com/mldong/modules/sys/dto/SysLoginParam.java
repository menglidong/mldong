package com.mldong.modules.sys.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

public class SysLoginParam {
	@ApiModelProperty(value="用户名",required=true)
	@NotBlank(message="用户名不能为空")
	private String userName;
	@ApiModelProperty(value="密码", required=true)
	@NotBlank(message="密码不能为空")
	private String password;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
