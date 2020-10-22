package com.mldong.modules.sys.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

import com.mldong.common.base.YesNoEnum;
import com.mldong.common.validator.Groups;

/**
 * <p>接收请求参数实体</p>
 * <p>Table: sys_menu - 菜单</p>
 * @since 2020-06-07 09:45:41
 */
@ApiModel(description="菜单")
public class SysMenuParam{

	@ApiModelProperty(value="主键-更新时必填")
	@NotNull(message="主键不能为空",groups={Groups.Update.class})
    private Long id;
    @ApiModelProperty(value = "父菜单id",required=false)
    private Long parentId;
    @ApiModelProperty(value = "菜单名称",required=false)
    private String name;
    @ApiModelProperty(value = "排序",required=false)
    private Double sort;
    @ApiModelProperty(value = "路由名称",required=false)
    private String routeName;
    @ApiModelProperty(value = "图标",required=false)
    private String icon;
    @ApiModelProperty(value = "是否显示(1->不显示|NO,2->显示|YES)",required=false)
    private YesNoEnum isShow;
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
}