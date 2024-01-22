package com.mldong.modules.dev.service;

import com.mldong.base.CommonPage;
import com.mldong.modules.dev.dto.SchemaFieldPageParam;
import com.mldong.modules.dev.dto.SchemaFieldParam;
import com.mldong.modules.dev.dto.UpdateSortParam;
import com.mldong.modules.dev.vo.SchemaFieldVO;
import com.mldong.modules.dev.entity.SchemaField;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * <p>
 * 模型字段 服务类
 * </p>
 *
 * @author mldong
 * @since 2024-01-17
 */
public interface SchemaFieldService extends IService<SchemaField> {
    /**
    * 添加模型字段
    * @param param
    * @return
    */
    boolean save(SchemaFieldParam param);

    /**
    * 更新模型字段
    * @param param
    * @return
    */
    boolean update(SchemaFieldParam param);

    /**
    * 自定义分页查询模型字段
    * @param param
    * @return
    */
    CommonPage<SchemaFieldVO> page(SchemaFieldPageParam param);
    /**
    * 通过id查询
    * @param id
    * @return
    */
    SchemaFieldVO findById(Long id);
    /**
     * 更新排序
     * @param param
     * @return
     */
    boolean updateSort(UpdateSortParam param);
}
