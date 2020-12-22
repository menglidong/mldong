package com.mldong.common.dauth;

import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import javax.annotation.PostConstruct;
import java.util.List;

@Configuration
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE + 10)
@AutoConfigureBefore(PageHelperAutoConfiguration.class)
public class DataScopeConfig {
    @Autowired
    private List<SqlSessionFactory> sqlSessionFactoryList;

    @PostConstruct
    public void addDataAuthInterceptor() {
        DataScopeInterceptor interceptor = new DataScopeInterceptor();
        for (SqlSessionFactory sqlSessionFactory : sqlSessionFactoryList) {
            // 添加自定义属性
            // Properties properties = new Properties();
            // properties.setProperty("key1", "value1");
            // interceptor.setProperties(properties);
            sqlSessionFactory.getConfiguration().addInterceptor(interceptor);

        }
    }
}
