package com.mldong.common.validator;

import com.mldong.common.annotation.UserNameValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class UserNameValidatorClass implements ConstraintValidator<UserNameValidator,String> {
    // 用户名字母开头,5-16位长度的字母、数字和下划线组合
    private Pattern pattern = Pattern.compile("^[a-zA-Z]{1}[a-zA-Z0-9_]{4,15}$");
    private boolean required;
    @Override
    public void initialize(UserNameValidator validator) {
        // 注解内配的值赋值给变量
        this.required = validator.required();
    }
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
    	if(this.required) {
     	   if(null == value || "".equals(value)) {
     		   return false;
     	   }
        }
        return pattern.matcher(value).matches();
    }
}
