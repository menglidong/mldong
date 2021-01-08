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
 * <p>Table: sys_menu - 菜单</p>
 * @since 2021-01-08 05:33:28
 */
@Table(name="sys_menu")
@ApiModel(description="菜单")
public class SysMenu implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1L;
	@Id
	@ApiModelProperty(value="主键", position = 1)
    private Long id;
    @ApiModelProperty(value = "父菜单id", position = 10)
    private Long parentId;
    @ApiModelProperty(value = "菜单名称", position = 15)
    private String name;
    @ApiModelProperty(value = "排序", position = 20)
    private Double sort;
    @ApiModelProperty(value = "路由名称", position = 25)
    private String routeName;
    @ApiModelProperty(value = "路由地址", position = 30)
    private String path;
    @ApiModelProperty(value = "图标", position = 35)
    private String icon;
    @ApiModelProperty(value = "是否显示(1->不显示|NO,2->显示|YES)", position = 40)
    private YesNoEnum isShow;
    @ApiModelProperty(value = "是否缓存(1->不缓存|NO,2->缓存|YES)", position = 45)
    private YesNoEnum isCache;
    @ApiModelProperty(value = "备注", position = 50)
    private String remark;
    @ApiModelProperty(value = "创建时间", position = 55)
    private Date createTime;
    @ApiModelProperty(value = "更新时间", position = 60)
    private Date updateTime;
    @ApiModelProperty(value = "是否删除(1->未删除|NO,2->已删除|YES)", position = 65)
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
     * 获取父菜单id
     *
     */
    public Long getParentId(){
        return this.parentId;
    }
	 /**
     * 设置父菜单id
     *
     * @param parentId
     */
    public void setParentId(Long parentId){
        this.parentId = parentId;
    }
    /**
     * 获取菜单名称
     *
     */
    public String getName(){
        return this.name;
    }
	 /**
     * 设置菜单名称
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
     * 获取路由名称
     *
     */
    public String getRouteName(){
        return this.routeName;
    }
	 /**
     * 设置路由名称
     *
     * @param routeName
     */
    public void setRouteName(String routeName){
        this.routeName = routeName;
    }
    /**
     * 获取路由地址
     *
     */
    public String getPath(){
        return this.path;
    }
	 /**
     * 设置路由地址
     *
     * @param path
     */
    public void setPath(String path){
        this.path = path;
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
     * 获取是否显示(1->不显示|NO,2->显示|YES)
     *
     */
    public YesNoEnum getIsShow(){
        return this.isShow;
    }
	 /**
     * 设置是否显示(1->不显示|NO,2->显示|YES)
     *
     * @param isShow
     */
    public void setIsShow(YesNoEnum isShow){
        this.isShow = isShow;
    }
    /**
     * 获取是否缓存(1->不缓存|NO,2->缓存|YES)
     *
     */
    public YesNoEnum getIsCache(){
        return this.isCache;
    }
	 /**
     * 设置是否缓存(1->不缓存|NO,2->缓存|YES)
     *
     * @param isCache
     */
    public void setIsCache(YesNoEnum isCache){
        this.isCache = isCache;
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