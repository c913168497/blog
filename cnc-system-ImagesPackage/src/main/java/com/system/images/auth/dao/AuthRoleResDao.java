package com.system.images.auth.dao;

import com.cnc.common.persistence.dao.BaseDao;
import com.system.images.auth.entity.AuthRoleRes;

import java.util.List;

/**
 * @描述：AuthRoleResDao接口
 * @作者:  Auto Code
 * @创建时间:  2017-6-16 14:34:37
 * @版本: 1.0
 */
public interface AuthRoleResDao extends BaseDao<AuthRoleRes> {
    public void insertPermission(List<Integer> longList , Integer roleId);
}
