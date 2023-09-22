package com.mldong.base;

/**
 * 一对多处理类
 * @author mldong
 * @date 2023/7/12
 */
public interface IOneToManyHandler<T,M> {
    /**
     * 处理新增和修改-param
     * @param t
     * @param update
     */
    void saveOrUpdate(T t,boolean update);

    /**
     * 处理包裹-vo
     * @param m
     */
    void wrap(M m);

    /**
     * 获取服务类-调用的类
     * @return
     */
    Class<?> getServiceClass();

    /**
     * 处理列表
     * @param m
     */
    default void wrapList(M m) {
        wrap(m);
    }

    /**
     * 处理详情
     * @param m
     */
    default void wrapDetail(M m) {
        wrap(m);
    }

    /**
     * 处理导出
     * @param m
     */
    default void wrapExport(M m) {
        wrap(m);
    }
 }
