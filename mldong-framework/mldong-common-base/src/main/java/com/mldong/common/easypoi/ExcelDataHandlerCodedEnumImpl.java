package com.mldong.common.easypoi;

import cn.afterturn.easypoi.handler.impl.ExcelDataHandlerDefaultImpl;
import com.mldong.common.tool.EasyPoiTool;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ExcelDataHandlerCodedEnumImpl<T> extends ExcelDataHandlerDefaultImpl<T> {
    @Override
    public Object exportHandler(T obj, String name, Object value) {
        return EasyPoiTool.exportEnumHandler(obj,name,value);
    }
    @Override
    public Object importHandler(T obj, String name, Object value) {
        return EasyPoiTool.importEnumHandler(obj,name,value);
    }

    @Override
    public String[] getNeedHandlerFields() {
        Type type = getClass().getGenericSuperclass();
        Type[] parameter = ((ParameterizedType) type).getActualTypeArguments();
        return EasyPoiTool.getEnumFields((Class<?>)parameter[0]);
    }
}
