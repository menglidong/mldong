package com.mldong.excel;

import cn.afterturn.easypoi.handler.inter.IExcelDictHandler;
import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author mldong
 * @date 2024/6/3
 */
@Component
@RequiredArgsConstructor
public class DefaultExcelDictHandler implements IExcelDictHandler {
    private final Map<String, IExcelColumnHandler> excelColumnHandlerMap;
    // 缓存数据，数据只缓存一分钟，相当于同一次导入、导出相同key的缓存
    private final TimedCache<String,String> timedCache = CacheUtil.newTimedCache(DateUnit.MINUTE.getMillis());
    @Override
    public String toName(String dict, Object obj, String name, Object value) {
        if(ObjectUtil.isEmpty(value)) return null;
        if(JSONUtil.isTypeJSON(dict)) {
            MyExcelExportEntity excelExportEntity = JSONUtil.toBean(dict, MyExcelExportEntity.class);
            String handlerType = excelExportEntity.getHandlerType();
            if(StrUtil.isNotEmpty(handlerType)) {
                IExcelColumnHandler handler = excelColumnHandlerMap.get(handlerType);
                if(handler == null) return value.toString();
                String key = "to_name_" + dict + value;
                if(handler.cache()) {
                    String cacheValue = timedCache.get(key);
                    if (cacheValue != null) {
                        return cacheValue;
                    }
                }
                String newValue = handler.toName(excelExportEntity, value);
                if(handler.cache() && newValue!=null) {
                    timedCache.put(key,newValue);
                }
                return newValue;
            }
        }
        return StrUtil.toString(value);
    }

    @Override
    public String toValue(String dict, Object obj, String name, Object value) {
        if(ObjectUtil.isEmpty(value)) return null;
        if(JSONUtil.isTypeJSON(dict)) {
            MyExcelExportEntity excelExportEntity = JSONUtil.toBean(dict, MyExcelExportEntity.class);
            String handlerType = excelExportEntity.getHandlerType();
            if(StrUtil.isNotEmpty(handlerType)) {
                IExcelColumnHandler handler = excelColumnHandlerMap.get(handlerType);
                if(handler == null) return value.toString();
                String key = "to_value_"+dict+value;
                if(handler.cache()) {
                    String cacheValue = timedCache.get(key);
                    if (cacheValue != null) {
                        return cacheValue;
                    }
                }
                String newValue = handler.toValue(excelExportEntity, value);
                if(handler.cache() && newValue!=null) {
                    timedCache.put(key,newValue);
                }
                return newValue;
            }
        }
        return StrUtil.toString(value);
    }
}
