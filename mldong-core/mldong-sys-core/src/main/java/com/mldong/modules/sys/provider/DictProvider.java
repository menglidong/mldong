package com.mldong.modules.sys.provider;

import cn.hutool.core.lang.Dict;
import com.mldong.modules.sys.api.DictApi;
import com.mldong.modules.sys.service.DictService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author mldong
 * @date 2023/10/12
 */
@Component
@RequiredArgsConstructor
public class DictProvider implements DictApi {
    private final DictService dictService;
    @Override
    public List<Dict> getByDictType(String dictType) {
        return dictService.getByDictType(dictType);
    }
}
