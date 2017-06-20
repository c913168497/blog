package com.system.images.auth.dao.impl;

import com.cnc.common.persistence.dao.BaseDaoImpl;
import com.system.images.auth.entity.AuthResource;
import com.system.images.auth.dao.AuthResourceDao;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @描述：[BEAN_NAME]Dao实现�?
 * @作�??:  Auto Code
 * @创建时间:  2017-4-22 10:13:05
 * @版本: 1.0
 */
@Repository("authResourceDao")
public class AuthResourceDaoImpl extends BaseDaoImpl<AuthResource> implements AuthResourceDao {
    public List<AuthResource> listResourseByUserId (Long userId){
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("userId",userId);
        return super.listBy(paramMap,"listResourseByUserId");
    }
}
