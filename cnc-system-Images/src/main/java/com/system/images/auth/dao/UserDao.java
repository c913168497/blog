package com.system.images.auth.dao;

import com.cnc.common.persistence.dao.BaseDao;
import com.system.images.auth.entity.AuthResource;
import com.system.images.auth.entity.User;

import java.util.List;

/**
 * @描述：[BEAN_NAME]Dao接口
 * @作�??:  Auto Code
 * @创建时间:  2017-4-22 10:10:43
 * @版本: 1.0
 */
public interface UserDao extends BaseDao<User> {
    public List<AuthResource> listResourseByUserId(Long userId);
    public User getByUserName(String username);
}
