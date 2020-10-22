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
 * <p>Table: cms_article - 文章</p>
 * @since 2020-10-22 11:30:29
 */
@Table(name="cms_article")
@ApiModel(description="文章")
public class CmsArticle implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1L;
	@Id
	@ApiModelProperty(value="主键")
    private Long id;
    @ApiModelProperty(value = "栏目id")
    private Long categoryId;
    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "描述")
    private String description;
    @ApiModelProperty(value = "大图")
    private String cover;
    @ApiModelProperty(value = "作者")
    private String author;
    @ApiModelProperty(value = "文章来源")
    private String source;
    @ApiModelProperty(value = "排序")
    private Double sort;
    @ApiModelProperty(value = "发布时间")
    private Date publishTime;
    @ApiModelProperty(value = "是否发布(1->否|NO,2->是|YES)")
    private YesNoEnum isPublish;
    @ApiModelProperty(value = "文本内容")
    private String content;
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
     * 获取栏目id
     *
     */
    public Long getCategoryId(){
        return this.categoryId;
    }
	 /**
     * 设置栏目id
     *
     * @param categoryId
     */
    public void setCategoryId(Long categoryId){
        this.categoryId = categoryId;
    }
    /**
     * 获取标题
     *
     */
    public String getTitle(){
        return this.title;
    }
	 /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(String title){
        this.title = title;
    }
    /**
     * 获取描述
     *
     */
    public String getDescription(){
        return this.description;
    }
	 /**
     * 设置描述
     *
     * @param description
     */
    public void setDescription(String description){
        this.description = description;
    }
    /**
     * 获取大图
     *
     */
    public String getCover(){
        return this.cover;
    }
	 /**
     * 设置大图
     *
     * @param cover
     */
    public void setCover(String cover){
        this.cover = cover;
    }
    /**
     * 获取作者
     *
     */
    public String getAuthor(){
        return this.author;
    }
	 /**
     * 设置作者
     *
     * @param author
     */
    public void setAuthor(String author){
        this.author = author;
    }
    /**
     * 获取文章来源
     *
     */
    public String getSource(){
        return this.source;
    }
	 /**
     * 设置文章来源
     *
     * @param source
     */
    public void setSource(String source){
        this.source = source;
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
     * 获取发布时间
     *
     */
    public Date getPublishTime(){
        return this.publishTime;
    }
	 /**
     * 设置发布时间
     *
     * @param publishTime
     */
    public void setPublishTime(Date publishTime){
        this.publishTime = publishTime;
    }
    /**
     * 获取是否发布(1->否|NO,2->是|YES)
     *
     */
    public YesNoEnum getIsPublish(){
        return this.isPublish;
    }
	 /**
     * 设置是否发布(1->否|NO,2->是|YES)
     *
     * @param isPublish
     */
    public void setIsPublish(YesNoEnum isPublish){
        this.isPublish = isPublish;
    }
    /**
     * 获取文本内容
     *
     */
    public String getContent(){
        return this.content;
    }
	 /**
     * 设置文本内容
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