package com.mldong.modules.sys.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Id;
import javax.persistence.Table;
import tk.mybatis.mapper.annotation.LogicDelete;
import com.mldong.common.base.YesNoEnum;
// START###################
// ###################END
/**
 * <p>实体类</p>
 * <p>Table: sys_config - 参数配置</p>
 * @since 2020-11-05 08:22:30
 */
@Table(name="sys_config")
@ApiModel(description="参数配置")
public class SysConfig implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1L;
	@Id
	@ApiModelProperty(value="编号")
    private Long id;
    @ApiModelProperty(value = "参数名称")
    private String configName;
    @ApiModelProperty(value = "参数键名")
    private String configKey;
    @ApiModelProperty(value = "参数键值")
    private String configValue;
    @ApiModelProperty(value = "系统内置(1->否|NO,2->是|YES)")
    private YesNoEnum isSystem;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
    @ApiModelProperty(value = "是否删除(1->未删除|NO,2->已删除|YES)")
	@LogicDelete(isDeletedValue=YesNoEnum.Y,notDeletedValue=YesNoEnum.N)
    private YesNoEnum isDeleted;
// START###################
// ###################END
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
     * 获取是否删除(1->未删除|NO,2->已删除|YES)
     *
     */
    public YesNoEnum getIsDeleted(){
        return this.isDeleted;
    }
	 /**
     * 设置是否删除(1->未删除|NO,2->已删除|YES)
     *
     * @param isDeleted
     */
    public void setIsDeleted(YesNoEnum isDeleted){
        this.isDeleted = isDeleted;
    }
// START###################
// ###################END
}