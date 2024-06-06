package com.mldong.excel;

import cn.hutool.core.lang.Dict;
import cn.hutool.json.JSONUtil;

/**
 * @author mldong
 * @date 2024/6/3
 */
public interface IExcelColumnHandler {
    /**
     * 处理调整excelExportEntity配置，主要是调整dict的值
     * @param excelExportEntity
     */
    default void handle(MyExcelExportEntity excelExportEntity){
        excelExportEntity.setDict(JSONUtil.toJsonStr(excelExportEntity));
    };

    /**
     * 这里为导出时专用
     * @param excelExportEntity 字典配置项，列处理类参数
     * @param value 数据值
     * @return
     */
    String toName(MyExcelExportEntity excelExportEntity, Object value);

    /**
     * 这里为导入时专用
     * @param excelExportEntity 字典配置项，列处理类参数
     * @param value  数据值
     * @return
     */
    String toValue(MyExcelExportEntity excelExportEntity,Object value);

    /**
     * 获取字典显示名称
     * @return
     */
    default String getMessage() {
        return this.getClass().getSimpleName();
    }

    /**
     * 获取字典排序
     * @return
     */
    default int getOrder() {
        return Integer.MIN_VALUE;
    }

    default Dict getComponentProps(MyExcelExportEntity excelExportEntity) {
        Dict dict = excelExportEntity.getComponentProps();
        String paramsJson = dict.getStr("params");
        if(JSONUtil.isTypeJSON(paramsJson)) {
            // 处理ApiSelect等请求参数labelKey和valueKey
            Dict params = JSONUtil.toBean(paramsJson, Dict.class);
            params.forEach((key,value)->{
                if(!dict.containsKey(key)) {
                    dict.put(key,value);
                }
            });
        }
        return dict;
    }

    /**
     * 是否做缓存
     * @return
     */
    default boolean cache() {
        return true;
    }
}
