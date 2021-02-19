package com.mldong.common.annotation;

import java.lang.annotation.*;

/**
 * 校验验证码
 */
@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface VerifyCaptcha {
}
