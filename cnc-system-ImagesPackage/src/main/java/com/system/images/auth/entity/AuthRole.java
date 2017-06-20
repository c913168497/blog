package com.system.images.auth.entity;

import com.cnc.common.lang.entity.BaseEntity;
import com.cnc.common.lang.annotation.Label;



/**
 * @描述：AuthRole 实体类
 * @作者: Auto Code
 * @创建时间: 2017-5-22 17:49:23
 * @版本: 1.0
 */
public class AuthRole extends BaseEntity {

	@Label("角色名")
	private String name;
	@Label("用户组名称")
	private String sn;
	@Label("状态(1、可用，0、不可用)")
	private Integer status;


	public String getName(){return this.name;}
	public void setName(String name){this.name = name;}

	public String getSn(){return this.sn;}
	public void setSn(String sn){this.sn = sn;}

	public Integer getStatus(){return this.status;}
	public void setStatus(Integer status){this.status = status;}



    @Override
    public String toString() {
        return "AuthRole{"+"name:"+name+","+"sn:"+sn+","+"status:"+status+"}";
    }
}
