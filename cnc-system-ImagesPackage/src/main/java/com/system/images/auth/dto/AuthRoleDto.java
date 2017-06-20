package com.system.images.auth.dto;

import com.cnc.common.lang.utils.BeanUtils;
import com.system.images.auth.entity.AuthRole;

import java.util.List;

/**
 * 作者： Administrator
 * 创建时间：2017-05-22.
 * 版本：1.0
 */
public class AuthRoleDto {
    /** ID **/
    private Long id;
    /** 角色名称 **/
    private String name;
    /** 角色唯一标识 **/
    private String sn;
    /**  状态  0 停用  1 启用**/
    private Integer status;

    private boolean checked;

    public AuthRoleDto(){

    }

    public AuthRoleDto(AuthRole role,boolean checked){
        BeanUtils.copyProperties(role, this);
        this.checked = checked;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName(){return this.name;}

    public void setName(String name){this.name = name;}

    public String getSn(){return this.sn;}

    public void setSn(String sn){this.sn = sn;}

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return "AuthRoleDto{" +
                "name='" + name + '\'' +
                ", sn='" + sn + '\'' +
                ", status=" + status +
                ", checked=" + checked +
                '}';
    }
}
