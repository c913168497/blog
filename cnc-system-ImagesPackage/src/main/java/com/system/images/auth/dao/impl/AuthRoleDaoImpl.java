package com.system.images.auth.dao.impl;

import com.cnc.common.persistence.dao.BaseDaoImpl;
import com.system.images.auth.entity.AuthResource;
import com.system.images.auth.entity.AuthRole;
import com.system.images.auth.dao.AuthRoleDao;
import com.system.images.auth.entity.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @描述：AuthRoleDao实现类
 * @作者:  Auto Code
 * @创建时间:  2017-5-22 17:49:23
 * @版本: 1.0
 */
@Repository("authRoleDao")
public class AuthRoleDaoImpl extends BaseDaoImpl<AuthRole> implements AuthRoleDao {
    public List<AuthRole> listAuthRole(){
        Map<String,Object> map = new HashMap<String, Object>();
        return super.listBy(map);
    }
    /**
     * 根据用户ID获取用户所拥有角色
     * @param userId
     * @return
     */
    public List<AuthRole> getAuthRoleByUserId(Long userId){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("id",userId);
        return super.listBy(map,"getAuthRoleByUserId");
    }
    public List<AuthRole> listAuthRoleByName(String name){
        Map<String ,Object> map = new HashMap<>();
        map.put("name",name);
        return super.listBy(map);
    }

    public List<User> listAuthUserByRoleId(long id){
        Map<String ,Object> map = new HashMap<>();
        map.put("id",id);
        return super.listBy(map, "listAuthUserByRoleId");
    }

    /**
     * 根据角色id获取所有资源
     * @param id
     * @return
     */
    public List<AuthResource> listAuthResourceByRoleId(long id){
        Map<String ,Object> map = new HashMap<>();
        map.put("id", id);
        return super.listBy(map, "listAuthResourceByRoleId");
    }

    /**
     * 根据角色id删除所有权限
     * @param roleId
     */
    public void deletePermission(Integer roleId){
        Map<String ,Object> map = new HashMap<>();
        map.put("id",roleId);
        delete(map, "deleteRoueseById");
    }

}
