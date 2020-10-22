/**
 * 
 */
package com.mldong.common.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.mldong.common.base.constant.CommonConstants;
import com.mldong.common.logger.LoggerModel;

/**
 * @author mldong
 *
 */
public class RequestHolder {
	private final static ThreadLocal<Long> requestHolderUserId = new ThreadLocal<>();
	private final static ThreadLocal<LoggerModel> requestHolderLoggerModel = new ThreadLocal<>();

	private RequestHolder() {
	}

	public static void setUserId(Long userId) {
		requestHolderUserId.set(userId);
	}
	public static Long getUserId() {
		return requestHolderUserId.get();
	}
	public static void removeUserId() {
		requestHolderUserId.remove();
	}

	public static void setLoggerModel(LoggerModel loggerModel) {
		requestHolderLoggerModel.set(loggerModel);
	}
	public static LoggerModel getLoggerModel() {
		return requestHolderLoggerModel.get();
	}
	public static void removeLoggerModel() {
		requestHolderLoggerModel.remove();
	}
	/**
	 * 获取ip地址
	 * @return
	 */
    public static String getIPAddress() {
    	HttpServletRequest  request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String ip = null;
        String ipAddresses = request.getHeader("X-Forwarded-For");
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            ipAddresses = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            ipAddresses = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            ipAddresses = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            ipAddresses = request.getHeader("X-Real-IP");
        }
        if (ipAddresses != null && ipAddresses.length() != 0) {
            ip = ipAddresses.split(",")[0];
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
    /**
     * 获取当前token
     * @return
     */
    public static String getToken() {
    	HttpServletRequest  request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String token = "";
		token = request.getHeader(CommonConstants.TOKEN);
		if(StringUtils.isEmpty(token)) {
			token = request.getParameter(CommonConstants.TOKEN);
		}
		return token;
	}
}
