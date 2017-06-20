package com.system.images.auth.service.impl;

import com.cnc.common.persistence.biz.BaseBizImpl;
import com.cnc.common.persistence.dao.BaseDao;
import com.system.images.auth.dao.AuthRoleResDao;
import com.system.images.auth.entity.AuthResource;
import com.system.images.auth.entity.AuthRole;
import com.system.images.auth.dao.AuthRoleDao;
import com.system.images.auth.entity.User;
import com.system.images.auth.service.AuthRoleResService;
import com.system.images.auth.service.AuthRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @描述：AuthRoleService实现类
 * @作者:  Auto Code
 * @创建时间:  2017-5-22 17:49:23
 * @版本: 1.0
 */
@Service("authRoleService")
public class AuthRoleServiceImpl extends BaseBizImpl<AuthRole> implements AuthRoleService {

    @Autowired
    private AuthRoleDao authRoleDao;
    @Autowired
    private AuthRoleResDao authRoleResDao;
    @Override
    protected BaseDao<AuthRole> getDao() {
        return authRoleDao;
    }

    public List<AuthRole> listAuthRole(){
        return authRoleDao.listAuthRole();
    }

    public List<AuthRole> listAuthRoleByName(String name) {
        return authRoleDao.listAuthRoleByName(name);
    }
    public List<User> listAuthUserByRoleId(long id){
        return  authRoleDao.listAuthUserByRoleId(id);
    }
    public List<AuthResource> listAuthResourceByRoleId(long id){
        return authRoleDao.listAuthResourceByRoleId(id);
    }
    public void setPermission(List<Integer> ids,Integer roleId){
        authRoleDao.deletePermission(roleId);
        authRoleResDao.insertPermission(ids,roleId);
    }
    public List<AuthRole> getAuthRoleByUserId(Long userId){
        return  authRoleDao.getAuthRoleByUserId(userId);
    };
}
