package com.mldong.modules.wf.engine.model;

import com.mldong.modules.wf.engine.core.Execution;
import com.mldong.modules.wf.engine.handlers.impl.EndProcessHandler;
import lombok.Data;
/**
 *
 * 结束模型
 * @author mldong
 * @date 2023/4/25
 */
@Data
public class EndModel extends NodeModel {
    @Override
    public void exec(Execution execution) {
        // 执行结束节点自定义执行逻辑
        System.out.println(super.toString());
        fire(new EndProcessHandler(this), execution);
    }
}