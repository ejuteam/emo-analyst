package com.emoOpner.utils;

import com.huaban.analysis.jieba.JiebaSegmenter;

import java.util.List;
import java.util.stream.Collectors;

public class DataPreprocessor {
    public static List<String> preprocessTexts(List<String> texts) {
        return texts.stream().map(DataPreprocessor::preprocess).collect(Collectors.toList());
    }

    public static String preprocess(String text) {
        // 使用Jieba进行中文分词
        JiebaSegmenter segmenter = new JiebaSegmenter();
        List<String> termList = segmenter.process(text, JiebaSegmenter.SegMode.INDEX)
                .stream()
                .map(segItem -> segItem.word)
                .collect(Collectors.toList());
        String cleanText = String.join(",", termList);
        /*// 使用THULAC进行中文分词
        Segmenter segmenter = new Segmenter();
        List<String> termList = segmenter.segment(text);
        String cleanText = String.join(" ", termList);*/
        return cleanText;
    }
}
