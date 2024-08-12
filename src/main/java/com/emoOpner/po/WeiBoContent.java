package com.emoOpner.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class WeiBoContent {
    @ApiModelProperty(notes = "微博ID")
    private String weiBoId;

    @ApiModelProperty(notes = "userId")
    private String userId;

    @ApiModelProperty(notes = "微博正文")
    private String text;

    @ApiModelProperty(notes = "入库时间")
    private Date createTime;

    @ApiModelProperty(notes = "微博发布时间")
    private String postTime;

    @ApiModelProperty(notes = "正文分词")
    private String tokenizeText;

    @ApiModelProperty(notes = "处理状态 0未分词 1已分词")
    private String status;
}
