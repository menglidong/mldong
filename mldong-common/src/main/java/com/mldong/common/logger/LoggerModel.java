package com.mldong.common.logger;

import java.io.Serializable;

/**
 * 日志实体类
 * @author mldong
 *
 */
public class LoggerModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4296799309713867875L;
	/**
	 * 请求唯一标识，可以自定义规则生成
	 */
	private String trackId;
	/**
	 * 请求路径
	 */
	private String uri;
	/**
	 * 请求url上的参数
	 */
	private String queryString;
	/**
	 * 请求方式，GET/POST等
	 */
	private String method;
	/**
	 * 操作说明
	 */
	private String description;
	/**
	 * 请求ip，客户端ip地址
	 */
	private String ip;
	/**
	 * 请求体，请求正文的内容
	 */
	private String body;
	/**
	 * 请求token，登录用户token，登录状态下存在
	 */
	private String token;
	/**
	 * 请求用户id，登录用户id，登录状态下存在
	 */
	private Long userId;
	/**
	 * 返回结果，请求的结果
	 */
	private String returnData;
	private long startTime;
	private long endTime;
	public String getTrackId() {
		return trackId;
	}
	public void setTrackId(String trackId) {
		this.trackId = trackId;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getQueryString() {
		return queryString;
	}
	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getReturnData() {
		return returnData;
	}
	public void setReturnData(String returnData) {
		this.returnData = returnData;
	}
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	public long getEndTime() {
		return endTime;
	}
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	
}
