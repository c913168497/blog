package com.system.images.auth.service.impl;

import com.cnc.common.persistence.biz.BaseBizImpl;
import com.cnc.common.persistence.dao.BaseDao;
import com.system.images.auth.dto.AuthRoleDto;
import com.system.images.auth.entity.AuthUserRole;
import com.system.images.auth.dao.AuthUserRoleDao;
import com.system.images.auth.service.AuthUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @描述：AuthUserRoleService实现类
 * @作者:  Auto Code
 * @创建时间:  2017-6-20 10:09:52
 * @版本: 1.0
 */
@Service("authUserRoleService")
public class AuthUserRoleServiceImpl extends BaseBizImpl<AuthUserRole> implements AuthUserRoleService {

    @Autowired
    private AuthUserRoleDao authUserRoleDao;

    @Override
    protected BaseDao<AuthUserRole> getDao() {
        return authUserRoleDao;
    }

    public void setUserRole(List<Long> authRoleList,Long id){authUserRoleDao.setUserRole(authRoleList,id);}

}
