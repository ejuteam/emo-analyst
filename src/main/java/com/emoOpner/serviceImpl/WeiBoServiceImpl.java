package com.emoOpner.serviceImpl;

import com.emoOpner.mapper.WeiBoMapper;
import com.emoOpner.po.APIClient;
import com.emoOpner.po.JsonDict;
import com.emoOpner.po.WeiBoInfo;
import com.emoOpner.request.AccessTokenRequest;
import com.emoOpner.request.WeiBoInfoRequest;
import com.emoOpner.response.AResponse;
import com.emoOpner.service.WeiBoService;
import com.emoOpner.utils.AResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.emoOpner.config.WeiBoApiConfig.APP_KEY;
import static com.emoOpner.config.WeiBoApiConfig.USER_TIMELINE;

@Service
public class WeiBoServiceImpl implements WeiBoService {

    @Autowired
    WeiBoMapper weiBoMapper;

    @Override
    public AResponse<WeiBoInfo> queryWeiBoInfo(WeiBoInfoRequest request) {
        try{
            List<WeiBoInfo> weiBoInfos = weiBoMapper.queryWeiBoList(request);
            return AResultUtil.success(weiBoInfos,"查询成功");
        }catch (Exception e){
            e.printStackTrace();
            return AResultUtil.error("查询失败!");
        }
    }

    @Override
    public AResponse getAuthUrl() {
        APIClient client = new APIClient();
        return AResultUtil.success(client.getAuthorizeUrl());
    }

    @Override
    public AResponse getAccessToken(AccessTokenRequest accessToken) {
        APIClient client = new APIClient();
        try {
            JsonDict jsonDict = client.requestAccessToken(client, accessToken.getCode());
            System.out.println(jsonDict.toString());
            return AResultUtil.success("获取access_token: " + jsonDict.getAccessToken());
        } catch (IOException e) {
            e.printStackTrace();
            return AResultUtil.error("无法获取access_token");
        }
    }

    @Override
    public AResponse getWeiBos(AccessTokenRequest accessToken) {
        Map<String,String> param = new HashMap<>();
        param.put("client_id",APP_KEY);
        param.put("access_token",accessToken.getAccessToken());
        APIClient apiClient = new APIClient();
        String res = apiClient.getWeiBos(USER_TIMELINE,param);
        System.out.println(res);
        return null;
    }
}
