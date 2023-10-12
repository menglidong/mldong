package com.mldong.modules.sys.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mldong.base.CommonPage;
import com.mldong.base.YesNoEnum;
import com.mldong.consts.CommonConstant;
import com.mldong.modules.sys.cache.DictCache;
import com.mldong.modules.sys.dto.DictItemPageParam;
import com.mldong.modules.sys.dto.DictItemParam;
import com.mldong.modules.sys.entity.DictItem;
import com.mldong.modules.sys.enums.DictDataType;
import com.mldong.modules.sys.mapper.DictItemMapper;
import com.mldong.modules.sys.mapper.DictMapper;
import com.mldong.modules.sys.service.DictItemService;
import com.mldong.modules.sys.vo.DictItemVO;
import com.mldong.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 字典项 服务实现类
 * </p>
 *
 * @author mldong
 * @since 2023-09-21
 */
@Service
@RequiredArgsConstructor
public class DictItemServiceImpl extends ServiceImpl<DictItemMapper, DictItem> implements DictItemService {
    private final DictMapper dictMapper;
    private final DictCache dictCache;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(DictItemParam param) {
        com.mldong.modules.sys.entity.Dict dict = dictMapper.selectById(param.getDictId());
        if(dict == null) return false;
        param.setId(null);
        DictItem dictItem = new DictItem();
        BeanUtils.copyProperties(param, dictItem);
        return RedisUtil.delayedDoubleRemove(dictCache,(com.mldong.modules.sys.entity.Dict object)->{
            return super.save(dictItem);
        },dict,"code");
    }

    @Override
    public boolean update(DictItemParam param) {
        com.mldong.modules.sys.entity.Dict dict = dictMapper.selectById(param.getDictId());
        if(dict == null) return false;
        DictItem dictItem = new DictItem();
        BeanUtils.copyProperties(param, dictItem);
        return RedisUtil.delayedDoubleRemove(dictCache,(com.mldong.modules.sys.entity.Dict object)->{
            return super.updateById(dictItem);
        },dict,"code");
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
    public boolean removeByIds(Collection<?> list) {
        List<DictItem> dictItemList = baseMapper.selectBatchIds((Collection<? extends Serializable>) list);
        List<com.mldong.modules.sys.entity.Dict> dictList = dictMapper.selectBatchIds(dictItemList.stream().map(item->item.getDictId()).collect(Collectors.toList()));
        return RedisUtil.delayedDoubleRemove(dictCache,(List<com.mldong.modules.sys.entity.Dict> objects)->{
            return super.removeByIds(list);
        },dictList,"code");
    }

    @Override
    public DictItemVO findById(Long id) {
        return baseMapper.findById(id);
    }

    @Override
    public List<Dict> getDictItemListByDictId(Long dictId, DictDataType dictDataType) {
        List<DictItem> items = this.list(Wrappers.lambdaQuery(DictItem.class).eq(DictItem::getDictId, dictId));
        //抽取code和value封装到map返回
        List<Dict> dictList = CollUtil.newArrayList();
        items.forEach(item -> {
            Dict dict = Dict.create();
            if(ObjectUtil.equals(dictDataType,DictDataType.INTEGER)) {
                dict.put(CommonConstant.VALUE, Convert.toInt(item.getCode()));
            } else {
                dict.put(CommonConstant.VALUE, item.getCode());
            }
            dict.put(CommonConstant.LABEL, item.getName());
            dictList.add(dict);
        });

        return dictList;
    }
}
