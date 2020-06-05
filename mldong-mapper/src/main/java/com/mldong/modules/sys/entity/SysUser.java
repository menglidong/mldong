package com.mldong.modules.sys.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import tk.mybatis.mapper.annotation.LogicDelete;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.mldong.common.annotation.DictEnum;
import com.mldong.common.base.CodedEnum;
import com.mldong.common.base.YesNoEnum;
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
	@Id
	@ApiModelProperty(value="主键")
	private Long id;
	@ApiModelProperty(value="用户名")
	private String userName;
	@ApiModelProperty(value="姓名")
	private String realName;
	@ApiModelProperty(value="头像")
	private String avatar;
	@ApiModelProperty(value="邮箱")
	private String email;
	@ApiModelProperty(value="手机号")
	private String mobilePhone;
	@ApiModelProperty(value="联系电话")
	private String telephone;
	@ApiModelProperty(value="密码")
	private String password;
	@ApiModelProperty(value="加盐")
	private String salt;
	@ApiModelProperty(value="性别")
	private Integer sex;
	@ApiModelProperty(value="是否锁定")
	private YesNoEnum isLocked;
	@ApiModelProperty(value="创建时间")
	private Date createTime;
	@ApiModelProperty(value="更新时间")
	private Date updateTime;
	@LogicDelete(isDeletedValue=YesNoEnum.Y,notDeletedValue=YesNoEnum.N)
	@ApiModelProperty(value="是否删除")
	private YesNoEnum isDeleted;
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
	public YesNoEnum getIsLocked() {
		return isLocked;
	}
	public void setIsLocked(YesNoEnum isLocked) {
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
	public YesNoEnum getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(YesNoEnum isDeleted) {
		this.isDeleted = isDeleted;
	}
	@DictEnum(name="性别", key="sys_user_sex")
	public enum SexEnum implements CodedEnum {
		
		/**
		 * 男
		 */
		MALE(1, "男"),
		/**
		 * 女
		 */
		FEMALE(2, "女"),
		/**
		 * 未知
		 */
		UNKNOWN(3, "未知"),
		;
		private int value;
		private String name;
		@JsonCreator
	    public static SexEnum forValue(int value) {
	        return CodedEnum.codeOf(SexEnum.class, value).get();

	    }
		SexEnum(int value, String name) {
			this.value = value;
			this.name = name;
		}
		@JsonValue
		public int getValue() {
			return value;
		}
		public String getName() {
			return name;
		}
	
    }
}
