package com.mldong.modules.sys.dto;

import com.mldong.common.annotation.PasswordValidator;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;

public class SysUpdatePwdParam {
    @ApiModelProperty("用户ID,前端不用填")
    private Long userId;
    @ApiModelProperty(value = "旧密码", required = true)
    @NotEmpty(message = "旧密码不能为空")
    private String password;
    @ApiModelProperty(value = "新密码", required = true)
    @NotEmpty(message = "新密码不能为空")
    @PasswordValidator
    private String newPassword;
    @ApiModelProperty(value = "确认密码", required = true)
    @NotEmpty(message = "确认密码不能为空")
    @PasswordValidator
    private String confirmPassword;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
