package com.system.images.auth.dto;

/**
 * 作者： cnc
 * 创建时间：2017-06-15.
 * 版本：1.0
 */
public class AuthUserDto {

    private Long id;
    private String account ;
    private int status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
