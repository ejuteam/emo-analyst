package com.emoOpner.controller;

import com.emoOpner.request.AccessTokenRequest;
import com.emoOpner.request.WeiBoInfoRequest;
import com.emoOpner.response.AResponse;
import com.emoOpner.service.WeiBoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.emoOpner.constant.SwaggerDocumentConstant.QUERY;

@RestController
@Api(tags = "微博接口")
@RequestMapping(value = "/w/weiboInfos")
public class WeiBoController {

    @Autowired
    WeiBoService weiBoService;

    @ApiOperation(notes = "queryWeiBoInfo", value = QUERY + "查询微博列表")
    @RequestMapping(value = "/queryWeiBoInfo", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public AResponse queryWeiBoInfo(@RequestBody WeiBoInfoRequest request) {
        return weiBoService.queryWeiBoInfo(request);
    }

    @ApiOperation(notes = "queryAuthUrl", value = QUERY + "获取授权地址")
    @RequestMapping(value = "/queryAuthUrl", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public AResponse getAuthUrl(@RequestBody AccessTokenRequest accessToken){
        return weiBoService.getAuthUrl();
    }

    @ApiOperation(notes = "getAccessToken", value = QUERY + "获取access_token")
    @RequestMapping(value = "/getAccessToken", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public AResponse getAccessToken(@RequestBody AccessTokenRequest accessToken){
        return weiBoService.getAccessToken(accessToken);
    }

    @ApiOperation(notes = "getWeiBos", value = QUERY + "获取微博")
    @RequestMapping(value = "/getWeiBos", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public AResponse getWeiBos(@RequestBody AccessTokenRequest accessToken){
        return weiBoService.getWeiBos(accessToken);
    }

    @ApiOperation(notes = "getWeiBosByUser", value = QUERY + "获取当前登录用户及其所关注（授权）用户的最新微博")
    @RequestMapping(value = "/getWeiBosByUser", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public AResponse getWeiBosByUser(@RequestBody AccessTokenRequest accessToken){
        return weiBoService.getWeiBosByUser(accessToken);
    }
}
