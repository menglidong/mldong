package com.mldong.modules.sys.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.mldong.common.validator.Groups;

/**
 * <p>接收请求参数实体</p>
 * <p>Table: sys_dict - 字典</p>
 * @since 2020-06-11 11:49:37
 */
@ApiModel(description="字典")
public class SysDictParam{

	@ApiModelProperty(value="主键-更新时必填")
	@NotNull(message="主键不能为空",groups={Groups.Update.class})
    private Long id;
    @ApiModelProperty(value = "名称",required=true)
    @NotBlank(message="名称不能为空",groups={Groups.Save.class,Groups.Update.class})
    private String name;
    @ApiModelProperty(value = "唯一编码",required=true)
    @NotBlank(message="唯一编码不能为空",groups={Groups.Save.class,Groups.Update.class})
    private String dictKey;
    @ApiModelProperty(value = "备注",required=false)
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
     * 获取唯一编码
     *
     */
    public String getDictKey(){
        return this.dictKey;
    }
	 /**
     * 设置唯一编码
     *
     * @param dictKey
     */
    public void setDictKey(String dictKey){
        this.dictKey = dictKey;
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
}