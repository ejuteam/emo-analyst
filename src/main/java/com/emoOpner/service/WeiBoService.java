package com.emoOpner.service;

import com.emoOpner.po.WeiBoContent;
import com.emoOpner.po.WeiBoInfo;
import com.emoOpner.request.AccessTokenRequest;
import com.emoOpner.request.WeiBoContentRequest;
import com.emoOpner.request.WeiBoInfoRequest;
import com.emoOpner.response.AResponse;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public interface WeiBoService {
    public AResponse<WeiBoInfo> queryWeiBoInfo(WeiBoInfoRequest weiBoInfo);

    public AResponse<WeiBoContent> queryWeiBoContents(WeiBoContentRequest weiBoInfoRequest);

    public AResponse getAuthUrl();

    public AResponse getAccessToken(AccessTokenRequest accessToken);

    public AResponse getWeiBos(AccessTokenRequest accessToken);

    public AResponse getWeiBosByUser(AccessTokenRequest accessToken);

    public AResponse delWeiBoContents();

    public AResponse delWeiBoContentById(WeiBoContentRequest weiBoContentRequest);
}
