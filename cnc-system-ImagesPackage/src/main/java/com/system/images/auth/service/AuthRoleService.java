package com.system.images.auth.service;

import com.cnc.common.persistence.biz.BaseBiz;
import com.system.images.auth.entity.AuthResource;
import com.system.images.auth.entity.AuthRole;
import com.system.images.auth.entity.User;

import java.util.List;


/**
 * @描述：AuthRoleService接口
 * @作者:  Auto Code
 * @创建时间:  2017-5-22 17:49:23
 * @版本: 1.0
 */
public interface AuthRoleService extends BaseBiz<AuthRole> {
    /**
     * 查出所有角色
     * @return
     */
    public List<AuthRole> listAuthRole();

    /**
     * 根据角色名查角色信息
     * @param name
     * @return
     */
    public List<AuthRole> listAuthRoleByName(String name);

    /**
     * 根据角色id获取角色所有用户
     * @param id
     * @return
     */
    public List<User> listAuthUserByRoleId(long id);

    /**
     * 根据角色id获取角色所拥有资源权限
     * @param id
     * @return
     */
    public List<AuthResource> listAuthResourceByRoleId(long id);

    /**
     * 批量添加权限
     * @param ids
     * @param roleId
     */
    public void setPermission(List<Integer> ids,Integer roleId);

    /**
     * 根据用户id获取所拥有角色
     * @param userId
     * @return
     */
    public List<AuthRole> getAuthRoleByUserId(Long userId);
}
