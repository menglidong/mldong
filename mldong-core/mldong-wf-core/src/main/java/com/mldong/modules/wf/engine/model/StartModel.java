package com.mldong.modules.wf.engine.model;

import com.mldong.modules.wf.engine.core.Execution;
import lombok.Data;
/**
 *
 * 开始模型
 * @author mldong
 * @date 2023/4/25
 */
@Data
public class StartModel extends NodeModel {
    @Override
    public void exec(Execution execution) {
        // 执行开始节点自定义执行逻辑
        System.out.println(super.toString());
        runOutTransition(execution);
    }
}