package com.mldong.base;

import java.util.Arrays;
import java.util.Optional;

public interface CodedEnum {

    int getValue();

    String getName();
    public static <E extends Enum<?> & CodedEnum> Optional<E> codeOf(Class<E> enumClass, int code) {
        return Arrays.stream(enumClass.getEnumConstants()).filter(e -> e.getValue() == code).findAny();
    }
}

