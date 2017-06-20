package com.system.images.auth.dao.impl;

import com.cnc.common.lang.page.PageBean;
import com.cnc.common.lang.page.PageParam;
import com.cnc.common.persistence.dao.BaseDaoImpl;
import com.system.images.auth.entity.AuthResource;
import com.system.images.auth.dao.AuthResourceDao;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @描述：[BEAN_NAME]Dao实现�?
 * @作�??:  Auto Code
 * @创建时间:  2017-4-22 10:13:05
 * @版本: 1.0
 */
@Repository("authResourceDao")
public class AuthResourceDaoImpl extends BaseDaoImpl<AuthResource> implements AuthResourceDao {

    /**
     * 根据用户id获取所拥有资源
     * @param userId
     * @return
     */
    public List<AuthResource> listResourseByUserId (Long userId){
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("userId",userId);
        return super.listBy(paramMap,"listResourseByUserId");
    }
    /**
     * 获取所有资源
     * @return
     */
    public List<AuthResource> listResourseAll(){
        Map<String,Object> paramMap = new HashMap<String,Object>();
        return super.listBy(paramMap);
    }
    public List<AuthResource> authResourceList (int type){
        Map<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("type",type);
        return super.listBy(paramMap);
    }

    public  PageBean<AuthResource> findResourceByPidAndType(PageParam pageParam, Long pId, Integer type){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("type",type);
        map.put("pid",pId);
        return super.listPage(pageParam, map);
    }

    public Boolean isRepeat (String name){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("name",name);
        List<AuthResource> authResourceList = super.listBy(map);
        if(authResourceList.size()>0)return false;else return true;
    }

    public List<AuthResource> listAuthResourceByPId(Long pId){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("pid",pId);
        return super.listBy(map);
    }

}
