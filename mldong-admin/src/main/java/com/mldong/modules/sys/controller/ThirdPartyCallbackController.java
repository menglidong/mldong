package com.mldong.modules.sys.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.mldong.base.CommonResult;
import com.mldong.context.constant.ConstantContextHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 第三方接口回调
 * @author mldong
 * @date 2023/12/15
 */
@RestController
@Api(tags = "第三方回调统一入口")
@RequiredArgsConstructor
@Slf4j
public class ThirdPartyCallbackController {
    @PostMapping("/thirdParty/checkStarred")
    @ApiOperation(value="校验是否点亮项目")
    @SaIgnore
    public CommonResult<?> checkStarred(@RequestBody Dict args) {
        String code = args.getStr("code");
        Dict params = Dict.create();
        params.put("code", code);
        params.put("client_id", ConstantContextHolder.getSysConfig("GITEE_CLIENT_ID",String.class,false));
        params.put("client_secret",ConstantContextHolder.getSysConfig("GITEE_CLIENT_SECRET",String.class,false));
        params.put("redirect_uri","https://flow.mldong.com/oauth/gitee");
        String url = StrUtil.format("https://gitee.com/oauth/token?grant_type=authorization_code&code={code}&client_id={client_id}&redirect_uri={redirect_uri}&client_secret={client_secret}",params);
        log.info("url:{}",url);
        String body = HttpUtil.post(url,"");
        log.info("get_gitee_token:{}",body);
        Dict bodyObj = JSONUtil.toBean(body, Dict.class);
        String accessToken = bodyObj.getStr("access_token");
        log.info("access_token:{}",accessToken);
        String owner = "mldong";
        String repo = "mldong";
        String checkUrl = StrUtil.format("https://gitee.com/api/v5/user/starred/{}/{}?access_token={}",owner,repo,accessToken);
        log.info("checkUrl:{}",checkUrl);
        body = HttpUtil.get(checkUrl);
        log.info("get_gitee_starred:{}",body);
        return CommonResult.data(Dict.of("flag", ObjectUtil.isEmpty(body)));
    }
}
