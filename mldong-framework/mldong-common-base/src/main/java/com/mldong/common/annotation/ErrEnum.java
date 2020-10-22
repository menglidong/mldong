package com.mldong.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 错误码枚举注解，方便收集错误码描述，统一查看的
 * @author mldong
 *
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface ErrEnum {
	/**
	 * 名称
	 * @return
	 */
	String name();
	/**
	 * 说明
	 * @return
	 */
	String value();
	/**
	 * 业务码
	 * @return
	 */
	int bizCode();
	/**
	 * 最小值
	 * @return
	 */
	int min();

	/**
	 * 最大值
	 * @return
	 */
	int max();

}
