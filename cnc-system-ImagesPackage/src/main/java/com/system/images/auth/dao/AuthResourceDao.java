package com.system.images.auth.dao;

import com.cnc.common.lang.page.PageBean;
import com.cnc.common.lang.page.PageParam;
import com.cnc.common.persistence.dao.BaseDao;
import com.system.images.auth.entity.AuthResource;

import java.util.List;

/**
 * @描述：[BEAN_NAME]Dao接口
 * @作�??:  Auto Code
 * @创建时间:  2017-4-22 10:13:05
 * @版本: 1.0
 */
public interface AuthResourceDao extends BaseDao<AuthResource> {
    public List<AuthResource> listResourseByUserId (Long userId);
    public List<AuthResource> authResourceList (int type);
    PageBean<AuthResource> findResourceByPidAndType(PageParam pageParam, Long pId, Integer type);
    public List<AuthResource> listAuthResourceByPId(Long pId);
    public Boolean isRepeat (String name);
    public List<AuthResource> listResourseAll();

}
