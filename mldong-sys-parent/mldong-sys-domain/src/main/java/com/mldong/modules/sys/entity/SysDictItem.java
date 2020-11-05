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
 * <p>Table: sys_dict_item - 字典项</p>
 * @since 2020-11-05 10:15:38
 */
@Table(name="sys_dict_item")
@ApiModel(description="字典项")
public class SysDictItem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1L;
	@Id
	@ApiModelProperty(value="主键")
    private Long id;
    @ApiModelProperty(value = "字典id")
    private Long dictId;
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "值")
    private Integer dictItemValue;
    @ApiModelProperty(value = "排序")
    private Double sort;
    @ApiModelProperty(value = "")
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