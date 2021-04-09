package com.mldong.common.easypoi;

import cn.afterturn.easypoi.handler.inter.IExcelDataHandler;
import com.mldong.common.tool.EasyPoiTool;
import com.mldong.common.tool.StringTool;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Hyperlink;

import java.util.List;
import java.util.Map;

public class ExcelDataHandlerCodedEnumImpl<T> implements IExcelDataHandler<T> {
    private List<ExcelPoiDictService> excelPoiDicts;
    private Class clazz;
    public ExcelDataHandlerCodedEnumImpl(Class<?> clazz) {
		this.clazz = clazz;
    }
    public ExcelDataHandlerCodedEnumImpl(Class<?> clazz, List<ExcelPoiDictService> excelPoiDicts) {
		this.clazz = clazz;
		this.excelPoiDicts = excelPoiDicts;
    }
    @Override
    public Object exportHandler(T obj, String name, Object value) {
        String dictKey = EasyPoiTool.getFieldDict(obj,name);
        if(StringTool.isNotEmpty(dictKey)&&(excelPoiDicts!=null&&!excelPoiDicts.isEmpty())) {
            for (ExcelPoiDictService excelPoiDict: excelPoiDicts) {
                String eName = excelPoiDict.toName(dictKey,value);
                if(eName!=null) {
                    return eName;
                }
            }
        }
        return EasyPoiTool.exportEnumHandler(obj,name,value);
    }
    @Override
    public Object importHandler(T obj, String name, Object value) {
        String dictKey = EasyPoiTool.getFieldDict(obj,name);
        if(StringTool.isNotEmpty(dictKey)&&(excelPoiDicts!=null&&!excelPoiDicts.isEmpty())) {
            for (ExcelPoiDictService excelPoiDict: excelPoiDicts) {
                Object eValue = excelPoiDict.toValue(dictKey,value);
                if(eValue!=null) {
                    return eValue;
                }
            }
        }
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
