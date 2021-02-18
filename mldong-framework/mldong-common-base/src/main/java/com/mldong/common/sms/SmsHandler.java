package com.mldong.common.sms;

/**
 * 验证码处理器接口，由业务或者通用模块实现
 */
public interface SmsHandler {
    /**
     * 发送验证码
     * @param mobilePhone
     * @param smsCode
     */
    public void sendOut(String mobilePhone,String smsCode);
}
