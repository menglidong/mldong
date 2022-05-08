package com.mldong.modules.snakerflow.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>接收请求参数实体</p>
 * @since 2022-05-08 09:12:53
 */
@ApiModel(description="模型设计定义")
public class WfModelDesignerDefineParam {


    @ApiModelProperty(value = "模型设计ID",required=true, position = 10)
    @NotNull(message = "模型设计ID不能为空")
    private Long modelDesignerId;
    @ApiModelProperty(value = "模型定义",required=false, position = 15)
    @NotBlank(message = "模型定义不能为空")
    private String content;


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
    public String getContent(){
        return this.content;
    }
	 /**
     * 设置模型定义
     *
     * @param content
     */
    public void setContent(String content){
        this.content = content;
    }
}