package com.mldong.modules.wf.engine.model;

import com.mldong.modules.wf.engine.core.Execution;
import com.mldong.modules.wf.engine.handlers.IHandler;
import lombok.Data;
/**
 *
 * 模型基类
 * @author mldong
 * @date 2023/4/25
 */
@Data
public class BaseModel {
    private String name; // 唯一编码
    private String displayName; // 显示名称
    /**
     * 将执行对象execution交给具体的处理器处理
     * @param handler
     * @param execution
     */
    protected void fire(IHandler handler, Execution execution) {
        handler.handle(execution);
    }
}
