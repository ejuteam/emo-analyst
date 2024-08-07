package com.emoOpner.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class WeiBoContentRequest {
    @ApiModelProperty(notes = "微博ID")
    private String weiBoId;

    @ApiModelProperty(notes = "userId")
    private String userId;

    @ApiModelProperty(notes = "微博正文")
    private String text;

    @ApiModelProperty(notes = "开始时间")
    private Date startTime;

    @ApiModelProperty(notes = "结束时间")
    private Date endTime;
}
