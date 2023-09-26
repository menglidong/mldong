package com.mldong.modules.wf.engine.core;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ReflectUtil;
import com.mldong.modules.wf.engine.Context;

import java.util.List;

/**
 *
 * 单例服务上下文
 * @author mldong
 * @date 2022/6/12
 */
public class ServiceContext {
    private static Context context;


    public static void setContext(Context context) {
        ServiceContext.context = context;
    }

    public static void put(String name, Object object) {
        Assert.notNull(context,"未注册服务上下文");
        context.put(name, object);
    }

    public static void put(String name, Class<?> clazz) {
        Assert.notNull(context,"未注册服务上下文");
        context.put(name, ReflectUtil.newInstance(clazz));
    }

    public static boolean exist(String name) {
        Assert.notNull(context,"未注册服务上下文");
        return context.exist(name);
    }

    public static <T> T find(Class<T> clazz) {
        Assert.notNull(context,"未注册服务上下文");
        return context.find(clazz);
    }

    public static <T> List<T> findList(Class<T> clazz) {
        Assert.notNull(context,"未注册服务上下文");
        return context.findList(clazz);
    }

    public static <T> T findByName(String name, Class<T> clazz) {
        Assert.notNull(context,"未注册服务上下文");
        return context.findByName(name, clazz);
    }
}
