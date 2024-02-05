package com.mldong.auth;

import cn.hutool.core.lang.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mldong
 * @date 2023/9/20
 */
@Data
@ApiModel(value = "LoginUser对象", description = "登录用户")
public class LoginUser {
    @ApiModelProperty(value = "用户ID")
    private Long id;
    @ApiModelProperty(value = "用户名")
    private String userName;
    @ApiModelProperty(value = "姓名")
    private String realName;
    @ApiModelProperty(value = "昵称")
    private String nickName;
    @ApiModelProperty(value = "管理员类型")
    private Integer adminType;
    @ApiModelProperty(value = "角色ID集合")
    private List<Long> roleIds;
    @ApiModelProperty(value = "角色名称集合")
    private List<String> roleNames = new ArrayList<>();
    @ApiModelProperty(value = "角色标识集合")
    private List<String> roleCodes = new ArrayList<>();
    @ApiModelProperty(value = "部门ID")
    private Long deptId;
    @ApiModelProperty(value = "部门名称")
    private String deptName;
    @ApiModelProperty(value = "岗位ID")
    private Long postId;
    @ApiModelProperty(value = "岗位名称")
    private String postName;
    @ApiModelProperty(value = "岗位名称")
    private Dict ext = Dict.create();
    private String appCode;
    @ApiModelProperty(value = "是否超级管理员")
    private boolean superAdmin;
    @ApiModelProperty(value = "登录时间戳")
    private Long loginTimestamp;
    @ApiModelProperty(value = "登录ip")
    private String loginIp;
    @ApiModelProperty(value = "登录浏览器")
    private String loginBrowser;
}
