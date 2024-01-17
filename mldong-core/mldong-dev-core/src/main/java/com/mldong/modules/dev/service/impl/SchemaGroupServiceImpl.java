package com.mldong.modules.dev.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mldong.base.CommonPage;
import com.mldong.base.YesNoEnum;
import com.mldong.modules.dev.dto.SchemaGroupPageParam;
import com.mldong.modules.dev.dto.SchemaGroupParam;
import com.mldong.modules.dev.vo.SchemaGroupVO;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import com.mldong.modules.dev.entity.SchemaGroup;
import com.mldong.modules.dev.mapper.SchemaGroupMapper;
import com.mldong.modules.dev.service.SchemaGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 模型分组 服务实现类
 * </p>
 *
 * @author mldong
 * @since 2024-01-17
 */
@Service
public class SchemaGroupServiceImpl extends ServiceImpl<SchemaGroupMapper, SchemaGroup> implements SchemaGroupService {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(SchemaGroupParam param) {
        param.setId(null);
        SchemaGroup schemaGroup = new SchemaGroup();
        BeanUtil.copyProperties(param, schemaGroup);
        return super.save(schemaGroup);
    }

    @Override
    public boolean update(SchemaGroupParam param) {
        SchemaGroup schemaGroup = new SchemaGroup();
        BeanUtil.copyProperties(param, schemaGroup);
        return super.updateById(schemaGroup);
    }

    @Override
    public CommonPage<SchemaGroupVO> page(SchemaGroupPageParam param) {
        IPage<SchemaGroupVO> page = param.buildMpPage();
        QueryWrapper queryWrapper = param.buildQueryWrapper();
        queryWrapper.eq("t.is_deleted",YesNoEnum.NO);
        List<SchemaGroupVO> list = baseMapper.selectCustom(page, queryWrapper);
        page.setRecords(list);
        return CommonPage.toPage(page);
    }
    @Override
    public SchemaGroupVO findById(Long id) {
        return baseMapper.findById(id);
    }
}
