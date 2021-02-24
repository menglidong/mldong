package com.mldong.common.annotation;

import com.mldong.common.validator.PasswordValidatorClass;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER,ElementType.FIELD})
@Constraint(validatedBy = PasswordValidatorClass.class)
public @interface PasswordValidator {
    // 是否必填
    boolean required() default true;
    // 提示内容
    String message() default "密码字母开头,6-16位长度的字母、数字和特殊字符(@#$%^&*_)组合";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
