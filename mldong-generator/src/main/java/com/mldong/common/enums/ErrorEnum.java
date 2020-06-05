package com.mldong.common.enums;

public enum ErrorEnum {
	/**
	 * 获取不到表
	 */
	notGetTable(1001,"获取不到表"),
	/**
	 * 获取不到注释
	 */
	notGetComment(1002,"获取不到注释"),
	/**
	 * 获取不到列
	 */
	notGetColumn(1003,"获取不到列"),
	/**
	 * 模板解析异常
	 */
	templateEngineProcess(1004,"模板解析异常")
	;
	public final int value;
	public final String name;
	ErrorEnum(int value,String name) {
		this.value = value;
		this.name = name;
	}
	public int getValue() {
		return this.value;
	}

	public String getName() {
		return this.name;
	}

}
