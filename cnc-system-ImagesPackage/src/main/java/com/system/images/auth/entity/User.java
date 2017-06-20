package com.system.images.auth.entity;

import com.cnc.common.lang.entity.BaseEntity;
import com.cnc.common.lang.annotation.Label;

import java.sql.Date;


/**
 * @描述：[BEAN_NAME] 实体�?
 * @作�??: Auto Code
 * @创建时间: 2017-4-22 10:10:43
 * @版本: 1.0
 */
public class User extends BaseEntity {

	//超级管理原账号
	public static final String ADMIN_USER_NAME = "cnc";
	@Label("姓名")
	private String name;
	@Label("账号")
	private String account;
	@Label("密码")
	private String password;
	@Label("密码提示")
	private String modifyTs;
	@Label("提示答案")
	private String modifyAnswer;
	@Label("是否锁定")
	private Integer is_lock;


	public String getName(){return this.name;}
	public void setName(String name){this.name = name;}

	public String getAccount(){return this.account;}
	public void setAccount(String account){this.account = account;}

	public String getPassword(){return this.password;}
	public void setPassword(String password){this.password = password;}

	public String getModifyTs(){return this.modifyTs;}
	public void setModifyTs(String modifyTs){this.modifyTs = modifyTs;}

	public String getModifyAnswer(){return this.modifyAnswer;}
	public void setModifyAnswer(String modifyAnswer){this.modifyAnswer = modifyAnswer;}


	public Integer getIs_lock() {
		return is_lock;
	}

	public void setIs_lock(Integer is_lock) {
		this.is_lock = is_lock;
	}


    @Override
    public String toString() {
        return "User{"+"name:"+name+","+"account:"+account+","+"password:"+password+","+"modifyTs:"+modifyTs+","+"modifyAnswer:"+modifyAnswer+","+"is_lock"+is_lock+"}";
    }
}
