package com.mldong.modules.sys.dto;

import io.swagger.annotations.ApiModelProperty;

public class SysUserParam {
	@ApiModelProperty(name="用户名",required=true)
	private String userName;
	@ApiModelProperty(name="姓名",required=true)
	private String realName;
	@ApiModelProperty(name="头像",required=false)
	private String avatar;
	@ApiModelProperty(name="邮箱",required=false)
	private String email;
	@ApiModelProperty(name="手机号",required=true)
	private String mobilePhone;
	@ApiModelProperty(name="联系电话",required=false)
	private String telephone;
	@ApiModelProperty(name="密码",required=false)
	private String password;
	@ApiModelProperty(name="确认密码",required=false)
	private String confirmPassword;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
}
