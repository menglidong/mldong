package com.mldong.modules.sys.enums;

import com.mldong.common.annotation.ErrEnum;
import com.mldong.common.base.CommonError;
/**
 * 8000 9001-9999
 * sys模块错误码定义
 * @author mldong
 *
 */
@ErrEnum(name="sys",value="系统管理",bizCode=8000,min=9001,max=9999)
public enum SysErrEnum implements CommonError {
	/**
	 * 用户不存在
	 */
	SYS80000001(80009001, "用户不存在"),
	/**
	 * 用户名或者密码错误
	 */
	SYS80000002(80009002, "用户名或者密码错误"),
	/**
	 * 登录次数太多，账号已被锁定，请联系管理员
	 */
	SYS80000003(80009003, "登录次数太多，账号已被锁定，请联系管理员"),
	/**
	 * 用户已被锁定
	 */
	SYS80000004(80009004, "用户已被锁定"),
	/**
	 * 两密码不一致
	 */
	SYS80000005(80009005,"两密码不一致"),
	/**
	 * 旧密码错误
	 */
	SYS80000006(80009006,"旧密码错误"),
	/**
	 * 用户名已存在
	 */
	SYS80000007(80009007, "用户名已存在"),
	/**
	 * 部门已禁用
	 */
	SYS80000008(80009008, "部门已禁用"),
	/**
	 * 不允许选自身为父节点
	 */
	SYS80000009(80009009, "不允许选自身为父节点")
	;
	public final int value;
	public final String name;
	SysErrEnum(int value,String name) {
		this.value = value;
		this.name = name;
	}
	@Override
	public int getValue() {
		return this.value;
	}

	@Override
	public String getName() {
		return this.name;
	}

}
