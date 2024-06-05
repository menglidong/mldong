package com.mldong.excel;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.mldong.dict.CustomDictService;
import com.mldong.dict.model.DictItemModel;
import com.mldong.dict.model.DictModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * @author mldong
 * @date 2024/6/3
 */
@Component
@RequiredArgsConstructor
public class ExcelCustomDictService implements CustomDictService {
    private final List<IExcelColumnHandler> excelColumnHandlerList;
    DictModel dictModel = null;

    @Override
    public DictModel getDictModel() {
        if(dictModel!=null) return dictModel;
        dictModel = new DictModel();
        dictModel.setName("ExcelCustomDictService|Excel自定义列处理类");
        dictModel.setDictKey("excel_column_handler");
        dictModel.setItems(new ArrayList<>());
        excelColumnHandlerList.sort(Comparator.comparingInt(IExcelColumnHandler::getOrder));
        excelColumnHandlerList.forEach(item->{
            DictItemModel dictItem = new DictItemModel();
            dictItem.setName(item.getMessage());
            dictItem.setDictItemKey(item.getClass().getSimpleName());
            dictItem.setDictItemValue(StrUtil.lowerFirst(item.getClass().getSimpleName()));
            dictModel.getItems().add(dictItem);
        });
        return dictModel;
    }

    /**
     * 获取枚举字典
     * @return
     */

    @Override
    public DictModel getByDictKey(Map<String, Object> args) {
        if ("excel_column_handler".equalsIgnoreCase(Convert.toStr(args.get("dictType")))){
            return getDictModel();
        }
        return null;
    }
}
