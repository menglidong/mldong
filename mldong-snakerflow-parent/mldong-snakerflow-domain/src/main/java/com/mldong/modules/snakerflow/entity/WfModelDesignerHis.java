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
 * <p>Table: wf_model_designer_his - 模型设计历史</p>
 * @since 2022-05-08 09:18:24
 */
@Table(name="wf_model_designer_his")
@ApiModel(description="模型设计历史")
public class WfModelDesignerHis implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1L;
	@Id
	@ApiModelProperty(value="主键", position = 1)
    private Long id;
    @ApiModelProperty(value = "模型设计ID", position = 10)
    private Long modelDesignerId;
    @ApiModelProperty(value = "模型定义", position = 15)
    private byte[] content;
    @ApiModelProperty(value = "创建时间", position = 20)
    private Date createTime;
    @ApiModelProperty(value = "更新时间", position = 25)
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
     * 获取模型设计ID
     *
     */
    public Long getModelDesignerId(){
        return this.modelDesignerId;
    }
	 /**
     * 设置模型设计ID
     *
     * @param modelDesignerId
     */
    public void setModelDesignerId(Long modelDesignerId){
        this.modelDesignerId = modelDesignerId;
    }
    /**
     * 获取模型定义
     *
     */
    public byte[] getContent(){
        return this.content;
    }
	 /**
     * 设置模型定义
     *
     * @param content
     */
    public void setContent(byte[] content){
        this.content = content;
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