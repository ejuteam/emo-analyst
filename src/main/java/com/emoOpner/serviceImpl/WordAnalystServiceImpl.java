package com.emoOpner.serviceImpl;

import com.emoOpner.mapper.WeiBoMapper;
import com.emoOpner.mapper.WordAnalystMapper;
import com.emoOpner.po.WeiBoContent;
import com.emoOpner.request.WeiBoContentRequest;
import com.emoOpner.response.AResponse;
import com.emoOpner.service.WordAnalystService;
import com.emoOpner.utils.DataPreprocessor;
import com.emoOpner.utils.AResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordAnalystServiceImpl implements WordAnalystService {

    @Autowired
    WeiBoMapper weiBoMapper;
    @Autowired
    WordAnalystMapper wordAnalystMapper;
    @Override
    public AResponse doTokenization() {
        try{
            List<WeiBoContent> weiBoContents = weiBoMapper.queryWeiBoContent(new WeiBoContentRequest());
            DataPreprocessor dataPreprocessor = new DataPreprocessor();
            WeiBoContent weiBoContent = new WeiBoContent();
            weiBoContents.stream().forEach(item -> {
                weiBoContent.setWeiBoId(item.getWeiBoId());
                weiBoContent.setTokenizeText(dataPreprocessor.preprocess(item.getText()));
                weiBoMapper.updateWeiBoContent(weiBoContent);
            });
            wordAnalystMapper.doStopWord();
            return AResultUtil.success("分词成功");
        }catch (Exception e){
            e.printStackTrace();
            return AResultUtil.error("分词失败");
        }
    }
}
