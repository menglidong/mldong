package com.mldong.service;

import com.mldong.common.base.CommonPage;

import java.util.Map;

/**
 * 数据服务
 */
public interface DataService {
    /**
     * 是否存在数据库表
     * @param tableName
     * @return
     */
    public boolean isExsit(String tableName);

    /**
     * 获取单条记录
     * @param tableName
     * @param pkPropertyName 主键属性
     * @param  pkPropertyValue 属性值
     * @return
     */
    public Map<String,Object> get(String tableName, String pkPropertyName, Object pkPropertyValue);
    /**
     * 获取单条记录
     * @param  dbName
     * @param tableName
     * @param pkPropertyName 主键属性
     * @param  pkPropertyValue 属性值
     * @return
     */
    public Map<String,Object> get(String dbName, String tableName, String pkPropertyName, Object pkPropertyValue);

    /**
     * 分页查询
     * @param tableName
     * @param map
     * @return
     */
    public CommonPage<Map<String,Object>> list(String tableName,Map<String,Object> map, Integer pageNum,Integer pageSize);
    /**
     * 分页查询
     * @param  dbName
     * @param tableName
     * @param map
     * @return
     */
    public CommonPage<Map<String,Object>> list(String dbName, String tableName,Map<String,Object> map, Integer pageNum,Integer pageSize);
}
