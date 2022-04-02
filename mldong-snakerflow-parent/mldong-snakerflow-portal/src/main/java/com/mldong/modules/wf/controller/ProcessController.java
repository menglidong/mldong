package com.mldong.modules.wf.controller;

import com.mldong.common.base.CommonPage;
import com.mldong.common.base.CommonResult;
import com.mldong.common.web.RequestHolder;
import com.mldong.modules.wf.dto.WfProcessPageParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.AuthorizationScope;
import org.snaker.engine.SnakerEngine;
import org.snaker.engine.access.Page;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.Process;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping("/wf/process")
@Api(tags="wf-流程定义",authorizations={
        @Authorization(value="wf|工作流",scopes={
                @AuthorizationScope(description="流程定义",scope="wf:process:index")
        })
})
public class ProcessController {
    @Autowired
    private SnakerEngine snakerEngine;
    @PostMapping("deploy")
    @ApiOperation(value="流程部署", notes = "wf:process:deploy")
    public CommonResult<?> deploy(@RequestBody String xml){
        snakerEngine.process().deploy(new ByteArrayInputStream(xml.getBytes()), RequestHolder.getUserId().toString());
        return CommonResult.success();
    }
    @PostMapping("list")
    @ApiOperation(value="流程定义列表", notes = "wf:process:list")
    public CommonResult<CommonPage<Process>> list(@RequestBody WfProcessPageParam param) {
        Page<Process> page = new Page<>();
        page.setPageNo(param.getPageNum());
        page.setPageSize(param.getPageSize());
        QueryFilter queryFilter = new QueryFilter();
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
}
