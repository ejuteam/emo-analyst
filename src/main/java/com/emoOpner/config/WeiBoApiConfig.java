package com.emoOpner.config;


import lombok.Data;

@Data
public class WeiBoApiConfig {
    public static final String APP_KEY = "3586408619";
    public static final String AUTHURL = "https://api.weibo.com/oauth2/";
    public static final String APP_SECRET = "44ca5ba42934fafc548c6f29cc515ef1";
    public static final String REDIRECT_URL = "https://api.weibo.com/oauth2/default.html";
    public static final String RESPONCE_TYPE = "code";
    public static final String USER_TIMELINE = "https://api.weibo.com/2/statuses/user_timeline.json";
}
