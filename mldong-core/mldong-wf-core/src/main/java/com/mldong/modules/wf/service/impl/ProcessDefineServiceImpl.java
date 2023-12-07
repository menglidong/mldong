package com.mldong.modules.wf.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mldong.base.CommonPage;
import com.mldong.base.UpAndDownParam;
import com.mldong.base.YesNoEnum;
import com.mldong.exception.ServiceException;
import com.mldong.modules.wf.dto.ProcessDefinePageParam;
import com.mldong.modules.wf.dto.ProcessDefineParam;
import com.mldong.modules.wf.engine.model.ProcessModel;
import com.mldong.modules.wf.engine.parser.ModelParser;
import com.mldong.modules.wf.entity.ProcessDefine;
import com.mldong.modules.wf.entity.ProcessInstance;
import com.mldong.modules.wf.enums.ProcessDefineStateEnum;
import com.mldong.modules.wf.enums.err.WfErrEnum;
import com.mldong.modules.wf.mapper.ProcessDefineMapper;
import com.mldong.modules.wf.mapper.ProcessInstanceMapper;
import com.mldong.modules.wf.service.ProcessDefineService;
import com.mldong.modules.wf.vo.ProcessDefineVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Date;
import java.util.List;
/**
 * <p>
 * 流程定义 服务实现类
 * </p>
 *
 * @author mldong
 * @since 2023-09-26
 */
@Service
@RequiredArgsConstructor
public class ProcessDefineServiceImpl extends ServiceImpl<ProcessDefineMapper, ProcessDefine> implements ProcessDefineService {
    private final ProcessInstanceMapper processInstanceMapper;
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
    @Transactional(rollbackFor = Exception.class)
    public boolean removeBatchByIds(Collection<?> list) {
        list.forEach(id->{
           long count = processInstanceMapper.selectCount(Wrappers.lambdaQuery(ProcessInstance.class).eq(ProcessInstance::getProcessDefineId,id));
           if(count>0) {
               ServiceException.throwBiz(WfErrEnum.EXIST_UN_FINISH_INSTANCE);
           }
        });
        return super.removeBatchByIds(list);
    }

    @Override
    public CommonPage<ProcessDefineVO> page(ProcessDefinePageParam param) {
        IPage<ProcessDefineVO> page = param.buildMpPage();
        if(StrUtil.isEmpty(param.getOrderBy())) {
            param.setOrderBy("t.id desc");
        }
        QueryWrapper queryWrapper = param.buildQueryWrapper();
        List<ProcessDefineVO> list = baseMapper.selectCustom(page, queryWrapper);
        list.forEach(processDefineVO -> {
            processDefineVO.setContent(null);
        });
        page.setRecords(list);
        return CommonPage.toPage(page);
    }
    @Override
    public ProcessDefineVO findById(Long id) {
        ProcessDefineVO vo =  baseMapper.findById(id);
        if(vo!=null) {
            vo.setContent(null);
            vo.setJsonObject(getDefineJsonObject(id));
        }
        return vo;
    }
    @Override
    public Long deploy(InputStream inputStream) {
        return deploy(IoUtil.readBytes(inputStream));
    }

    @Override
    public Long deploy(byte[] bytes) {
        Date now = new Date();
        // 1. json定义文件转成流程模型
        ProcessModel processModel = ModelParser.parse(bytes);
        // 2. 根据名称查询，取最新版本的流程定义记录
        List<com.mldong.modules.wf.entity.ProcessDefine> processDefineList = baseMapper.selectList(
                Wrappers.lambdaQuery(ProcessDefine.class)
                        .eq(com.mldong.modules.wf.entity.ProcessDefine::getName,processModel.getName())
                        .orderByDesc(com.mldong.modules.wf.entity.ProcessDefine::getId)
        );
        com.mldong.modules.wf.entity.ProcessDefine processDefine = null;
        // 3.1 如果存在，则版本+1，并插入一条新的流程定义记录
        if(!processDefineList.isEmpty()) {
            processDefine = processDefineList.get(0);
            processDefine.setId(null);
            processDefine.setVersion(processDefine.getVersion()+1);
        } else {
            // 3.2 如果不存在，则版本默认为1，并插入一条新的流程定义记录
            processDefine = new ProcessDefine();
            processDefine.setVersion(0);
        }
        processDefine.setName(processModel.getName());
        processDefine.setDisplayName(processModel.getDisplayName());
        processDefine.setType(processModel.getType());
        processDefine.setCreateTime(now);
        processDefine.setUpdateTime(now);
        processDefine.setState(ProcessDefineStateEnum.ENABLE.getCode());
        processDefine.setContent(bytes);
        baseMapper.insert(processDefine);
        return processDefine.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long deploy(String json) {
        return deploy(json.getBytes());
    }

    @Override
    public void redeploy(Long processDefineId, InputStream inputStream) {
        redeploy(processDefineId,IoUtil.readBytes(inputStream));
    }

    @Override
    public void redeploy(Long processDefineId, byte[] bytes) {
        Date now = new Date();
        // 1. json定义文件转成流程模型
        ProcessModel processModel = ModelParser.parse(bytes);
        // 2. 构造模型定义信息
        ProcessDefine processDefine = new ProcessDefine();
        processDefine.setId(processDefineId);
        processDefine.setType(processModel.getType());
        processDefine.setName(processModel.getName());
        processDefine.setDisplayName(processModel.getDisplayName());
        processDefine.setUpdateTime(now);
        processDefine.setContent(bytes);
        // 3. 更新模型定义文件
        baseMapper.updateById(processDefine);
    }

    @Override
    public void redeploy(Long processDefineId, String json) {
        redeploy(processDefineId, json.getBytes());
    }

    @Override
    public void unDeploy(Long processDefineId) {
        ProcessDefine processDefine = new ProcessDefine();
        processDefine.setId(processDefineId);
        processDefine.setState(ProcessDefineStateEnum.DISABLE.getCode());
        baseMapper.updateById(processDefine);
    }

    @Override
    public void reDeploy(Long processDefineId) {
        ProcessDefine processDefine = new ProcessDefine();
        processDefine.setId(processDefineId);
        processDefine.setState(ProcessDefineStateEnum.ENABLE.getCode());
        baseMapper.updateById(processDefine);
    }

    @Override
    public void updateType(Long processDefineId, String type) {
        ProcessDefine processDefine = new ProcessDefine();
        processDefine.setId(processDefineId);
        processDefine.setType(type);
        baseMapper.updateById(processDefine);
    }

    @Override
    public ProcessDefine getById(Long processDefineId) {
        return baseMapper.selectById(processDefineId);
    }

    @Override
    public ProcessModel getProcessModel(Long processDefineId) {
        return processDefineToModel(getById(processDefineId));
    }

    @Override
    public ProcessModel processDefineToModel(ProcessDefine processDefine) {
        if(processDefine == null) return null;
        ProcessModel processModel = ModelParser.parse(processDefine.getContent());
        // 注：后续再进行缓存优化，减少频繁解析
        return processModel;
    }

    @Override
    public String getDefineJsonStr(Long id) {
        ProcessDefine processDefine = getById(id);
        if(processDefine == null) return null;
        return StrUtil.str(processDefine.getContent(), Charset.defaultCharset());
    }

    @Override
    public JSONObject getDefineJsonObject(Long processDefineId) {
        return JSONUtil.parseObj(Convert.toStr(getDefineJsonStr(processDefineId),"{}"));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void upAndDown(UpAndDownParam param) {
        if(YesNoEnum.YES.equals(param.getOpType())) {
            param.getIds().forEach(id->{
                reDeploy(Convert.toLong(id));
            });
        } else {
            param.getIds().forEach(id->{
                unDeploy(Convert.toLong(id));
            });
        }
    }

    @Override
    public ProcessDefineVO getLastByName(String name) {
        return baseMapper.selectLastByName(name);
    }
    @Override
    public ProcessDefine getProcessDefineByVersion(String name, Integer version) {
        LambdaQueryWrapper<ProcessDefine> queryWrapper = Wrappers.lambdaQuery(ProcessDefine.class);
        queryWrapper.eq(ProcessDefine::getName,name);
        if(version!=null) {
            queryWrapper.eq(ProcessDefine::getVersion, version);
        }
        queryWrapper.orderByDesc(ProcessDefine::getId);
        List<ProcessDefine> processDefineList = baseMapper.selectList(queryWrapper);
        if(!processDefineList.isEmpty()) {
            return processDefineList.get(0);
        }
        return null;
    }
}
