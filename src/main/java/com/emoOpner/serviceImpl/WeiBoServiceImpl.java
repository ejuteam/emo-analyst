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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.emoOpner.config.WeiBoApiConfig.*;

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
        param.put("uid","6496346043");
        //param.put("screen_name","zhufit");
        APIClient apiClient = new APIClient();
        String res = apiClient.getWeiBos(USER_TIMELINE,param);
        System.out.println(res);
        return null;
    }

    @Override
    public AResponse getWeiBosByUser(AccessTokenRequest accessToken) {
        try{
            Map<String,String> param = new HashMap<>();
            param.put("client_id",APP_KEY);
            param.put("access_token",accessToken.getAccessToken());
            param.put("uid","");//某人的微博ID
            APIClient apiClient = new APIClient();
            String res = apiClient.getWeiBos(HOME_TIMELINE,param);

            JSONObject jsonObject = new JSONObject(res);
            ObjectMapper objectMapper = new ObjectMapper();
            //提取微博正文
            List<Map<String, Object>> list = objectMapper.readValue(jsonObject.get("statuses").toString(), new TypeReference<List<Map<String, Object>>>(){})
                    .stream()
                    //过滤mblogtype为0的微博
                    .filter(map -> !(map.containsKey("mblogtype") && map.get("mblogtype") instanceof Integer && (Integer) map.get("mblogtype") == 0))
                    .collect(Collectors.toList());
            list.stream().forEach(item -> {
                //输出正文
                System.out.println(item.get("text"));
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
