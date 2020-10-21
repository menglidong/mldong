package com.mldong.modules.sys.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.mldong.common.validator.Groups;
import javax.validation.constraints.*;
import com.mldong.common.base.YesNoEnum;

/**
 * <p>接收请求参数实体</p>
 * <p>Table: sys_dept - 部门</p>
 * @since 2020-10-21 09:39:50
 */
@ApiModel(description="部门")
public class SysDeptParam{

	@ApiModelProperty(value="主键-更新时必填")
	@NotNull(message="主键不能为空",groups={Groups.Update.class})
    private Long id;
    @ApiModelProperty(value = "父级id",required=false)
    private Long parentId;
    @ApiModelProperty(value = "机构编码",required=true)
    @NotBlank(message="机构编码不能为空",groups={Groups.Save.class,Groups.Update.class})
    private String name;
    @ApiModelProperty(value = "机构编码",required=false)
    private String code;
    @ApiModelProperty(value = "排序",required=false)
    private Double sort;
    @ApiModelProperty(value = "联系人",required=false)
    private String contacts;
    @ApiModelProperty(value = "联系人手机号",required=false)
    private String mobilePhone;
    @ApiModelProperty(value = "电话",required=false)
    private String telephone;
    @ApiModelProperty(value = "邮箱",required=false)
    private String email;
    @ApiModelProperty(value = "地址",required=false)
    private String address;
    @ApiModelProperty(value = "是否启用(1->禁用|NO,2->启用|YES)",required=false)
    private YesNoEnum isEnabled;
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
     * 获取机构编码
     *
     */
    public String getName(){
        return this.name;
    }
	 /**
     * 设置机构编码
     *
     * @param name
     */
    public void setName(String name){
        this.name = name;
    }
    /**
     * 获取机构编码
     *
     */
    public String getCode(){
        return this.code;
    }
	 /**
     * 设置机构编码
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
}