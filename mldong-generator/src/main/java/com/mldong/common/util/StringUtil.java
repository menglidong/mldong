package com.mldong.common.util;

import java.io.File;

public class StringUtil {
	public static boolean isEmpty(String value) {
		return value == null || value.trim().length() == 0;
	}

	/**
	 * 验证对象是否为空
	 * 
	 * @param object
	 *            对象实例
	 * @return true：不为空，false：为空
	 */
	public static boolean isNotEmpty(Object object) {
		if (object instanceof String) {
			return object != null && String.valueOf(object).length() > 0;
		}
		return object != null;
	}

	/**
	 * 格式化驼峰命名字符串
	 * 
	 * @param inputString
	 *            未格式化的字符串
	 * @param firstCharacterUppercase
	 *            首字母是否大写
	 * @return 格式化后的字符串
	 */
	public static String getCamelCaseString(String inputString,
			boolean firstCharacterUppercase) {
		StringBuilder sb = new StringBuilder();
		boolean nextUpperCase = false;
		for (int i = 0; i < inputString.length(); i++) {
			char c = inputString.charAt(i);

			switch (c) {
			case '_':
			case '-':
			case '@':
			case '$':
			case '#':
			case ' ':
			case '/':
			case '&':
				if (sb.length() > 0) {
					nextUpperCase = true;
				}
				break;

			default:
				if (nextUpperCase) {
					sb.append(Character.toUpperCase(c));
					nextUpperCase = false;
				} else {
					sb.append(Character.toLowerCase(c));
				}
				break;
			}
		}

		if (firstCharacterUppercase) {
			sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
		}

		return sb.toString();
	}

	public static String packagePathToFilePath(String packagepath) {
		String result = null;
		if (packagepath != null) {
			result = packagepath.replace(".", File.separator);
		}
		return result;
	}
}
