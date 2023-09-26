package com.mldong.modules.wf.engine.handlers;


import com.mldong.modules.wf.engine.core.Execution;

/**
 * 流程各模型操作处理接口
 * @author mldong
 * @date 2023/5/17
 */
public interface IHandler {
    /**
     * 子类需要实现的方法，来处理具体的操作
     * @param execution 执行对象
     */
    void handle(Execution execution);
}
