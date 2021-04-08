package com.mldong.common.easypoi;

public interface ExcelPoiDict {
    String toName(String dictKey,Object value);
    Object toValue(String dictKey,Object name);
}
