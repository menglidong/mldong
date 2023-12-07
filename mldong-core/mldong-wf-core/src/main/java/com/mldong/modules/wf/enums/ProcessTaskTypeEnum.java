package com.mldong.modules.wf.enums;

import cn.hutool.core.convert.Convert;
import com.mldong.annotation.DictEnum;
import com.mldong.base.CodedEnum;

import java.util.Arrays;

/**
 *
 * 任务类型
 * @author mldong
 * @date 2023/4/25
 */
@DictEnum(key = "wf_process_task_type", name = "流程任务类型")
public enum ProcessTaskTypeEnum implements CodedEnum {
    MAJOR(0,"主办"),
    SECONDARY(1, "协办"),
    RECORD(2,"记录"),
    ;
    private final Integer code;

    private final String message;


    ProcessTaskTypeEnum(Integer code, String message) {
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
    public static ProcessTaskTypeEnum codeOf(Object code) {
        return Arrays.stream(ProcessTaskTypeEnum.class.getEnumConstants()).filter(e ->
                e.getCode().equals(Convert.toInt(code))
                        || e.name().equalsIgnoreCase(Convert.toStr(code))
                        || e.getMessage().equalsIgnoreCase(Convert.toStr(code))
        ).findAny().orElse(ProcessTaskTypeEnum.MAJOR);
    }
}
