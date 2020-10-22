package com.mldong.modules.cms.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.mldong.common.validator.Groups;
import javax.validation.constraints.*;
import com.mldong.common.base.YesNoEnum;

/**
 * <p>接收请求参数实体</p>
 * <p>Table: cms_category - 栏目</p>
 * @since 2020-10-22 10:47:45
 */
@ApiModel(description="栏目")
public class CmsCategoryParam{

	@ApiModelProperty(value="主键-更新时必填")
	@NotNull(message="主键不能为空",groups={Groups.Update.class})
    private Long id;
    @ApiModelProperty(value = "父栏目id",required=false)
    private Long parentId;
    @ApiModelProperty(value = "栏目名称",required=true)
    @NotBlank(message="栏目名称不能为空",groups={Groups.Save.class,Groups.Update.class})
    private String name;
    @ApiModelProperty(value = "排序",required=false)
    private Double sort;
    @ApiModelProperty(value = "图标",required=false)
    private String icon;
    @ApiModelProperty(value = "是否导航(1->否|NO,2->是|YES)",required=false)
    private YesNoEnum isNav;
    @ApiModelProperty(value = "是否显示(1->否|NO,2->是|YES)",required=false)
    private YesNoEnum isShow;
    @ApiModelProperty(value = "seo关键字",required=false)
    private String seoKeyworks;
    @ApiModelProperty(value = "seo描述",required=false)
    private String seoDescription;
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
}