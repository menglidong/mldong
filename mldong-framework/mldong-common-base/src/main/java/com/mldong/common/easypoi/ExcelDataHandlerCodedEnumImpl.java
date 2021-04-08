package com.mldong.common.easypoi;

import cn.afterturn.easypoi.handler.inter.IExcelDataHandler;
import com.mldong.common.tool.EasyPoiTool;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Hyperlink;

import java.util.Map;

public class ExcelDataHandlerCodedEnumImpl<T> implements IExcelDataHandler<T> {

    private Class clazz;
    public ExcelDataHandlerCodedEnumImpl(Class<?> clazz) {
		this.clazz = clazz;
    }
    @Override
    public Object exportHandler(T obj, String name, Object value) {
        return EasyPoiTool.exportEnumHandler(obj,name,value);
    }
    @Override
    public Object importHandler(T obj, String name, Object value) {
        return EasyPoiTool.importEnumHandler(obj,name,value);
    }

    @Override
    public void setNeedHandlerFields(String[] strings) {

    }

    @Override
    public void setMapValue(Map<String, Object> map, String s, Object o) {

    }

    @Override
    public Hyperlink getHyperlink(CreationHelper creationHelper, T t, String s, Object o) {
        return null;
    }

    @Override
    public String[] getNeedHandlerFields() {
        return EasyPoiTool.getEnumFields(this.clazz);
    }
}
