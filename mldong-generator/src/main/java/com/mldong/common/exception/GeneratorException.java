package com.mldong.common.exception;

import com.mldong.common.enums.ErrorEnum;

/**
 * 代码生成器异常
 * @author mldong
 *
 */
public class GeneratorException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6435996314204588819L;
	private ErrorEnum errorEnum;
	public GeneratorException(ErrorEnum errorEnum) {
		this.errorEnum = errorEnum;
	}
	public ErrorEnum getErrorEnum() {
		return errorEnum;
	}
	public void setErrorEnum(ErrorEnum errorEnum) {
		this.errorEnum = errorEnum;
	}
	@Override
	public String toString() {
		return errorEnum.getValue()+":"+errorEnum.getName();
	}
}
