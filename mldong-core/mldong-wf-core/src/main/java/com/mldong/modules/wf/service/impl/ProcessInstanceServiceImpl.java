package com.mldong.modules.wf.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mldong.base.CommonPage;
import com.mldong.base.YesNoEnum;
import com.mldong.modules.wf.dto.ProcessInstancePageParam;
import com.mldong.modules.wf.dto.ProcessInstanceParam;
import com.mldong.modules.wf.vo.ProcessInstanceVO;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import com.mldong.modules.wf.entity.ProcessInstance;
import com.mldong.modules.wf.mapper.ProcessInstanceMapper;
import com.mldong.modules.wf.service.ProcessInstanceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 流程实例 服务实现类
 * </p>
 *
 * @author mldong
 * @since 2023-09-26
 */
@Service
public class ProcessInstanceServiceImpl extends ServiceImpl<ProcessInstanceMapper, ProcessInstance> implements ProcessInstanceService {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(ProcessInstanceParam param) {
        param.setId(null);
        ProcessInstance processInstance = new ProcessInstance();
        BeanUtil.copyProperties(param, processInstance);
        return super.save(processInstance);
    }

    @Override
    public boolean update(ProcessInstanceParam param) {
        ProcessInstance processInstance = new ProcessInstance();
        BeanUtil.copyProperties(param, processInstance);
        return super.updateById(processInstance);
    }

    @Override
    public CommonPage<ProcessInstanceVO> page(ProcessInstancePageParam param) {
        IPage<ProcessInstanceVO> page = param.buildMpPage();
        QueryWrapper queryWrapper = param.buildQueryWrapper();
        List<ProcessInstanceVO> list = baseMapper.selectCustom(page, queryWrapper);
        page.setRecords(list);
        return CommonPage.toPage(page);
    }
    @Override
    public ProcessInstanceVO findById(Long id) {
        return baseMapper.findById(id);
    }
}
