package com.mldong.common.base.constant;

import com.mldong.common.annotation.ErrEnum;
import com.mldong.common.base.CommonError;
/**
 * 全局错误码
 * @author mldong
 *
 */
@ErrEnum(name="global",value="全局错误码",bizCode=9999,min=0,max=1000)
public enum GlobalErrEnum implements CommonError {
	/**
	 * 参数异常
	 */
	GL99990100(99990100, "参数异常"),
	/**
	 * 无访问权限
	 */
	GL99990401(99990401, "无访问权限"),
	/**
	 * 未知异常
	 */
	GL99990500(99990500, "未知异常"),
	/**
	 * 无权访问
	 */
	GL99990403(99990403, "无权访问"),
	/**
	 * 找不到指定资源
	 */
	GL99990404(99990404, "找不到指定资源"),
	/**
	 * 注解使用错误
	 */
	GL99990001(99990001, "注解使用错误"),
	/**
	 * 微服务不在线,或者网络超时
	 */
	GL99990002(99990002, "微服务不在线,或者网络超时"),
	/**
	 * 没有数据
	 */
	GL99990003(99990003, "没有数据"),
	/**
	 * 演示账号，无写权限
	 */
	GL99990004(99990004, "演示账号，无写权限"),
	/**
	 * 数据库插入异常
	 */
	GL99990005(99990005, "数据库插入异常"),
	/**
	 * 文件后辍不允许
	 */
	GL99990006(99990006, "文件后辍不允许"),
	;
	
	private GlobalErrEnum(int value, String name){
		this.value = value;
		this.name = name;
	} 
	private String name;

	private int value;

	public int getValue() {
		return value;
	}
	public String getName() {
		return name;
	}

}
