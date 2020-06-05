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
	<delete id="deleteBatchByIds" parameterType="java.util.List">
		delete from ${table.tableName}
		where id in
		<#noparse><foreach collection="list" item="item" index="index" open="("
			close=")" separator=","></#noparse>
			<#noparse>#{item}</#noparse>
		<#noparse></foreach></#noparse>
	</delete>
	<update id="removeBatchByIds" parameterType="java.util.List">
		update ${table.tableName}
		<set>
			update_time=now(),
			is_deleted=1
		</set>
		where id in
		<#noparse><foreach collection="list" item="item" index="index" open="("</#noparse>
			close=")" separator=",">
			<#noparse>#{item}</#noparse>
		<#noparse></foreach></#noparse>
	</update>
	<update id="restoreBatchByIds" parameterType="java.util.List">
		update ${table.tableName}
		<set>
			update_time=now(),
			is_deleted=0
		</set>
		where id in
		<#noparse><foreach collection="list" item="item" index="index" open="("
			close=")" separator=","></#noparse>
			<#noparse>#{item}</#noparse>
		<#noparse></foreach></#noparse>
	</update>
</mapper>