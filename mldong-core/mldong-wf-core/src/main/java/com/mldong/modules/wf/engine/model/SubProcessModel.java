package com.mldong.modules.wf.engine.model;

import com.mldong.modules.wf.engine.core.Execution;
import lombok.Data;

/**
 * 子流程模型
 * @author mldong
 * @date 2023/12/7
 */
@Data
public class SubProcessModel extends NodeModel{
    private String form;
    private Integer version;
    @Override
    void exec(Execution execution) {
        runOutTransition(execution);
    }
}
