package com.mldong.modules.wf.enums;

import cn.hutool.core.convert.Convert;
import com.mldong.annotation.DictEnum;
import com.mldong.base.CodedEnum;
/**
 *
 * 任务参与类型
 * @author mldong
 * @date 2023/4/25
 */
@DictEnum(key = "wf_process_task_perform_type", name = "流程任务参与类型")
public enum ProcessTaskPerformTypeEnum implements CodedEnum {
    /**
     * 普通参与：一个或多个人同时参与一个任务，所有人只要其中一人执行完成，就能驱动任务节点往下一步执行
     */
    NORMAL(0,"普通参与"),
    /**
     * 会签参与：给每个人都创建任务，满足一定条件时，才能驱动任务节点往下一步执行
     * 1. 所有人都执行完成
     * 2. 有一定比率执行完成时
     * 3. 某特殊人员执行完成时
     * ……等等
     */
    COUNTERSIGN(1, "会签参与"),
    ;
    private final Integer code;

    private final String message;


    ProcessTaskPerformTypeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    /**
     * code转成枚举
     * @param code
     * @return
     */
    public static ProcessTaskPerformTypeEnum codeOf(Object code) {
        if("ALL".equalsIgnoreCase(Convert.toStr(code))
                || COUNTERSIGN.toString().toUpperCase().equals(code)
                || COUNTERSIGN.toString().toLowerCase().equals(code)
                || COUNTERSIGN.code.equals(code)) {
            return COUNTERSIGN;
        }
        return NORMAL;
    }
}
