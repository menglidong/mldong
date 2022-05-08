package com.mldong.modules.snakerflow.dto;

import com.mldong.common.validator.Groups;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
/**
 * <p>接收请求参数实体</p>
 * <p>Table: wf_model_group - 模型分组</p>
 * @since 2022-05-08 09:12:53
 */
@ApiModel(description="模型分组")
public class WfModelGroupParam{

	@ApiModelProperty(value="主键-更新时必填", position = 1)
	@NotNull(message="主键不能为空",groups={Groups.Update.class})
    private Long id;
    @ApiModelProperty(value = "分组名称",required=true, position = 10)
    @NotBlank(message="分组名称不能为空",groups={Groups.Save.class,Groups.Update.class})
    private String name;
    @ApiModelProperty(value = "备注",required=false, position = 15)
    private String remark;
    @ApiModelProperty(value = "用户ID",required=true, position = 20)
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
}