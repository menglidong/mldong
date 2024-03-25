package com.mldong.modules.dev.provider;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Dict;
import com.mldong.modules.dev.api.SchemaApi;
import com.mldong.modules.dev.entity.Schema;
import com.mldong.modules.dev.service.SchemaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author mldong
 * @date 2024/3/25
 */
@Component
@RequiredArgsConstructor
public class SchemaApiProvider implements SchemaApi {
    private final SchemaService schemaService;
    @Override
    public Dict getSchema(Long id) {
        Schema schema = schemaService.getById(id);
        if(schema!=null) {
            return BeanUtil.toBean(schemaService.getByTableName(schema.getTableName()),Dict.class);
        }
        return Dict.create();
    }

    @Override
    public Dict getSchema(String tableName) {
        return BeanUtil.toBean(schemaService.getByTableName(tableName),Dict.class);
    }
}
