package com.system.images.auth.service;

import com.cnc.common.lang.page.PageBean;
import com.cnc.common.lang.page.PageParam;
import com.cnc.common.persistence.biz.BaseBiz;
import com.system.images.auth.entity.AuthResource;

import java.util.List;


/**
 * @描述：[BEAN_NAME]Service接口
 * @作�??:  Auto Code
 * @创建时间:  2017-4-22 10:13:05
 * @版本: 1.0
 */
public interface AuthResourceService extends BaseBiz<AuthResource> {

    /**
     * 用户资源列表
     * @param type
     * @return
     */
    public List<AuthResource> authResourceList (int type);

    /**
     * 获取所有资源
     * @return
     */
    public List<AuthResource> listResourseAll();
    /**
     * 获取Pid下的所有子菜单
     * @param pId
     * @return
     */
    public List<AuthResource> listAuthResourceByPId(Long pId);
    /**
     * 根据用户id获取所拥有的的资源
     * @param id
     * @return
     */
    public List<AuthResource> listResourseByUserId (Long id);

    /**
     * 根据父Id 和资源类型 获取该父Id下面的子资源(分页)
     * @param pageParam 分页参数
     * @param pId
     * @param type 1 or Resource.MENU_RESOURCE 菜单资源   2 or Resource.FUN_RESOURCE .功能资源
     * @return
     */
    PageBean<AuthResource> findResourceByPidAndType(PageParam pageParam, Long pId, Integer type);


    /**
     * 判断是否有相同的名称
     * @param name 菜单信息
     */
    Boolean isRepeat (String name);
}
