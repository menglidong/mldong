package com.mldong.common.tool;

import java.util.Calendar;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringTool {
	/**
	 * 字符串是否为空
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(String s) {
		return (s == null) || ("".equals(s));
	}

	/**
	 * 判断字符串是否不为空
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isNotEmpty(String s) {
		return !isEmpty(s);
	}

	/**
	 * 获取主键
	 * 
	 * @return
	 */
	public static String getPrimaryKey() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * 获取随机字符串
	 * 
	 * @param len
	 * @return
	 */
	public static String getRandomString(int len) {
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < len; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * 获取随机数（仅数字）
	 * 
	 * @param len
	 * @return
	 */
	public static String getRandomNumber(int len) {
		String base = "0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < len; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * 获取某区间内的随机数
	 * 
	 * @param min
	 *            最小值
	 * @param max
	 *            最大值
	 * @return
	 */
	public static int getRandom(int min, int max) {
		int result = 0;
		result = min + (int) (Math.random() * ((max - min) + 1));
		return result;
	}

	private static Pattern linePattern = Pattern.compile("_(\\w)");

	/** 下划线转驼峰 */
	public static String lineToHump(String str) {
		str = str.toLowerCase();
		Matcher matcher = linePattern.matcher(str);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
		}
		matcher.appendTail(sb);
		return sb.toString();
	}

	private static Pattern humpPattern = Pattern.compile("[A-Z]");

	/** 驼峰转下划线,效率比上面高 */
	public static String humpToLine(String str) {
		Matcher matcher = humpPattern.matcher(str);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
		}
		matcher.appendTail(sb);
		return sb.toString();
	}

	/**
	 * 首字母大写
	 * 
	 * @param str
	 * @return
	 */
	public static String upperCase(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}
	/**
	 * 校验数据库列有效性
	 * @param column 列名
	 * @return
	 */
	public static boolean checkColumn(String column) {
		String regExp = "(^_([a-zA-Z0-9]_?)*$)|(^[a-zA-Z](_?[a-zA-Z0-9])*_?$)";
		return column.matches(regExp);
	}
	private static final int SIZE = 6;
	private static final String SYMBOL = "*";

	/**
	 * 隐藏长度
	 * @param value
	 * @param hiddenLen
	 * @return
	 */
	public static String commonDisplay(String value, int hiddenLen) {
		if (null == value || "".equals(value)) {
			return value;
		}
		int len = value.length();
		int pamaone = len / 2;
		int pamatwo = pamaone - 1;
		int pamathree = len % 2;
		StringBuilder stringBuilder = new StringBuilder();
		if (len <= 2) {
			if (pamathree == 1) {
				return SYMBOL;
			}
			stringBuilder.append(SYMBOL);
			stringBuilder.append(value.charAt(len - 1));
		} else {
			if (pamatwo <= 0) {
				stringBuilder.append(value.substring(0, 1));
				stringBuilder.append(SYMBOL);
				stringBuilder.append(value.substring(len - 1, len));

			} else if (pamatwo >= hiddenLen / 2 && hiddenLen + 1 != len) {
				int pamafive = (len - hiddenLen) / 2;
				stringBuilder.append(value.substring(0, pamafive));
				for (int i = 0; i < hiddenLen; i++) {
					stringBuilder.append(SYMBOL);
				}
				if ((pamathree == 0 && hiddenLen / 2 == 0) || (pamathree != 0 && hiddenLen % 2 != 0)) {
					stringBuilder.append(value.substring(len - pamafive, len));
				} else {
					stringBuilder.append(value.substring(len - (pamafive + 1), len));
				}
			} else {
				int pamafour = len - 2;
				stringBuilder.append(value.substring(0, 1));
				for (int i = 0; i < pamafour; i++) {
					stringBuilder.append(SYMBOL);
				}
				stringBuilder.append(value.substring(len - 1, len));
			}
		}
		return stringBuilder.toString();
	}
	/**
	 * 脱敏显示
	 * @param value
	 * @return
	 */
	public static String commonDisplay(String value) {
		return commonDisplay(value, SIZE);
	}

	/**
	 * 身份证号读取年龄
	 * @param idCardNo
	 * @return
	 */
	public static  String idCardNoToAge(String idCardNo) {
		String age="未知";
		if(idCardNo == null || "".equals(idCardNo) ){
			return age;
		}

		if (idCardNo.length() != 15 && idCardNo.length() != 18){
			return age;
		}
		Calendar cal = Calendar.getInstance();
		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH)+1;
		int dayNow = cal.get(Calendar.DATE);

		int year = Integer.valueOf(idCardNo.substring(6, 10));
		int month = Integer.valueOf(idCardNo.substring(10,12));
		int day = Integer.valueOf(idCardNo.substring(12,14));

		if ((month < monthNow) || (month == monthNow && day<= dayNow) ){
			age = String.valueOf(yearNow - year);
		}else {
			age = String.valueOf(yearNow - year-1);
		}
		return age;
	}
}
