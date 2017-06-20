package com.system.images.auth.utils;

import com.cnc.common.lang.utils.BeanUtils;
import com.system.images.auth.dto.AuthResDto;
import com.system.images.auth.dto.AuthRoleDto;
import com.system.images.auth.dto.TreeDto;
import com.system.images.auth.entity.AuthResource;
import com.system.images.auth.entity.AuthRole;

import java.util.ArrayList;
import java.util.List;

/**
 * @描述: TODO(这里用一句话描述这个类的作用)
 * @作者: haw
 * @创建时间: 2017-01-09
 * @版本: 1.0
 */
public class DtoUtils {
    /**
     * 树结构的转化类
     * @param resourceList
     * @return
     */
    public static  List<TreeDto> res2treeDto(List<AuthResource> resourceList) {
        List<TreeDto> tree = null;
        if(resourceList != null && resourceList.size()>0){
            tree = new ArrayList<TreeDto>();
            for (AuthResource resource : resourceList) {
                TreeDto dto = new TreeDto(resource.getId(),resource.getName(),resource.getPid(),false,resource.getDisplay());
                tree.add(dto);
            }
        }
        return  tree;
    }
    /***
     * 将所有的资源和角色拥有的资源整合，角色拥有的将checked = true
     * @param allList
     * @param roleList
     * @return
     */
    public static List<TreeDto> res2treeDto(List<AuthResource> allList,List<AuthResource> roleList) {
        if(roleList == null || roleList.size()<=0){
            return res2treeDto(allList);
        }

        List<TreeDto> tree = null;
        if(allList != null && allList.size()>0){
            tree = new ArrayList<TreeDto>();
            for (AuthResource resource : allList) {
                if(roleList.toString().contains(resource.toString())){
                    TreeDto dto = new TreeDto(resource.getId(),resource.getName(),resource.getPid(),true,resource.getDisplay());
                    tree.add(dto);
                }else{
                    TreeDto dto = new TreeDto(resource.getId(),resource.getName(),resource.getPid(),false,resource.getDisplay());
                    tree.add(dto);
                }
            }
        }
        return  tree;
    }

    public static  List<AuthResDto> res2resDto(List<AuthResource> resourceList){
        List<AuthResDto> list = new ArrayList<AuthResDto>();
        BeanUtils.copyListProperties(resourceList, list, AuthResDto.class);
        return list;
    }

    /**
     * 将所有的角色资源和用户拥有的角色整合，用户拥有的将checked=true;
     * @param userRoles 用户拥有的角色
     * @param allRoles  所有的角色
     * @return 所有的角色，用户拥有的角色checked=true，其他checked=false;
     */
    public static List<AuthRoleDto> roles2roleDto(List<AuthRole> allRoles, List<AuthRole> userRoles) {
        List<AuthRoleDto> list = new ArrayList<AuthRoleDto>();
        if(allRoles != null){
            if(userRoles == null){
                BeanUtils.copyListProperties(allRoles,list,AuthResDto.class);
                return list;
            }else{
                for (AuthRole role : allRoles) {
                    if(userRoles.toString().contains(role.toString())){
                        list.add(new AuthRoleDto(role,true));
                    }else {
                        list.add(new AuthRoleDto(role,false));
                    }
                }
            }
        }
        return list;
    }
}
