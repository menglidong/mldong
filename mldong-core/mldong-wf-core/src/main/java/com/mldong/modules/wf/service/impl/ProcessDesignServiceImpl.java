package com.mldong.modules.wf.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mldong.base.CommonPage;
import com.mldong.base.YesNoEnum;
import com.mldong.modules.wf.dto.ProcessDesignPageParam;
import com.mldong.modules.wf.dto.ProcessDesignParam;
import com.mldong.modules.wf.entity.ProcessDesign;
import com.mldong.modules.wf.entity.ProcessDesignHis;
import com.mldong.modules.wf.mapper.ProcessDesignMapper;
import com.mldong.modules.wf.service.ProcessDefineService;
import com.mldong.modules.wf.service.ProcessDesignHisService;
import com.mldong.modules.wf.service.ProcessDesignService;
import com.mldong.modules.wf.vo.ProcessDesignVO;
import com.mldong.util.LowCodeServiceUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
/**
 * <p>
 * 流程设计 服务实现类
 * </p>
 *
 * @author mldong
 * @since 2023-09-25
 */
@Service
@RequiredArgsConstructor
public class ProcessDesignServiceImpl extends ServiceImpl<ProcessDesignMapper, ProcessDesign> implements ProcessDesignService {
    private final ProcessDesignHisService processDesignHisService;
    private final ProcessDefineService processDefineService;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(ProcessDesignParam param) {
        LowCodeServiceUtil.checkUnique(baseMapper,"name",param.getName(),null,"唯一编码已存在，请检查name参数");
        param.setId(null);
        ProcessDesign processDesign = new ProcessDesign();
        BeanUtil.copyProperties(param, processDesign);
        return super.save(processDesign);
    }

    @Override
    public boolean update(ProcessDesignParam param) {
        LowCodeServiceUtil.checkUnique(baseMapper,"name",param.getName(),param.getId(),"唯一编码已存在，请检查name参数");
        ProcessDesign processDesign = new ProcessDesign();
        BeanUtil.copyProperties(param, processDesign);
        return super.updateById(processDesign);
    }

    @Override
    public CommonPage<ProcessDesignVO> page(ProcessDesignPageParam param) {
        IPage<ProcessDesignVO> page = param.buildMpPage();
        QueryWrapper queryWrapper = param.buildQueryWrapper();
        queryWrapper.eq("t.is_deleted",YesNoEnum.NO);
        List<ProcessDesignVO> list = baseMapper.selectCustom(page, queryWrapper);
        page.setRecords(list);
        return CommonPage.toPage(page);
    }
    @Override
    public ProcessDesignVO findById(Long id) {
        ProcessDesignVO vo =  baseMapper.findById(id);
        if(vo!=null) {
            ProcessDesignHis processDesignHis = processDesignHisService.getLatestByProcessDesignId(id);
            if(processDesignHis!=null && ObjectUtil.isNotNull(processDesignHis.getContent())) {
                vo.setJsonObject(JSONUtil.parseObj(Convert.toStr(StrUtil.str(processDesignHis.getContent(), Charset.defaultCharset()),"{")));
            }
            JSONObject jsonObject = vo.getJsonObject();
            if(ObjectUtil.isNull(jsonObject)) {
                jsonObject = new JSONObject();
                vo.setJsonObject(jsonObject);
            }
            if(!jsonObject.containsKey("name")) {
                jsonObject.set("name",vo.getName());
            }
            if(!jsonObject.containsKey("displayName")) {
                jsonObject.set("displayName",vo.getDisplayName());
            }
        }
        return vo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateDefine(JSONObject jsonObject) {
        Long processDesignId = jsonObject.getLong("processDesignId");
        jsonObject.remove("processDesignId");
        ProcessDesignHis latestProcessDesign = processDesignHisService.getLatestByProcessDesignId(processDesignId);
        if(latestProcessDesign!=null) {
            JSONObject latestObject = JSONUtil.parseObj(Convert.toStr(StrUtil.str(latestProcessDesign.getContent(), Charset.defaultCharset()),"{"));
            if(ObjectUtil.equals(latestObject,jsonObject)) {
                // 与数据库中最新一条记录进行比较，无变化，则不更新
                return true;
            }
        }
        ProcessDesignHis processDesignHis = new ProcessDesignHis();
        processDesignHis.setProcessDesignId(processDesignId);
        processDesignHis.setContent(JSONUtil.toJsonStr(jsonObject).getBytes(StandardCharsets.UTF_8));
        boolean success = processDesignHisService.save(processDesignHis);
        if(success) {
            // 更新显示name和displayName
            String name = jsonObject.getStr("name");
            String displayName = jsonObject.getStr("displayName");
            ProcessDesignParam param = new ProcessDesignParam();
            param.setId(processDesignId);
            param.setName(name);
            param.setDisplayName(displayName);
            update(param);
        }
        return  success;
    }

    @Override
    public void deploy(Long processDefineId) {
        ProcessDesignVO processDesign = findById(processDefineId);
        processDefineService.deploy(JSONUtil.toJsonStr(processDesign.getJsonObject()));
    }
}
