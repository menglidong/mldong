package com.mldong.common.validator;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.mldong.common.annotation.PhoneValidator;
/**
 * 手机号校验器
 * @author mldong
 *
 */
public class PhoneValidatorClass implements ConstraintValidator<PhoneValidator,String> {
    private Pattern pattern = Pattern.compile("^1[3456789]\\d{9}$");
    private boolean required;
    @Override
    public void initialize(PhoneValidator validator) {
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
