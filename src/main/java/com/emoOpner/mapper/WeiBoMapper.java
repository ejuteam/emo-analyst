package com.emoOpner.mapper;

import com.emoOpner.po.WeiBoContent;
import com.emoOpner.po.WeiBoInfo;
import com.emoOpner.request.WeiBoContentRequest;
import com.emoOpner.request.WeiBoInfoRequest;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface WeiBoMapper {
    List<WeiBoInfo> queryWeiBoList(WeiBoInfoRequest request);

    void insertWeiBoContent(WeiBoContent weiBoContent);

    List<WeiBoContent> queryWeiBoContent(WeiBoContentRequest request);

    void delWeiBoContents();

    void delWeiBoContentById(WeiBoContentRequest delWeiBoContentById);
}
