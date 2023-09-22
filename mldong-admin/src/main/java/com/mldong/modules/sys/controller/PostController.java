package com.mldong.modules.sys.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.mldong.base.CommonPage;
import com.mldong.base.CommonResult;
import com.mldong.base.IdParam;
import com.mldong.base.IdsParam;
import com.mldong.modules.sys.dto.PostPageParam;
import com.mldong.modules.sys.dto.PostParam;
import com.mldong.modules.sys.service.PostService;
import com.mldong.modules.sys.vo.PostVO;
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
    * 岗位 前端控制器
    * </p>
*
* @author mldong
* @since 2023-09-21
*/
@RestController
@Api(tags = "岗位管理")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    /**
     * 添加岗位
     * @param param
     * @return
     */
    @PostMapping("/sys/post/save")
    @ApiOperation(value = "添加岗位")
    @SaCheckPermission("sys:post:save")
    public CommonResult<?> save(@RequestBody @Validated({Groups.Save.class}) PostParam param) {
        postService.save(param);
        return CommonResult.ok();
    }
    /**
     * 删除岗位
     * @param param
     * @return
     */
    @PostMapping("/sys/post/remove")
    @ApiOperation(value = "删除岗位")
    @SaCheckPermission("sys:post:remove")
    public CommonResult<?> remove(@RequestBody IdsParam param) {
        postService.removeBatchByIds(param.getIds());
        return CommonResult.ok();
    }
    /**
     * 修改岗位
     * @param param
     * @return
     */
    @PostMapping("/sys/post/update")
    @ApiOperation(value = "修改岗位")
    @SaCheckPermission("sys:post:update")
    public CommonResult<?> update(@RequestBody @Validated({Groups.Update.class}) PostParam param) {
        postService.update(param);
        return CommonResult.ok();
    }
    /**
     * 查询单个岗位
     * @param param
     * @return
     */
    @PostMapping("/sys/post/detail")
    @ApiOperation(value = "查询单个岗位")
    @SaCheckPermission("sys:post:detail")
    public CommonResult<PostVO> detail(@RequestBody IdParam param) {
        PostVO post = postService.findById(param.getId());
        return CommonResult.data(post);
    }
    /**
     *分页查询岗位列表
     * @param param
     * @return
     */
    @PostMapping("/sys/post/page")
    @ApiOperation(value = "分页查询岗位列表")
    @SaCheckPermission("sys:post:page")
    public CommonResult<CommonPage<PostVO>> page(@RequestBody PostPageParam param) {
        return CommonResult.data(postService.page(param));
    }
}
