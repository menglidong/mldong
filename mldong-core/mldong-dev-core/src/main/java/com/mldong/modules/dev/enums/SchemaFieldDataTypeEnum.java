package com.mldong.modules.dev.enums;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.mldong.annotation.DictEnum;
import com.mldong.base.CodedEnum;

import java.util.Arrays;

/**
 * @author mldong
 * @Description
 * @date 2024-01-19
 */
@DictEnum(key = "dev_schema_field_data_type", name = "模型字段类型")
public enum SchemaFieldDataTypeEnum implements CodedEnum {
    // 基本类型
//    BASE_BYTE(1, "byte", null),
//    BASE_SHORT(2, "short", null),
//    BASE_CHAR(3, "char", null),
//    BASE_INT(4, "int", null),
//    BASE_LONG(5, "long", null),
//    BASE_FLOAT(6, "float", null),
//    BASE_DOUBLE(7, "double", null),
//    BASE_BOOLEAN(8, "boolean", null),

    // 包装类型
    BYTE(9, "Byte", null),
    SHORT(10, "Short", null),
    CHARACTER(11, "Character", null),
    INTEGER(12, "Integer", null),
    LONG(13, "Long", null),
    FLOAT(14, "Float", null),
    DOUBLE(15, "Double", null),
    BOOLEAN(16, "Boolean", null),
    STRING(17, "String", null),

    // sql 包下数据类型
    DATE_SQL(18, "Date", "java.sql.Date"),
    TIME(19, "Time", "java.sql.Time"),
    TIMESTAMP(20, "Timestamp", "java.sql.Timestamp"),
    BLOB(21, "Blob", "java.sql.Blob"),
    CLOB(22, "Clob", "java.sql.Clob"),

    // java8 新时间类型
    LOCAL_DATE(23, "LocalDate", "java.time.LocalDate"),
    LOCAL_TIME(24, "LocalTime", "java.time.LocalTime"),
    YEAR(25, "Year", "java.time.Year"),
    YEAR_MONTH(26, "YearMonth", "java.time.YearMonth"),
    LOCAL_DATE_TIME(27, "LocalDateTime", "java.time.LocalDateTime"),
    INSTANT(28, "Instant", "java.time.Instant"),

    // 其他杂类
//    BYTE_ARRAY(29, "byte[]", null),
    OBJECT(30, "Object", null),
    DATE(31, "Date", "java.util.Date"),
    BIG_INTEGER(32, "BigInteger", "java.math.BigInteger"),
    BIG_DECIMAL(33, "BigDecimal", "java.math.BigDecimal");
    private Integer code;
    private String message;
    private String pkg;

    @JsonCreator
    public static SchemaFieldDataTypeEnum forValue(Integer value) {
        return CodedEnum.codeOf(SchemaFieldDataTypeEnum.class, value).get();
    }

    @JsonCreator
    public static SchemaFieldDataTypeEnum forValue(String message) {
        return Arrays.stream(SchemaFieldDataTypeEnum.values()).filter(v -> v.getMessage().equals(message)).findFirst().get();
    }

    SchemaFieldDataTypeEnum(Integer code, String message, String pkg) {
        this.code = code;
        this.message = message;
        this.pkg = pkg;
    }

    @JsonValue
    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public String getPkg() {
        return this.pkg;
    }

    @Override
    public Class<?> getDataType() {
        return String.class;
    }
}
