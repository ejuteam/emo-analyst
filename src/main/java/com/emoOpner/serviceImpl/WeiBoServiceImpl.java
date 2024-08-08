package com.emoOpner.serviceImpl;

import com.emoOpner.mapper.WeiBoMapper;
import com.emoOpner.po.APIClient;
import com.emoOpner.po.JsonDict;
import com.emoOpner.po.WeiBoContent;
import com.emoOpner.po.WeiBoInfo;
import com.emoOpner.request.AccessTokenRequest;
import com.emoOpner.request.WeiBoContentRequest;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
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
    public AResponse<WeiBoContent> queryWeiBoContents(WeiBoContentRequest weiBoInfoRequest) {
        try{
            List<WeiBoContent> weiBoContents =weiBoMapper.queryWeiBoContent(weiBoInfoRequest);
            return AResultUtil.success(weiBoContents,"查询成功!");
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
            param.put("uid","6496346043");//某人的微博ID
            APIClient apiClient = new APIClient();
            String res = apiClient.getWeiBos(HOME_TIMELINE,param);

            JSONObject jsonObject = new JSONObject(res);
            ObjectMapper objectMapper = new ObjectMapper();

            //System.out.println(jsonObject.get("statuses").toString());
            //提取微博正文
            List<Map<String, Object>> list = objectMapper.readValue(jsonObject.get("statuses").toString(), new TypeReference<List<Map<String, Object>>>(){})
                    .stream()
                    //过滤mblogtype为0的微博
                    .filter(map -> !(map.containsKey("mblogtype") && map.get("mblogtype") instanceof Integer && (Integer) map.get("mblogtype") == 0))
                    .collect(Collectors.toList());
            WeiBoContent weiBoContent = new WeiBoContent();
            list.stream().forEach(item -> {
                //处理发布时间
                String postTimeStr = item.get("created_at").toString();
                try {
                    // 解析原始日期字符串为 Date 对象
                    Date date = null;
                    // 定义输入、输出日期格式
                    SimpleDateFormat inputFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH);
                    SimpleDateFormat outputFormat = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
                    date = inputFormat.parse(postTimeStr);
                    // 设置时区为东八区（可选，视需求而定）
                    outputFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
                    // 格式化 Date 对象为目标字符串格式
                    postTimeStr = outputFormat.format(date);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                //入库
                weiBoContent.setText(item.get("text").toString());
                weiBoContent.setUserId("6496346043");
                weiBoContent.setWeiBoId(item.get("id").toString());
                weiBoContent.setPostTime(postTimeStr);
                weiBoContent.setCreateTime(new Date());
                weiBoMapper.insertWeiBoContent(weiBoContent);
            });
            return AResultUtil.success("获取成功!");
        }catch (Exception e){
            e.printStackTrace();
            return AResultUtil.error("获取失败!");
        }
    }

    @Override
    public AResponse delWeiBoContents() {
        try {
            weiBoMapper.delWeiBoContents();
            return AResultUtil.success("全部删除成功!");
        }catch (Exception e){
            e.printStackTrace();
            return AResultUtil.error("全部删除成功!");
        }
    }

    @Override
    public AResponse delWeiBoContentById(WeiBoContentRequest weiBoContentRequest) {
        try {
            weiBoMapper.delWeiBoContentById(weiBoContentRequest);
            return AResultUtil.success(String.format("删除成功",weiBoContentRequest.getWeiBoId()));
        }catch (Exception e){
            e.printStackTrace();
            return AResultUtil.error("删除失败");
        }
    }
}
