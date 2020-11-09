package com.mldong.modules.sys.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.mldong.common.validator.Groups;
import javax.validation.constraints.*;
import com.mldong.common.base.YesNoEnum;
/**
 * <p>接收请求参数实体</p>
 * <p>Table: sys_config - 参数配置</p>
 * @since 2020-11-09 04:37:10
 */
@ApiModel(description="参数配置")
public class SysConfigParam{

	@ApiModelProperty(value="编号-更新时必填")
	@NotNull(message="编号不能为空",groups={Groups.Update.class})
    private Long id;
    @ApiModelProperty(value = "参数名称",required=false)
    private String configName;
    @ApiModelProperty(value = "参数键名",required=false)
    private String configKey;
    @ApiModelProperty(value = "参数键值",required=false)
    private String configValue;
    @ApiModelProperty(value = "系统内置(1->否|NO,2->是|YES)",required=false)
    private YesNoEnum isSystem;
    @ApiModelProperty(value = "备注",required=false)
    private String remark;

    /**
     * 获取编号
     *
     */
    public Long getId(){
        return this.id;
    }
	 /**
     * 设置编号
     *
     * @param id
     */
    public void setId(Long id){
        this.id = id;
    }
    /**
     * 获取参数名称
     *
     */
    public String getConfigName(){
        return this.configName;
    }
	 /**
     * 设置参数名称
     *
     * @param configName
     */
    public void setConfigName(String configName){
        this.configName = configName;
    }
    /**
     * 获取参数键名
     *
     */
    public String getConfigKey(){
        return this.configKey;
    }
	 /**
     * 设置参数键名
     *
     * @param configKey
     */
    public void setConfigKey(String configKey){
        this.configKey = configKey;
    }
    /**
     * 获取参数键值
     *
     */
    public String getConfigValue(){
        return this.configValue;
    }
	 /**
     * 设置参数键值
     *
     * @param configValue
     */
    public void setConfigValue(String configValue){
        this.configValue = configValue;
    }
    /**
     * 获取系统内置(1->否|NO,2->是|YES)
     *
     */
    public YesNoEnum getIsSystem(){
        return this.isSystem;
    }
	 /**
     * 设置系统内置(1->否|NO,2->是|YES)
     *
     * @param isSystem
     */
    public void setIsSystem(YesNoEnum isSystem){
        this.isSystem = isSystem;
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