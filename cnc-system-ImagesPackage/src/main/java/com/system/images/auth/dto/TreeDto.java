package com.system.images.auth.dto;

/**
 * 作者： Administrator
 * 创建时间：2017-05-15.
 * 版本：1.0
 */
public class TreeDto {
    private long id;
    private String name;
    private Long pId;
    private boolean checked;
    private Integer status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public TreeDto() {
    }
    public TreeDto(long id, String name, Long pId) {
        super();
        this.id = id;
        this.name = name;
        this.pId = pId;
    }

    public TreeDto(long id, String name, Long pId, boolean checked) {
        this.id = id;
        this.name = name;
        this.pId = pId;
        this.checked = checked;
    }

    public TreeDto(long id, String name, Long pId, boolean checked, Integer status) {
        this.id = id;
        this.name = name;
        this.pId = pId;
        this.checked = checked;
        this.status = status;
    }

    @Override
    public String toString() {
        return "TreeDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pId=" + pId +
                ", checked=" + checked +
                '}';
    }

}
