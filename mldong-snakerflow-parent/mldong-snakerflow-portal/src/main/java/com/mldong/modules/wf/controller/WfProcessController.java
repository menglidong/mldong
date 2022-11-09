package com.mldong.modules.wf.controller;

import com.mldong.common.base.CommonPage;
import com.mldong.common.base.CommonResult;
import com.mldong.modules.wf.dto.WfIdParam;
import com.mldong.modules.wf.dto.WfProcessPageParam;
import com.mldong.modules.wf.service.WfProcessService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.AuthorizationScope;
import org.snaker.engine.entity.Process;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/wf/process")
@Api(tags="wf-流程定义",authorizations={
        @Authorization(value="wf|工作流",scopes={
                @AuthorizationScope(description="流程定义",scope="wf:process:index")
        })
})
public class WfProcessController {
    @Autowired
    private WfProcessService processService;
    @PostMapping("deploy")
    @ApiOperation(value="流程部署", notes = "wf:process:deploy")
    public CommonResult<?> deploy(@RequestBody String xml){
        processService.deploy(xml);
        return CommonResult.success();
    }
    @PostMapping("redeploy")
    @ApiOperation(value="重新流程部署", notes = "wf:process:redeploy")
    public CommonResult<?> redeploy(@RequestParam("id") String id, @RequestBody String xml){
        processService.redeploy(id, xml);
        return CommonResult.success();
    }
    @PostMapping("undeploy")
    @ApiOperation(value="卸载流程", notes = "wf:process:undeploy")
    public CommonResult<?> undeploy(@RequestBody @Validated WfIdParam param){
        processService.undeploy(param.getId());
        return CommonResult.success();
    }
    @PostMapping("list")
    @ApiOperation(value="流程定义列表", notes = "wf:process:list")
    public CommonResult<CommonPage<Process>> list(@RequestBody WfProcessPageParam param) {
        return CommonResult.success(processService.list(param));
    }
    @PostMapping("get")
    @ApiOperation(value="流程定义详情", notes = "wf:process:get")
    public CommonResult<Process> get(@RequestBody @Validated WfIdParam param) {
        return CommonResult.success(processService.get(param.getId()));
    }
    @PostMapping("getXml")
    @ApiOperation(value="流程定义xml", notes = "wf:process:getXml")
    public CommonResult<String> getXml(@RequestBody @Validated WfIdParam param) throws IOException {
        return CommonResult.success(processService.getXml(param.getId()));
    }
    @PostMapping(value = "/wf/process/getOnlyXml", produces = MediaType.APPLICATION_XML_VALUE)
    @ApiOperation(value="流程定义xml-仅返回xml", notes = "wf:process:getOnlyXml")
    public String getOnlyXml(@RequestBody @Validated WfIdParam param) throws IOException {
        return processService.getXml(param.getId());
    }
    @PostMapping("cascadeRemove")
    @ApiOperation(value="级联删除", notes = "wf:process:cascadeRemove")
    public CommonResult<?> cascadeRemove(@RequestBody @Validated WfIdParam param) {
        processService.cascadeRemove(param.getId());
        return CommonResult.success();
    }
}
