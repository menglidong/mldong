package com.mldong.base;

import cn.hutool.core.convert.Convert;

import java.util.Arrays;
import java.util.Optional;

public interface CodedEnum {

    Integer getCode();

    String getMessage();
    public static <E extends Enum<?> & CodedEnum> Optional<E> codeOf(Class<E> enumClass, Object code) {
        try {
            return Arrays.stream(enumClass.getEnumConstants()).filter(e ->
                    e.getCode().equals(Convert.toInt(code))
                    || e.name().equalsIgnoreCase(Convert.toStr(code))
                    || e.getMessage().equalsIgnoreCase(Convert.toStr(code))
            ).findAny();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取数据类型，用于转换String或Integer
     * @return
     */
    default Class<?> getDataType() {
        return Integer.class;
    }
}

