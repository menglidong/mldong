package com.mldong.exception;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import com.mldong.auth.err.AuthErrEnum;
import com.mldong.base.CommonResult;
import com.mldong.base.ErrEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

/**
 * 统一异常处理
 * @author mldong
 * @date 2023/9/20
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public CommonResult handler(Exception e) {
        log.error("异常",e);
        return CommonResult.fail(e.getMessage());
    }
    @ExceptionHandler(ServiceException.class)
    public CommonResult serviceExceptionHandler(ServiceException e) {
        log.error("业务异常：", e);
        return CommonResult.fail(new ErrEnum() {
            @Override
            public Integer getCode() {
                return e.getCode();
            }

            @Override
            public String getMessage() {
                return e.getErrorMessage();
            }
        });
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResult methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        log.error("参数校验异常：", e);
        // field1,errMsg1;field2,errMsg2
        String msg = e.getBindingResult().getAllErrors().stream().map(item->{
            FieldError fieldError = (FieldError) item;
            String field = fieldError.getField();
            String errMsg = fieldError.getDefaultMessage();
            return field + "," + errMsg;
        }).collect(Collectors.joining(";"));
        return CommonResult.fail(new ErrEnum() {
            @Override
            public Integer getCode() {
                return 99999999;
            }

            @Override
            public String getMessage() {
                return msg;
            }
        });
    }

    /**
     * 没有权限时
     * @param e
     * @return
     */
    @ExceptionHandler(NotPermissionException.class)
    public CommonResult notPermissionExceptionHandler(NotPermissionException e) {
        return CommonResult.fail(AuthErrEnum.NO_RESOURCE_AUTH.getCode(),e.getMessage());
    }
    /**
     * token无效时
     * @param e
     * @return
     */
    @ExceptionHandler(NotLoginException.class)
    public CommonResult notLoginExceptionHandler(NotLoginException e) {
        return CommonResult.fail(AuthErrEnum.TOKEN_NOT_EXIST.getCode(),e.getMessage());
    }
}
