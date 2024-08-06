package com.emoOpner.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class WeiBoInfo {
    private Integer id;

    @ApiModelProperty(notes = "转发数")
    private String repostsCount;

    @ApiModelProperty(notes = "评论数")
    private String commentsCount;

    @ApiModelProperty(notes = "表态数")
    private String attiudesCount;

    @ApiModelProperty(notes = "微博ID")
    private Integer wbId;

    @ApiModelProperty(notes = "微博MID")
    private Integer wbMid;

    @ApiModelProperty(notes = "微博内容")
    private String text;

    @ApiModelProperty(notes = "微博来源")
    private String source;

    @ApiModelProperty(notes = "微博配图ID")
    private String picIds;

    @ApiModelProperty(notes = "关联用户ID")
    private String userId;

    @ApiModelProperty(notes = "原始图片地址")
    private String originalPic;

    @ApiModelProperty(notes = "缩略图片地址")
    private String thumbnallPic;

    @ApiModelProperty(notes = "微博创建时间")
    private String createdTime;

    @ApiModelProperty(notes = "微博ID字符串")
    private String wbIdStr;

    @ApiModelProperty(notes = "中等尺寸图片地址")
    private String bmiddlePic;

    @ApiModelProperty(notes = "是否被收藏 1是 0否")
    private Integer favorited;

    @ApiModelProperty(notes = "是否被截断 1是 0否")
    private Integer truncated;

    @ApiModelProperty(notes = "被转发的原微博信息字段")
    private String retweetedStatus;
}
