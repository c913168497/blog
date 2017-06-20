package com.system.images.auth.dao;

import com.cnc.common.persistence.dao.BaseDao;
import com.system.images.auth.entity.AuthResource;
import com.system.images.auth.entity.AuthRole;
import com.system.images.auth.entity.User;

import java.util.List;

/**
 * @描述：AuthRoleDao接口
 * @作者:  Auto Code
 * @创建时间:  2017-5-22 17:49:23
 * @版本: 1.0
 */
public interface AuthRoleDao extends BaseDao<AuthRole> {
        public List<AuthRole> listAuthRole();
        public List<AuthRole> getAuthRoleByUserId(Long userId);
        public List<AuthRole> listAuthRoleByName(String name);
        public List<User> listAuthUserByRoleId(long id);
        public List<AuthResource> listAuthResourceByRoleId(long id);
        public void deletePermission(Integer roleId);
}
