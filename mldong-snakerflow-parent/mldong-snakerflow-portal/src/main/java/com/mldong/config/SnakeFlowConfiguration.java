package com.mldong.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.snaker.engine.IManagerService;
import org.snaker.engine.IOrderService;
import org.snaker.engine.IProcessService;
import org.snaker.engine.IQueryService;
import org.snaker.engine.ITaskService;
import org.snaker.engine.access.mybatis.MybatisAccess;
import org.snaker.engine.cache.CacheManager;
import org.snaker.engine.cache.memory.MemoryCacheManager;
import org.snaker.engine.core.ManagerService;
import org.snaker.engine.core.OrderService;
import org.snaker.engine.core.ProcessService;
import org.snaker.engine.core.QueryService;
import org.snaker.engine.core.TaskService;
import org.snaker.engine.impl.LogInterceptor;
import org.snaker.engine.spring.SpelExpression;
import org.snaker.engine.spring.SpringSnakerEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(
        basePackages = {"org.snaker.engine"}
)
public class SnakeFlowConfiguration {
    public SnakeFlowConfiguration() {
    }

    @Bean
    MybatisAccess dbAccess(SqlSessionFactory sqlSessionFactory) {
        MybatisAccess mybatisAccess = new MybatisAccess();
        mybatisAccess.setSqlSessionFactory(sqlSessionFactory);
        return mybatisAccess;
    }

    @Bean
    SpringSnakerEngine springSnakerEngine(IProcessService processService, IOrderService orderService, ITaskService taskService, IManagerService managerService, IQueryService queryService) {
        SpringSnakerEngine engine = new SpringSnakerEngine();
        engine.setProcessService(processService);
        engine.setOrderService(orderService);
        engine.setTaskService(taskService);
        engine.setQueryService(queryService);
        engine.setManagerService(managerService);
        return engine;
    }

    @Bean
    IProcessService processService(MybatisAccess dbAccess) {
        ProcessService processService = new ProcessService();
        processService.setAccess(dbAccess);
        return processService;
    }

    @Bean
    IOrderService orderService(MybatisAccess dbAccess) {
        OrderService orderService = new OrderService();
        orderService.setAccess(dbAccess);
        return orderService;
    }

    @Bean
    ITaskService taskService(MybatisAccess dbAccess) {
        TaskService taskService = new TaskService();
        taskService.setAccess(dbAccess);
        return taskService;
    }

    @Bean
    IManagerService managerService(MybatisAccess dbAccess) {
        ManagerService managerService = new ManagerService();
        managerService.setAccess(dbAccess);
        return managerService;
    }

    @Bean
    IQueryService queryService(MybatisAccess dbAccess) {
        QueryService queryService = new QueryService();
        queryService.setAccess(dbAccess);
        return queryService;
    }

    @Bean({"snakerMemoryCacheManager"})
    CacheManager cacheManager() {
        return new MemoryCacheManager();
    }

    @Bean
    LogInterceptor logInterceptor() {
        return new LogInterceptor();
    }

    @Bean
    SpelExpression spelExpression() {
        return new SpelExpression();
    }
}
