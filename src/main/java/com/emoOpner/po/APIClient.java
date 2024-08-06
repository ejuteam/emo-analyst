package com.emoOpner.po;

import com.emoOpner.utils.WeiBoHttpUtil;
import lombok.Data;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.emoOpner.config.WeiBoApiConfig.*;

/**
 * 微博API主类
 */
@Data
public class APIClient {
    public String app_key;
    public String authurl;
    public String api_url;
    public String expires;
    public String app_secret;
    public String redirect_url;
    public String access_token;
    private String clientSecret; //用户信息密钥
    public String responce_type;
    public WeiBoUser user;

    public APIClient(){
        this.app_key = APP_KEY;
        this.authurl = AUTHURL;
        this.api_url = "";
        this.expires = "";
        this.app_secret = APP_SECRET;
        this.redirect_url = REDIRECT_URL;
        this.access_token = "";
        this.clientSecret = "";
        this.responce_type = RESPONCE_TYPE;
    }


    /**
     * 跳转授权地址
     *
     * @return
     */
    public String getAuthorizeUrl()  {
        WeiBoHttpUtil weiBoHttpUtil = new WeiBoHttpUtil();
        String redirect = (this.getRedirect_url() != null) ? this.getRedirect_url() : "";
        String responseType = this.getResponce_type().isEmpty() ? "code" : this.getResponce_type();
        return String.format("%sauthorize?%s", this.getAuthurl(), weiBoHttpUtil.encodeParamsAuthUrl(this.getApp_key(), responseType, redirect));
    }

    /**
     * 获取access_token
     * @param client
     * @param code
     * @return
     * @throws IOException
     */
    public JsonDict requestAccessToken(APIClient client, String code) throws IOException {
        WeiBoHttpUtil weiBoHttpUtil =  new WeiBoHttpUtil();
        Map<String, String> params = new HashMap<>();
        params.put("client_id", client.getApp_key());
        params.put("client_secret", client.getApp_secret());
        params.put("redirect_uri", client.getRedirect_url());
        params.put("code", code);
        params.put("grant_type", "authorization_code");

        String response = weiBoHttpUtil.httpPost(client.getAuthurl() + "access_token",null, params);
        return weiBoHttpUtil.parseAccessToken(response);
    }

    public String getWeiBos(String url, Map<String, String> params){
        WeiBoHttpUtil weiBoHttpUtil  = new WeiBoHttpUtil();
        try {
            String response = weiBoHttpUtil.httpGet(url,null,params);
            return response;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
