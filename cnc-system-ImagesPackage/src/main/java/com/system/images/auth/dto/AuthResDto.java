package com.system.images.auth.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 作者： Administrator
 * 创建时间：2017-05-15.
 * 版本：1.0
 */
public class AuthResDto {
        private Long id;

        /** 资源名称 **/
        private String name;
        /** 权限字符串 **/
        private String permission;
        /** 菜单的超链接 **/
        private String url;
        /** 唯一标识符 **/
        private String sn;
        /** 菜单所在的位置 **/
        private String menupos;
        /** 图标样式 **/
        private String icon;
        /** 排序号 **/
        private Integer ordernum;
        /**  菜单的父类菜单Id **/
        @JsonIgnore
        private Long pid;
        /** 是否显示菜单，1表示显示，-1表示不显示 **/
        private Integer display;
        /** 菜单的父类sn，方便初始化的时候做操作 **/
        private String psn;

        private Long _parentId;

        public String getName(){return this.name;}

        public void setName(String name){this.name = name;}

        public String getPermission(){return this.permission;}

        public void setPermission(String permission){this.permission = permission;}

        public String getUrl(){return this.url;}

        public void setUrl(String url){this.url = url;}

        public String getSn(){return this.sn;}

        public void setSn(String sn){this.sn = sn;}

        public String getMenupos(){return this.menupos;}

        public void setMenupos(String menupos){this.menupos = menupos;}

        public String getIcon(){return this.icon;}

        public void setIcon(String icon){this.icon = icon;}

        public Integer getOrdernum(){return this.ordernum;}

        public void setOrdernum(Integer ordernum){this.ordernum = ordernum;}

        public Long getPid(){return this.pid;}

        //这里注意！！！！
        //用easyUI的treegrid来显示树状图时，_parentId的根目录必须为null，不然整棵树将不会显示！！！！！！
        public void setPid(Long pid){
            this.pid = pid;
            if(pid == 0){
                this._parentId = null;
            }else {
                this._parentId = pid;
            }
        }


        public Integer getDisplay(){return this.display;}

        public void setDisplay(Integer display){this.display = display;}

        public String getPsn(){return this.psn;}

        public void setPsn(String psn){this.psn = psn;}

        public Long get_parentId() {
            return _parentId;
        }

        public void set_parentId(Long _parentId) {
            this._parentId = _parentId;
        }


        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }
