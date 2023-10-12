package com.mldong.modules.sys.api;

import cn.hutool.core.lang.Dict;

import java.util.List;

/**
 * 字典接口
 * @author mldong
 * @date 2023/10/12
 */
public interface DictApi {
    /**
     * 通过dictType获取字典
     * @param dictType
     * @return
     */
    List<Dict> getByDictType(String dictType);
}
