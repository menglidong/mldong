package com.mldong.modules.dev.service;

import com.mldong.base.CommonPage;
import com.mldong.modules.dev.dto.SchemaPageParam;
import com.mldong.modules.dev.dto.SchemaParam;
import com.mldong.modules.dev.vo.SchemaVO;
import com.mldong.modules.dev.entity.Schema;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * <p>
 * 数据模型 服务类
 * </p>
 *
 * @author mldong
 * @since 2024-01-17
 */
public interface SchemaService extends IService<Schema> {
    /**
    * 添加数据模型
    * @param param
    * @return
    */
    boolean save(SchemaParam param);

    /**
    * 更新数据模型
    * @param param
    * @return
    */
    boolean update(SchemaParam param);

    /**
    * 自定义分页查询数据模型
    * @param param
    * @return
    */
    CommonPage<SchemaVO> page(SchemaPageParam param);
    /**
    * 通过id查询
    * @param id
    * @return
    */
    SchemaVO findById(Long id);
}
