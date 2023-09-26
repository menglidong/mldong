package com.mldong.modules.wf.enums.err;

import com.mldong.base.ErrEnum;

/**
 *
 * 错误枚举
 * @author mldong
 * @date 2023/5/1
 */
public enum WfErrEnum implements ErrEnum {
    NOT_FOUND_NEXT_NODE(1001,"decision节点无法确定下一步执行路线"),
    NOT_FOUND_PROCESS_DEFINE(1002,"没有流程定义"),
    NOT_FOUND_DOING_PROCESS_TASK(1003, "没有进行中的流程任务"),
    NOT_ALLOWED_EXECUTE(1004,"当前参与者不能执行该流程任务")
    ;
    private final Integer code;

    private final String message;


    WfErrEnum(Integer code, String message) {
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
