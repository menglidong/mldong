package com.mldong.tree;

/**
 * 树型递归接口
 * @author mldong
 * @date 2023/09/23
 */
public interface IRecursionTree<T,R> {
    /**
     * 先序处理
     * @param t
     * @param res
     */
    void rowHandleBefore(T t, R res);

    /**
     * 后序处理
     * @param t
     * @param res
     */
    void rowHandleAfter(T t,R res);
}
