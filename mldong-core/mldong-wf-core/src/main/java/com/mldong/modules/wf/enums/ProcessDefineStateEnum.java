package com.mldong.modules.wf.enums;

import com.mldong.annotation.DictEnum;
import com.mldong.base.CodedEnum;

/**
 *
 * 流程定义状态
 * @author mldong
 * @date 2023/5/28
 */
@DictEnum(key = "wf_process_define_state", name = "流程定义状态")
public enum ProcessDefineStateEnum implements CodedEnum {
    DISABLE(0,"禁用"),
    ENABLE(1, "启用"),
    ;
    private final Integer code;

    private final String message;


    ProcessDefineStateEnum(Integer code, String message) {
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
