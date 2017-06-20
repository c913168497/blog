package com.system.images.auth.dao.impl;

import com.cnc.common.persistence.dao.BaseDaoImpl;
import com.system.images.auth.dao.AuthResourceDao;
import com.system.images.auth.dto.AuthRoleDto;
import com.system.images.auth.dto.UserDto;
import com.system.images.auth.entity.AuthResource;
import com.system.images.auth.entity.AuthRole;
import com.system.images.auth.entity.User;
import com.system.images.auth.dao.UserDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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
    public User getByAccount(String username){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("account", username);
        return (User) super.getBy(map,"getByAccount");
    }
    public List<UserDto> getAllUser(){
        Map<String,Object> map = new HashMap<String, Object>();
        List<User> userList = super.listBy(map);
        List<UserDto> userDtos = new ArrayList<UserDto>();
        for (User user : userList) {
            UserDto userDto = new UserDto();
            userDto.setAccount(user.getAccount());
            userDto.setName(user.getName());
            userDto.setId(user.getId());
            userDto.setIs_lock(user.getIs_lock());
            userDtos.add(userDto);
        }
        return userDtos;
    }
    

}
