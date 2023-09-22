package com.mldong.exception;

import com.mldong.base.ErrEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * 业务异常
 * @author mldong
 * @date 2022-01-06
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ServiceException extends RuntimeException {

    private Integer code;

    private String errorMessage;

    public ServiceException(Integer code, String errorMessage) {
        super(errorMessage);
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public ServiceException(ErrEnum exception) {
        super(exception.getMessage());
        this.code = exception.getCode();
        this.errorMessage = exception.getMessage();
    }
    /**
     * 抛出自定义异常
     * @param code
     * @param message
     */
    public static void throwBiz(Integer code, String message) {
        throw new ServiceException(code, message);
    }
    /**
     * 抛出自定义异常
     * @param errEnum
     */
    public static void throwBiz(ErrEnum errEnum) {
        throw new ServiceException(errEnum.getCode(), errEnum.getMessage());
    }
}
