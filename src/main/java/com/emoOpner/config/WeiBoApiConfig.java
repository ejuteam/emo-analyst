package com.emoOpner.config;


import lombok.Data;

@Data
public class WeiBoApiConfig {
    public static final String APP_KEY = "3586408619";
    public static final String AUTHURL = "https://api.weibo.com/oauth2/";
    public static final String APP_SECRET = "";

    /**
     * 回调地址
     */
    public static final String REDIRECT_URL = "https://api.weibo.com/oauth2/default.html";
    public static final String RESPONCE_TYPE = "code";

    /**
     * 获取授权用户发布的微博
     */
    public static final String USER_TIMELINE = "https://api.weibo.com/2/statuses/user_timeline.json";

    /**
     * 获取当前登录用户及其所关注（授权）用户的最新微博
     */
    public static final String HOME_TIMELINE = "https://api.weibo.com/2/statuses/home_timeline.json";
}
