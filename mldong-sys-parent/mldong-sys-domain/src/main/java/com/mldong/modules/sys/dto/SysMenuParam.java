package com.mldong.modules.sys.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.mldong.common.validator.Groups;
import javax.validation.constraints.*;
import com.mldong.common.base.YesNoEnum;
/**
 * <p>接收请求参数实体</p>
 * <p>Table: sys_menu - 菜单</p>
 * @since 2021-01-08 05:33:28
 */
@ApiModel(description="菜单")
public class SysMenuParam{

	@ApiModelProperty(value="主键-更新时必填", position = 1)
	@NotNull(message="主键不能为空",groups={Groups.Update.class})
    private Long id;
    @ApiModelProperty(value = "父菜单id",required=false, position = 10)
    private Long parentId;
    @ApiModelProperty(value = "菜单名称",required=true, position = 15)
    @NotBlank(message="菜单名称不能为空",groups={Groups.Save.class,Groups.Update.class})
    private String name;
    @ApiModelProperty(value = "排序",required=false, position = 20)
    private Double sort;
    @ApiModelProperty(value = "路由名称",required=false, position = 25)
    private String routeName;
    @ApiModelProperty(value = "路由地址",required=false, position = 30)
    private String path;
    @ApiModelProperty(value = "图标",required=false, position = 35)
    private String icon;
    @ApiModelProperty(value = "是否显示(1->不显示|NO,2->显示|YES)",required=false, position = 40)
    private YesNoEnum isShow;
    @ApiModelProperty(value = "是否缓存(1->不缓存|NO,2->缓存|YES)",required=false, position = 45)
    private YesNoEnum isCache;
    @ApiModelProperty(value = "备注",required=false, position = 50)
    private String remark;

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
}