package com.mldong.modules.wf.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mldong.base.CommonPage;
import com.mldong.base.YesNoEnum;
import com.mldong.consts.CommonConstant;
import com.mldong.modules.sys.api.DictApi;
import com.mldong.modules.wf.dto.ProcessDesignPageParam;
import com.mldong.modules.wf.dto.ProcessDesignParam;
import com.mldong.modules.wf.entity.ProcessDesign;
import com.mldong.modules.wf.entity.ProcessDesignHis;
import com.mldong.modules.wf.enums.FlowConst;
import com.mldong.modules.wf.mapper.ProcessDefineMapper;
import com.mldong.modules.wf.mapper.ProcessDesignMapper;
import com.mldong.modules.wf.service.ProcessDefineService;
import com.mldong.modules.wf.service.ProcessDesignHisService;
import com.mldong.modules.wf.service.ProcessDesignService;
import com.mldong.modules.wf.vo.ProcessDefineVO;
import com.mldong.modules.wf.vo.ProcessDesignTypeVO;
import com.mldong.modules.wf.vo.ProcessDesignVO;
import com.mldong.util.LowCodeServiceUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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
    private final DictApi dictApi;
    private final ProcessDefineMapper processDefineMapper;
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
    @Transactional(rollbackFor = Exception.class)
    public boolean update(ProcessDesignParam param) {
        LowCodeServiceUtil.checkUnique(baseMapper,"name",param.getName(),param.getId(),"唯一编码已存在，请检查name参数");
        ProcessDesign processDesign = new ProcessDesign();
        BeanUtil.copyProperties(param, processDesign);
        ProcessDesignHis latestProcessDesign = processDesignHisService.getLatestByProcessDesignId(param.getId());
        if(latestProcessDesign!=null) {
            JSONObject jsonObject = JSONUtil.parseObj(latestProcessDesign.getContent());
            jsonObject.set(FlowConst.PROCESS_NAME_KEY,processDesign.getName());
            jsonObject.set(FlowConst.PROCESS_DISPLAY_NAME_KEY,processDesign.getDisplayName());
            jsonObject.set(FlowConst.PROCESS_TYPE,processDesign.getType());
            jsonObject.set(FlowConst.PROCESS_DESIGN_ID_KEY,processDesign.getId());
            updateDefine(jsonObject);
        }
        return super.updateById(processDesign);
    }

    @Override
    public CommonPage<ProcessDesignVO> page(ProcessDesignPageParam param) {
        // 按id倒序
        if(StrUtil.isEmpty(param.getOrderBy())) {
            param.setOrderBy("t.id desc");
        }
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
            if(!jsonObject.containsKey(FlowConst.PROCESS_NAME_KEY)) {
                jsonObject.set(FlowConst.PROCESS_NAME_KEY,vo.getName());
            }
            if(!jsonObject.containsKey(FlowConst.PROCESS_DISPLAY_NAME_KEY)) {
                jsonObject.set(FlowConst.PROCESS_DISPLAY_NAME_KEY,vo.getDisplayName());
            }
        }
        return vo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateDefine(JSONObject jsonObject) {
        Long processDesignId = jsonObject.getLong(FlowConst.PROCESS_DESIGN_ID_KEY);
        jsonObject.remove(FlowConst.PROCESS_DESIGN_ID_KEY);
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
            String name = jsonObject.getStr(FlowConst.PROCESS_NAME_KEY);
            String displayName = jsonObject.getStr(FlowConst.PROCESS_DISPLAY_NAME_KEY);
            String type = jsonObject.getStr(FlowConst.PROCESS_TYPE);
            ProcessDesignParam processDesign = new ProcessDesignParam();
            processDesign.setId(processDesignId);
            processDesign.setName(name);
            processDesign.setDisplayName(displayName);
            processDesign.setIsDeployed(YesNoEnum.NO.getCode());
            processDesign.setType(type);
            update(processDesign);
        }
        return  success;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deploy(Long processDesignId) {
        ProcessDesignVO processDesign = findById(processDesignId);
        ProcessDesign up = new ProcessDesign();
        up.setId(processDesignId);
        up.setIsDeployed(YesNoEnum.YES.getCode());
        if(updateById(up)) {
            // 先更新状态，更新成功，再部署
            processDefineService.deploy(JSONUtil.toJsonStr(processDesign.getJsonObject()));
        }
    }

    @Override
    public void redeploy(Long processDesignId) {
        ProcessDesignVO processDesign = findById(processDesignId);
        ProcessDesign up = new ProcessDesign();
        up.setId(processDesignId);
        up.setIsDeployed(YesNoEnum.YES.getCode());
        if(updateById(up)) {
            // 取最新流程定义
            ProcessDefineVO processDefineVO = processDefineService.getLastByName(processDesign.getName());
            // 直接替换
            processDefineService.redeploy(processDefineVO.getId(),JSONUtil.toJsonStr(processDesign.getJsonObject()));
        }
    }

    @Override
    public List<ProcessDesignTypeVO> listByType() {
        List<ProcessDesignTypeVO> res = new ArrayList<>();
        // 先拿到分类
        List<Dict> dictList = dictApi.getByDictType("wf_process_type");
        if(CollectionUtil.isEmpty(dictList)) return res;
        dictList.forEach(dict -> {
            ProcessDesignTypeVO typeVO = new ProcessDesignTypeVO();
            typeVO.setType(dict.getStr(CommonConstant.VALUE));
            typeVO.setTitle(dict.getStr(CommonConstant.LABEL));
            typeVO.setItems(new ArrayList<>());
            // 根据分类查询流程设计
            List<ProcessDesign> processDesignList = baseMapper.selectList(Wrappers.lambdaQuery(ProcessDesign.class).eq(ProcessDesign::getType,typeVO.getType()));
            if(CollectionUtil.isNotEmpty(processDesignList)) {
                processDesignList.forEach(processDesign->{
                    // 要以已部署的为准，所以查询最新版的流程定义信息
                    ProcessDefineVO processDefineVO = processDefineMapper.selectLastByName(processDesign.getName());
                    if(processDefineVO!=null) {
                        ProcessDesignVO processDesignVO = BeanUtil.toBean(processDesign,ProcessDesignVO.class);
                        processDesignVO.setProcessDefineId(processDefineVO.getId());
                        processDesignVO.setName(processDefineVO.getName());
                        processDesignVO.setDisplayName(processDefineVO.getDisplayName());
                        processDefineVO.setJsonObject(JSONUtil.parseObj(processDefineVO.getContent()));
                        processDesignVO.setIcon(processDesign.getIcon());
                        typeVO.getItems().add(processDesignVO);
                    }
                });
            }
            res.add(typeVO);
        });
        return res;
    }
}
