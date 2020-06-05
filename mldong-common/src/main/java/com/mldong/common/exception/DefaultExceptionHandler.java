package com.mldong.common.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import com.mldong.common.base.CodedEnum;
import com.mldong.common.base.CommonError;
import com.mldong.common.base.CommonResult;
import com.mldong.common.base.constant.GlobalErrEnum;
 /**
  * 全局异常处理类
  * @author mldong
  *
  */
@ControllerAdvice
public class DefaultExceptionHandler {
 
    private static Logger log = LoggerFactory.getLogger(DefaultExceptionHandler.class);  
    /**
     * 服务层异常
     * @param e
     * @return
     */
    @ExceptionHandler(BizException.class)
    @ResponseBody
    public CommonResult<?> serviceExceptionHandler(BizException e) {
    	log.error("服务层异常:", e);
    	return CommonResult.error(e.getError());
    }
    /**
     * json转换异常，一般为自定义枚举类转换出问题
     * @param e
     * @return
     */
    @ExceptionHandler(HttpMessageConversionException.class)
    @ResponseBody
    public CommonResult<?> invalidDefinitionExceptionHandler(HttpMessageConversionException e) {
    	log.error("json转换异常:", e);
    	if(e.getCause().getClass().isAssignableFrom(InvalidDefinitionException.class)){
    		// 如果是InvalidDefinitionException
    		final InvalidDefinitionException invalidDefinitionException = (InvalidDefinitionException) e.getCause();
    		if(invalidDefinitionException.getType().isEnumType()){
    			// 如果是自定义枚举类异常
    			try {
    				String typeName = invalidDefinitionException.getType().getTypeName();
    				typeName = typeName.substring(20, typeName.length()-1);
					Class<?> clazz = Class.forName(typeName);
					List<Map<String,Object>> list = new ArrayList<>();
					Arrays.stream(clazz.getEnumConstants()).forEach(i->{
						if(i instanceof CodedEnum) {
							CodedEnum coded = (CodedEnum)i;
							int value = coded.getValue();
							String name = coded.getName();
							Map<String,Object> map = new HashMap<>();
							map.put("value", value);
							map.put("name", name);
							list.add(map);
						}
					});
					return CommonResult.error(new CommonError() {
						
						@Override
						public int getValue() {
							return GlobalErrEnum.GL99990100.getValue();
						}
						
						@Override
						public String getName() {
							return invalidDefinitionException.getMessage();
						}
					},list);
    			} catch (ClassNotFoundException e1) {
					return CommonResult.fail(e1);
				}
    		}
    	}
    	return CommonResult.fail();
    }
    
    /**
     * 控制层参数校验异常
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class) 
    @ResponseBody
    public CommonResult<?> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
    	log.error("参数校验异常:{}", e.getMessage());
    	final String errorMsg = String.join(",", e.getBindingResult().getAllErrors().stream().map(item->{
    		return item.getDefaultMessage();
    	}).collect(Collectors.toList()));
    	return CommonResult.error(new CommonError() {
			
			@Override
			public int getValue() {
				return GlobalErrEnum.GL99990100.getValue();
			}
			
			@Override
			public String getName() {
				return errorMsg;
			}
		});
    }
    /**
     * 其他异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class) 
    @ResponseBody
    public CommonResult<?> excetionHandler(Exception e){
    	log.error("未捕获异常:", e);
    	return CommonResult.fail(e.getMessage(), null);
    }
}
