package com.mldong.security.controller;

import com.mldong.common.annotation.AuthIgnore;
import com.mldong.common.base.CommonResult;
import com.mldong.security.vo.Oauth2TokenVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.Map;

/**
 * 自定义Oauth2获取令牌接口
 */
@Controller
@RequestMapping("/oauth")
@Api(tags = "Oauth2登录认证")
public class AuthController {
    @Resource
    private TokenEndpoint tokenEndpoint;
    @Autowired
    AuthenticationManager authenticationManager;
    @RequestMapping(value = "/token", method = RequestMethod.POST)
    @AuthIgnore
    @ApiOperation(value = "用户名密码登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name="client_id", value = "应用ID",required = true),
            @ApiImplicitParam(name="client_secret", value = "应用密钥",required = true),
            @ApiImplicitParam(name="grant_type", value = "准入类型<password>",required = true),
            @ApiImplicitParam(name="scope", value = "授权域",required = false),
            @ApiImplicitParam(name="username", value = "登录名",required = false),
            @ApiImplicitParam(name="password", value = "登录密码",required = false),
            @ApiImplicitParam(name="mobile_phone", value = "手机号码",required = false),
            @ApiImplicitParam(name="sms_code", value = "短信验证码",required = false),
            @ApiImplicitParam(name="refresh_token", value = "刷新token",required = false),

    })
    @ResponseBody
    public CommonResult<Oauth2TokenVo> postAccessToken(@ApiIgnore Principal principal,@ApiIgnore @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();
        Oauth2TokenVo oauth2TokenVo = new Oauth2TokenVo();
        oauth2TokenVo.setToken(oAuth2AccessToken.getValue());
        oauth2TokenVo.setRefreshToken(oAuth2AccessToken.getRefreshToken().getValue());
        oauth2TokenVo.setExpiresIn(oAuth2AccessToken.getExpiresIn());
        oauth2TokenVo.setTokenHead("Bearer ");
        return CommonResult.success(oauth2TokenVo);
    }
}