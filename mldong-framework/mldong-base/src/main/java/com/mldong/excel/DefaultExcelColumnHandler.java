package com.mldong.excel;

import org.springframework.stereotype.Component;

/**
 * @author mldong
 * @date 2024/6/3
 */
@Component
public class DefaultExcelColumnHandler implements IExcelColumnHandler{
    @Override
    public void handle(MyExcelExportEntity excelExportEntity) {

    }

    @Override
    public String toName(MyExcelExportEntity excelExportEntity, Object value) {
        return value == null?null:value.toString();
    }

    @Override
    public String toValue(MyExcelExportEntity excelExportEntity, Object value) {
        return value == null?null:value.toString();
    }

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public String getMessage() {
        return "默认处理类";
    }

    @Override
    public boolean cache() {
        return false;
    }
}
