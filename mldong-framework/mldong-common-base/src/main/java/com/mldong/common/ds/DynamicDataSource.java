package com.mldong.common.ds;

import com.mldong.common.tool.StringTool;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;

public class DynamicDataSource extends AbstractRoutingDataSource {
    private Map<Object, Object> dynamicTargetDataSources;
    private Object dynamicDefaultTargetDataSource;
    @Override
    protected Object determineCurrentLookupKey() {
        String type = DynamicDataSourceContextHolder.getDataSourceType();
        return StringTool.isEmpty(type) ? "dataSource" : type;
    }
    public DynamicDataSource(DataSource defaultTargetDataSource, Map<Object, Object> targetDataSources) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }
    // 创建数据源
    public boolean createDataSource(String key, String driveClass, String url, String username, String password) {
        // TODO 这里创建数据源，添加到this.dynamicTargetDataSources
        // 设置数据源
        setTargetDataSources(this.dynamicTargetDataSources);
        // 调用这里，新创建的数据源才生效
        super.afterPropertiesSet();
        return true;
    }
    // 删除数据源
    public boolean delDatasources(String datasourceid) {
        // TODO
        // 调用这里，新创建的数据源才生效
        super.afterPropertiesSet();
        return true;
    }
    @Override
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        super.setTargetDataSources(targetDataSources);
        this.dynamicTargetDataSources = targetDataSources;
    }

    @Override
    public void setDefaultTargetDataSource(Object defaultTargetDataSource) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        this.dynamicDefaultTargetDataSource = defaultTargetDataSource;
    }

}
