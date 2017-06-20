package com.system.images.auth.service;

import com.cnc.common.persistence.biz.BaseBiz;
import com.system.images.auth.dto.UserDto;
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
    /**
     * 根据用户ID获取用户可用资源信息
     * @param userId
     * @return
     */
    List<AuthResource> listResourseByUserId(Long userId);

    /**
     * 根据用户名获取用户信息
     * @param username
     * @return
     */
    User getByAccount(String username);

    /**
     * 根据账号密码，获取用户信息
     * @param account
     * @param password
     * @return
     */
    public User login(String account, String password);


    /**
     * 获取所有用户信息
     * @return
     */
    public List<UserDto> getAllUser();

    public void insertUser(User user);
}
