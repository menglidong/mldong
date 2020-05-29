package com.mldong.common.ex;

import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mldong.common.base.CommonResult;
 /**
  * 全局异常处理类
  * @author mldong
  *
  */
@ControllerAdvice
public class DefaultExceptionHandler {
 
    private static Logger log = LoggerFactory.getLogger(DefaultExceptionHandler.class);  
    
    /**
     * 控制层参数校验异常
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class) 
    @ResponseBody
    public CommonResult<?> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
    	log.error("参数校验异常:{}", e.getMessage());
    	;
    	String errorMsg = String.join(",", e.getBindingResult().getAllErrors().stream().map(item->{
    		return item.getDefaultMessage();
    	}).collect(Collectors.toList()));
    	return CommonResult.fail(errorMsg, e.getBindingResult());
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
