package com.mldong.modules.sys.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Id;
import javax.persistence.Table;
import tk.mybatis.mapper.annotation.LogicDelete;
import com.mldong.common.base.YesNoEnum;

/**
 * <p>实体类</p>
 * <p>Table: sys_dept - </p>
 * @since 2022-04-23 05:26:04
 */
@Table(name="sys_dept")
@ApiModel(description="")
public class SysDept implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1L;
	@Id
	@ApiModelProperty(value="主键", position = 1)
    private Long id;
    @ApiModelProperty(value = "父级id", position = 10)
    private Long parentId;
    @ApiModelProperty(value = "部门名称", position = 15)
    private String name;
    @ApiModelProperty(value = "部门编码", position = 20)
    private String code;
    @ApiModelProperty(value = "排序", position = 25)
    private Double sort;
    @ApiModelProperty(value = "联系人", position = 30)
    private String contacts;
    @ApiModelProperty(value = "联系人手机号", position = 35)
    private String mobilePhone;
    @ApiModelProperty(value = "电话", position = 40)
    private String telephone;
    @ApiModelProperty(value = "邮箱", position = 45)
    private String email;
    @ApiModelProperty(value = "地址", position = 50)
    private String address;
    @ApiModelProperty(value = "是否启用(1->禁用|NO,2->启用|YES)", position = 55)
    private YesNoEnum isEnabled;
    @ApiModelProperty(value = "创建时间", position = 60)
    private Date createTime;
    @ApiModelProperty(value = "更新时间", position = 65)
    private Date updateTime;
    @ApiModelProperty(value = "是否删除(1->未删除|NO,2->已删除|YES)", position = 70)
	@LogicDelete(isDeletedValue=YesNoEnum.Y,notDeletedValue=YesNoEnum.N)
    private YesNoEnum isDeleted;

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
     * 获取父级id
     *
     */
    public Long getParentId(){
        return this.parentId;
    }
	 /**
     * 设置父级id
     *
     * @param parentId
     */
    public void setParentId(Long parentId){
        this.parentId = parentId;
    }
    /**
     * 获取部门名称
     *
     */
    public String getName(){
        return this.name;
    }
	 /**
     * 设置部门名称
     *
     * @param name
     */
    public void setName(String name){
        this.name = name;
    }
    /**
     * 获取部门编码
     *
     */
    public String getCode(){
        return this.code;
    }
	 /**
     * 设置部门编码
     *
     * @param code
     */
    public void setCode(String code){
        this.code = code;
    }
    /**
     * 获取排序
     *
     */
    public Double getSort(){
        return this.sort;
    }
	 /**
     * 设置排序
     *
     * @param sort
     */
    public void setSort(Double sort){
        this.sort = sort;
    }
    /**
     * 获取联系人
     *
     */
    public String getContacts(){
        return this.contacts;
    }
	 /**
     * 设置联系人
     *
     * @param contacts
     */
    public void setContacts(String contacts){
        this.contacts = contacts;
    }
    /**
     * 获取联系人手机号
     *
     */
    public String getMobilePhone(){
        return this.mobilePhone;
    }
	 /**
     * 设置联系人手机号
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
     * 获取地址
     *
     */
    public String getAddress(){
        return this.address;
    }
	 /**
     * 设置地址
     *
     * @param address
     */
    public void setAddress(String address){
        this.address = address;
    }
    /**
     * 获取是否启用(1->禁用|NO,2->启用|YES)
     *
     */
    public YesNoEnum getIsEnabled(){
        return this.isEnabled;
    }
	 /**
     * 设置是否启用(1->禁用|NO,2->启用|YES)
     *
     * @param isEnabled
     */
    public void setIsEnabled(YesNoEnum isEnabled){
        this.isEnabled = isEnabled;
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

}