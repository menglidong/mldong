package com.mldong.security.smscode;

import com.mldong.common.base.constant.GlobalErrEnum;
import com.mldong.common.exception.BizException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Objects;

public class PhoneAndVerificationCodeAuthenticationProvider implements AuthenticationProvider {
    /**
     * UserDetailsService
     */
    private final UserDetailsService userDetailsService;

    /**
     * redis服务
     */
    public final RedisTemplate<String, Object> redisTemplate;

    public PhoneAndVerificationCodeAuthenticationProvider(UserDetailsService mallUserDetailsService, RedisTemplate<String, Object> redisTemplate) {
        this.userDetailsService = mallUserDetailsService;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        PhoneAndVerificationCodeAuthenticationToken phoneAndVerificationCodeAuthenticationToken = (PhoneAndVerificationCodeAuthenticationToken) authentication;
        Object verificationCodeObj;
        String smsCode = Objects.nonNull(verificationCodeObj = phoneAndVerificationCodeAuthenticationToken.getCredentials()) ?
                verificationCodeObj.toString() : StringUtils.EMPTY;
        // TODO 验证授权码
        // 验证用户
        Object phoneNumberObj;
        String mobilePhone = Objects.nonNull(phoneNumberObj = phoneAndVerificationCodeAuthenticationToken.getPrincipal())
                ? phoneNumberObj.toString() : StringUtils.EMPTY;
        if (StringUtils.isBlank(mobilePhone)) {
            throw new InternalAuthenticationServiceException("手机号不能为空");
        }
        if(!"1234".equals(smsCode)) {
            throw new BizException(GlobalErrEnum.GL99990100);
        }
        // 根据电话号码获取用户
        UserDetails userDetails = userDetailsService.loadUserByUsername(mobilePhone);
        if (Objects.isNull(userDetails)) {
            throw new InternalAuthenticationServiceException(
                    "用户不存在");
        }
        // 封装需要认证的PhoneAndVerificationCodeAuthenticationToken对象
        return new PhoneAndVerificationCodeAuthenticationToken(userDetails.getAuthorities(), mobilePhone, smsCode);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return PhoneAndVerificationCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
