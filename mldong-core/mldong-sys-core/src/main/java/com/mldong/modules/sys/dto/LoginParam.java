package com.mldong.modules.sys.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author mldong
 * @date 2022/9/8
 */
@Data
public class LoginParam {
    @ApiModelProperty(value = "用户名")
    private String userName;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "授权类型")
    private String grantType;
}
