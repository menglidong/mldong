package com.mldong.common.access;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.AuthorizationScope;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import com.mldong.common.access.model.SysAccessModel;
import com.mldong.common.tool.StringTool;
/**
 * 控制类权限处理器(主要将注解转成权限资源模型)
 * @author mldong
 *
 */
@Component
public class AccessInitProcessor implements BeanPostProcessor{
	/**
	 * 权限集合
	 */
	private List<SysAccessModel> accessList = new ArrayList<>();
	/**
	 * 模块权限
	 */
	private Map<String,SysAccessModel> moduleMap = new HashMap<>();
	@Override
	public Object postProcessBeforeInitialization(Object bean,
			String beanName) throws BeansException {
		return bean;
	}
	@Override
	public Object postProcessAfterInitialization(Object bean,
			String beanName) throws BeansException {
		if(beanName.endsWith("Controller")) {
			Api api = bean.getClass().getAnnotation(Api.class);
			if(api==null) {
				return bean;
			}
			Authorization[] authorizations = api.authorizations();
			if(authorizations.length>0) {
				Authorization authorization = authorizations[0];
				// 处理模块
				SysAccessModel moduleAccess = moduleMap.get(authorization.value());
				if(null == moduleAccess) {
					moduleAccess = handleAuthorization(authorization);
					moduleAccess.setChildren(new ArrayList<>());
					if(StringTool.isNotEmpty(moduleAccess.getAccess())) {
						accessList.add(moduleAccess);
						moduleMap.put(authorization.value(), moduleAccess);
					}
				}
				// 处理控制类
				AuthorizationScope[] authorizationScopes = authorization.scopes();
				if(authorizations.length>0) {
					AuthorizationScope authorizationScope = authorizationScopes[0];
					SysAccessModel controllerAccess = handleAuthorizationScope(authorizationScope);
					controllerAccess.setChildren(new ArrayList<>());
					if(StringTool.isNotEmpty(controllerAccess.getAccess())) {
						moduleAccess.getChildren().add(controllerAccess);
					}
					// 处理方法
					Method[] methods = bean.getClass().getMethods();
					for (Method method : methods) {
						ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
						if(apiOperation==null) {
							continue;
						}
						authorizations = apiOperation.authorizations();
						if(authorizations.length>0) {
							authorization = authorizations[0];
							authorizationScopes = authorization.scopes();
							if(authorizations.length>0) {
								authorizationScope = authorizationScopes[0];
								SysAccessModel methodAccess = handleAuthorizationScope(authorizationScope);
								if(StringTool.isNotEmpty(methodAccess.getAccess())) {
									controllerAccess.getChildren().add(methodAccess);
								}
							}
						}
					}
				}
			}
		}
		return bean;
	}
	/**
	 * 处理权限模块
	 * @param authorization
	 */
	private SysAccessModel handleAuthorization(Authorization authorization) {
		SysAccessModel moduleAccess = new SysAccessModel();
		String value = authorization.value();
		String arr [] = value.split("\\|");
		if(arr.length==2) {
			moduleAccess.setId(arr[0]);
			moduleAccess.setAccess(arr[0]);
			moduleAccess.setName(arr[1]);
			moduleAccess.setRemark(arr[1]);
		} else {
			moduleAccess.setId(value);
			moduleAccess.setAccess(value);
			moduleAccess.setName(value);
			moduleAccess.setRemark(value);
		}
		moduleAccess.setUri("/"+moduleAccess.getAccess().replaceAll(":", "/"));
		return moduleAccess;
	}
	/**
	 * 处理控制类
	 * @param authorizationScope
	 */
	private SysAccessModel handleAuthorizationScope(AuthorizationScope authorizationScope) {
		SysAccessModel access = new SysAccessModel();
		String scope = authorizationScope.scope();
		String descripion = authorizationScope.description();
		access.setId(scope);
		access.setAccess(scope);
		access.setUri("/"+scope.replaceAll(":", "/"));
		access.setName(descripion);
		access.setRemark(descripion);
		return access;
	}
	public List<SysAccessModel> getAccessList() {
		return accessList;
	}
	/**
	 * 获取apiOperation获取权限标识
	 * @param apiOperation
	 * @return
	 */
	public static String getAccess(ApiOperation apiOperation) {
		if(apiOperation==null) {
			return null;
		}
		Authorization[] authorizations = apiOperation.authorizations();
		if(authorizations.length == 0) {
			return null;
		}
		AuthorizationScope[] authorizationScopes = authorizations[0].scopes();
		if(authorizationScopes.length == 0) {
			return null;
		}
		return authorizationScopes[0].scope();
	}
}
