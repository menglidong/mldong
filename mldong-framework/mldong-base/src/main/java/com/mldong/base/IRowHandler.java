package com.mldong.base;

/**
 * 行处理接口
 * @author mldong
 * @date 2023/7/10
 */
public interface IRowHandler<T> {
    void handle(T t);
}
