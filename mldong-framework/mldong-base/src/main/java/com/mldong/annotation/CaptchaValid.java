package com.mldong.annotation;

import java.lang.annotation.*;

/**
 * @author mldong
 * @date 2024/2/28
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface CaptchaValid {
}
