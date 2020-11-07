package com.mldong.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.mldong.common.ds.DsDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 临时做的多数据源配置
 */
@Configuration
public class DsConfig {
    @Bean
    @ConfigurationProperties("ds.stmsdb")
    @ConditionalOnProperty(prefix = "ds.stmsdb", name = "open", havingValue = "true", matchIfMissing = false)
    public DataSource stmsdb(){
        DataSource dataSource = DruidDataSourceBuilder.create().build();
        return  dataSource;
    }
    @Bean
    @ConditionalOnBean(name = "stmsdb")
    public DsDataSource targetDataSource(@Qualifier("stmsdb") DataSource dataSource){
        return new DsDataSource() {
            @Override
            public DataSource getDataSource() {
                return dataSource;
            }

            @Override
            public String getDataSourceType() {
                return "stmsdb";
            }
        };
    }

}
