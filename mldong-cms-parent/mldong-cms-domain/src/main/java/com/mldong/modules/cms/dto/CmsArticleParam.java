package com.mldong.modules.cms.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.mldong.common.validator.Groups;
import javax.validation.constraints.*;
import java.util.Date;
import com.mldong.common.base.YesNoEnum;
/**
 * <p>接收请求参数实体</p>
 * <p>Table: cms_article - 文章</p>
 * @since 2020-12-22 05:56:37
 */
@ApiModel(description="文章")
public class CmsArticleParam{

	@ApiModelProperty(value="主键-更新时必填", position = 1)
	@NotNull(message="主键不能为空",groups={Groups.Update.class})
    private Long id;
    @ApiModelProperty(value = "栏目id",required=false, position = 10)
    private Long categoryId;
    @ApiModelProperty(value = "标题",required=true, position = 15)
    @NotBlank(message="标题不能为空",groups={Groups.Save.class,Groups.Update.class})
    private String title;
    @ApiModelProperty(value = "描述",required=false, position = 20)
    private String description;
    @ApiModelProperty(value = "大图",required=false, position = 25)
    private String cover;
    @ApiModelProperty(value = "作者",required=false, position = 30)
    private String author;
    @ApiModelProperty(value = "文章来源",required=false, position = 35)
    private String source;
    @ApiModelProperty(value = "排序",required=false, position = 40)
    private Double sort;
    @ApiModelProperty(value = "发布时间",required=false, position = 45)
    private Date publishTime;
    @ApiModelProperty(value = "是否发布(1->否|NO,2->是|YES)",required=false, position = 50)
    private YesNoEnum isPublish;
    @ApiModelProperty(value = "文本内容",required=false, position = 55)
    private String content;
    @ApiModelProperty(value = "所属部门",required=false, position = 60)
    private Long deptId;
    @ApiModelProperty(value = "所属用户",required=false, position = 65)
    private Long userId;

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
}