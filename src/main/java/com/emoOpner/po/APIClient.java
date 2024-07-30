package com.emoOpner.po;

import lombok.Data;
import org.json.JSONObject;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.TreeMap;

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

    public APIClient(String app_key,      String authurl,      String api_url,
                     String expires,      String app_secret,   String responce_type,
                     String redirect_url, String access_token, String clientSecret){
        this.app_key = app_key;
        this.authurl = authurl;
        this.api_url = api_url;
        this.expires = expires;
        this.app_secret = app_secret;
        this.redirect_url = redirect_url;
        this.access_token = access_token;
        this.clientSecret = clientSecret;
        this.responce_type = responce_type;
    }

    /**
     * 解析签名请求
     * 1.加密数据先通过Base64解码，再检查algorithm是否为HMAC-SHA256
     * 2.解析后内容为 { 'uid': 12345, 'access_token': 'ABC123XYZ', 'expires': unix-timestamp },
     *           或 null
     * @param signedRequest 加密数据，包含用户、access_token等信息
     * @return
     */
    public Object parseSignedRequest(String signedRequest){
        try{
            //根据'.'分割签名数据为两个列表
            String[] parts = signedRequest.split("\\.");
            if (parts.length != 2) {
                System.out.println("--------------------");
                System.out.println("签名数据不合法!");
                System.out.println(signedRequest);
                System.out.println("--------------------");
                return null;
            }
            String encSig = parts[0];
            String encPayload = parts[1];

            //解密第一段数据
            byte[] sig = base64UrlDecode(encSig);
            //解密第二段数据
            JSONObject data = new JSONObject(new String(base64UrlDecode(encPayload)));
            if (!"HMAC-SHA256".equals(data.getString("algorithm"))) {
                System.out.println("签名算法必须为HMAC-SHA256");
                return null;
            }
            byte[] expectedSig = getExpectedSignature(encPayload);
            //提取最终信息
            if (MessageDigest.isEqual(expectedSig, sig)) {
                data.put("uid", data.optString("user_id", null));
                data.put("access_token", data.optString("oauth_token", null));
                if (data.has("expires")) {
                    long expires = data.getLong("expires");
                    data.put("expires_in", System.currentTimeMillis() / 1000 + expires);
                }
                return data;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密第一段数据
     * 手动将 - 替换为 +，将 _ 替换为 /，并添加必要的 = 填充字符。
     * @param input
     * @return
     */
    private byte[] base64UrlDecode(String input) {
        String normalized = input.replace('-', '+').replace('_', '/');
        switch (normalized.length() % 4) {
            case 2:
                normalized += "==";
                break;
            case 3:
                normalized += "=";
                break;
        }
        return Base64.getDecoder().decode(normalized);
    }

    /**
     * 解密第二段数据
     * 根据HmacSHA256签名计算方式
     * @param payload
     * @return
     * @throws Exception
     */
    private byte[] getExpectedSignature(String payload) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(clientSecret.getBytes(), "HmacSHA256");
        mac.init(secretKeySpec);
        return mac.doFinal(payload.getBytes());
    }

}
