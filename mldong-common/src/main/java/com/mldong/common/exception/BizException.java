package com.mldong.common.exception;

import com.mldong.common.base.CommonError;
/**
 * 业务错码异常类
 * @author mldong
 *
 */
public class BizException extends RuntimeException{

	private static final long serialVersionUID = -8059304617449091328L;

	/**
	 * 错误
	 */
	private CommonError error;

	public BizException(CommonError error) {
		super("[" + error.getValue() + "]" + error.getName());
		this.error = error;
	}

	public CommonError getError() {
		return error;
	}

	public void setError(CommonError error) {
		this.error = error;
	}
}
