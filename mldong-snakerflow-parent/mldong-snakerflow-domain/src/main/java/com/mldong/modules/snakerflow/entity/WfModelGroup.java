package com.mldong.modules.snakerflow.entity;

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
 * <p>Table: wf_model_group - 模型分组</p>
 * @since 2022-05-08 09:18:24
 */
@Table(name="wf_model_group")
@ApiModel(description="模型分组")
public class WfModelGroup implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1L;
	@Id
	@ApiModelProperty(value="主键", position = 1)
    private Long id;
    @ApiModelProperty(value = "分组名称", position = 10)
    private String name;
    @ApiModelProperty(value = "备注", position = 15)
    private String remark;
    @ApiModelProperty(value = "用户ID", position = 20)
    private Long userId;
    @ApiModelProperty(value = "创建时间", position = 25)
    private Date createTime;
    @ApiModelProperty(value = "更新时间", position = 30)
    private Date updateTime;

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
     * 获取分组名称
     *
     */
    public String getName(){
        return this.name;
    }
	 /**
     * 设置分组名称
     *
     * @param name
     */
    public void setName(String name){
        this.name = name;
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
     * 获取用户ID
     *
     */
    public Long getUserId(){
        return this.userId;
    }
	 /**
     * 设置用户ID
     *
     * @param userId
     */
    public void setUserId(Long userId){
        this.userId = userId;
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

}