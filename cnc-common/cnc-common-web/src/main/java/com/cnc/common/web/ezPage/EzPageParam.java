package com.cnc.common.web.ezPage;

import com.cnc.common.lang.annotation.Label;

/**
 * Created by Administrator on 2016-12-24.
 */
public class EzPageParam {
    @Label("当前页")
    private int page = 1;
    @Label("每页显示条数")
    private int rows = 20;
    @Label("排序字段")
    private String sort;
    @Label("正序asc,倒序desc")
    private String order = "asc";

    public EzPageParam() {
    }

    public EzPageParam(int page, int rows, String sort, String order) {
        this.page = page;
        this.rows = rows;
        this.sort = sort;
        this.order = order;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "EzPageParam{" +
                "page=" + page +
                ", rows=" + rows +
                ", sort='" + sort + '\'' +
                ", order='" + order + '\'' +
                '}';
    }
}
