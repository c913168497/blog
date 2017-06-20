package com.system.images.auth.dao.impl;

import com.cnc.common.persistence.dao.BaseDaoImpl;
import com.system.images.auth.dao.AuthResourceDao;
import com.system.images.auth.entity.AuthResource;
import com.system.images.auth.entity.User;
import com.system.images.auth.dao.UserDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @描述：[BEAN_NAME]Dao实现�?
 * @作�??:  Auto Code
 * @创建时间:  2017-4-22 10:10:43
 * @版本: 1.0
 */
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

    @Autowired
    AuthResourceDao authResourceDao;
    public List<AuthResource> listResourseByUserId(Long userId){
        return  authResourceDao.listResourseByUserId(userId);
    }
    public User getByUserName(String username){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("account", username);
        return (User) super.getBy(map,"getByUserName");
    }
}
