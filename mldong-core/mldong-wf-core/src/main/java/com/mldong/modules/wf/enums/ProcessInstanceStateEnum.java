package com.mldong.modules.wf.enums;

import com.mldong.annotation.DictEnum;
import com.mldong.base.CodedEnum;

/**
 *
 * 流程实例状态(10：进行中；20：已完成；30：已撤回；40：强行终止；50：挂起；99：已废弃)
 * @author mldong
 * @date 2023/5/28
 */
@DictEnum(key = "wf_process_instance_state", name = "流程实例状态")
public enum ProcessInstanceStateEnum implements CodedEnum {
    DOING(10,"进行中"),
    FINISHED(20, "已完成"),
    WITHDRAW(30, "已撤回"),
    INTERRUPT(40, "强行终止"),
    REJECT(45, "已拒绝"),
    PENDING(50,"挂起"),
    ABANDON(99,"已废弃")
    ;
    private final Integer code;

    private final String message;


    ProcessInstanceStateEnum(Integer code, String message) {
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
