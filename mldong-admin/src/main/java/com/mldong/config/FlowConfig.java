package com.mldong.config;

import com.mldong.modules.wf.engine.FlowEngine;
import com.mldong.modules.wf.engine.core.FlowEngineImpl;
import com.mldong.modules.wf.engine.impl.SpringContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 工作流引擎配置
 * @author mldong
 * @date 2023/9/26
 */
@Configuration
public class FlowConfig {
    @Bean
    public FlowEngine flowEngine(SpringContext springContext) {
        FlowEngine engine = new FlowEngineImpl().configure(new com.mldong.modules.wf.engine.cfg.Configuration(springContext));
        return engine;
    }
}
