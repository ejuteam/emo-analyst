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

    @ApiModelProperty(notes = "创建时间")
    private Date createTime;

}
