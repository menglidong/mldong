package com.mldong.modules.wf.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mldong.base.CommonPage;
import com.mldong.base.YesNoEnum;
import com.mldong.modules.wf.dto.ProcessDefinePageParam;
import com.mldong.modules.wf.dto.ProcessDefineParam;
import com.mldong.modules.wf.vo.ProcessDefineVO;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import com.mldong.modules.wf.entity.ProcessDefine;
import com.mldong.modules.wf.mapper.ProcessDefineMapper;
import com.mldong.modules.wf.service.ProcessDefineService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 流程定义 服务实现类
 * </p>
 *
 * @author mldong
 * @since 2023-09-26
 */
@Service
public class ProcessDefineServiceImpl extends ServiceImpl<ProcessDefineMapper, ProcessDefine> implements ProcessDefineService {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(ProcessDefineParam param) {
        param.setId(null);
        ProcessDefine processDefine = new ProcessDefine();
        BeanUtil.copyProperties(param, processDefine);
        return super.save(processDefine);
    }

    @Override
    public boolean update(ProcessDefineParam param) {
        ProcessDefine processDefine = new ProcessDefine();
        BeanUtil.copyProperties(param, processDefine);
        return super.updateById(processDefine);
    }

    @Override
    public CommonPage<ProcessDefineVO> page(ProcessDefinePageParam param) {
        IPage<ProcessDefineVO> page = param.buildMpPage();
        QueryWrapper queryWrapper = param.buildQueryWrapper();
        List<ProcessDefineVO> list = baseMapper.selectCustom(page, queryWrapper);
        page.setRecords(list);
        return CommonPage.toPage(page);
    }
    @Override
    public ProcessDefineVO findById(Long id) {
        return baseMapper.findById(id);
    }
}
