package com.mldong.modules.wf.engine.impl;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import com.mldong.modules.wf.engine.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * 简单的上下文发现实现类
 * @author mldong
 * @date 2023/4/26
 */
public class SimpleContext implements Context {
    private Dict dict = Dict.create();
    @Override
    public void put(String name, Object object) {
        dict.put(name, object);
    }

    @Override
    public void put(String name, Class<?> clazz) {
        dict.put(name, ReflectUtil.newInstance(clazz));
    }

    @Override
    public boolean exist(String name) {
        return ObjectUtil.isNotNull(dict.getObj(name));
    }

    @Override
    public <T> T find(Class<T> clazz) {
        for (Map.Entry<String, Object> entry : dict.entrySet()) {
            if (clazz.isInstance(entry.getValue())) {
                return clazz.cast(entry.getValue());
            }
        }
        return null;
    }

    @Override
    public <T> List<T> findList(Class<T> clazz) {
        List<T> res = new ArrayList<>();
        for (Map.Entry<String, Object> entry : dict.entrySet()) {
            if (clazz.isInstance(entry.getValue())) {
                res.add(clazz.cast(entry.getValue()));
            }
        }
        return res;
    }

    @Override
    public <T> T findByName(String name, Class<T> clazz) {
        for (Map.Entry<String, Object> entry : dict.entrySet()) {
            if (entry.getKey().equals(name) && clazz.isInstance(entry.getValue())) {
                return clazz.cast(entry.getValue());
            }
        }
        return null;
    }
}
