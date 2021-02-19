package com.mldong.dto;

import com.mldong.common.annotation.PhoneValidator;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SmsVerifyParam {
    @ApiModelProperty(value = "手机号", required = true)
    @PhoneValidator
    private String mobilePhone;
    @ApiModelProperty(value = "短信验证码", required = true)
    @NotNull(message = "短信验证码不能为空")
    @NotBlank(message = "短信验证码不能为空")
    private String smsCode;

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }
}
