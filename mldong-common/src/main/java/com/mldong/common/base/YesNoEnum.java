package com.mldong.common.base;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.mldong.common.annotation.DictEnum;
/**
 * yes_no
 * @author mldong
 *
 */
@DictEnum(key="yes_no",name="是否")
public enum YesNoEnum implements CodedEnum{
	/**
	 * 是
	 */
	YES(1, "是"),
	/**
	 * 否
	 */
	NO(2,"否")
	;
	private int value;
	private String name;
	/**
	 * 未删除
	 */
	public final static int Y=1;
	/**
	 * 已删除
	 */
	public final static int N=2;
	@JsonCreator
    public static YesNoEnum forValue(int value) {
        return CodedEnum.codeOf(YesNoEnum.class, value).get();

    }
	YesNoEnum(int value, String name) {
		this.value = value;
		this.name = name;
	}
	@JsonValue
	public int getValue() {
		return value;
	}
	public String getName() {
		return name;
	}
	
}
