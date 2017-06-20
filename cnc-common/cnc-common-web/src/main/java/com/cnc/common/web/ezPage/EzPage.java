package com.cnc.common.web.ezPage;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016-12-24.
 */
public class EzPage<T> implements Serializable {

    private long total;
    private List<T> rows;

    public EzPage() {}

    public EzPage(long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "EzPage{" +
                "total=" + total +
                ", rows=" + rows +
                '}';
    }
}
