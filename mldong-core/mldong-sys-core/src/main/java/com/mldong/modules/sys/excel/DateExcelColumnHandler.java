package com.mldong.modules.sys.excel;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Dict;
import com.mldong.excel.IExcelColumnHandler;
import com.mldong.excel.MyExcelExportEntity;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author mldong
 * @date 2024/6/6
 */
@Component
public class DateExcelColumnHandler implements IExcelColumnHandler {
    @Override
    public String toName(MyExcelExportEntity excelExportEntity, Object value) {
        if(value instanceof Date) {
            Dict props = getComponentProps(excelExportEntity);
            String format = props.get("valueFormat","yyyy-MM-dd HH:mm:ss");
            return DateUtil.format((Date) value,format);
        }
        return value.toString();
    }

    @Override
    public String toValue(MyExcelExportEntity excelExportEntity, Object value) {
        return value.toString();
    }

    @Override
    public String getMessage() {
        return "日期转换类";
    }

    @Override
    public int getOrder() {
        return 15;
    }

    @Override
    public boolean cache() {
        return false;
    }
}
