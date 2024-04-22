package com.mldong.annotation;

import com.mldong.consts.CommonConstant;

import java.lang.annotation.*;

/**
 * @author mldong
 * @date 2024/2/28
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface CaptchaValid {
    String uuidKey() default CommonConstant.CAPTCHA_UUID_KEY;
    String codeKey() default CommonConstant.CAPTCHA_CODE_KEY;
}
