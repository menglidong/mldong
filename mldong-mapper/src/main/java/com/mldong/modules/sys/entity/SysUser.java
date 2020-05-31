package com.mldong.modules.sys.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 用户表实体
 * @author mldong
 *
 */
@Table(name="sys_user")
public class SysUser implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2687095050668229447L;
	/**
	 * 主键
	 */
	@Id
	private Long id;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 姓名
	 */
	private String realName;
	/**
	 * 头像
	 */
	private String avatar;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 手机号
	 */
	private String mobilePhone;
	/**
	 * 联系电话
	 */
	private String telephone;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 加盐
	 */
	private String salt;
	/**
	 * 性别
	 */
	private Integer sex;
	/**
	 * 是否锁定
	 */
	private Boolean isLocked;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 是否删除
	 */
	private Boolean isDeleted;
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
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Boolean getIsLocked() {
		return isLocked;
	}
	public void setIsLocked(Boolean isLocked) {
		this.isLocked = isLocked;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
}
