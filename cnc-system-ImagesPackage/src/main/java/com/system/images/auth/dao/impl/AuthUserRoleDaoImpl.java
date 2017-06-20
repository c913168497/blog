package com.system.images.auth.dao.impl;

import com.cnc.common.persistence.dao.BaseDaoImpl;
import com.system.images.auth.dto.AuthRoleDto;
import com.system.images.auth.entity.AuthUserRole;
import com.system.images.auth.dao.AuthUserRoleDao;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @描述：AuthUserRoleDao实现类
 * @作者:  Auto Code
 * @创建时间:  2017-6-20 10:06:31
 * @版本: 1.0
 */
@Repository("authUserRoleDao")
public class AuthUserRoleDaoImpl extends BaseDaoImpl<AuthUserRole> implements AuthUserRoleDao {
    public void setUserRole(List<Long> authRoleList,Long id){
        Map<String , Object> map = new HashMap<String , Object>();
        map.put("id",id);
        super.delete(map,"deleteRoleByUserId");
        List<Long> listId = new ArrayList<Long>();
        for (Long idLong : authRoleList) {
                AuthUserRole authUserRole = new AuthUserRole();
                authUserRole.setRoleId(idLong);
                authUserRole.setUserId(id);
                super.insert(authUserRole);
        }
    }
}
