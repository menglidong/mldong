package com.mldong.modules.wf.service.impl;

import com.mldong.common.base.CommonPage;
import com.mldong.common.base.constant.GlobalErrEnum;
import com.mldong.common.tool.AssertTool;
import com.mldong.common.tool.StringTool;
import com.mldong.common.web.RequestHolder;
import com.mldong.modules.wf.dto.WfProcessPageParam;
import com.mldong.modules.wf.service.WfProcessService;
import org.apache.commons.io.IOUtils;
import org.snaker.engine.SnakerEngine;
import org.snaker.engine.access.Page;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.Process;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class WfProcessServiceImpl implements WfProcessService {
    @Autowired
    private SnakerEngine snakerEngine;
    @Override
    public void deploy(String xml) {
        snakerEngine.process().deploy(new ByteArrayInputStream(xml.getBytes()), RequestHolder.getUserId().toString());
    }

    @Override
    public void redeploy(String id, String xml) {
        snakerEngine.process().redeploy(id,new ByteArrayInputStream(xml.getBytes()));
    }

    @Override
    public void undeploy(String id) {
        snakerEngine.process().undeploy(id);
    }

    @Override
    public CommonPage<Process> list(WfProcessPageParam param) {
        param.buildPage();
        Page<Process> page = new Page<>();
        page.setPageNo(param.getPageNum());
        page.setPageSize(param.getPageSize());
        QueryFilter queryFilter = new QueryFilter();
        if(param.getState()!=null) {
            queryFilter.setState(param.getState());
        }
        if(StringTool.isNotEmpty(param.getDisplayName())) {
            queryFilter.setDisplayName(param.getDisplayName());
        }
        if(StringTool.isNotEmpty(param.getName())) {
            queryFilter.setName(param.getName());
        }
        List<Process> processList = snakerEngine.process().getProcesss(page, queryFilter);
        processList.forEach(process -> {
            process.setContent(null);
        });
        CommonPage<Process> commonPage = new CommonPage<>();
        commonPage.setPageNum(param.getPageNum());
        commonPage.setPageSize(param.getPageSize());
        commonPage.setRows(processList);
        commonPage.setTotalPage(Long.valueOf(page.getTotalCount()).intValue());
        return commonPage;
    }

    @Override
    public Process get(String id) {
        QueryFilter queryFilter = new QueryFilter();
        queryFilter.setProcessId(id);
        List<Process> processs = snakerEngine.process().getProcesss(queryFilter);
        if(processs.isEmpty()) {
            AssertTool.throwBiz(GlobalErrEnum.GL99990003);
        }
        Process process = processs.get(0);
        process.setContent(null);
        return process;
    }

    @Override
    public String getXml(String id) {
        Process process = snakerEngine.process().getProcessById(id);
        if(process == null) {
            AssertTool.throwBiz(GlobalErrEnum.GL99990003);
        }
        String xml = null;
        try {
            xml = IOUtils.toString(process.getDBContent(), "utf-8");
        } catch (IOException e) {
            AssertTool.throwBiz(GlobalErrEnum.GL99990500);
        }
        return xml;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cascadeRemove(String id) {
        snakerEngine.process().cascadeRemove(id);
    }
}
