package com.mldong.modules.wf.engine;

import com.mldong.modules.wf.engine.core.Execution;
import com.mldong.modules.wf.engine.model.TaskModel;

import java.util.List;

/**
 *
 * 分配参与者的处理接口
 * @author mldong
 * @date 2023/6/15
 */
public interface AssignmentHandler {
    /**
     * 分配参与者方法，可获取到当前的执行对象
     * @param model 模型对象
     * @param execution 执行对象
     * @return Object 参与者对象
     */
    List<String> assign(TaskModel model, Execution execution);
    default String getMessage() {
        return this.getClass().getSimpleName();
    }
    default int getOrder() {
        return Integer.MIN_VALUE;
    }
}
