package com.mldong.security.smscode;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class PhoneAndVerificationCodeAuthenticationToken extends AbstractAuthenticationToken {
    /**
     * 手机号
     */
    private final Object mobilePhone;

    /**
     * 验证码
     */
    private final Object smsCode;

    public PhoneAndVerificationCodeAuthenticationToken(Object mobilePhone, Object smsCode) {
        super(null);
        this.mobilePhone = mobilePhone;
        this.smsCode = smsCode;
    }

    public PhoneAndVerificationCodeAuthenticationToken(Collection<? extends GrantedAuthority> authorities, Object mobilePhone, Object smsCode) {
        super(authorities);
        this.mobilePhone = mobilePhone;
        this.smsCode = smsCode;
        // 认证已经通过
        setAuthenticated(true);
    }

    /**
     * 用户身份凭证（一般是密码或者验证码）
     */
    @Override
    public Object getCredentials() {
        return smsCode;
    }

    /**
     * 身份标识（一般是姓名，手机号）
     */
    @Override
    public Object getPrincipal() {
        return mobilePhone;
    }

}
