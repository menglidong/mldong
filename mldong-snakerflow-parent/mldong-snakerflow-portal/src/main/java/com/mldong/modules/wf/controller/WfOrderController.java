package com.mldong.modules.wf.controller;

import com.mldong.common.base.CommonPage;
import com.mldong.common.base.CommonResult;
import com.mldong.modules.wf.dto.WfIdParam;
import com.mldong.modules.wf.dto.WfOrderPageParam;
import com.mldong.modules.wf.dto.WfOrderParam;
import com.mldong.modules.wf.service.WfOrderService;
import com.mldong.modules.wf.vo.WfHighlihtDataVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.AuthorizationScope;
import org.snaker.engine.entity.HistoryOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wf/order")
@Api(tags="wf-流程实例",authorizations={
        @Authorization(value="wf|工作流",scopes={
                @AuthorizationScope(description="流程实例",scope="wf:order:index")
        })
})
public class WfOrderController {
        @Autowired
        private WfOrderService orderService;
        @PostMapping("startAndExecute")
        @ApiOperation(value="启动并执行第一个任务节点", notes = "wf:order:startAndExecute")
        @Transactional(rollbackFor = Exception.class)
        public CommonResult<?> startAndExecute(@RequestBody @Validated({WfOrderParam.ProcessId.class}) WfOrderParam param){
                orderService.startAndExecute(param);
                return CommonResult.success();
        }
        @PostMapping("startAndExecuteByName")
        @ApiOperation(value="启动并执行第一个任务节点(通过名称)", notes = "wf:order:startAndExecuteByName")
        @Transactional(rollbackFor = Exception.class)
        public CommonResult<?> startAndExecuteByName(@RequestBody @Validated({WfOrderParam.ProcessName.class}) WfOrderParam param){
                orderService.startAndExecuteByName(param);
                return CommonResult.success();
        }
        @PostMapping("list")
        @ApiOperation(value="我发起的流程实例", notes = "wf:order:list")
        public CommonResult<CommonPage<HistoryOrder>> list(@RequestBody WfOrderPageParam param) {
                return CommonResult.success(orderService.list(param));
        }
        @PostMapping("get")
        @ApiOperation(value="获取流程实例详情", notes = "wf:order:get")
        public CommonResult<HistoryOrder> get(@RequestBody WfIdParam param) {
                return CommonResult.success(orderService.get(param.getId()));
        }
        @PostMapping("cascadeRemove")
        @ApiOperation(value="级联删除", notes = "wf:order:cascadeRemove")
        public CommonResult<?> cascadeRemove(@RequestBody @Validated WfIdParam param) {
                orderService.cascadeRemove(param.getId());
                return CommonResult.success();
        }
        @PostMapping("highLightData")
        @ApiOperation(value="获取流程实例高亮数据", notes = "wf:order:highLightData")
        public CommonResult<WfHighlihtDataVO> highLightData(@RequestBody WfIdParam param) {
                return CommonResult.success(orderService.highLightData(param.getId()));
        }
        @PostMapping("takeBack")
        @ApiOperation(value="取回流程", notes = "wf:order:takeBack")
        public CommonResult<?> takeBack(@RequestBody WfIdParam param) {
                orderService.takeBack(param.getId());
                return CommonResult.success();
        }
        @PostMapping("undo")
        @ApiOperation(value="作废流程", notes = "wf:order:undo")
        public CommonResult<?> undo(@RequestBody WfIdParam param) {
                orderService.undo(param.getId());
                return CommonResult.success();
        }

}
