package com.mldong.modules.sys.entity;

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
 * <p>Table: sys_dict - 字典</p>
 * @since 2022-04-23 05:26:04
 */
@Table(name="sys_dict")
@ApiModel(description="字典")
public class SysDict implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1L;
	@Id
	@ApiModelProperty(value="主键", position = 1)
    private Long id;
    @ApiModelProperty(value = "名称", position = 10)
    private String name;
    @ApiModelProperty(value = "唯一编码", position = 15)
    private String dictKey;
    @ApiModelProperty(value = "备注", position = 20)
    private String remark;
    @ApiModelProperty(value = "创建时间", position = 25)
    private Date createTime;
    @ApiModelProperty(value = "更新时间", position = 30)
    private Date updateTime;
    @ApiModelProperty(value = "是否删除(1->未删除|NO,2->已删除|YES)", position = 35)
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

}