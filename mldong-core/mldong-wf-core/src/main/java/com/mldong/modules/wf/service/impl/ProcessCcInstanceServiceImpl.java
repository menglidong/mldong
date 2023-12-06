package com.mldong.modules.wf.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mldong.base.CommonPage;
import com.mldong.base.YesNoEnum;
import com.mldong.modules.wf.dto.ProcessCcInstancePageParam;
import com.mldong.modules.wf.dto.ProcessCcInstanceParam;
import com.mldong.modules.wf.vo.ProcessCcInstanceVO;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import com.mldong.modules.wf.entity.ProcessCcInstance;
import com.mldong.modules.wf.mapper.ProcessCcInstanceMapper;
import com.mldong.modules.wf.service.ProcessCcInstanceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 流程实例抄送 服务实现类
 * </p>
 *
 * @author mldong
 * @since 2023-12-05
 */
@Service
public class ProcessCcInstanceServiceImpl extends ServiceImpl<ProcessCcInstanceMapper, ProcessCcInstance> implements ProcessCcInstanceService {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(ProcessCcInstanceParam param) {
        param.setId(null);
        ProcessCcInstance processCcInstance = new ProcessCcInstance();
        BeanUtil.copyProperties(param, processCcInstance);
        return super.save(processCcInstance);
    }

    @Override
    public boolean update(ProcessCcInstanceParam param) {
        ProcessCcInstance processCcInstance = new ProcessCcInstance();
        BeanUtil.copyProperties(param, processCcInstance);
        return super.updateById(processCcInstance);
    }

    @Override
    public CommonPage<ProcessCcInstanceVO> page(ProcessCcInstancePageParam param) {
        IPage<ProcessCcInstanceVO> page = param.buildMpPage();
        QueryWrapper queryWrapper = param.buildQueryWrapper();
        List<ProcessCcInstanceVO> list = baseMapper.selectCustom(page, queryWrapper);
        page.setRecords(list);
        return CommonPage.toPage(page);
    }
    @Override
    public ProcessCcInstanceVO findById(Long id) {
        return baseMapper.findById(id);
    }
}
