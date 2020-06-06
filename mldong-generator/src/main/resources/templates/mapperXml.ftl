<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${basePackage}.modules.${moduleName}.mapper.${table.className}Mapper">
	<resultMap id="BaseResultMap" type="${basePackage}.modules.${moduleName}.entity.${table.className}">
		<#list table.columns as column>
		<#if column.primaryKey==true>
		<id column="${column.columnName}" property="${column.javaProperty}" />
		</#if>
	    <#if column.primaryKey!=true>
		<result column="${column.columnName}" property="${column.javaProperty}" />  
	    </#if>
	</#list>
	</resultMap>
</mapper>