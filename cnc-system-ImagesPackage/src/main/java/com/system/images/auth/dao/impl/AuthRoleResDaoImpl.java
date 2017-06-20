package com.system.images.auth.dao.impl;

import com.cnc.common.persistence.dao.BaseDaoImpl;
import com.system.images.auth.entity.AuthRole;
import com.system.images.auth.entity.AuthRoleRes;
import com.system.images.auth.dao.AuthRoleResDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @描述：AuthRoleResDao实现类
 * @作者:  Auto Code
 * @创建时间:  2017-6-16 14:34:37
 * @版本: 1.0
 */
@Repository("authRoleResDao")
public class AuthRoleResDaoImpl extends BaseDaoImpl<AuthRoleRes> implements AuthRoleResDao {

    /**
     * 批量添加权限给角色
     * @param longList
     * @param roleId
     */
    @Transactional
    public void insertPermission(List<Integer> longList , Integer roleId){
        List<AuthRoleRes> roleResList = new ArrayList<AuthRoleRes>();
        for (Integer aLong : longList) {
            AuthRoleRes roleres = new AuthRoleRes();
            roleres.setResId(aLong);
            roleres.setRoleId(roleId);
            roleResList.add(roleres);
        }
        this.insert(roleResList);
    }

    public void deleteRole(Long id){


    }


}
