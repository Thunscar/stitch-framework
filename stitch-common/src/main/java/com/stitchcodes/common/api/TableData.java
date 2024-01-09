package com.stitchcodes.common.api;

import java.util.List;

/**
 * @Author: stitch
 * @Date: 2023/4/30 16:58
 * @Description:
 */
public class TableData {

    private int code;

    private String msg;

    private List<?> list;

    private long total;
    
    public TableData() {
    }

    public TableData(int code, String msg, List<?> list, long total) {
        this.code = code;
        this.msg = msg;
        this.list = list;
        this.total = total;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

}
