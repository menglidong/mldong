package com.mldong.modules.sys.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mldong.base.CommonPage;
import com.mldong.base.YesNoEnum;
import com.mldong.modules.sys.dto.ConfigPageParam;
import com.mldong.modules.sys.dto.ConfigParam;
import com.mldong.modules.sys.vo.ConfigVO;
import com.mldong.util.LowCodeServiceUtil;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import com.mldong.modules.sys.entity.Config;
import com.mldong.modules.sys.mapper.ConfigMapper;
import com.mldong.modules.sys.service.ConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 配置 服务实现类
 * </p>
 *
 * @author mldong
 * @since 2023-09-21
 */
@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements ConfigService {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(ConfigParam param) {
        param.setId(null);
        LowCodeServiceUtil.checkUnique(baseMapper,"code",param.getCode(),null,"唯一编码已存在，请检查code参数");
        Config config = new Config();
        BeanUtil.copyProperties(param, config);
        return super.save(config);
    }

    @Override
    public boolean update(ConfigParam param) {
        Config config = new Config();
        LowCodeServiceUtil.checkUnique(baseMapper,"code",param.getCode(),param.getId(),"唯一编码已存在，请检查code参数");
        BeanUtil.copyProperties(param, config);
        return super.updateById(config);
    }

    @Override
    public CommonPage<ConfigVO> page(ConfigPageParam param) {
        IPage<ConfigVO> page = param.buildMpPage();
        QueryWrapper queryWrapper = param.buildQueryWrapper();
        queryWrapper.eq("t.is_deleted",YesNoEnum.NO);
        List<ConfigVO> list = baseMapper.selectCustom(page, queryWrapper);
        page.setRecords(list);
        return CommonPage.toPage(page);
    }
    @Override
    public ConfigVO findById(Long id) {
        return baseMapper.findById(id);
    }
}
