package com.cnc.common.lang.entity;

import java.io.Serializable;

/**
 *
 * @描述: 基础实体类，包含各实体公用属性 .
 * @作者: haw .
 * @创建时间: 2016-12-15 .
 * @版本: 1.0 .
 */
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;				//主键ID
//	private Integer version = 0;	//版本号

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	public Integer getVersion() {
//		return version;
//	}
//
//	public void setVersion(Integer version) {
//		this.version = version;
//	}

}
