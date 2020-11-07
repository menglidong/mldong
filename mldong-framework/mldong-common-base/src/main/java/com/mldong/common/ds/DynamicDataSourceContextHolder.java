package com.mldong.common.ds;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据源切换工具
 */
public class DynamicDataSourceContextHolder {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
    public static List<String> dataSourceIds = new ArrayList<>();

    private DynamicDataSourceContextHolder() {}
    public static void setDataSourceType(String dataSourceType) {
        contextHolder.set(dataSourceType);
    }

    /**
     * 获取当前数据源
     * @return
     */
    public static String getDataSourceType() {
        return contextHolder.get();
    }

    /**
     * 设置当前数据源
     */
    public static void clearDataSourceType() {
        contextHolder.remove();
    }

    /**
     * 数据源是否存在
     * @param dataSourceId
     * @return
     */
    public static boolean containsDataSource(String dataSourceId){
        return dataSourceIds.contains(dataSourceId);
    }
}
