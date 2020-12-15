package com.mldong.modules.sys.dto;

import com.mldong.modules.sys.entity.SysUser;
import io.swagger.annotations.ApiModelProperty;

public class SysUpdateProfileParam {
    @ApiModelProperty("用户ID,前端不用填")
    private Long userId;
    @ApiModelProperty(value = "姓名")
    private String realName;
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "手机号")
    private String mobilePhone;
    @ApiModelProperty(value = "电话")
    private String telephone;
    @ApiModelProperty(value = "性别(1->男|MALE,2->女|FEMALE,3->未知|UNKNOWN)")
    private SysUser.SexEnum sex;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public SysUser.SexEnum getSex() {
        return sex;
    }

    public void setSex(SysUser.SexEnum sex) {
        this.sex = sex;
    }
}
