package com.mldong.modules.wf.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mldong.base.CommonPage;
import com.mldong.base.YesNoEnum;
import com.mldong.modules.wf.dto.ProcessSurrogatePageParam;
import com.mldong.modules.wf.dto.ProcessSurrogateParam;
import com.mldong.modules.wf.vo.ProcessSurrogateVO;
import com.mldong.web.LoginUserHolder;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import com.mldong.modules.wf.entity.ProcessSurrogate;
import com.mldong.modules.wf.mapper.ProcessSurrogateMapper;
import com.mldong.modules.wf.service.ProcessSurrogateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 流程委托代理 服务实现类
 * </p>
 *
 * @author mldong
 * @since 2023-12-06
 */
@Service
public class ProcessSurrogateServiceImpl extends ServiceImpl<ProcessSurrogateMapper, ProcessSurrogate> implements ProcessSurrogateService {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(ProcessSurrogateParam param) {
        param.setId(null);
        param.setOperator(LoginUserHolder.getUserId().toString());
        ProcessSurrogate processSurrogate = new ProcessSurrogate();
        BeanUtil.copyProperties(param, processSurrogate);
        return super.save(processSurrogate);
    }

    @Override
    public boolean update(ProcessSurrogateParam param) {
        ProcessSurrogate processSurrogate = new ProcessSurrogate();
        param.setOperator(LoginUserHolder.getUserId().toString());
        BeanUtil.copyProperties(param, processSurrogate);
        return super.updateById(processSurrogate);
    }

    @Override
    public CommonPage<ProcessSurrogateVO> page(ProcessSurrogatePageParam param) {
        IPage<ProcessSurrogateVO> page = param.buildMpPage();
        QueryWrapper queryWrapper = param.buildQueryWrapper();
        queryWrapper.eq("t.operator", LoginUserHolder.getUserId());
        List<ProcessSurrogateVO> list = baseMapper.selectCustom(page, queryWrapper);
        page.setRecords(list);
        return CommonPage.toPage(page);
    }
    @Override
    public ProcessSurrogateVO findById(Long id) {
        return baseMapper.findById(id);
    }

    @Override
    public String getSurrogate(String operator, String processName) {
        List<ProcessSurrogate> processSurrogateList = baseMapper.selectList(
                Wrappers.lambdaQuery(ProcessSurrogate.class)
                        .eq(ProcessSurrogate::getOperator,operator)
                        .eq(ProcessSurrogate::getEnabled,1)
                        .orderByDesc(ProcessSurrogate::getId)
        );
        for (ProcessSurrogate processSurrogate : processSurrogateList) {
            // 为空表示全部，优先级最高，直接返回
            if(StrUtil.isEmpty(processSurrogate.getProcessName())) {
                // 开始时间判断
                if(processSurrogate.getStartTime()!= null && processSurrogate.getStartTime().getTime() > System.currentTimeMillis()) {
                    continue;
                }
                // 结束时间判断
                if(processSurrogate.getEndTime()!= null && processSurrogate.getEndTime().getTime() < System.currentTimeMillis()) {
                    continue;
                }
                return processSurrogate.getSurrogate();
            }
        }
        // 取满足条件的最新一条
        return processSurrogateList.stream().filter(processSurrogate -> {
            // 只查询流程名称一样的
            return ObjectUtil.equals(processSurrogate.getProcessName(),processName);
        }).filter(processSurrogate -> {
            // 开始时间判断
            if(processSurrogate.getStartTime()!= null && processSurrogate.getStartTime().getTime() > System.currentTimeMillis()) {
                return false;
            }
            // 结束时间判断
            if(processSurrogate.getEndTime()!= null && processSurrogate.getEndTime().getTime() < System.currentTimeMillis()) {
                return false;
            }
            return true;
        }).map(processSurrogate -> processSurrogate.getSurrogate()).findFirst().orElse(null);
    }
}
