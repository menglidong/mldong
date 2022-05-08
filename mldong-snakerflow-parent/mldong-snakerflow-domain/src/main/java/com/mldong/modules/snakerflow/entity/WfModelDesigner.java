package com.mldong.modules.snakerflow.entity;

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

/**
 * <p>实体类</p>
 * <p>Table: wf_model_designer - 模型设计</p>
 * @since 2022-05-08 09:18:24
 */
@Table(name="wf_model_designer")
@ApiModel(description="模型设计")
public class WfModelDesigner implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1L;
	@Id
	@ApiModelProperty(value="主键", position = 1)
    private Long id;
    @ApiModelProperty(value = "分组ID", position = 10)
    private Long modelGroupId;
    @ApiModelProperty(value = "模型类型(10->流程|PROCESS,20->表单|FORM)", position = 15)
    private ModelTypeEnum modelType;
    @ApiModelProperty(value = "模型名称", position = 20)
    private String modelName;
    @ApiModelProperty(value = "唯一标识", position = 25)
    private String modelKey;
    @ApiModelProperty(value = "用户ID", position = 30)
    private Long userId;
    @ApiModelProperty(value = "创建时间", position = 35)
    private Date createTime;
    @ApiModelProperty(value = "更新时间", position = 40)
    private Date updateTime;
    @ApiModelProperty(value = "是否删除", position = 45)
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
     * 获取分组ID
     *
     */
    public Long getModelGroupId(){
        return this.modelGroupId;
    }
	 /**
     * 设置分组ID
     *
     * @param modelGroupId
     */
    public void setModelGroupId(Long modelGroupId){
        this.modelGroupId = modelGroupId;
    }
    /**
     * 获取模型类型(10->流程|PROCESS,20->表单|FORM)
     *
     */
    public ModelTypeEnum getModelType(){
        return this.modelType;
    }
	 /**
     * 设置模型类型(10->流程|PROCESS,20->表单|FORM)
     *
     * @param modelType
     */
    public void setModelType(ModelTypeEnum modelType){
        this.modelType = modelType;
    }
    /**
     * 获取模型名称
     *
     */
    public String getModelName(){
        return this.modelName;
    }
	 /**
     * 设置模型名称
     *
     * @param modelName
     */
    public void setModelName(String modelName){
        this.modelName = modelName;
    }
    /**
     * 获取唯一标识
     *
     */
    public String getModelKey(){
        return this.modelKey;
    }
	 /**
     * 设置唯一标识
     *
     * @param modelKey
     */
    public void setModelKey(String modelKey){
        this.modelKey = modelKey;
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
    /**
     * 获取是否删除
     *
     */
    public YesNoEnum getIsDeleted(){
        return this.isDeleted;
    }
	 /**
     * 设置是否删除
     *
     * @param isDeleted
     */
    public void setIsDeleted(YesNoEnum isDeleted){
        this.isDeleted = isDeleted;
    }

    @DictEnum(key="wf_model_designer_model_type",name="模型类型")
    public enum ModelTypeEnum implements CodedEnum {
		/**
		 * 流程
		 */
		PROCESS(10, "流程"),
		/**
		 * 表单
		 */
		FORM(20, "表单");

		private int value;
		private String name;
		@JsonCreator
	    public static ModelTypeEnum forValue(int value) {
	        return CodedEnum.codeOf(ModelTypeEnum.class, value).get();

	    }
		ModelTypeEnum(int value, String name) {
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