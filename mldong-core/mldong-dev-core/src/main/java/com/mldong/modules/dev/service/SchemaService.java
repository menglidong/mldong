package com.mldong.modules.dev.service;

import cn.hutool.core.lang.Dict;
import com.mldong.base.CommonPage;
import com.mldong.base.LabelValueVO;
import com.mldong.modules.dev.dto.SchemaPageParam;
import com.mldong.modules.dev.dto.SchemaParam;
import com.mldong.modules.dev.vo.SchemaVO;
import com.mldong.modules.dev.entity.Schema;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

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

    /**
     * 数据模型树
     * @param keywords
     * @return
     */
    List<Dict> dbTable(String keywords);

    /**
     * 导入表
     * @param schemaGroupId
     * @param tableNames
     */
    void importTable(Long schemaGroupId,List<String> tableNames);
    /**
     * 获取表元数据
     * @param tableName
     */
    SchemaVO getByTableName(String tableName);
}
