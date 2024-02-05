package com.mldong.modules.biz.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mldong.base.CommonPage;
import com.mldong.base.YesNoEnum;
import com.mldong.modules.biz.dto.DemoPageParam;
import com.mldong.modules.biz.dto.DemoParam;
import com.mldong.modules.biz.vo.DemoVO;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import com.mldong.modules.biz.entity.Demo;
import com.mldong.modules.biz.mapper.DemoMapper;
import com.mldong.modules.biz.service.DemoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 演示 服务实现类
 * </p>
 *
 * @author mldong
 * @since 2023-12-28
 */
@Service
public class DemoServiceImpl extends ServiceImpl<DemoMapper, Demo> implements DemoService {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(DemoParam param) {
        param.setId(null);
        Demo demo = new Demo();
        BeanUtil.copyProperties(param, demo);
        return super.save(demo);
    }

    @Override
    public boolean update(DemoParam param) {
        Demo demo = new Demo();
        BeanUtil.copyProperties(param, demo);
        return super.updateById(demo);
    }

    @Override
    public CommonPage<DemoVO> page(DemoPageParam param) {
        IPage<DemoVO> page = param.buildMpPage();
        QueryWrapper queryWrapper = param.buildQueryWrapper();
        queryWrapper.eq("t.is_deleted",YesNoEnum.NO);
        List<DemoVO> list = baseMapper.selectCustom(page, queryWrapper);
        page.setRecords(list);
        return CommonPage.toPage(page);
    }
    @Override
    public DemoVO findById(Long id) {
        DemoVO vo = baseMapper.findById(id);
        if(vo!=null) {
            vo.$disabledKeyList = CollectionUtil.newArrayList("dict");
            vo.$hideKeyList = CollectionUtil.newArrayList("upload");
        }
        return vo;
    }
}
