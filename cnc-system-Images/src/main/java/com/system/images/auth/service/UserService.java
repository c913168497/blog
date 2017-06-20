package com.system.images.auth.service;

import com.cnc.common.persistence.biz.BaseBiz;
import com.system.images.auth.entity.AuthResource;
import com.system.images.auth.entity.User;


import java.util.List;


/**
 * @描述：[BEAN_NAME]Service接口
 * @作�??:  Auto Code
 * @创建时间:  2017-4-22 10:10:43
 * @版本: 1.0
 */
public interface UserService extends BaseBiz<User> {
    List<AuthResource> listResourseByUserId(Long userId);
    User getByUserName(String username);
    public User login(String account, String password);
}
