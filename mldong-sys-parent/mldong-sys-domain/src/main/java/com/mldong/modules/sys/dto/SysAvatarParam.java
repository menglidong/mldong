package com.mldong.modules.sys.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;

public class SysAvatarParam {
    @ApiModelProperty("用户ID,前端不用填")
    private Long userId;
    @ApiModelProperty(value = "头像", required = true)
    @NotEmpty(message = "头像不能为空")
    private String avatar;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
