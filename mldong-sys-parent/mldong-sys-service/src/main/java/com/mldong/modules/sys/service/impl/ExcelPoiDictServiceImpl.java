package com.mldong.modules.sys.service.impl;

import com.mldong.common.easypoi.ExcelPoiDictService;
import com.mldong.common.scanner.model.DictItemModel;
import com.mldong.common.scanner.model.DictModel;
import com.mldong.modules.sys.dto.SysDictKeyParam;
import com.mldong.modules.sys.service.SysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExcelPoiDictServiceImpl implements ExcelPoiDictService {
    @Autowired
    private SysDictService sysDictService;
    @Override
    public String toName(String dictKey, Object value) {
        SysDictKeyParam param = new SysDictKeyParam();
        param.setDictKey(dictKey);
        DictModel dictModel = sysDictService.getByDictKey(param);
        if(dictModel!=null) {
            List<DictItemModel> dictItemModels = dictModel.getItems();
            for(int i=0,len=dictItemModels.size();i<len;i++) {
                DictItemModel itemModel = dictItemModels.get(i);
                if(value!=null &&(value.toString().equals(itemModel.getDictItemValue().toString())||value.toString().equals(itemModel.getDictItemKey()))) {
                    return itemModel.getName();
                }
            }
        }
        return null;
    }

    @Override
    public Object toValue(String dictKey, Object name) {
        SysDictKeyParam param = new SysDictKeyParam();
        param.setDictKey(dictKey);
        DictModel dictModel = sysDictService.getByDictKey(param);
        if(dictModel!=null) {
            List<DictItemModel> dictItemModels = dictModel.getItems();
            for(int i=0,len=dictItemModels.size();i<len;i++) {
                DictItemModel itemModel = dictItemModels.get(i);
                if(name!=null&&(name.toString().equals(itemModel.getName().toString()))) {
                    return itemModel.getDictItemValue();
                }
            }
        }
        return null;
    }
}
