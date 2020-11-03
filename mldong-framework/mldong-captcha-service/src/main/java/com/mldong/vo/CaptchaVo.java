package com.mldong.vo;

import io.swagger.annotations.ApiModelProperty;

public class CaptchaVo {
    @ApiModelProperty(value = "当前验证码唯一编码,请求时将captchaCode={{uuid}}放在请求头或者url上")
    private String uuid;
    @ApiModelProperty(value = "当前图片验证码")
    private String img;

    public CaptchaVo(String uuid, String img) {
        this.uuid = uuid;
        this.img = img;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
