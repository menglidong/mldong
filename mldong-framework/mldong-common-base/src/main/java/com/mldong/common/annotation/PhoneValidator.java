package com.mldong.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.mldong.common.validator.PhoneValidatorClass;

/**
 * 手机校验注解
 * @author mldong
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER,ElementType.FIELD})
@Constraint(validatedBy = PhoneValidatorClass.class)
public @interface PhoneValidator {
	// 是否必填
    boolean required() default true;
    // 提示内容
    String message() default "手机号不合法";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
