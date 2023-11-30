package com.mldong.modules.wf.enums;

import cn.hutool.core.convert.Convert;
import com.mldong.annotation.DictEnum;
import com.mldong.base.CodedEnum;

import java.util.Arrays;

/**
* @author mldong
* @date 2023/11/24
*/
@DictEnum(key = "wf_countersign_type", name = "会签类型")
public enum CountersignTypeEnum implements CodedEnum {
    PARALLEL(0, "并行会签"),
    SEQUENTIAL(1, "串行会签"),
    ;
    private final Integer code;

    private final String message;
    public static CountersignTypeEnum codeOf(Object code) {
        return Arrays.stream(CountersignTypeEnum.class.getEnumConstants()).filter(e ->
                e.getCode().equals(Convert.toInt(code))
                        || e.name().equalsIgnoreCase(Convert.toStr(code))
                        || e.getMessage().equalsIgnoreCase(Convert.toStr(code))
        ).findAny().orElse(CountersignTypeEnum.PARALLEL);
    }

    CountersignTypeEnum(Integer code, String message) {
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
