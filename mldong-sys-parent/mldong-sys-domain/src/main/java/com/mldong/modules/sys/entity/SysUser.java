package com.mldong.modules.sys.entity;

import io.swagger.annotations.ApiModel;
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
// START###################
// ###################END
/**
 * <p>实体类</p>
 * <p>Table: sys_user - 用户</p>
 * @since 2020-11-05 10:15:38
 */
@Table(name="sys_user")
@ApiModel(description="用户")
public class SysUser implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1L;
	@Id
	@ApiModelProperty(value="主键")
    private Long id;
    @ApiModelProperty(value = "用户名")
    private String userName;
    @ApiModelProperty(value = "姓名")
    private String realName;
    @ApiModelProperty(value = "头像")
    private String avatar;
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "手机号")
    private String mobilePhone;
    @ApiModelProperty(value = "电话")
    private String telephone;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "加盐")
    private String salt;
    @ApiModelProperty(value = "性别(1->男|MALE,2->女|FEMALE,3->未知|UNKNOWN)")
    private SexEnum sex;
    @ApiModelProperty(value = "是否锁定(2->已锁定|YES,1->未锁定|NO)")
    private YesNoEnum isLocked;
    @ApiModelProperty(value = "部门id")
    private Long deptId;
    @ApiModelProperty(value = "岗位id")
    private Long postId;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
    @ApiModelProperty(value = "是否删除(1->未删除|NO,2->已删除|YES)")
	@LogicDelete(isDeletedValue=YesNoEnum.Y,notDeletedValue=YesNoEnum.N)
    private YesNoEnum isDeleted;
// START###################
// ###################END
    /**
     * 获取主键
     *
     */
    public Long getId(){
        return this.id;
    }
	 /**
     * 设置主键
     *
     * @param id
     */
    public void setId(Long id){
        this.id = id;
    }
    /**
     * 获取用户名
     *
     */
    public String getUserName(){
        return this.userName;
    }
	 /**
     * 设置用户名
     *
     * @param userName
     */
    public void setUserName(String userName){
        this.userName = userName;
    }
    /**
     * 获取姓名
     *
     */
    public String getRealName(){
        return this.realName;
    }
	 /**
     * 设置姓名
     *
     * @param realName
     */
    public void setRealName(String realName){
        this.realName = realName;
    }
    /**
     * 获取头像
     *
     */
    public String getAvatar(){
        return this.avatar;
    }
	 /**
     * 设置头像
     *
     * @param avatar
     */
    public void setAvatar(String avatar){
        this.avatar = avatar;
    }
    /**
     * 获取邮箱
     *
     */
    public String getEmail(){
        return this.email;
    }
	 /**
     * 设置邮箱
     *
     * @param email
     */
    public void setEmail(String email){
        this.email = email;
    }
    /**
     * 获取手机号
     *
     */
    public String getMobilePhone(){
        return this.mobilePhone;
    }
	 /**
     * 设置手机号
     *
     * @param mobilePhone
     */
    public void setMobilePhone(String mobilePhone){
        this.mobilePhone = mobilePhone;
    }
    /**
     * 获取电话
     *
     */
    public String getTelephone(){
        return this.telephone;
    }
	 /**
     * 设置电话
     *
     * @param telephone
     */
    public void setTelephone(String telephone){
        this.telephone = telephone;
    }
    /**
     * 获取密码
     *
     */
    public String getPassword(){
        return this.password;
    }
	 /**
     * 设置密码
     *
     * @param password
     */
    public void setPassword(String password){
        this.password = password;
    }
    /**
     * 获取加盐
     *
     */
    public String getSalt(){
        return this.salt;
    }
	 /**
     * 设置加盐
     *
     * @param salt
     */
    public void setSalt(String salt){
        this.salt = salt;
    }
    /**
     * 获取性别(1->男|MALE,2->女|FEMALE,3->未知|UNKNOWN)
     *
     */
    public SexEnum getSex(){
        return this.sex;
    }
	 /**
     * 设置性别(1->男|MALE,2->女|FEMALE,3->未知|UNKNOWN)
     *
     * @param sex
     */
    public void setSex(SexEnum sex){
        this.sex = sex;
    }
    /**
     * 获取是否锁定(2->已锁定|YES,1->未锁定|NO)
     *
     */
    public YesNoEnum getIsLocked(){
        return this.isLocked;
    }
	 /**
     * 设置是否锁定(2->已锁定|YES,1->未锁定|NO)
     *
     * @param isLocked
     */
    public void setIsLocked(YesNoEnum isLocked){
        this.isLocked = isLocked;
    }
    /**
     * 获取部门id
     *
     */
    public Long getDeptId(){
        return this.deptId;
    }
	 /**
     * 设置部门id
     *
     * @param deptId
     */
    public void setDeptId(Long deptId){
        this.deptId = deptId;
    }
    /**
     * 获取岗位id
     *
     */
    public Long getPostId(){
        return this.postId;
    }
	 /**
     * 设置岗位id
     *
     * @param postId
     */
    public void setPostId(Long postId){
        this.postId = postId;
    }
    /**
     * 获取备注
     *
     */
    public String getRemark(){
        return this.remark;
    }
	 /**
     * 设置备注
     *
     * @param remark
     */
    public void setRemark(String remark){
        this.remark = remark;
    }
    /**
     * 获取创建时间
     *
     */
    public Date getCreateTime(){
        return this.createTime;
    }
	 /**
     * 设置创建时间
     *
     * @param createTime
     */
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }
    /**
     * 获取更新时间
     *
     */
    public Date getUpdateTime(){
        return this.updateTime;
    }
	 /**
     * 设置更新时间
     *
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime){
        this.updateTime = updateTime;
    }
    /**
     * 获取是否删除(1->未删除|NO,2->已删除|YES)
     *
     */
    public YesNoEnum getIsDeleted(){
        return this.isDeleted;
    }
	 /**
     * 设置是否删除(1->未删除|NO,2->已删除|YES)
     *
     * @param isDeleted
     */
    public void setIsDeleted(YesNoEnum isDeleted){
        this.isDeleted = isDeleted;
    }
// START###################
// ###################END
    @DictEnum(key="sys_user_sex",name="性别")
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
		UNKNOWN(3, "未知");
		// START###################
		// ###################END
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