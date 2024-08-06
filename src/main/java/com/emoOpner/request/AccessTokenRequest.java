package com.emoOpner.request;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;


@Data
public class AccessTokenRequest {
    @ApiModelProperty(notes = "code")
    private String code;

    @ApiModelProperty(notes = "accessToken")
    private String accessToken;
}
