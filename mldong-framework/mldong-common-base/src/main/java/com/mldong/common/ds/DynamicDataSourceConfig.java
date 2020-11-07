package com.mldong.common.ds;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Configuration
public class DynamicDataSourceConfig {
    @Autowired(required = false)
    private List<DsDataSource> dsDataSources;
    @Bean
    @ConfigurationProperties("spring.datasource.druid")
    @Primary
    public DataSource defaultDataSource(){
        DataSource dataSource = DruidDataSourceBuilder.create().build();
        return dataSource;
    }
    @Bean
    @Primary
    public JdbcTemplate jdbcTemplate(DynamicDataSource dynamicDataSource) {
        return new JdbcTemplate(dynamicDataSource);
    }
    @Bean
    public DynamicDataSource dynamicDataSource(DataSource defaultDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>(5);
        targetDataSources.put("dataSource", defaultDataSource);
        DynamicDataSourceContextHolder.dataSourceIds.add("dataSource");
        if(dsDataSources != null) {
            dsDataSources.forEach(item -> {
                targetDataSources.put(item.getDataSourceType(), item.getDataSource());
                DynamicDataSourceContextHolder.dataSourceIds.add(item.getDataSourceType());
            });
        }
        return new DynamicDataSource(defaultDataSource, targetDataSources);
    }
}
