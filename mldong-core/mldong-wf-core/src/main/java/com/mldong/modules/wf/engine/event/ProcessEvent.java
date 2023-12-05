package com.mldong.modules.wf.engine.event;

import com.mldong.modules.wf.enums.ProcessEventTypeEnum;
import lombok.Builder;
import lombok.Data;

/**
 * 流程开始事件
 * @author mldong
 * @date 2023/12/5
 */
@Data
@Builder
public class ProcessEvent {
    // 流程事件类型
    private ProcessEventTypeEnum eventType;
    // 执行源id(流程实例id,流程任务id)
    private Long sourceId;
}
