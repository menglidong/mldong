package com.mldong.modules.wf.enums;

import com.mldong.annotation.DictEnum;
import com.mldong.base.CodedEnum;

/**
 * @author mldong
 * @date 2023/12/5
 */
@DictEnum(key = "wf_process_event_type_enum", name = "流程定义状态")
public enum ProcessEventTypeEnum implements CodedEnum {
    PROCESS_INSTANCE_START(1,"流程实例开始事件"),
    PROCESS_INSTANCE_END(2, "流程实例结束事件"),
    PROCESS_TASK_START(3, "流程任务开始事件"),
    PROCESS_TASK_END(4, "流程任务结束事件"),
    ;
    private final Integer code;

    private final String message;


    ProcessEventTypeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
