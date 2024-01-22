package com.mldong.modules.dev.service;

import com.mldong.base.CommonPage;
import com.mldong.modules.dev.dto.SchemaGroupPageParam;
import com.mldong.modules.dev.dto.SchemaGroupParam;
import com.mldong.modules.dev.vo.SchemaGroupVO;
import com.mldong.modules.dev.entity.SchemaGroup;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * <p>
 * 模型分组 服务类
 * </p>
 *
 * @author mldong
 * @since 2024-01-17
 */
public interface SchemaGroupService extends IService<SchemaGroup> {
    /**
    * 添加模型分组
    * @param param
    * @return
    */
    boolean save(SchemaGroupParam param);

    /**
    * 更新模型分组
    * @param param
    * @return
    */
    boolean update(SchemaGroupParam param);

    /**
    * 自定义分页查询模型分组
    * @param param
    * @return
    */
    CommonPage<SchemaGroupVO> page(SchemaGroupPageParam param);
    /**
    * 通过id查询
    * @param id
    * @return
    */
    SchemaGroupVO findById(Long id);
}
