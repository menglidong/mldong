package com.mldong.modules.dev.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mldong.base.CommonPage;
import com.mldong.base.YesNoEnum;
import com.mldong.modules.dev.dto.SchemaFieldPageParam;
import com.mldong.modules.dev.dto.SchemaFieldParam;
import com.mldong.modules.dev.dto.UpdateSortParam;
import com.mldong.modules.dev.vo.SchemaFieldVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateSort(UpdateSortParam param) {
        List<SchemaField> schemaFieldList = baseMapper.selectList(
                Wrappers.lambdaQuery(SchemaField.class).eq(SchemaField::getSchemaId,param.getSchemaId())
                        .orderByAsc(SchemaField::getSort)
        );
        int dragRowIndex = 0; // 拖拽行索引
        int hoverRowIndex = 0; // 拖拽到指定行索引
        for (int i = 0; i < schemaFieldList.size(); i++) {
            if(schemaFieldList.get(i).getId().equals(param.getDragRowId()))  {
                dragRowIndex = i;
            }
            if(schemaFieldList.get(i).getId().equals(param.getHoverRowId()))  {
                hoverRowIndex = i;
            }
        }
        List<SchemaField> upList = new ArrayList<>();
        // 先更新拖拽行排序
        SchemaField upSchemaField = new SchemaField();
        upSchemaField.setId(param.getDragRowId());
        upSchemaField.setSort(schemaFieldList.get(hoverRowIndex).getSort());
        upList.add(upSchemaField);
        // 拖拽到指定行排序
        if(dragRowIndex>hoverRowIndex) {
            // 往上拖动
            upSchemaField = new SchemaField();
            upSchemaField.setId(param.getHoverRowId());
            upSchemaField.setSort(schemaFieldList.get(hoverRowIndex).getSort()+1);
            upList.add(upSchemaField);
        } else if(dragRowIndex<hoverRowIndex) {
            // 往下拖动
            upSchemaField = new SchemaField();
            upSchemaField.setId(param.getHoverRowId());
            upSchemaField.setSort(schemaFieldList.get(hoverRowIndex).getSort()-1);
            upList.add(upSchemaField);
        }
        // 再更新其他行
        for (int i = 0; i < schemaFieldList.size(); i++) {
            if(i>=0 && i<hoverRowIndex && i!=dragRowIndex && i!=hoverRowIndex) {
                upSchemaField = new SchemaField();
                upSchemaField.setId(schemaFieldList.get(i).getId());
                upSchemaField.setSort(schemaFieldList.get(i).getSort()-1);
                upList.add(upSchemaField);
            } else if(i>hoverRowIndex && i!=dragRowIndex && i!=hoverRowIndex) {
                upSchemaField = new SchemaField();
                upSchemaField.setId(schemaFieldList.get(i).getId());
                upSchemaField.setSort(schemaFieldList.get(i).getSort()+1);
                upList.add(upSchemaField);
            }
        }
        return super.updateBatchById(upList);
    }
}
