package com.mldong.modules.wf.enums;

import com.mldong.annotation.DictEnum;
import com.mldong.base.CodedEnum;

/**
 *
 * 任务状态枚举
 * @author mldong
 * @date 2023/5/17
 */
@DictEnum(key = "wf_process_task_state", name = "流程任务状态")
public enum ProcessTaskStateEnum implements CodedEnum {
    DOING(10,"进行中"),
    FINISHED(20, "已完成"),
    WITHDRAW(30, "已撤回"),
    INTERRUPT(40, "强行终止"),
    PENDING(50,"挂起"),
    ABANDON(99,"已废弃")
    ;
    private final Integer code;

    private final String message;


    ProcessTaskStateEnum(Integer code, String message) {
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
