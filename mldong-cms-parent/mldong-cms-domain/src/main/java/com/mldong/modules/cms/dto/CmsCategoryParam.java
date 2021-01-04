package com.mldong.modules.cms.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.mldong.common.validator.Groups;
import javax.validation.constraints.*;
import com.mldong.common.base.YesNoEnum;
/**
 * <p>接收请求参数实体</p>
 * <p>Table: cms_category - 栏目</p>
 * @since 2021-01-04 10:06:29
 */
@ApiModel(description="栏目")
public class CmsCategoryParam{

	@ApiModelProperty(value="主键-更新时必填", position = 1)
	@NotNull(message="主键不能为空",groups={Groups.Update.class})
    private Long id;
    @ApiModelProperty(value = "父栏目id",required=false, position = 10)
    private Long parentId;
    @ApiModelProperty(value = "栏目名称",required=true, position = 15)
    @NotBlank(message="栏目名称不能为空",groups={Groups.Save.class,Groups.Update.class})
    private String name;
    @ApiModelProperty(value = "排序",required=false, position = 20)
    private Double sort;
    @ApiModelProperty(value = "图标",required=false, position = 25)
    private String icon;
    @ApiModelProperty(value = "是否导航(1->否|NO,2->是|YES)",required=false, position = 30)
    private YesNoEnum isNav;
    @ApiModelProperty(value = "是否显示(1->否|NO,2->是|YES)",required=false, position = 35)
    private YesNoEnum isShow;
    @ApiModelProperty(value = "是否单页面(1->否|NO,2->是|YES)",required=false, position = 40)
    private YesNoEnum isPage;
    @ApiModelProperty(value = "所属部门",required=false, position = 45)
    private Long deptId;
    @ApiModelProperty(value = "所属用户",required=false, position = 50)
    private Long userId;
    @ApiModelProperty(value = "所属模型",required=false, position = 55)
    private Long modelId;
    @ApiModelProperty(value = "seo关键字",required=false, position = 60)
    private String seoKeyworks;
    @ApiModelProperty(value = "seo描述",required=false, position = 65)
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
     * 获取是否单页面(1->否|NO,2->是|YES)
     *
     */
    public YesNoEnum getIsPage(){
        return this.isPage;
    }
	 /**
     * 设置是否单页面(1->否|NO,2->是|YES)
     *
     * @param isPage
     */
    public void setIsPage(YesNoEnum isPage){
        this.isPage = isPage;
    }
    /**
     * 获取所属部门
     *
     */
    public Long getDeptId(){
        return this.deptId;
    }
	 /**
     * 设置所属部门
     *
     * @param deptId
     */
    public void setDeptId(Long deptId){
        this.deptId = deptId;
    }
    /**
     * 获取所属用户
     *
     */
    public Long getUserId(){
        return this.userId;
    }
	 /**
     * 设置所属用户
     *
     * @param userId
     */
    public void setUserId(Long userId){
        this.userId = userId;
    }
    /**
     * 获取所属模型
     *
     */
    public Long getModelId(){
        return this.modelId;
    }
	 /**
     * 设置所属模型
     *
     * @param modelId
     */
    public void setModelId(Long modelId){
        this.modelId = modelId;
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