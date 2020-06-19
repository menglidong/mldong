package com.mldong.modules.sys.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.mldong.common.annotation.PhoneValidator;
import com.mldong.common.validator.Groups;
import com.mldong.modules.sys.entity.SysUser;

public class SysUserParam {
	@ApiModelProperty(value="主键-更新时必填")
	@NotBlank(message="主键不能为空",groups={Groups.Update.class})
    private Long id;
	@ApiModelProperty(name="用户名",required=true)
	@NotBlank(message="用户名不能为空",groups={Groups.Save.class})
	private String userName;
	@ApiModelProperty(name="姓名",required=true)
	@NotBlank(message="姓名不能为空",groups={Groups.Save.class,Groups.Update.class})
	private String realName;
	@ApiModelProperty(name="头像",required=false)
	private String avatar;
	@ApiModelProperty(name="邮箱",required=false)
	@Email(message="邮箱不合法",groups={Groups.Save.class,Groups.Update.class})
	private String email;
	@ApiModelProperty(name="手机号",required=true)
	@PhoneValidator(message="手机号不合法",required=false,groups={Groups.Save.class,Groups.Update.class})
	private String mobilePhone;
	@ApiModelProperty(name="联系电话",required=false)
	private String telephone;
	@ApiModelProperty(name="密码",required=false)
	private String password;
	@ApiModelProperty(name="确认密码",required=false)
	private String confirmPassword;
	@ApiModelProperty(name="性别",required=false)
	private SysUser.SexEnum sex;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public SysUser.SexEnum getSex() {
		return sex;
	}
	public void setSex(SysUser.SexEnum sex) {
		this.sex = sex;
	}
	
}
