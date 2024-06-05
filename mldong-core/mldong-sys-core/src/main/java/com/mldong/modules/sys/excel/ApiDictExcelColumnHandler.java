package com.mldong.modules.sys.excel;

import cn.hutool.core.lang.Dict;
import com.mldong.consts.CommonConstant;
import com.mldong.excel.IExcelColumnHandler;
import com.mldong.excel.MyExcelExportEntity;
import com.mldong.modules.sys.service.DictService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author mldong
 * @date 2024/6/3
 */
@Component
@RequiredArgsConstructor
public class ApiDictExcelColumnHandler implements IExcelColumnHandler {
    private final DictService dictService;
    @Override
    public String toName(MyExcelExportEntity excelExportEntity, Object value) {
        Dict props = getComponentProps(excelExportEntity);
        String code = props.getStr("code");
        List<Dict> dictList = dictService.getByDictType(code);
        Dict dictObj = dictList.stream().filter(dict -> {
            return dict.getStr(CommonConstant.VALUE).equals(value);
        }).findFirst().orElse(Dict.create());
        return dictObj.getStr(CommonConstant.LABEL);
    }

    @Override
    public String toValue(MyExcelExportEntity excelExportEntity, Object value) {
        Dict props = getComponentProps(excelExportEntity);
        String code = props.getStr("code");
        List<Dict> dictList = dictService.getByDictType(code);
        Dict dictObj = dictList.stream().filter(dict -> {
            return dict.getStr(CommonConstant.LABEL).equals(value);
        }).findFirst().orElse(Dict.create());
        return dictObj.getStr(CommonConstant.VALUE);
    }

    @Override
    public int getOrder() {
        return 10;
    }

    @Override
    public String getMessage() {
        return "字典处理类";
    }
}
