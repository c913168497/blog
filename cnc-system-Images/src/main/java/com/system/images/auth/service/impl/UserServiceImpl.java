package com.system.images.auth.service.impl;

import com.cnc.common.persistence.biz.BaseBizImpl;
import com.cnc.common.persistence.dao.BaseDao;
import com.system.images.auth.entity.AuthResource;
import com.system.images.auth.entity.User;
import com.system.images.auth.dao.UserDao;
import com.system.images.auth.kit.ShiroKit;
import com.system.images.auth.service.UserService;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.shiro.authc.UnknownAccountException;

import java.util.List;


/**
 * @描述：[BEAN_NAME]Service实现�?
 * @作�??:  Auto Code
 * @创建时间:  2017-4-22 10:10:43
 * @版本: 1.0
 */
@Service("userService")
public class UserServiceImpl extends BaseBizImpl<User> implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    protected BaseDao<User> getDao() {
        return userDao;
    }
    public User getByUserName(String username){
        return userDao.getByUserName(username);
    }
    public List<AuthResource> listResourseByUserId(Long userId){
        return userDao.listResourseByUserId(userId);
    }

    public User login(String account, String password){
            User user = this.getByUserName(account);
            if(user==null){
                throw new UnknownAccountException("用户或密码不正确！");
            }
            if (!user.getPassword().equals(ShiroKit.md5(password, account + password)))
                throw new UnknownAccountException("用户名或密码不正确！");
            if(user.getIs_lock() == 1 &&user.getIs_lock()==null){
                throw new LockedAccountException("账号已被锁定！");
            }
            return user;
    }
}
