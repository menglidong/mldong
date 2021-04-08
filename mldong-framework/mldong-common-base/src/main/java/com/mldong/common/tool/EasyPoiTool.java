package com.mldong.common.tool;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.mldong.common.base.CodedEnum;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
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

	/**
	 * 获取实体类中的字典项列
	 * @param t
	 * @return
	 */
	public static String [] getEnumFields(Class<?> t) {
		List<String> dictFields = new ArrayList<>();
		Field [] fields = t.getDeclaredFields();
		for(Field field:fields) {
			if("java.lang.Enum".equals(field.getType().getSuperclass().getName())){
				Excel excel = field.getAnnotation(Excel.class);
				dictFields.add(excel.name());
			}
		}
		return dictFields.toArray(new String[]{});
	}
	/**
	 * 导出枚举处理
	 * @param obj
	 * @param name
	 * @param value
	 * @return
	 */
	public static Object exportEnumHandler(Object obj, String name, Object value) {
		if(value instanceof CodedEnum) {
			CodedEnum codedEnum = (CodedEnum) value;
			return codedEnum.getName();
		}
		return value;
	}
	/**
	 * 导入枚举处理
	 * @param obj
	 * @param name
	 * @param value
	 * @return
	 */
	public static Object importEnumHandler(Object obj, String name, Object value) {
		Object res = null;
		try {
			Field[] fields = obj.getClass().getDeclaredFields();
			for (int i = 0, len = fields.length; i < len; i++) {
				Field field = fields[i];
				Excel excel = field.getAnnotation(Excel.class);
				if (excel.name().equals(name)) {
					CodedEnum[] values = (CodedEnum[]) field.getType().getMethod("values").invoke(new Object(), new Object[]{});
					for(CodedEnum codedEnum:values) {
						if(codedEnum.getName().equals(value.toString())) {
							res =  codedEnum;
							break;
						}
					}
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
}
