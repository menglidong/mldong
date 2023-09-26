package com.mldong.modules.wf.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.mldong.base.CommonPage;
import com.mldong.base.CommonResult;
import com.mldong.base.IdParam;
import com.mldong.base.IdsParam;
import com.mldong.modules.wf.dto.ProcessInstancePageParam;
import com.mldong.modules.wf.dto.ProcessInstanceParam;
import com.mldong.modules.wf.service.ProcessInstanceService;
import com.mldong.modules.wf.vo.ProcessInstanceVO;
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
    * 流程实例 前端控制器
    * </p>
*
* @author mldong
* @since 2023-09-26
*/
@RestController
@Api(tags = "流程实例管理")
@RequiredArgsConstructor
public class ProcessInstanceController {
    private final ProcessInstanceService processInstanceService;
    /**
     * 添加流程实例
     * @param param
     * @return
     */
    @PostMapping("/wf/processInstance/save")
    @ApiOperation(value = "添加流程实例")
    @SaCheckPermission("wf:processInstance:save")
    public CommonResult<?> save(@RequestBody @Validated({Groups.Save.class}) ProcessInstanceParam param) {
        processInstanceService.save(param);
        return CommonResult.ok();
    }
    /**
     * 删除流程实例
     * @param param
     * @return
     */
    @PostMapping("/wf/processInstance/remove")
    @ApiOperation(value = "删除流程实例")
    @SaCheckPermission("wf:processInstance:remove")
    public CommonResult<?> remove(@RequestBody IdsParam param) {
        processInstanceService.removeBatchByIds(param.getIds());
        return CommonResult.ok();
    }
    /**
     * 修改流程实例
     * @param param
     * @return
     */
    @PostMapping("/wf/processInstance/update")
    @ApiOperation(value = "修改流程实例")
    @SaCheckPermission("wf:processInstance:update")
    public CommonResult<?> update(@RequestBody @Validated({Groups.Update.class}) ProcessInstanceParam param) {
        processInstanceService.update(param);
        return CommonResult.ok();
    }
    /**
     * 查询单个流程实例
     * @param param
     * @return
     */
    @PostMapping("/wf/processInstance/detail")
    @ApiOperation(value = "查询单个流程实例")
    @SaCheckPermission("wf:processInstance:detail")
    public CommonResult<ProcessInstanceVO> detail(@RequestBody IdParam param) {
        ProcessInstanceVO processInstance = processInstanceService.findById(param.getId());
        return CommonResult.data(processInstance);
    }
    /**
     *分页查询流程实例列表
     * @param param
     * @return
     */
    @PostMapping("/wf/processInstance/page")
    @ApiOperation(value = "分页查询流程实例列表")
    @SaCheckPermission("wf:processInstance:page")
    public CommonResult<CommonPage<ProcessInstanceVO>> page(@RequestBody ProcessInstancePageParam param) {
        return CommonResult.data(processInstanceService.page(param));
    }
}
