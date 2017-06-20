package com.system.images.auth.service;

import com.cnc.common.persistence.biz.BaseBiz;
import com.system.images.auth.dto.AuthRoleDto;
import com.system.images.auth.entity.AuthUserRole;

import java.util.List;


/**
 * @描述：AuthUserRoleService接口
 * @作者:  Auto Code
 * @创建时间:  2017-6-20 10:09:52
 * @版本: 1.0
 */
public interface AuthUserRoleService extends BaseBiz<AuthUserRole> {
    public void setUserRole(List<Long> authRoleList,Long id);
}
