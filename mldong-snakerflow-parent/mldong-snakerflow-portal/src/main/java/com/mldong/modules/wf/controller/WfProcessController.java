package com.mldong.modules.wf.controller;

import com.mldong.common.base.CommonPage;
import com.mldong.common.base.CommonResult;
import com.mldong.common.base.constant.GlobalErrEnum;
import com.mldong.common.tool.StringTool;
import com.mldong.common.web.RequestHolder;
import com.mldong.modules.wf.dto.WfIdParam;
import com.mldong.modules.wf.dto.WfProcessPageParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.AuthorizationScope;
import org.apache.commons.io.IOUtils;
import org.snaker.engine.SnakerEngine;
import org.snaker.engine.access.Page;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.Process;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/wf/process")
@Api(tags="wf-流程定义",authorizations={
        @Authorization(value="wf|工作流",scopes={
                @AuthorizationScope(description="流程定义",scope="wf:process:index")
        })
})
public class WfProcessController {
    @Autowired
    private SnakerEngine snakerEngine;
    @PostMapping("deploy")
    @ApiOperation(value="流程部署", notes = "wf:process:deploy")
    public CommonResult<?> deploy(@RequestBody String xml){
        snakerEngine.process().deploy(new ByteArrayInputStream(xml.getBytes()), RequestHolder.getUserId().toString());
        return CommonResult.success();
    }
    @PostMapping("redeploy")
    @ApiOperation(value="重新流程部署", notes = "wf:process:redeploy")
    public CommonResult<?> redeploy(@RequestParam("id") String id, @RequestBody String xml){
        snakerEngine.process().redeploy(id,new ByteArrayInputStream(xml.getBytes()));
        return CommonResult.success();
    }
    @PostMapping("undeploy")
    @ApiOperation(value="卸载流程", notes = "wf:process:undeploy")
    public CommonResult<?> undeploy(@RequestBody @Validated WfIdParam param){
        snakerEngine.process().undeploy(param.getId());
        return CommonResult.success();
    }
    @PostMapping("list")
    @ApiOperation(value="流程定义列表", notes = "wf:process:list")
    public CommonResult<CommonPage<Process>> list(@RequestBody WfProcessPageParam param) {
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
        return CommonResult.success(commonPage);
    }
    @PostMapping("get")
    @ApiOperation(value="流程定义详情", notes = "wf:process:get")
    public CommonResult<Process> get(@RequestBody @Validated WfIdParam param) {
        QueryFilter queryFilter = new QueryFilter();
        queryFilter.setProcessId(param.getId());
        List<Process> processs = snakerEngine.process().getProcesss(queryFilter);
        if(processs.isEmpty()) {
            return CommonResult.error(GlobalErrEnum.GL99990003);
        }
        Process process = processs.get(0);
        process.setContent(null);
        return CommonResult.success(process);
    }
    @PostMapping("getXml")
    @ApiOperation(value="流程定义xml", notes = "wf:process:getXml")
    public CommonResult<String> getXml(@RequestBody @Validated WfIdParam param) throws IOException {
        Process process = snakerEngine.process().getProcessById(param.getId());
        if(process == null) {
            return CommonResult.error(GlobalErrEnum.GL99990003);
        }
        String xml = IOUtils.toString(process.getDBContent(), "utf-8");
        return CommonResult.success(xml);
    }
    @PostMapping("cascadeRemove")
    @ApiOperation(value="级联删除", notes = "wf:process:cascadeRemove")
    public CommonResult<String> cascadeRemove(@RequestBody @Validated WfIdParam param) {
        snakerEngine.process().cascadeRemove(param.getId());
        return CommonResult.success();
    }
}
