package com.mldong.modules.sys.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.mldong.base.YesNoEnum;
import com.mldong.modules.sys.enums.MenuOpenTypeEnum;
import com.mldong.modules.sys.enums.MenuTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "SyncRouteParam对象", description = "同步路由实体")
public class SyncRouteParam {
    @ApiModelProperty(value = "应用编码",position = 5)
    private String appCode;

    @ApiModelProperty(value = "是否同步",position = 85)
    private Integer isSync;

    @ApiModelProperty(value = "父ID集合",position = 20)
    private String pids;

    @ApiModelProperty(value = "菜单名称",position = 10)
    private String name;

    @ApiModelProperty(value = "唯一编码",position = 15)
    private String code;

    @ApiModelProperty(value = "菜单类型<sys_menu_type>",position = 25)
    private Integer type;

    @ApiModelProperty(value = "排序",position = 30)
    @TableField(fill = FieldFill.INSERT)
    private Long sort;

    @ApiModelProperty(value = "路由地址",position = 35)
    private String path;

    @ApiModelProperty(value = "组件地址",position = 40)
    private String component;

    @ApiModelProperty(value = "菜单图标",position = 45)
    private String icon;

    @ApiModelProperty(value = "是否显示",position = 50)
    private Integer isShow;

    @ApiModelProperty(value = "是否链接",position = 55)
    private Integer isLink;

    @ApiModelProperty(value = "外部链接地址",position = 60)
    private String url;

    @ApiModelProperty(value = "启用是否",position = 65)
    private Integer enabled;

    @ApiModelProperty(value = "打开方式<sys_menu_open_type>",position = 70)
    private Integer openType;

    @ApiModelProperty(value = "是否缓存",position = 75)
    private Integer isCache;

    @ApiModelProperty(value = "额外参数JSON",position = 80)
    private String variable;

    @ApiModelProperty(value = "子菜单集合", position = 45)
    private List<SyncRouteParam> children;
}
