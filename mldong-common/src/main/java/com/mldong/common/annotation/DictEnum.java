package com.mldong.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 字典枚举类注解，方便收集做为字典使用的
 * @author mldong
 *
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface DictEnum {
	/**
	 * 名称
	 * @return
	 */
	String name();
	/**
	 * 唯一标识
	 * @return
	 */
	String key();
}
