package com.mldong.modules.sys.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 路由meta实体
 */
public class MetaVo implements Serializable {
    @ApiModelProperty(value = "设置该路由在侧边栏和面包屑中展示的名字")
    private String title;
    @ApiModelProperty(value = "设置该路由的图标，支持 svg-class，也支持 el-icon-x element-ui 的 icon")
    private String icon;
    @ApiModelProperty(value = "如果设置为true，则不会被 <keep-alive> 缓存(默认 false)")
    private boolean noCache = true;
    @ApiModelProperty(value = "如果设置为false，则不会在breadcrumb面包屑中显示(默认 true)")
    private boolean breadcrumb;
    @ApiModelProperty(value = "若果设置为true，它则会固定在tags-view中(默认 false)")
    private boolean affix = true;
    // 当路由设置了该属性，则会高亮相对应的侧边栏。
    // 这在某些场景非常有用，比如：一个文章的列表页路由为：/article/list
    // 点击文章进入文章详情页，这时候路由为/article/1，但你想在侧边栏高亮文章列表的路由，就可以进行如下设置
    @ApiModelProperty(value = "文章列表跳到详情页控制侧边栏高亮-待验证")
    private String activeMenu;
    @ApiModelProperty(value = "菜单标签页别名")
    private String tagName;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public boolean isNoCache() {
        return noCache;
    }

    public void setNoCache(boolean noCache) {
        this.noCache = noCache;
    }

    public boolean isBreadcrumb() {
        return breadcrumb;
    }

    public void setBreadcrumb(boolean breadcrumb) {
        this.breadcrumb = breadcrumb;
    }

    public boolean isAffix() {
        return affix;
    }

    public void setAffix(boolean affix) {
        this.affix = affix;
    }

    public String getActiveMenu() {
        return activeMenu;
    }

    public void setActiveMenu(String activeMenu) {
        this.activeMenu = activeMenu;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
