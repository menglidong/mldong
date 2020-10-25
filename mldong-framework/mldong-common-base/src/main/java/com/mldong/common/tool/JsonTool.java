package com.mldong.common.tool;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
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

	/**
	 * 解析当个key-字符串
	 * @param body
	 * @param field
	 * @return
	 */
	public static String parseString(String body, String field) {
		JsonNode node = null;
		try {
			node = objectMapper.readTree(body);
			JsonNode leaf = node.get(field);
			if(leaf != null)
				return leaf.asText();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 解析当个key-整型
	 * @param body
	 * @param field
	 * @return
	 */
	public static Integer parseInteger(String body, String field) {
		JsonNode node = null;
		try {
			node = objectMapper.readTree(body);
			JsonNode leaf = node.get(field);
			if(leaf != null)
				return leaf.asInt();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 解析当个key-列表
	 * @param body
	 * @param field
	 * @return
	 */
	public static List<Integer> parseIntegerList(String body, String field) {
		JsonNode node = null;
		try {
			node = objectMapper.readTree(body);
			JsonNode leaf = node.get(field);

			if(leaf != null)
				return objectMapper.convertValue(leaf, new TypeReference<List<Integer>>(){});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}



	public static Boolean parseBoolean(String body, String field) {
		JsonNode node = null;
		try {
			node = objectMapper.readTree(body);
			JsonNode leaf = node.get(field);
			if(leaf != null)
				return leaf.asBoolean();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Short parseShort(String body, String field) {
		JsonNode node = null;
		try {
			node = objectMapper.readTree(body);
			JsonNode leaf = node.get(field);
			if(leaf != null) {
				Integer value = leaf.asInt();
				return value.shortValue();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static <T> T parseObject(String body, String field, Class<T> clazz) {
		JsonNode node = null;
		try {
			node = objectMapper.readTree(body);
			node = node.get(field);
			return objectMapper.treeToValue(node, clazz);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
