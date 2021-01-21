package com.mldong.modules.sys.dto;

import com.mldong.common.validator.Groups;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>接收请求参数实体</p>
 * <p>Table: sys_dict_item - 字典项</p>
 * @since 2020-06-11 11:49:37
 */
@ApiModel(description="字典项")
public class SysDictItemParam{

	@ApiModelProperty(value="主键-更新时必填")
	@NotNull(message="主键不能为空",groups={Groups.Update.class})
    private Long id;
    @ApiModelProperty(value = "字典id",required=true)
    @NotNull(message="字典id不能为空",groups={Groups.Save.class,Groups.Update.class})
    private Long dictId;
    @ApiModelProperty(value = "名称",required=true)
    @NotBlank(message="名称不能为空",groups={Groups.Save.class,Groups.Update.class})
    private String name;
    @ApiModelProperty(value = "值",required=true)
    private Integer dictItemValue;
    @ApiModelProperty(value = "排序",required=false)
    private Double sort;
    @ApiModelProperty(value = "",required=false)
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
     * 获取字典id
     *
     */
    public Long getDictId(){
        return this.dictId;
    }
	 /**
     * 设置字典id
     *
     * @param dictId
     */
    public void setDictId(Long dictId){
        this.dictId = dictId;
    }
    /**
     * 获取名称
     *
     */
    public String getName(){
        return this.name;
    }
	 /**
     * 设置名称
     *
     * @param name
     */
    public void setName(String name){
        this.name = name;
    }
    /**
     * 获取值
     *
     */
    public Integer getDictItemValue(){
        return this.dictItemValue;
    }
	 /**
     * 设置值
     *
     * @param dictItemValue
     */
    public void setDictItemValue(Integer dictItemValue){
        this.dictItemValue = dictItemValue;
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
     * 获取
     *
     */
    public String getRemark(){
        return this.remark;
    }
	 /**
     * 设置
     *
     * @param remark
     */
    public void setRemark(String remark){
        this.remark = remark;
    }
}