package com.system.images.auth.entity;

import com.cnc.common.lang.entity.BaseEntity;
import com.cnc.common.lang.annotation.Label;



/**
 * @描述：AuthRoleRes 实体类
 * @作者: Auto Code
 * @创建时间: 2017-6-16 14:34:37
 * @版本: 1.0
 */
public class AuthRoleRes extends BaseEntity {

	@Label("")
	private Integer resId;
	@Label("")
	private Integer roleId;


	public Integer getResId(){return this.resId;}
	public void setResId(Integer resId){this.resId = resId;}

	public Integer getRoleId(){return this.roleId;}
	public void setRoleId(Integer roleId){this.roleId = roleId;}



    @Override
    public String toString() {
        return "AuthRoleRes{"+"resId:"+resId+","+"roleId:"+roleId+"}";
    }
}
