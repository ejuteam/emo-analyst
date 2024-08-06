package com.emoOpner.utils;

import com.emoOpner.po.APIClient;
import com.emoOpner.po.JsonDict;
import lombok.Data;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * 微博API工具类
 */
@Data
public class WeiBoHttpUtil {
    private static final Logger LOGGER = Logger.getLogger(APIClient.class.getName());
    private static final String _HTTP_GET = "GET";
    private static final String _HTTP_POST = "POST";
    private static final String _HTTP_UPLOAD = "UPLOAD";


    public String httpGet(String url, String authorization, Map<String, String> params) throws IOException {
        LOGGER.info("GET " + url);
        return httpCall(url, _HTTP_GET, authorization, params);
    }

    public String httpPost(String url, String authorization, Map<String, String> params) throws IOException {
        LOGGER.info("POST " + url);
        return httpCall(url, _HTTP_POST, authorization, params);
    }

    public String httpCall(String theUrl, String method, String authorization, Map<String, String> params) throws IOException {
        String boundary = null;
        String encodedParams = "";
        encodedParams = encodeParams(params);
        if (theUrl.contains("/remind/")) {
            // fix sina remind api:
            theUrl = theUrl.replace("https://api.", "https://rm.api.");
        }

        String httpUrl = method.equals(_HTTP_GET) ? theUrl + "?" + encodedParams : theUrl;
        String httpBody = method.equals(_HTTP_GET) ? null : encodedParams;

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            CloseableHttpResponse response = null;

            if (method.equals(_HTTP_GET)) {
                HttpGet httpGet = new HttpGet(httpUrl);
                if (authorization != null) {
                    httpGet.addHeader("Authorization", "OAuth2 " + authorization);
                }
                httpGet.addHeader("Accept-Encoding", "gzip");
                response = httpClient.execute(httpGet);
            } else if (method.equals(_HTTP_POST)) {
                HttpPost httpPost = new HttpPost(httpUrl);
                if (authorization != null) {
                    httpPost.addHeader("Authorization", "OAuth2 " + authorization);
                }
                if (boundary != null) {
                    httpPost.addHeader("Content-Type", "multipart/form-data; boundary=" + boundary);
                } else {
                    httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
                }
                httpPost.addHeader("Accept-Encoding", "gzip");
                if (httpBody != null) {
                    httpPost.setEntity(new StringEntity(httpBody, ContentType.APPLICATION_JSON));
                }
                response = httpClient.execute(httpPost);
            }

            if (response != null) {
                String responseBody = EntityUtils.toString(response.getEntity());
                JSONObject json = new JSONObject(new JSONTokener(responseBody));
                return json.toString();
            } else {
                throw new IOException("No response from server");
            }
        }
    }


    /**
     * 获取授权地址方法-入参拼接转码
     * @param clientId
     * @param responseType
     * @param redirectUri
     * @return
     */
    public String encodeParamsAuthUrl(String clientId, String responseType, String redirectUri) {
        StringBuilder encodedParams = new StringBuilder();
        try {
            encodedParams.append("client_id=").append(URLEncoder.encode(clientId, "UTF-8"));
            encodedParams.append("&response_type=").append(URLEncoder.encode(responseType, "UTF-8"));
            encodedParams.append("&redirect_uri=").append(URLEncoder.encode(redirectUri, "UTF-8"));

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // 处理异常
        }
        return encodedParams.toString();
    }

    /**
     * 发起微博API方法-参数拼接
     * @param params
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encodeParams(Map<String, String> params) throws UnsupportedEncodingException {
        StringBuilder encodedParams = new StringBuilder();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if (value instanceof String) {
                encodedParams.append(URLEncoder.encode(key, "UTF-8"))
                        .append("=")
                        .append(URLEncoder.encode((String) value, "UTF-8"))
                        .append("&");
            } else if (value instanceof List) {
                for (Object item : (List<?>) value) {
                    encodedParams.append(URLEncoder.encode(key, "UTF-8"))
                            .append("=")
                            .append(URLEncoder.encode(String.valueOf(item), "UTF-8"))
                            .append("&");
                }
            } else {
                encodedParams.append(URLEncoder.encode(key, "UTF-8"))
                        .append("=")
                        .append(URLEncoder.encode(String.valueOf(value), "UTF-8"))
                        .append("&");
            }
        }

        if (encodedParams.length() > 0) {
            encodedParams.setLength(encodedParams.length() - 1);
        }

        return encodedParams.toString();
    }

    /**
     * 获取acess_token结果-转换为JSON
     * @param response
     * @return
     */
    public JsonDict parseAccessToken(String response) {
        JSONObject r = new JSONObject(response);

        long current = Instant.now().getEpochSecond();
        long expiresIn = r.getLong("expires_in");
        long expires = expiresIn + current;

        if (r.has("remind_in")) {
            long remindIn = r.getLong("remind_in");
            long rtime = remindIn + current;
            if (rtime < expires) {
                expires = rtime;
            }
        }

        String accessToken = r.getString("access_token");
        String uid = r.has("uid") ? r.getString("uid") : null;

        return new JsonDict(accessToken, expires, expiresIn, uid);
    }
}
