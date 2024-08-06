package com.emoOpner.utils;



import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.emoOpner.response.AmsResponse;
import org.springframework.cglib.beans.BeanMap;

public class AResultUtil {
    public static final int CODE_SUCCESS = 1;
    public static final int CODE_ERROR = -1;

    public AResultUtil() {
    }

    public static <T> AmsResponse<T> success() {
        return success("");
    }

    public static <T> AmsResponse<T> success(String note) {
        AmsResponse<T> response = new AmsResponse();
        response.setCode(1);
        response.setNote(note);
        return response;
    }

    public static <T> AmsResponse<T> success(List<T> data) {
        return success(data, "");
    }

    public static <T> AmsResponse<T> success(List<T> data, String note) {
        AmsResponse<T> response = success();
        if (data != null) {
            List<Map<String, Object>> result = new ArrayList();
            Iterator var4 = data.iterator();

            while(var4.hasNext()) {
                T t = (T) var4.next();
                if (t instanceof Map) {
                    result.add((Map)t);
                } else {
                    result.add(BeanMap.create(t));
                }
            }

            response.setNote(note);
            response.setRecords(result);
            if (data instanceof PagedArrayList && ((PagedArrayList)data).getTotal() > data.size()) {
                response.setTotalRecord((long)((PagedArrayList)data).getTotal());
            } else {
                response.setTotalRecord((long)data.size());
            }
        }

        return response;
    }

    public static <T> AmsResponse<T> success(List<T> data, String dataColumn, String note) {
        AmsResponse<T> response = success();
        if (data != null) {
            List<Map<String, Object>> result = new ArrayList();
            Iterator var5 = data.iterator();

            while(var5.hasNext()) {
                T t = (T) var5.next();
                if (t instanceof Map) {
                    result.add((Map)t);
                } else {
                    result.add(BeanMap.create(t));
                }
            }

            response.setNote(note);
            response.setRecords(result);
            response.setData(dataColumn);
            if (data instanceof PagedArrayList && ((PagedArrayList)data).getTotal() > data.size()) {
                response.setTotalRecord((long)((PagedArrayList)data).getTotal());
            } else {
                response.setTotalRecord((long)data.size());
            }
        }

        return response;
    }

    public static <T> AmsResponse<T> success(List<Map> data, List<T> columns, String note, long currentPage, long pageSize) {
        AmsResponse<T> response = success();
        ArrayList resultColumns;
        Iterator var9;
        if (data != null) {
            resultColumns = new ArrayList();
            var9 = data.iterator();

            while(var9.hasNext()) {
                Map t = (Map)var9.next();
                resultColumns.add(t);
            }

            response.setNote(note);
            response.setRecords(resultColumns);
            if (data instanceof PagedArrayList && ((PagedArrayList)data).getTotal() > data.size()) {
                response.setTotalRecord((long)((PagedArrayList)data).getTotal());
            } else {
                response.setTotalRecord((long)data.size());
            }
        }

        if (columns != null) {
            resultColumns = new ArrayList();
            var9 = columns.iterator();

            while(var9.hasNext()) {
                T t = (T) var9.next();
                if (t instanceof Map) {
                    resultColumns.add((Map)t);
                } else {
                    resultColumns.add(BeanMap.create(t));
                }
            }

            response.setColumns(resultColumns);
        }

        response.setPageSize(pageSize);
        response.setCurrentPage(currentPage);
        response.setTotalPage(calculateTotalPage(pageSize, response.getTotalRecord()));
        return response;
    }

    public static <T> AmsResponse<T> success(List<T> data, String note, long currentPage, long pageSize) {
        AmsResponse<T> response = success(data, note);
        response.setPageSize(pageSize);
        response.setCurrentPage(currentPage);
        response.setTotalPage(calculateTotalPage(pageSize, response.getTotalRecord()));
        return response;
    }

    public static <T> AmsResponse<T> success(Page<T> data) {
        AmsResponse<T> response = success(data.getRecords());
        response.setPageSize(data.getSize());
        response.setCurrentPage(data.getCurrent());
        response.setTotalPage(data.getPages());
        response.setTotalRecord(data.getTotal());
        return response;
    }

    public static <T> AmsResponse<T> success(com.github.pagehelper.Page<T> page, String note) {
        AmsResponse<T> response = success((List)page, note);
        response.setPageSize((long)page.getPageSize());
        response.setCurrentPage((long)page.getPageNum());
        response.setTotalPage((long)page.getPages());
        response.setTotalRecord(page.getTotal());
        return response;
    }

    public static <T> AmsResponse<T> success(com.github.pagehelper.Page<T> page) {
        AmsResponse<T> response = success((List)page);
        response.setPageSize((long)page.getPageSize());
        response.setCurrentPage((long)page.getPageNum());
        response.setTotalPage((long)page.getPages());
        response.setTotalRecord(page.getTotal());
        return response;
    }

    public static <T> AmsResponse<T> successResponse(List<T> data, Long totalRecord) {
        AmsResponse<T> response = success();
        if (data != null) {
            List<Map<String, Object>> result = new ArrayList();
            Iterator var4 = data.iterator();

            while(var4.hasNext()) {
                Object t = var4.next();
                if (t instanceof Map) {
                    result.add((Map)t);
                } else {
                    result.add(BeanMap.create(t));
                }
            }

            response.setRecords(result);
            response.setTotalRecord(totalRecord);
        }

        return response;
    }

    public static long calculateTotalPage(long pageSize, long totalRecord) {
        if (pageSize < 1L) {
            return -1L;
        } else if (totalRecord < 0L) {
            return -1L;
        } else {
            return totalRecord == 0L ? 0L : totalRecord / pageSize + (long)(totalRecord % pageSize == 0L ? 0 : 1);
        }
    }

    /** @deprecated */
    @Deprecated
    public static <T> AmsResponse<T> successRecords(List<T> list) {
        AmsResponse<T> response = success();
        if (list != null) {
            List<Map<String, Object>> result = new ArrayList();
            Iterator var3 = list.iterator();

            while(var3.hasNext()) {
                T t = (T) var3.next();
                result.add(BeanMap.create(t));
            }

            response.setRecords(result);
        }

        return response;
    }

    public static <T> AmsResponse<T> success(Map<String, Object> data) {
        return success(data, "");
    }

    public static <T> AmsResponse<T> success(Map<String, Object> data, String note) {
        AmsResponse<T> response = success();
        if (data != null && !data.isEmpty()) {
            response.setData(data);
        }

        response.setNote(note);
        return response;
    }

    /** @deprecated */
    @Deprecated
    public static <T> AmsResponse<T> successData(Map<String, Object> data) {
        AmsResponse<T> response = success();
        if (data != null && !data.isEmpty()) {
            response.setData(data);
        }

        return response;
    }

    public static <T> AmsResponse<T> successData(T data) {
        AmsResponse<T> response = success();
        response.setData(BeanMap.create(data));
        return response;
    }

    public static <T> AmsResponse<T> successNote(String note) {
        AmsResponse<T> response = success();
        response.setNote(note);
        return response;
    }

    public static <T> AmsResponse<T> result(int code, String note) {
        AmsResponse<T> response = new AmsResponse();
        response.setCode(code);
        response.setNote(note);
        return response;
    }

    public static <T> AmsResponse<T> error(String note) {
        return result(-1, note);
    }
}