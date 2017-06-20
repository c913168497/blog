package com.system.images.auth.entity;

import com.cnc.common.lang.entity.BaseEntity;
import com.cnc.common.lang.annotation.Label;



/**
 * @描述：AuthUserRole 实体类
 * @作者: Auto Code
 * @创建时间: 2017-6-20 10:09:52
 * @版本: 1.0
 */
public class AuthUserRole extends BaseEntity {

	@Label("用户id")
	private Long userId;
	@Label("用户的角色id")
	private Long roleId;


	public Long getUserId(){return this.userId;}
	public void setUserId(Long userId){this.userId = userId;}

	public Long getRoleId(){return this.roleId;}
	public void setRoleId(Long roleId){this.roleId = roleId;}



    @Override
    public String toString() {
        return "AuthUserRole{"+"userId:"+userId+","+"roleId:"+roleId+"}";
    }
}
