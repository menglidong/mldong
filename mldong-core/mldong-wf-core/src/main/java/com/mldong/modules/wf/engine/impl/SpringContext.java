package com.mldong.modules.wf.engine.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.mldong.modules.wf.engine.Context;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * spring的服务查找实现
 * @author mldong
 * @date 2023/5/31
 */
@Component
public class SpringContext implements Context, ApplicationContextAware {
    private ApplicationContext applicationContext;
    DefaultListableBeanFactory beanFactory;

    @Override
    public void put(String name, Object object) {
        ConfigurableApplicationContext context = (ConfigurableApplicationContext)applicationContext;
        context.getBeanFactory().registerSingleton(name, object);
    }

    @Override
    public void put(String name, Class<?> clazz) {
        BeanDefinition definition = new RootBeanDefinition(clazz);
        if(beanFactory==null) {
            beanFactory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
        }
        beanFactory.registerBeanDefinition(name, definition);
    }

    @Override
    public boolean exist(String name) {
        try{
            return ObjectUtil.isNotNull(SpringUtil.getBean(name));
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public <T> T find(Class<T> clazz) {
        return SpringUtil.getBean(clazz);
    }

    @Override
    public <T> List<T> findList(Class<T> clazz) {
        List<T> res = new ArrayList<>();
        Map<String,T> map = SpringUtil.getBeansOfType(clazz);
        map.forEach((k,v)->{
            res.add(v);
        });
        return res;
    }

    @Override
    public <T> T findByName(String name, Class<T> clazz) {
        return SpringUtil.getBean(name, clazz);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
