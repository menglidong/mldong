package com.mldong.common.annotation;

import com.mldong.common.validator.UserNameValidatorClass;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER,ElementType.FIELD})
@Constraint(validatedBy = UserNameValidatorClass.class)
public @interface UserNameValidator {
    // 是否必填
    boolean required() default true;
    // 提示内容
    String message() default "用户名字母开头,5-16位长度的字母、数字和下划线组合";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
