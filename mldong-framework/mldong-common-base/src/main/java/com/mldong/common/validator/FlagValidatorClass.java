package com.mldong.common.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.mldong.common.annotation.FlagValidator;
/**
 * flag校验器
 * @author mldong
 *
 */
public class FlagValidatorClass implements ConstraintValidator<FlagValidator,Object> {

    // 保存flag值
    private String values;
    private boolean required;
    @Override
    public void initialize(FlagValidator flagValidator) {
        // 注解内配的值赋值给变量
        this.values = flagValidator.values();
        this.required = flagValidator.required();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
       if(this.required) {
    	   if(null == value || "".equals(value)) {
    		   return false;
    	   }
       }
    	// 切割获取值
        String[] valueArray = values.split(",");
        Boolean isFlag = false;
        for (int i = 0; i < valueArray.length; i++){
            // 存在一致就跳出循环
            if (valueArray[i] .equals(value.toString())){
                isFlag = true;
                break;
            }
        }
        return isFlag;
    }
}
