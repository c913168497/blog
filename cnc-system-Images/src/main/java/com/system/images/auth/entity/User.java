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
	public static final String ADMIN_USER_NAME = "admin";
	@Label("手机号码")
	private String mobile;
	@Label("账号")
	private String account;
	@Label("密码")
	private String password;
	@Label("密码提示")
	private String modifyTs;
	@Label("提示答案")
	private String modifyAnswer;
	@Label("创建时间")
	private Date createTime;
	@Label("修改时间")
	private Date modifyTime;
	@Label("是否锁定")
	private Integer is_lock;


	public String getMobile(){return this.mobile;}
	public void setMobile(String mobile){this.mobile = mobile;}

	public String getAccount(){return this.account;}
	public void setAccount(String account){this.account = account;}

	public String getPassword(){return this.password;}
	public void setPassword(String password){this.password = password;}

	public String getModifyTs(){return this.modifyTs;}
	public void setModifyTs(String modifyTs){this.modifyTs = modifyTs;}

	public String getModifyAnswer(){return this.modifyAnswer;}
	public void setModifyAnswer(String modifyAnswer){this.modifyAnswer = modifyAnswer;}

	public Date getCreateTime(){return this.createTime;}
	public void setCreateTime(Date createTime){this.createTime = createTime;}

	public Date getModifyTime(){return this.modifyTime;}
	public void setModifyTime(Date modifyTime){this.modifyTime = modifyTime;}

	public Integer getIs_lock() {
		return is_lock;
	}

	public void setIs_lock(Integer is_lock) {
		this.is_lock = is_lock;
	}


    @Override
    public String toString() {
        return "User{"+"mobile:"+mobile+","+"account:"+account+","+"password:"+password+","+"modifyTs:"+modifyTs+","+"modifyAnswer:"+modifyAnswer+","+"createTime:"+createTime+","+"modifyTime:"+modifyTime+"is_lock"+is_lock+"}";
    }
}
