package com.mldong.common.validator;

import com.mldong.common.annotation.PasswordValidator;
import com.mldong.common.annotation.UserNameValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PasswordValidatorClass implements ConstraintValidator<PasswordValidator,String> {
    // 密码字母开头,6-16位长度的字母、数字和特殊字符(@#$%^&*_)组合
    private Pattern pattern = Pattern.compile("^[a-zA-Z]{1}[a-zA-Z0-9@#$%^&*_]{5,15}$");
    private boolean required;
    @Override
    public void initialize(PasswordValidator validator) {
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
