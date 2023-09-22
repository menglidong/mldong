package com.mldong.base;

/**
 * @author mldong
 * @date 2023/9/20
 */
public interface ErrEnum {
    /**
     * 获取异常的状态码
     *
     * @return 状态码
     */
    Integer getCode();

    /**
     * 获取异常的提示信息
     *
     * @return 提示信息
     */
    String getMessage();

}
