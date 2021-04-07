package com.mldong.common.tool;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Map;

public class EasyPoiTool {
	private EasyPoiTool(){}
	/**
	 * 更新替换规则
	 * @param t
	 * @param propertyName
	 * @param value
	 */
    public static void updateReplace(Object t,String propertyName,Object value)  {
		Field field = null;
		try {
			field = t.getClass().getDeclaredField(propertyName);
			Excel excel = field.getAnnotation(Excel.class);
		    InvocationHandler invocationHandler = Proxy.getInvocationHandler(excel);
		    Field declaredField = invocationHandler.getClass().getDeclaredField("memberValues");
		    // 因为这个字段是 private final 修饰，所以要打开权限
			  declaredField.setAccessible(true);
			  // 获取 memberValues
			  Map memberValues = (Map) declaredField.get(invocationHandler);
			  // 修改 replace 属性值
			  memberValues.put("replace", value);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 隐藏列
	 * @param t
	 * @param propertyName
	 */
	public static void hiddenColumn(Object t,String propertyName)  {
		Field field = null;
		try {
			field = t.getClass().getDeclaredField(propertyName);
			Excel excel = field.getAnnotation(Excel.class);
		    InvocationHandler invocationHandler = Proxy.getInvocationHandler(excel);
		    Field declaredField = invocationHandler.getClass().getDeclaredField("memberValues");
		    // 因为这个字段是 private final 修饰，所以要打开权限
			  declaredField.setAccessible(true);
			  // 获取 memberValues
			  Map memberValues = (Map) declaredField.get(invocationHandler);
			  // 修改 replace 属性值
			  memberValues.put("isColumnHidden", true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 显示列
	 * @param t
	 * @param propertyName
	 */
	public static void showColumn(Object t,String propertyName)  {
		Field field = null;
		try {
			field = t.getClass().getDeclaredField(propertyName);
			Excel excel = field.getAnnotation(Excel.class);
		    InvocationHandler invocationHandler = Proxy.getInvocationHandler(excel);
		    Field declaredField = invocationHandler.getClass().getDeclaredField("memberValues");
		    // 因为这个字段是 private final 修饰，所以要打开权限
			  declaredField.setAccessible(true);
			  // 获取 memberValues
			  Map memberValues = (Map) declaredField.get(invocationHandler);
			  // 修改 replace 属性值
			  memberValues.put("isColumnHidden", false);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
