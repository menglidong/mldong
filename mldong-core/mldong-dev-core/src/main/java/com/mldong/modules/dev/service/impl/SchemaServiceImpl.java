package com.mldong.modules.dev.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mldong.base.CommonPage;
import com.mldong.base.YesNoEnum;
import com.mldong.modules.dev.dto.SchemaPageParam;
import com.mldong.modules.dev.dto.SchemaParam;
import com.mldong.modules.dev.vo.SchemaVO;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import com.mldong.modules.dev.entity.Schema;
import com.mldong.modules.dev.mapper.SchemaMapper;
import com.mldong.modules.dev.service.SchemaService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 数据模型 服务实现类
 * </p>
 *
 * @author mldong
 * @since 2024-01-17
 */
@Service
public class SchemaServiceImpl extends ServiceImpl<SchemaMapper, Schema> implements SchemaService {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(SchemaParam param) {
        param.setId(null);
        Schema schema = new Schema();
        BeanUtil.copyProperties(param, schema);
        return super.save(schema);
    }

    @Override
    public boolean update(SchemaParam param) {
        Schema schema = new Schema();
        BeanUtil.copyProperties(param, schema);
        return super.updateById(schema);
    }

    @Override
    public CommonPage<SchemaVO> page(SchemaPageParam param) {
        IPage<SchemaVO> page = param.buildMpPage();
        QueryWrapper queryWrapper = param.buildQueryWrapper();
        queryWrapper.eq("t.is_deleted",YesNoEnum.NO);
        List<SchemaVO> list = baseMapper.selectCustom(page, queryWrapper);
        page.setRecords(list);
        return CommonPage.toPage(page);
    }
    @Override
    public SchemaVO findById(Long id) {
        return baseMapper.findById(id);
    }
}
