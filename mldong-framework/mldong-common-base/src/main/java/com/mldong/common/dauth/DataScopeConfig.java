package com.mldong.common.dauth;

import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;

import javax.annotation.PostConstruct;
import java.util.List;

// 后添加 先执行
@AutoConfigureAfter(PageHelperAutoConfiguration.class)
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
