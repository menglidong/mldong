package com.mldong.common.base;

import java.io.Serializable;

/**
 * 统一返回
 * @author mldong
 *
 */
public class CommonResult<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7504120764828454657L;
	private int code;
    private String msg;
    private T data;
	/**
	 * 
	 */
    
	@SuppressWarnings("unused")
	private CommonResult () {
		
	}
	public CommonResult(Type type,String msg) {
		this.code = type.value;
		this.msg = msg;
	}
	
	public CommonResult(Type type,String msg,T data) {
		this.code = type.value;
		this.msg = msg;
		this.data = data;
	}
	public CommonResult(CommonError error) {
		this.code = error.getValue(); 
		this.msg = error.getName();
	}
	public CommonResult(CommonError error,T data) {
		this.code = error.getValue(); 
		this.msg = error.getName();
		this.data = data;
	}
	public enum Type {
		SUCCESS(0),
		FAIL(99999999)
		;
		private final int value;
		Type(int value) {
			this.value = value;
		}
	}
	/**
	 * 返回成功
	 * @param msg
	 * @param data
	 * @return
	 */
	public static <T> CommonResult<T> success(String msg,T data) {
		return new CommonResult<T>(Type.SUCCESS, msg, data);
	}
	/**
	 * 返回成功
	 * @param data
	 * @return
	 */
	public static <T> CommonResult<T> success(T data) {
		return success("操作成功",data);
	}
	/**
	 * 返回成功
	 * @return
	 */
	public static <T> CommonResult<T> success() {
		return success("操作成功", null);
	}
	/**
	 * 返回失败
	 * @param msg
	 * @param data
	 * @return
	 */
	public static <T> CommonResult<T> fail(String msg,T data) {
		return new CommonResult<T>(Type.FAIL, msg, data);
	}
	/**
	 * 返回失败
	 * @param data
	 * @return
	 */
	public static <T> CommonResult<T> fail(T data) {
		return fail("操作失败",data);
	}
	/**
	 * 返回失败
	 * @return
	 */
	public static <T> CommonResult<T> fail() {
		return fail("操作失败", null);
	}
	/**
	 * 自定义异常返回
	 * @return
	 */
	public static <T> CommonResult<T> error(CommonError error,T data) {
		return new CommonResult<T>(error, data);
	}
	/**
	 * 自定义异常返回
	 * @return
	 */
	public static <T> CommonResult<T> error(CommonError error) {
		return error(error,null);
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
}
