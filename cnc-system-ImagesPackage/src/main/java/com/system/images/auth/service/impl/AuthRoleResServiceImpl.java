package com.system.images.auth.service.impl;

import com.cnc.common.persistence.biz.BaseBizImpl;
import com.cnc.common.persistence.dao.BaseDao;
import com.system.images.auth.entity.AuthRoleRes;
import com.system.images.auth.dao.AuthRoleResDao;
import com.system.images.auth.service.AuthRoleResService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @描述：AuthRoleResService实现类
 * @作者:  Auto Code
 * @创建时间:  2017-6-16 14:34:37
 * @版本: 1.0
 */
@Service("authRoleResService")
public class AuthRoleResServiceImpl extends BaseBizImpl<AuthRoleRes> implements AuthRoleResService {

    @Autowired
    private AuthRoleResDao authRoleResDao;

    @Override
    protected BaseDao<AuthRoleRes> getDao() {
        return authRoleResDao;
    }

}
