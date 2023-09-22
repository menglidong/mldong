package com.mldong.modules.sys.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Dict;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mldong.base.CommonPage;
import com.mldong.base.YesNoEnum;
import com.mldong.consts.CommonConstant;
import com.mldong.modules.sys.dto.DictItemPageParam;
import com.mldong.modules.sys.dto.DictItemParam;
import com.mldong.modules.sys.entity.DictItem;
import com.mldong.modules.sys.mapper.DictItemMapper;
import com.mldong.modules.sys.service.DictItemService;
import com.mldong.modules.sys.vo.DictItemVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 字典项 服务实现类
 * </p>
 *
 * @author mldong
 * @since 2023-09-21
 */
@Service
public class DictItemServiceImpl extends ServiceImpl<DictItemMapper, DictItem> implements DictItemService {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(DictItemParam param) {
        param.setId(null);
        DictItem dictItem = new DictItem();
        BeanUtil.copyProperties(param, dictItem);
        return super.save(dictItem);
    }

    @Override
    public boolean update(DictItemParam param) {
        DictItem dictItem = new DictItem();
        BeanUtil.copyProperties(param, dictItem);
        return super.updateById(dictItem);
    }

    @Override
    public CommonPage<DictItemVO> page(DictItemPageParam param) {
        IPage<DictItemVO> page = param.buildMpPage();
        QueryWrapper queryWrapper = param.buildQueryWrapper();
        queryWrapper.eq("t.is_deleted",YesNoEnum.NO);
        List<DictItemVO> list = baseMapper.selectCustom(page, queryWrapper);
        page.setRecords(list);
        return CommonPage.toPage(page);
    }
    @Override
    public DictItemVO findById(Long id) {
        return baseMapper.findById(id);
    }

    @Override
    public List<Dict> getDictItemListByDictId(Long dictId) {
        List<DictItem> items = this.list(Wrappers.lambdaQuery(DictItem.class).eq(DictItem::getDictId, dictId));
        //抽取code和value封装到map返回
        List<Dict> dictList = CollUtil.newArrayList();
        items.forEach(item -> {
            Dict dict = Dict.create();
            dict.put(CommonConstant.VALUE, item.getCode());
            dict.put(CommonConstant.LABEL, item.getName());
            dictList.add(dict);
        });

        return dictList;
    }
}
