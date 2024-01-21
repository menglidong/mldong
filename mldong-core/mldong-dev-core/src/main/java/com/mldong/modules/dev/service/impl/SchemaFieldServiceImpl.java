package com.mldong.modules.dev.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mldong.base.CommonPage;
import com.mldong.base.YesNoEnum;
import com.mldong.modules.dev.dto.SchemaFieldPageParam;
import com.mldong.modules.dev.dto.SchemaFieldParam;
import com.mldong.modules.dev.vo.SchemaFieldVO;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import com.mldong.modules.dev.entity.SchemaField;
import com.mldong.modules.dev.mapper.SchemaFieldMapper;
import com.mldong.modules.dev.service.SchemaFieldService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 模型字段 服务实现类
 * </p>
 *
 * @author mldong
 * @since 2024-01-17
 */
@Service
public class SchemaFieldServiceImpl extends ServiceImpl<SchemaFieldMapper, SchemaField> implements SchemaFieldService {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(SchemaFieldParam param) {
        param.setId(null);
        SchemaField schemaField = new SchemaField();
        BeanUtil.copyProperties(param, schemaField);
        schemaField.setVariable(JSONUtil.toJsonStr(param.getExt()));
        return super.save(schemaField);
    }

    @Override
    public boolean update(SchemaFieldParam param) {
        SchemaField schemaField = new SchemaField();
        BeanUtil.copyProperties(param, schemaField);
        schemaField.setVariable(JSONUtil.toJsonStr(param.getExt()));
        return super.updateById(schemaField);
    }

    @Override
    public CommonPage<SchemaFieldVO> page(SchemaFieldPageParam param) {
        IPage<SchemaFieldVO> page = param.buildMpPage();
        QueryWrapper queryWrapper = param.buildQueryWrapper();
        queryWrapper.eq("t.is_deleted",YesNoEnum.NO);
        List<SchemaFieldVO> list = baseMapper.selectCustom(page, queryWrapper);
        page.setRecords(list);
        return CommonPage.toPage(page);
    }
    @Override
    public SchemaFieldVO findById(Long id) {
        return baseMapper.findById(id);
    }
}
