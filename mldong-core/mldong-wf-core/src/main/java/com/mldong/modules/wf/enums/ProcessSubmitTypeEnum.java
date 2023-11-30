package com.mldong.modules.wf.enums;

import com.mldong.annotation.DictEnum;
import com.mldong.base.CodedEnum;

/**
 * 流程提交类型（操作类型）
 * @author mldong
 * @date 2023/10/7
 */
@DictEnum(key = "wf_process_submit_type", name = "操作类型")
public enum ProcessSubmitTypeEnum  implements CodedEnum {
    APPLY(0,"发起申请"),
    AGREE(1, "同意申请"),
    REJECT(2, "拒绝申请"),
    ROLLBACK(3, "退回上一步"),
    JUMP(4, "跳转"),
    RE_APPLY(5, "重新提交"),
    ROLLBACK_TO_OPERATOR(6, "退回发起人"),
    COUNTERSIGN_DISAGREE(20, "拒绝申请")
    ;
    private final Integer code;

    private final String message;


    ProcessSubmitTypeEnum(Integer code, String message) {
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
