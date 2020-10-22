package com.mldong.common.tool;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * json处理工具
 * @author mldong
 *
 */
public class JsonTool {
	private JsonTool(){}
	private static ObjectMapper objectMapper = new ObjectMapper();
	/**
	 * 将对象转成json
	 * @param obj
	 * @return
	 */
	public static String toJson(Object obj){
		if(obj == null){
			return "";
		}
		try {
			return objectMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "";
		}
	}
	/**
	 * 将json转成对象
	 * @param s
	 * @param clazz
	 * @return
	 */
	public static <T> T jsonToBean(String s,Class<T> clazz){
		try {
			T t = objectMapper.readValue(s, clazz);
			return t;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}
	/**
	 * json 转 map
	 * @param s
	 * @return
	 */
	public static Map<String,Object> jsonToMap(String s){
		try {
			Map<String,Object> map = objectMapper.readValue(s, new TypeReference<Map<String,Object>>() {});
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return new HashMap<String, Object>();
		} 
	}
	/**
	 * json转bean
	 * @param s
	 * @param type
	 * @return
	 */
	public static <T> T jsonToBean(String s,TypeReference<T> type){
		try {
			return objectMapper.readValue(s,type);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}
}
