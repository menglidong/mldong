package com.mldong.dict;


import com.mldong.dict.model.DictModel;

import java.util.Map;

/**
 * 自定义字典服务，由业务模块实现
 */
public interface CustomDictService {
    /**
     * @param args
     * @return
     */
    DictModel getByDictKey(Map<String, Object> args);

    /**
     * 获取当前模型
     * @return
     */
    DictModel getDictModel();
}
