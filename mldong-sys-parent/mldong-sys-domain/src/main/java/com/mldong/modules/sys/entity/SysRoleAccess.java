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
 * <p>Table: sys_role_access - 角色权限关系</p>
 * @since 2020-06-07 08:49:04
 */
@Table(name="sys_role_access")
@ApiModel(description="角色权限关系")
public class SysRoleAccess implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1L;
	@Id
	@ApiModelProperty(value="主键")
    private Long id;
    @ApiModelProperty(value = "角色id")
    private Long roleId;
    @ApiModelProperty(value = "权限标识")
    private String access;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
    @ApiModelProperty(value = "是否删除(1->未删除|NO,2->已删除|YES)")
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
     * 获取角色id
     *
     */
    public Long getRoleId(){
        return this.roleId;
    }
	 /**
     * 设置角色id
     *
     * @param roleId
     */
    public void setRoleId(Long roleId){
        this.roleId = roleId;
    }
    /**
     * 获取权限标识
     *
     */
    public String getAccess(){
        return this.access;
    }
	 /**
     * 设置权限标识
     *
     * @param access
     */
    public void setAccess(String access){
        this.access = access;
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