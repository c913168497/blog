package com.system.images.auth.dto;

/**
 * description ： 用户查询类
 * 作者： cnc
 * 创建时间：2017-06-16.
 * 版本：1.0
 */
public class UserDto {

    private Long id;
    private String name;
    private String account;
    private Integer is_lock;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Integer getIs_lock() {
        return is_lock;
    }

    public void setIs_lock(Integer is_lock) {
        this.is_lock = is_lock;
    }
}
