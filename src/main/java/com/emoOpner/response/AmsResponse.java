package com.emoOpner.response;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class AmsResponse<T> extends AResponse {
    @ApiModelProperty(
            notes = "每页数:在分页查询中有用"
    )
    private long pageSize = -1L;
    @ApiModelProperty(
            notes = "当前页:在分页查询中有用"
    )
    private long currentPage = -1L;

    public AmsResponse() {
    }

    public long getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public long getCurrentPage() {
        return this.currentPage;
    }

    public void setCurrentPage(long currentPage) {
        this.currentPage = currentPage;
    }
}
