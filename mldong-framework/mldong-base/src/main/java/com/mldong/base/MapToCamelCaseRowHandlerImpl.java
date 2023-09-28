package com.mldong.base;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author mldong
 * @date 2023/7/11
 */
public class MapToCamelCaseRowHandlerImpl implements IRowHandler<Map<String,Object>> {
    @Override
    public void handle(Map<String, Object> map) {
        List<String> removeKeys = new ArrayList<>();
        Map<String,Object> camelCaseMap = new HashMap<>();
        map.forEach((k,v)->{
            String camelCase = StrUtil.toCamelCase(k);
            camelCaseMap.put(camelCase,v);
            if(ObjectUtil.notEqual(camelCase, k)) {
                removeKeys.add(k);
            }
        });
        removeKeys.forEach(key->{
            map.remove(key);
        });
        map.putAll(camelCaseMap);
    }
}
