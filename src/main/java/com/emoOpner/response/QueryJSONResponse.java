package com.emoOpner.response;


import io.swagger.annotations.ApiModelProperty;

public class QueryJSONResponse extends JSONResponse {
    @ApiModelProperty(
            notes = "每页数:在分页查询中有用"
    )
    private long pageSize = -1L;
    @ApiModelProperty(
            notes = "当前页:在分页查询中有用"
    )
    private long currentPage = -1L;
    @ApiModelProperty(
            notes = "总记录数:在分页查询中有用"
    )
    private long totalRecord = -1L;
    @ApiModelProperty(
            notes = "总页数:在分页查询中有用"
    )
    private long totalPage = -1L;

    public QueryJSONResponse(int code, String note) {
        super(code, note);
    }

    public QueryJSONResponse() {
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

    public long getTotalRecord() {
        return this.totalRecord;
    }

    public void setTotalRecord(long totalRecord) {
        this.totalRecord = totalRecord;
    }

    public long getTotalPage() {
        return this.totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }
}