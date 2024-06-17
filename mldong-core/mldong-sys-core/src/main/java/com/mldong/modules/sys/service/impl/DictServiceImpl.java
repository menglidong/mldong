package com.mldong.modules.sys.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mldong.base.CodedEnum;
import com.mldong.base.CommonPage;
import com.mldong.base.YesNoEnum;
import com.mldong.consts.CommonConstant;
import com.mldong.dict.CustomDictService;
import com.mldong.dict.DictScanner;
import com.mldong.dict.model.DictModel;
import com.mldong.modules.sys.cache.DictCache;
import com.mldong.modules.sys.dto.DictPageParam;
import com.mldong.modules.sys.dto.DictParam;
import com.mldong.modules.sys.enums.DictDataType;
import com.mldong.modules.sys.service.DictItemService;
import com.mldong.modules.sys.vo.DictVO;
import com.mldong.util.LowCodeServiceUtil;
import com.mldong.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.*;

import com.mldong.modules.sys.entity.Dict;
import com.mldong.modules.sys.mapper.DictMapper;
import com.mldong.modules.sys.service.DictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 字典 服务实现类
 * </p>
 *
 * @author mldong
 * @since 2023-09-21
 */
@Service
@RequiredArgsConstructor
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {
    private final DictScanner dictScanner;
    private final DictItemService dictItemService;
    private final DictCache dictCache;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(DictParam param) {
        param.setId(null);
        LowCodeServiceUtil.checkUnique(baseMapper,"code",param.getCode(),null,"唯一编码已存在，请检查code参数");
        Dict dict = new Dict();
        BeanUtil.copyProperties(param, dict);
        return super.save(dict);
    }

    @Override
    public boolean update(DictParam param) {
        LowCodeServiceUtil.checkUnique(baseMapper,"code",param.getCode(),param.getId(),"唯一编码已存在，请检查code参数");
        Dict dict = new Dict();
        BeanUtil.copyProperties(param, dict);
        return RedisUtil.delayedDoubleRemove(dictCache,(Dict object)->{
            return super.updateById(object);
        },dict,"code");
    }

    @Override
    public boolean removeByIds(Collection<?> list) {
        List<Dict> dictList = baseMapper.selectBatchIds((Collection<? extends Serializable>) list);
        return RedisUtil.delayedDoubleRemove(dictCache,(List<Dict> objects)->{
            return super.removeByIds(list);
        },dictList,"code");
    }

    @Override
    public CommonPage<DictVO> page(DictPageParam param) {
        IPage<DictVO> page = param.buildMpPage();
        QueryWrapper queryWrapper = param.buildQueryWrapper();
        queryWrapper.eq("t.is_deleted",YesNoEnum.NO);
        List<DictVO> list = baseMapper.selectCustom(page, queryWrapper);
        page.setRecords(list);
        return CommonPage.toPage(page);
    }
    @Override
    public DictVO findById(Long id) {
        return baseMapper.findById(id);
    }

    @Override
    public List<cn.hutool.core.lang.Dict> getByDictType(String dictType) {
        DictModel res = dictCache.get(dictType);
        if(ObjectUtil.isNotNull(res)) return dictModelToDictList(res);
        DictModel dictModel;
        DictDataType dictDataType;
        Dict dict = this.getOne(Wrappers.lambdaQuery(Dict.class).eq(Dict::getCode, dictType));
        if (ObjectUtil.isNull(dict)) {
            // 空，则使用枚举类
            dictModel = dictScanner.getDictMap().get(dictType);
            if (dictModel == null) {
                String[] customDictServiceArr = SpringUtil.getBeanNamesForType(CustomDictService.class);
                // 这里是自定义范围，由业务模块实现
                for (String name : customDictServiceArr) {
                    CustomDictService customDictService = SpringUtil.getBean(name);
                    Map<String, Object> args = new HashMap<>();
                    args.put("dictType", dictType);
                    dictModel = customDictService.getByDictKey(args);
                    if(dictModel != null) {
                        break;
                    }
                }
            }
            if (dictModel == null) {
                return new ArrayList<>();
            } else {
                //抽取code和value封装到map返回
                return dictModelToDictList(dictModel);
            }
        } else {
            dictDataType = CodedEnum.codeOf(DictDataType.class,dict.getDataType()).orElse(DictDataType.STRING);
        }
        List<cn.hutool.core.lang.Dict> dicts = dictItemService.getDictItemListByDictId(dict.getId(), dictDataType);

        return dicts;
    }
    /**
     * 将字典模型对象转成字典列表
     *
     * @param dictModel
     * @return
     */
    private List<cn.hutool.core.lang.Dict> dictModelToDictList(DictModel dictModel) {
        List<cn.hutool.core.lang.Dict> dictList = CollectionUtil.newArrayList();
        dictModel.getItems().forEach(dictItemModel -> {
            cn.hutool.core.lang.Dict dict = cn.hutool.core.lang.Dict.create();
            dict.put(CommonConstant.VALUE, dictItemModel.getDictItemValue());
            dict.put(CommonConstant.LABEL, dictItemModel.getName());
            dictList.add(dict);
        });
        return dictList;
    }
}
