package com.mldong.base;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.mldong.annotation.DictEnum;

/**
 * yes_no
 * @author mldong
 *
 */
@DictEnum(key="yes_no",name="是否")
public enum YesNoEnum implements CodedEnum {
	/**
	 * 是
	 */
	YES(1, "是"),
	/**
	 * 否
	 */
	NO(0,"否")
	;
	private Integer code;
	private String message;
	/**
	 * 未删除
	 */
	public final static int Y=1;
	/**
	 * 已删除
	 */
	public final static int N=0;
	@JsonCreator
    public static YesNoEnum forValue(Object value) {
        return CodedEnum.codeOf(YesNoEnum.class, value).get();
    }
	YesNoEnum(int value, String name) {
		this.code = value;
		this.message = name;
	}
	@JsonValue
	public Integer getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}

}