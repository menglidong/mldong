package com.mldong.modules.sys.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.mldong.common.validator.Groups;
import javax.validation.constraints.NotBlank;
import com.mldong.modules.sys.entity.SysRole;
import com.mldong.common.base.YesNoEnum;

/**
 * <p>接收请求参数实体</p>
 * <p>Table: sys_role - 角色</p>
 * @since 2020-06-05 10:06:12
 */
@ApiModel(description="角色")
public class SysRoleParam{

	@ApiModelProperty(value="主键-更新时必填")
	@NotBlank(message="主键不能为空",groups={Groups.Update.class})
    private Long id;
    @ApiModelProperty(value = "角色名称",required=true)
    @NotBlank(message="角色名称不能为空",groups={Groups.Save.class,Groups.Update.class})
    private String name;
    @ApiModelProperty(value = "角色标识(唯一)",required=false)
    private String roleKey;
    @ApiModelProperty(value = "角色类型(10->管理员|ADMIN,20->流程审核员|WORKFLOW)",required=false)
    private SysRole.RoleTypeEnum roleType;
    @ApiModelProperty(value = "是否启用(1->禁用|NO,2->启用|YES)",required=false)
    private YesNoEnum isEnabled;
    @ApiModelProperty(value = "备注",required=false)
    private String remark;
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
     * 获取角色名称
     *
     */
    public String getName(){
        return this.name;
    }
	 /**
     * 设置角色名称
     *
     * @param name
     */
    public void setName(String name){
        this.name = name;
    }
    /**
     * 获取角色标识(唯一)
     *
     */
    public String getRoleKey(){
        return this.roleKey;
    }
	 /**
     * 设置角色标识(唯一)
     *
     * @param roleKey
     */
    public void setRoleKey(String roleKey){
        this.roleKey = roleKey;
    }
    /**
     * 获取角色类型(10->管理员|ADMIN,20->流程审核员|WORKFLOW)
     *
     */
    public SysRole.RoleTypeEnum getRoleType(){
        return this.roleType;
    }
	 /**
     * 设置角色类型(10->管理员|ADMIN,20->流程审核员|WORKFLOW)
     *
     * @param roleType
     */
    public void setRoleType(SysRole.RoleTypeEnum roleType){
        this.roleType = roleType;
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
}