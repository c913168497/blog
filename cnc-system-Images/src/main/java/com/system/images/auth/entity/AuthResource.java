package com.system.images.auth.entity;

import com.cnc.common.lang.entity.BaseEntity;
import com.cnc.common.lang.annotation.Label;



/**
 * @描述：[BEAN_NAME] 实体�?
 * @作�??: Auto Code
 * @创建时间: 2017-4-22 10:13:05
 * @版本: 1.0
 */
public class AuthResource extends BaseEntity {

	public static final int MENU_RESOURCE = 1;//菜单资源
	public static final int FUN_RESOURCE = 2;//功能资源
	public static final int UNENABLED = 0; //不可用

	@Label("资源名称")
	private String name;
	@Label("权限字符串")
	private String permission;
	@Label("菜单超链接")
	private String url;
	@Label("唯一标识符")
	private String sn;
	@Label("菜单所在位置")
	private String menupos;
	@Label("图标样式")
	private String icon;
	@Label("排序号")
	private String ordernum;
	@Label("菜单的父类菜单Id")
	private String pid;
	@Label("菜单是否显示(1表示显示，0表示不显示)ʾ)")
	private Integer display;
	@Label("菜单的父类sn,方便初始化的时候操作")
	private String psn;
	@Label("资源类型，1菜单资源 2功能资源")
	private Integer type;


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

	public String getOrdernum(){return this.ordernum;}
	public void setOrdernum(String ordernum){this.ordernum = ordernum;}

	public String getPid(){return this.pid;}
	public void setPid(String pid){this.pid = pid;}

	public Integer getDisplay(){return this.display;}
	public void setDisplay(Integer display){this.display = display;}

	public String getPsn(){return this.psn;}
	public void setPsn(String psn){this.psn = psn;}

	public Integer getType(){return this.type;}
	public void setType(Integer type){this.type = type;}



    @Override
    public String toString() {
        return "AuthResource{"+"name:"+name+","+"permission:"+permission+","+"url:"+url+","+"sn:"+sn+","+"menupos:"+menupos+","+"icon:"+icon+","+"ordernum:"+ordernum+","+"pid:"+pid+","+"display:"+display+","+"psn:"+psn+","+"type:"+type+"}";
    }
}
