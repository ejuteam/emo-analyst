package com.emoOpner.response;


import io.swagger.annotations.ApiModelProperty;

public class JSONResponse {
    public static final int CODE_SUCCESS = 1;
    public static final int CODE_FAIL = -1;
    public static final int CODE_VALID = -2;
    public static final int CODE_AC_AUTH_FAIL = -9;
    @ApiModelProperty(
            notes = "状态码:>0,调用成功,<0,调用失败"
    )
    private int code = 1;
    @ApiModelProperty(
            notes = "调用说明:code<0时，是失败原因"
    )
    private String note = "";

    public JSONResponse() {
        this.code = 1;
        this.note = "";
    }

    public JSONResponse(int code, String note) {
        this.code = code;
        this.note = note;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}