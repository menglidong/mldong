package com.mldong.modules.sys.dao;

import com.mldong.modules.sys.entity.SysConfig;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface SysConfigDao {
    /**
     * 通过key查询
     * @param configKey
     * @return
     */
    @Select("select * from sys_config c where c.config_key = #{configKey} and c.is_deleted = 1 limit 1")
    public SysConfig selectByConfigKey(@Param("configKey") String configKey);
}
