package com.mldong.modules.cms.entity;

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
 * <p>Table: cms_category - 栏目</p>
 * @since 2020-10-22 11:30:30
 */
@Table(name="cms_category")
@ApiModel(description="栏目")
public class CmsCategory implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1L;
	@Id
	@ApiModelProperty(value="主键")
    private Long id;
    @ApiModelProperty(value = "父栏目id")
    private Long parentId;
    @ApiModelProperty(value = "栏目名称")
    private String name;
    @ApiModelProperty(value = "排序")
    private Double sort;
    @ApiModelProperty(value = "图标")
    private String icon;
    @ApiModelProperty(value = "是否导航(1->否|NO,2->是|YES)")
    private YesNoEnum isNav;
    @ApiModelProperty(value = "是否显示(1->否|NO,2->是|YES)")
    private YesNoEnum isShow;
    @ApiModelProperty(value = "seo关键字")
    private String seoKeyworks;
    @ApiModelProperty(value = "seo描述")
    private String seoDescription;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
    @ApiModelProperty(value = "是否删除(1->未删除|NO,2->已删除|YES)")
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
     * 获取父栏目id
     *
     */
    public Long getParentId(){
        return this.parentId;
    }
	 /**
     * 设置父栏目id
     *
     * @param parentId
     */
    public void setParentId(Long parentId){
        this.parentId = parentId;
    }
    /**
     * 获取栏目名称
     *
     */
    public String getName(){
        return this.name;
    }
	 /**
     * 设置栏目名称
     *
     * @param name
     */
    public void setName(String name){
        this.name = name;
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
     * 获取图标
     *
     */
    public String getIcon(){
        return this.icon;
    }
	 /**
     * 设置图标
     *
     * @param icon
     */
    public void setIcon(String icon){
        this.icon = icon;
    }
    /**
     * 获取是否导航(1->否|NO,2->是|YES)
     *
     */
    public YesNoEnum getIsNav(){
        return this.isNav;
    }
	 /**
     * 设置是否导航(1->否|NO,2->是|YES)
     *
     * @param isNav
     */
    public void setIsNav(YesNoEnum isNav){
        this.isNav = isNav;
    }
    /**
     * 获取是否显示(1->否|NO,2->是|YES)
     *
     */
    public YesNoEnum getIsShow(){
        return this.isShow;
    }
	 /**
     * 设置是否显示(1->否|NO,2->是|YES)
     *
     * @param isShow
     */
    public void setIsShow(YesNoEnum isShow){
        this.isShow = isShow;
    }
    /**
     * 获取seo关键字
     *
     */
    public String getSeoKeyworks(){
        return this.seoKeyworks;
    }
	 /**
     * 设置seo关键字
     *
     * @param seoKeyworks
     */
    public void setSeoKeyworks(String seoKeyworks){
        this.seoKeyworks = seoKeyworks;
    }
    /**
     * 获取seo描述
     *
     */
    public String getSeoDescription(){
        return this.seoDescription;
    }
	 /**
     * 设置seo描述
     *
     * @param seoDescription
     */
    public void setSeoDescription(String seoDescription){
        this.seoDescription = seoDescription;
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