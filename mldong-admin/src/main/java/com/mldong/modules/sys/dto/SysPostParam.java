package com.mldong.modules.sys.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.mldong.common.validator.Groups;
import javax.validation.constraints.*;
import com.mldong.common.base.YesNoEnum;

/**
 * <p>接收请求参数实体</p>
 * <p>Table: sys_post - 岗位</p>
 * @since 2020-10-21 09:08:07
 */
@ApiModel(description="岗位")
public class SysPostParam{

	@ApiModelProperty(value="主键-更新时必填")
	@NotNull(message="主键不能为空",groups={Groups.Update.class})
    private Long id;
    @ApiModelProperty(value = "岗位名称",required=true)
    @NotBlank(message="岗位名称不能为空",groups={Groups.Save.class,Groups.Update.class})
    private String name;
    @ApiModelProperty(value = "岗位编号",required=true)
    @NotBlank(message="岗位编号不能为空",groups={Groups.Save.class,Groups.Update.class})
    private String code;
    @ApiModelProperty(value = "排序",required=false)
    private Double sort;
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
}