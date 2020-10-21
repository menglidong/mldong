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
 * <p>Table: sys_post - 岗位</p>
 * @since 2020-10-21 09:08:07
 */
@Table(name="sys_post")
@ApiModel(description="岗位")
public class SysPost implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1L;
	@Id
	@ApiModelProperty(value="主键")
    private Long id;
    @ApiModelProperty(value = "岗位名称")
    private String name;
    @ApiModelProperty(value = "岗位编号")
    private String code;
    @ApiModelProperty(value = "排序")
    private Double sort;
    @ApiModelProperty(value = "是否启用(1->禁用|NO,2->启用|YES)")
    private YesNoEnum isEnabled;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
    @ApiModelProperty(value = "是否删除(1->未删除|YES,2->已删除|NO)")
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
     * 获取岗位名称
     *
     */
    public String getName(){
        return this.name;
    }
	 /**
     * 设置岗位名称
     *
     * @param name
     */
    public void setName(String name){
        this.name = name;
    }
    /**
     * 获取岗位编号
     *
     */
    public String getCode(){
        return this.code;
    }
	 /**
     * 设置岗位编号
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
     * 获取是否删除(1->未删除|YES,2->已删除|NO)
     *
     */
    public YesNoEnum getIsDeleted(){
        return this.isDeleted;
    }
	 /**
     * 设置是否删除(1->未删除|YES,2->已删除|NO)
     *
     * @param isDeleted
     */
    public void setIsDeleted(YesNoEnum isDeleted){
        this.isDeleted = isDeleted;
    }
}