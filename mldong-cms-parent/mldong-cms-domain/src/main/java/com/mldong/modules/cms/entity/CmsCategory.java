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
 * <p>Table: cms_category - </p>
 * @since 2022-04-23 05:32:35
 */
@Table(name="cms_category")
@ApiModel(description="")
public class CmsCategory implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1L;
	@Id
	@ApiModelProperty(value="主键", position = 1)
    private Long id;
    @ApiModelProperty(value = "父栏目id", position = 10)
    private Long parentId;
    @ApiModelProperty(value = "栏目名称", position = 15)
    private String name;
    @ApiModelProperty(value = "排序", position = 20)
    private Double sort;
    @ApiModelProperty(value = "图标", position = 25)
    private String icon;
    @ApiModelProperty(value = "是否导航(1->否|NO,2->是|YES)", position = 30)
    private YesNoEnum isNav;
    @ApiModelProperty(value = "是否显示(1->否|NO,2->是|YES)", position = 35)
    private YesNoEnum isShow;
    @ApiModelProperty(value = "是否单页面(1->否|NO,2->是|YES)", position = 40)
    private YesNoEnum isPage;
    @ApiModelProperty(value = "所属部门", position = 45)
    private Long deptId;
    @ApiModelProperty(value = "所属用户", position = 50)
    private Long userId;
    @ApiModelProperty(value = "所属模型", position = 55)
    private Long modelId;
    @ApiModelProperty(value = "seo关键字", position = 60)
    private String seoKeyworks;
    @ApiModelProperty(value = "seo描述", position = 65)
    private String seoDescription;
    @ApiModelProperty(value = "扩展的表单配置", position = 70)
    private String extFormConfig;
    @ApiModelProperty(value = "扩展表单值", position = 75)
    private String extFormValue;
    @ApiModelProperty(value = "文章扩展字段配置", position = 80)
    private String extArticleFormConfig;
    @ApiModelProperty(value = "富文本", position = 85)
    private String content;
    @ApiModelProperty(value = "创建时间", position = 90)
    private Date createTime;
    @ApiModelProperty(value = "更新时间", position = 95)
    private Date updateTime;
    @ApiModelProperty(value = "是否删除(1->未删除|NO,2->已删除|YES)", position = 100)
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
    /**
     * 获取扩展的表单配置
     *
     */
    public String getExtFormConfig(){
        return this.extFormConfig;
    }
	 /**
     * 设置扩展的表单配置
     *
     * @param extFormConfig
     */
    public void setExtFormConfig(String extFormConfig){
        this.extFormConfig = extFormConfig;
    }
    /**
     * 获取扩展表单值
     *
     */
    public String getExtFormValue(){
        return this.extFormValue;
    }
	 /**
     * 设置扩展表单值
     *
     * @param extFormValue
     */
    public void setExtFormValue(String extFormValue){
        this.extFormValue = extFormValue;
    }
    /**
     * 获取文章扩展字段配置
     *
     */
    public String getExtArticleFormConfig(){
        return this.extArticleFormConfig;
    }
	 /**
     * 设置文章扩展字段配置
     *
     * @param extArticleFormConfig
     */
    public void setExtArticleFormConfig(String extArticleFormConfig){
        this.extArticleFormConfig = extArticleFormConfig;
    }
    /**
     * 获取富文本
     *
     */
    public String getContent(){
        return this.content;
    }
	 /**
     * 设置富文本
     *
     * @param content
     */
    public void setContent(String content){
        this.content = content;
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