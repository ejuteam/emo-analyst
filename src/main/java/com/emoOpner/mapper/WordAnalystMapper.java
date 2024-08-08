package com.emoOpner.mapper;

import com.emoOpner.po.StopWord;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface WordAnalystMapper {
    int queryStopWord(StopWord stopWord);
}
