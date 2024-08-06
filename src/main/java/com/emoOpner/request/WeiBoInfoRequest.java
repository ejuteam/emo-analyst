package com.emoOpner.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class WeiBoInfoRequest {
    private Integer id;

    @ApiModelProperty(notes = "微博ID")
    private Integer wbId;

    @ApiModelProperty(notes = "微博MID")
    private Integer wbMid;

    @ApiModelProperty(notes = "微博ID字符串")
    private String wbIdStr;
}
