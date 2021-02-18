package com.mldong.common.base;

import com.mldong.common.annotation.PhoneValidator;
import io.swagger.annotations.ApiModelProperty;

public class PhoneParam {
    @ApiModelProperty(value = "手机号码", required = true)
    @PhoneValidator
    private String mobilePhone;

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }
}
