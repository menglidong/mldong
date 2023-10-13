package com.mldong.modules.wf.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.json.JSONObject;
import com.mldong.base.CommonPage;
import com.mldong.base.CommonResult;
import com.mldong.base.IdParam;
import com.mldong.base.IdsParam;
import com.mldong.modules.wf.dto.ProcessDesignPageParam;
import com.mldong.modules.wf.dto.ProcessDesignParam;
import com.mldong.modules.wf.service.ProcessDesignService;
import com.mldong.modules.wf.vo.ProcessDesignTypeVO;
import com.mldong.modules.wf.vo.ProcessDesignVO;
import com.mldong.validation.Groups;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
* <p>
    * 流程设计 前端控制器
    * </p>
*
* @author mldong
* @since 2023-09-25
*/
@RestController
@Api(tags = "流程设计管理")
@RequiredArgsConstructor
public class ProcessDesignController {
    private final ProcessDesignService processDesignService;
    /**
     * 添加流程设计
     * @param param
     * @return
     */
    @PostMapping("/wf/processDesign/save")
    @ApiOperation(value = "添加流程设计")
    @SaCheckPermission("wf:processDesign:save")
    public CommonResult<?> save(@RequestBody @Validated({Groups.Save.class}) ProcessDesignParam param) {
        processDesignService.save(param);
        return CommonResult.ok();
    }
    /**
     * 删除流程设计
     * @param param
     * @return
     */
    @PostMapping("/wf/processDesign/remove")
    @ApiOperation(value = "删除流程设计")
    @SaCheckPermission("wf:processDesign:remove")
    public CommonResult<?> remove(@RequestBody IdsParam param) {
        processDesignService.removeBatchByIds(param.getIds());
        return CommonResult.ok();
    }
    /**
     * 修改流程设计
     * @param param
     * @return
     */
    @PostMapping("/wf/processDesign/update")
    @ApiOperation(value = "修改流程设计")
    @SaCheckPermission("wf:processDesign:update")
    public CommonResult<?> update(@RequestBody @Validated({Groups.Update.class}) ProcessDesignParam param) {
        processDesignService.update(param);
        return CommonResult.ok();
    }
    /**
     * 查询单个流程设计
     * @param param
     * @return
     */
    @PostMapping("/wf/processDesign/detail")
    @ApiOperation(value = "查询单个流程设计")
    @SaCheckPermission("wf:processDesign:detail")
    public CommonResult<ProcessDesignVO> detail(@RequestBody IdParam param) {
        ProcessDesignVO processDesign = processDesignService.findById(param.getId());
        return CommonResult.data(processDesign);
    }
    /**
     *分页查询流程设计列表
     * @param param
     * @return
     */
    @PostMapping("/wf/processDesign/page")
    @ApiOperation(value = "分页查询流程设计列表")
    @SaCheckPermission("wf:processDesign:page")
    public CommonResult<CommonPage<ProcessDesignVO>> page(@RequestBody ProcessDesignPageParam param) {
        return CommonResult.data(processDesignService.page(param));
    }
    /**
     * 更新流程设计定义
     * @param jsonObject
     * @return
     */
    @PostMapping("/wf/processDesign/updateDefine")
    @ApiOperation(value = "更新流程设计定义")
    @SaCheckPermission("wf:processDesign:updateDefine")
    public CommonResult<?> updateDefine(@RequestBody JSONObject jsonObject) {
        processDesignService.updateDefine(jsonObject);
        return CommonResult.ok();
    }
    /**
     * 部署流程定义
     * @param param
     * @return
     */
    @PostMapping("/wf/processDesign/deploy")
    @ApiOperation(value = "部署流程定义")
    @SaCheckPermission("wf:processDesign:deploy")
    public CommonResult<?> deploy(@RequestBody IdParam param) {
        processDesignService.deploy(param.getId());
        return CommonResult.ok();
    }
    /**
     * 重新部署流程定义
     * @param param
     * @return
     */
    @PostMapping("/wf/processDesign/redeploy")
    @ApiOperation(value = "重新部署流程定义")
    @SaCheckPermission("wf:processDesign:redeploy")
    public CommonResult<?> redeploy(@RequestBody IdParam param) {
        processDesignService.redeploy(param.getId());
        return CommonResult.ok();
    }
    /**
     * 按流程分类给流程设计分组
     * @return
     */
    @PostMapping("/wf/processDesign/listByType")
    @ApiOperation(value = "按流程分类给流程设计分组")
    @SaCheckPermission("wf:processDesign:listByType")
    public CommonResult<List<ProcessDesignTypeVO>> listByType() {
        return CommonResult.data(processDesignService.listByType());
    }
}
