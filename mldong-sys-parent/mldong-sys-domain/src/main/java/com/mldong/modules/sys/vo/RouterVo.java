package com.mldong.modules.sys.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 前端路由对象
 */
public class RouterVo implements Serializable {
    @ApiModelProperty(value = "当设置 true 的时候该路由不会在侧边栏出现 如401，login等页面，或者如一些编辑页面/edit/1")
    private boolean hidden;
    @ApiModelProperty(value = "当设置 noRedirect 的时候该路由在面包屑导航中不可被点击")
    private String redirect;
    // 当你一个路由下面的 children 声明的路由大于1个时，自动会变成嵌套的模式--如组件页面
    // 只有一个时，会将那个子路由当做根路由显示在侧边栏--如引导页面
    // 若你想不管路由下面的 children 声明的个数都显示你的根路由
    // 你可以设置 alwaysShow: true，这样它就会忽略之前定义的规则，一直显示根路由
    @ApiModelProperty(value = "")
    private boolean alwayShow;
    @ApiModelProperty(value = "设定路由的名字，一定要填写不然使用<keep-alive>时会出现各种问题")
    private String name;
    @ApiModelProperty(value = "访问地址")
    private String path;
    @ApiModelProperty(value = "组件")
    private String component;
    @ApiModelProperty(value = "其他元素")
    private MetaVo meta;
    @ApiModelProperty(value = "子路由")
    private List<RouterVo> children;

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public boolean isAlwayShow() {
        return alwayShow;
    }

    public void setAlwayShow(boolean alwayShow) {
        this.alwayShow = alwayShow;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public MetaVo getMeta() {
        return meta;
    }

    public void setMeta(MetaVo meta) {
        this.meta = meta;
    }

    public List<RouterVo> getChildren() {
        return children;
    }

    public void setChildren(List<RouterVo> children) {
        this.children = children;
    }
}
