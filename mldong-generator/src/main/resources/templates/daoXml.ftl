<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${basePackage}.modules.${moduleName}.dao.${table.className}Dao">
	<resultMap id="BaseResultMap"
			   extends="${basePackage}.modules.${moduleName}.mapper.${table.className}Mapper.BaseResultMap"
			   type="${basePackage}.modules.${moduleName}.entity.${table.className}">
	</resultMap>
</mapper>