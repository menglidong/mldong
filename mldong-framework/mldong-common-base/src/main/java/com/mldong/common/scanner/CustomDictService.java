package com.mldong.common.scanner;

import com.mldong.common.scanner.model.DictModel;

/**
 * 自定义字典服务，由业务模块实现
 */
public interface CustomDictService {
    /**
     * 通过字典唯一编码查询
     * @param dictKey
     * @return
     */
    public DictModel getByDictKey(String dictKey);
}
