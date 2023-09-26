package com.mldong.modules.wf.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.mldong.base.CommonPage;
import com.mldong.base.CommonResult;
import com.mldong.base.IdParam;
import com.mldong.base.IdsParam;
import com.mldong.modules.wf.dto.ProcessDefinePageParam;
import com.mldong.modules.wf.dto.ProcessDefineParam;
import com.mldong.modules.wf.service.ProcessDefineService;
import com.mldong.modules.wf.vo.ProcessDefineVO;
import com.mldong.validation.Groups;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
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
     * 添加流程定义
     * @param param
     * @return
     */
    @PostMapping("/wf/processDefine/save")
    @ApiOperation(value = "添加流程定义")
    @SaCheckPermission("wf:processDefine:save")
    public CommonResult<?> save(@RequestBody @Validated({Groups.Save.class}) ProcessDefineParam param) {
        processDefineService.save(param);
        return CommonResult.ok();
    }
    /**
     * 删除流程定义
     * @param param
     * @return
     */
    @PostMapping("/wf/processDefine/remove")
    @ApiOperation(value = "删除流程定义")
    @SaCheckPermission("wf:processDefine:remove")
    public CommonResult<?> remove(@RequestBody IdsParam param) {
        processDefineService.removeBatchByIds(param.getIds());
        return CommonResult.ok();
    }
    /**
     * 修改流程定义
     * @param param
     * @return
     */
    @PostMapping("/wf/processDefine/update")
    @ApiOperation(value = "修改流程定义")
    @SaCheckPermission("wf:processDefine:update")
    public CommonResult<?> update(@RequestBody @Validated({Groups.Update.class}) ProcessDefineParam param) {
        processDefineService.update(param);
        return CommonResult.ok();
    }
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
