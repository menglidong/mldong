package com.mldong.modules.snakerflow.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.mldong.common.validator.Groups;
import javax.validation.constraints.*;
import com.mldong.modules.snakerflow.entity.WfModelDesigner;
/**
 * <p>接收请求参数实体</p>
 * <p>Table: wf_model_designer - 模型设计</p>
 * @since 2022-05-08 09:12:53
 */
@ApiModel(description="模型设计")
public class WfModelDesignerParam{

	@ApiModelProperty(value="主键-更新时必填", position = 1)
	@NotNull(message="主键不能为空",groups={Groups.Update.class})
    private Long id;
    @ApiModelProperty(value = "分组ID",required=true, position = 10)
    private Long modelGroupId;
    @ApiModelProperty(value = "模型类型(10->流程|PROCESS,20->表单|FORM)",required=true, position = 15)
    private WfModelDesigner.ModelTypeEnum modelType;
    @ApiModelProperty(value = "模型名称",required=true, position = 20)
    @NotBlank(message="模型名称不能为空",groups={Groups.Save.class,Groups.Update.class})
    private String modelName;
    @ApiModelProperty(value = "唯一标识",required=true, position = 25)
    @NotBlank(message="唯一标识不能为空",groups={Groups.Save.class,Groups.Update.class})
    private String modelKey;
    @ApiModelProperty(value = "用户ID",required=true, position = 30)
    private Long userId;

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
    public WfModelDesigner.ModelTypeEnum getModelType(){
        return this.modelType;
    }
	 /**
     * 设置模型类型(10->流程|PROCESS,20->表单|FORM)
     *
     * @param modelType
     */
    public void setModelType(WfModelDesigner.ModelTypeEnum modelType){
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
}