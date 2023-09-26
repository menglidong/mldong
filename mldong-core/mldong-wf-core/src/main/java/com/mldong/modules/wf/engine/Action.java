package com.mldong.modules.wf.engine;

import com.mldong.modules.wf.engine.core.Execution;

/**
 *
 * 模型行为
 * @author mldong
 * @date 2023/4/25
 */
public interface Action {
    public void execute(Execution execution);
}