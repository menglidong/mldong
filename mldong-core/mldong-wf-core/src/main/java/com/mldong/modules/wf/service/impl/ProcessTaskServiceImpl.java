package com.mldong.modules.wf.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mldong.base.CommonPage;
import com.mldong.base.YesNoEnum;
import com.mldong.modules.wf.dto.ProcessTaskPageParam;
import com.mldong.modules.wf.dto.ProcessTaskParam;
import com.mldong.modules.wf.vo.ProcessTaskVO;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import com.mldong.modules.wf.entity.ProcessTask;
import com.mldong.modules.wf.mapper.ProcessTaskMapper;
import com.mldong.modules.wf.service.ProcessTaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 流程任务 服务实现类
 * </p>
 *
 * @author mldong
 * @since 2023-09-26
 */
@Service
public class ProcessTaskServiceImpl extends ServiceImpl<ProcessTaskMapper, ProcessTask> implements ProcessTaskService {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(ProcessTaskParam param) {
        param.setId(null);
        ProcessTask processTask = new ProcessTask();
        BeanUtil.copyProperties(param, processTask);
        return super.save(processTask);
    }

    @Override
    public boolean update(ProcessTaskParam param) {
        ProcessTask processTask = new ProcessTask();
        BeanUtil.copyProperties(param, processTask);
        return super.updateById(processTask);
    }

    @Override
    public CommonPage<ProcessTaskVO> page(ProcessTaskPageParam param) {
        IPage<ProcessTaskVO> page = param.buildMpPage();
        QueryWrapper queryWrapper = param.buildQueryWrapper();
        List<ProcessTaskVO> list = baseMapper.selectCustom(page, queryWrapper);
        page.setRecords(list);
        return CommonPage.toPage(page);
    }
    @Override
    public ProcessTaskVO findById(Long id) {
        return baseMapper.findById(id);
    }
}
