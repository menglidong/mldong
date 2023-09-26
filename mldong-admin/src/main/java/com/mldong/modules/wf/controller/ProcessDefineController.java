package com.mldong.modules.wf.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.mldong.base.CommonPage;
import com.mldong.base.CommonResult;
import com.mldong.base.IdParam;
import com.mldong.modules.wf.dto.ProcessDefinePageParam;
import com.mldong.modules.wf.service.ProcessDefineService;
import com.mldong.modules.wf.vo.ProcessDefineVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
/**
* <p>
    * 流程定义 前端控制器
    * </p>
*
* @author mldong
* @since 2023-09-26
*/
@RestController
@Api(tags = "流程定义管理")
@RequiredArgsConstructor
public class ProcessDefineController {
    private final ProcessDefineService processDefineService;

    /**
     * 查询单个流程定义
     * @param param
     * @return
     */
    @PostMapping("/wf/processDefine/detail")
    @ApiOperation(value = "查询单个流程定义")
    @SaCheckPermission("wf:processDefine:detail")
    public CommonResult<ProcessDefineVO> detail(@RequestBody IdParam param) {
        ProcessDefineVO processDefine = processDefineService.findById(param.getId());
        return CommonResult.data(processDefine);
    }
    /**
     *分页查询流程定义列表
     * @param param
     * @return
     */
    @PostMapping("/wf/processDefine/page")
    @ApiOperation(value = "分页查询流程定义列表")
    @SaCheckPermission("wf:processDefine:page")
    public CommonResult<CommonPage<ProcessDefineVO>> page(@RequestBody ProcessDefinePageParam param) {
        return CommonResult.data(processDefineService.page(param));
    }
}
