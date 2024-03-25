package com.mldong.modules.dev.api;

import cn.hutool.core.lang.Dict;

/**
 * @author mldong
 * @date 2024/3/25
 */
public interface SchemaApi {
    /**
     * 获取元数据
     * @param id
     * @return
     */
    Dict getSchema(Long id);

    /**
     * 获取元数据
     * @param tableName
     * @return
     */
    Dict getSchema(String tableName);
}
