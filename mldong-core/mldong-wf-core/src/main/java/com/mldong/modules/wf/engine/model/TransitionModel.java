package com.mldong.modules.wf.engine.model;

import com.mldong.modules.wf.engine.handlers.impl.CreateTaskHandler;
import com.mldong.modules.wf.engine.Action;
import com.mldong.modules.wf.engine.core.Execution;
import com.mldong.modules.wf.engine.handlers.impl.StartSubProcessHandler;
import lombok.Data;
/**
 *
 * 边模型
 * @author mldong
 * @date 2023/4/25
 */
@Data
public class TransitionModel extends BaseModel implements Action {
    private NodeModel source; // 边源节点引用
    private NodeModel target; // 边目标节点引用
    private String to; // 目标节点名称
    private String expr; // 边表达式
    private String g; // 边点坐标集合(x1,y1;x2,y2,x3,y3……)开始、拐角、结束
    private boolean enabled; // 是否可执行
    @Override
    public void execute(Execution execution) {
        if(!enabled) return;
        if(target instanceof TaskModel) {
            // 创建阻塞任务
            fire(new CreateTaskHandler((TaskModel) target),execution);
        } else if(target instanceof SubProcessModel){
            // 如果为子流程，则启动子流程
            fire(new StartSubProcessHandler((SubProcessModel) target), execution);
        } else {
            target.execute(execution);
        }
    }
}
