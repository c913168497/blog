package com.system.images.auth.service.impl;

import com.cnc.common.lang.page.PageBean;
import com.cnc.common.lang.page.PageParam;
import com.cnc.common.persistence.biz.BaseBizImpl;
import com.cnc.common.persistence.dao.BaseDao;
import com.system.images.auth.entity.AuthResource;
import com.system.images.auth.dao.AuthResourceDao;
import com.system.images.auth.service.AuthResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @描述：[BEAN_NAME]Service实现�?
 * @作�??:  Auto Code
 * @创建时间:  2017-4-22 10:13:05
 * @版本: 1.0
 */
@Service("authResourceService")
public class AuthResourceServiceImpl extends BaseBizImpl<AuthResource> implements AuthResourceService {

    @Autowired
    private AuthResourceDao authResourceDao;

    @Override
    protected BaseDao<AuthResource> getDao() {
        return authResourceDao;
    }
    public List<AuthResource> listResourseByUserId(Long userId){
         return  authResourceDao.listResourseByUserId(userId);
    }
    public List<AuthResource> authResourceList (int type){
        return  authResourceDao.authResourceList(type);
    }

    public PageBean<AuthResource> findResourceByPidAndType(PageParam pageParam, Long pId, Integer type){
        return  authResourceDao.findResourceByPidAndType(pageParam, pId, type);
    }

    public List<AuthResource> listAuthResourceByPId(Long pId) {
        return  authResourceDao.listAuthResourceByPId(pId);
    }
    public Boolean isRepeat (String name){
        return  authResourceDao.isRepeat(name);
    }

    public List<AuthResource> listResourseAll(){
        return  authResourceDao.listResourseAll();
    };
}
