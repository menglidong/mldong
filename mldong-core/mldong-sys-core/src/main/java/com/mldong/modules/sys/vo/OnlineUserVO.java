package com.mldong.modules.sys.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 在线用户
 * @author mldong
 * @date 2024/2/5
 */
@Data
@ApiModel
public class OnlineUserVO {
    @ApiModelProperty(value = "用户ID")
    private Long id;
    @ApiModelProperty(value = "用户名")
    private String userName;
    @ApiModelProperty(value = "用户姓名")
    private String realName;
    @ApiModelProperty(value = "是否当前用户")
    private Boolean isCurrentUser;
    @ApiModelProperty(value = "token值")
    private String tokenValue;
    @ApiModelProperty(value = "登录时间")
    private Date loginTime;
    @ApiModelProperty(value = "登录时间戳")
    private Long loginTimestamp;
    @ApiModelProperty(value = "登录ip")
    private String loginIp;
    @ApiModelProperty(value = "登录浏览器")
    private String loginBrowser;
    @ApiModelProperty(value = "是否超级管理员")
    private boolean superAdmin;
    @ApiModelProperty(value = "会话失效时长")
    private Long timeout;
    @ApiModelProperty(value = "会话失效时间")
    private Date expireTime;
    @ApiModelProperty(value = "登录设备")
    private String device;
    @ApiModelProperty(value = "token值列表")
    private List<OnlineUserVO> tokenList;
}
