package com.cnc.common.web.ezPage;


import com.cnc.common.lang.page.PageBean;
import com.cnc.common.lang.page.PageParam;

/**
 * Created by Administrator on 2016-12-24.
 */
public class PageUtils {

    public static PageParam convert(EzPageParam param){
       return new PageParam(param.getPage(),param.getRows(),param.getSort(),param.getOrder());
    }

    public static <T> EzPage<T> convertPageBean(PageBean<T> pageBean) {
        return new EzPage<T>(pageBean.getTotalCount(),pageBean.getRecordList());
    }
}
