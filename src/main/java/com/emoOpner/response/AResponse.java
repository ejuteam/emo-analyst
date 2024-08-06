package com.emoOpner.response;

import io.swagger.annotations.ApiModelProperty;
import java.util.List;

public class AResponse<T> extends QueryJSONResponse {
    @ApiModelProperty(
            notes = "错误代码"
    )
    private String errCode;
    @ApiModelProperty(
            notes = "执行时间"
    )
    private Long duration;
    @ApiModelProperty(
            notes = "输出数据"
    )
    private T data;
    @ApiModelProperty(
            notes = "通用查询字段关系"
    )
    private List<T> columns;
    @ApiModelProperty(
            notes = "输出列表"
    )
    private List<T> records;

    public AResponse() {
    }

    public String getErrCode() {
        return this.errCode;
    }

    public Long getDuration() {
        return this.duration;
    }

    public T getData() {
        return this.data;
    }

    public List<T> getColumns() {
        return this.columns;
    }

    public List<T> getRecords() {
        return this.records;
    }

    public void setErrCode(final String errCode) {
        this.errCode = errCode;
    }

    public void setDuration(final Long duration) {
        this.duration = duration;
    }

    public void setData(final T data) {
        this.data = data;
    }

    public void setColumns(final List<T> columns) {
        this.columns = columns;
    }

    public void setRecords(final List<T> records) {
        this.records = records;
    }

    public String toString() {
        return "AResponse(errCode=" + this.getErrCode() + ", duration=" + this.getDuration() + ", data=" + this.getData() + ", columns=" + this.getColumns() + ", records=" + this.getRecords() + ")";
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof AResponse)) {
            return false;
        } else {
            AResponse<?> other = (AResponse)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label71: {
                    Object this$errCode = this.getErrCode();
                    Object other$errCode = other.getErrCode();
                    if (this$errCode == null) {
                        if (other$errCode == null) {
                            break label71;
                        }
                    } else if (this$errCode.equals(other$errCode)) {
                        break label71;
                    }

                    return false;
                }

                Object this$duration = this.getDuration();
                Object other$duration = other.getDuration();
                if (this$duration == null) {
                    if (other$duration != null) {
                        return false;
                    }
                } else if (!this$duration.equals(other$duration)) {
                    return false;
                }

                label57: {
                    Object this$data = this.getData();
                    Object other$data = other.getData();
                    if (this$data == null) {
                        if (other$data == null) {
                            break label57;
                        }
                    } else if (this$data.equals(other$data)) {
                        break label57;
                    }

                    return false;
                }

                Object this$columns = this.getColumns();
                Object other$columns = other.getColumns();
                if (this$columns == null) {
                    if (other$columns != null) {
                        return false;
                    }
                } else if (!this$columns.equals(other$columns)) {
                    return false;
                }

                Object this$records = this.getRecords();
                Object other$records = other.getRecords();
                if (this$records == null) {
                    if (other$records == null) {
                        return true;
                    }
                } else if (this$records.equals(other$records)) {
                    return true;
                }

                return false;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof AResponse;
    }

    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        Object $errCode = this.getErrCode();
        result = result * 59 + ($errCode == null ? 43 : $errCode.hashCode());
        Object $duration = this.getDuration();
        result = result * 59 + ($duration == null ? 43 : $duration.hashCode());
        Object $data = this.getData();
        result = result * 59 + ($data == null ? 43 : $data.hashCode());
        Object $columns = this.getColumns();
        result = result * 59 + ($columns == null ? 43 : $columns.hashCode());
        Object $records = this.getRecords();
        result = result * 59 + ($records == null ? 43 : $records.hashCode());
        return result;
    }
}
