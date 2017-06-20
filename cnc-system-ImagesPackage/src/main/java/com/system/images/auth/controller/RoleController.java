package com.system.images.auth.controller;

import com.cnc.common.web.util.MessageBox;
import com.system.images.auth.dto.AuthUserDto;
import com.system.images.auth.dto.TreeDto;
import com.system.images.auth.entity.AuthResource;
import com.system.images.auth.entity.AuthRole;
import com.system.images.auth.entity.User;
import com.system.images.auth.service.AuthResourceService;
import com.system.images.auth.service.AuthRoleService;
import com.system.images.auth.utils.DtoUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者： cnc
 * 创建时间：2017-06-16.
 * 版本：1.0
 */
@Controller
@RequestMapping(value = "/role")
public class RoleController {

        @Autowired
        AuthResourceService authResourceService;
        @Autowired
        AuthRoleService authRoleService;

        @RequestMapping(value = "/list/menu/{userId}",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
        @ResponseBody
        public List<AuthResource> getMenu(@PathVariable long userId) {
            Subject subject = SecurityUtils.getSubject();
            User user = (User) subject.getPrincipal();
            //超级管理员
            if(user.getAccount().equals(User.ADMIN_USER_NAME)){
                List<AuthResource> authResources = authResourceService.authResourceList(AuthResource.MENU_RESOURCE);
                return  authResources;
            }else {
                List<AuthResource> list = new ArrayList<AuthResource>();
                List<AuthResource> resourceslist = authResourceService.listResourseByUserId(userId);
                if (resourceslist != null && resourceslist.size() > 0){
                    for (AuthResource resource : resourceslist) {
                        if (resource.getType().equals(AuthResource.MENU_RESOURCE)) {
                            list.add(resource);
                        }
                    }
                }
                return  list;
            }
        }
        @RequestMapping(value = "/index", method = RequestMethod.GET)
        public String index(){
            return "auth/role_manage";
        }

        /**
         * 获取所有角色
         * @return
         */
        @RequestMapping(value = "/list",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
        @ResponseBody
        public List<AuthRole> authRoleList(){
            return authRoleService.listAuthRole();
        }

        /**
         * 添加角色
         * @param authRole
         * @return
         */
        @RequestMapping(value = "/add", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
        @ResponseBody
        public MessageBox addRole(AuthRole authRole){
            System.out.println("角色添加:"+authRole.toString());
            List<AuthRole>  authRoleList = authRoleService.listAuthRoleByName(authRole.getName());
            if(authRoleList.size() > 0){
                return MessageBox.getErrorMsg("已有相同角色名!");
            }
            authRoleService.create(authRole); //创建角色成功
            return MessageBox.getSuccessMsg("添加成功!");
        }
        @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
        @ResponseBody
        public MessageBox deleteRole(@PathVariable Long id){
            authRoleService.deleteById(id);
            return MessageBox.getSuccessMsg("删除成功!");
        }
        @RequestMapping(value = "/update", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
        @ResponseBody
        public MessageBox updateRole(AuthRole authRole){
            authRoleService.update(authRole);
            return MessageBox.getSuccessMsg("修改成功!");
        }

        /**
         * 根据id获取角色所拥有用户
         * @param id
         * @return
         */
        @RequestMapping(value = "/roleUserResource/{id}", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
        @ResponseBody
        public List<AuthUserDto> authUserDtoList(@PathVariable Long id){
            List<AuthUserDto> authUserDtoList = toChange(authRoleService.listAuthUserByRoleId(id));
            return  authUserDtoList;
        }

        /**
         * 根据id获取角色所拥有的资源
         * @param id
         * @return
         */
        @RequestMapping(value = "/roleResource/{id}", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
        @ResponseBody
        public List<TreeDto> authResourcesList(@PathVariable Long id){
            System.out.println("数据:"+authRoleService.listAuthResourceByRoleId(id));
            return DtoUtils.res2treeDto(authRoleService.listAuthResourceByRoleId(id));
        }

        /**
         * 设置权限
         * @param roleId
         * @return
         */
        @RequestMapping(value ="/set/permission/list/{roleId}",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
        @ResponseBody
        public List<TreeDto>  getRoleListPermission(@PathVariable  Long roleId){
            List<AuthResource> allList = authResourceService.listResourseAll();
            List<AuthResource> roleList = authRoleService.listAuthResourceByRoleId(roleId);
            System.out.println(DtoUtils.res2treeDto(allList, roleList));
            return DtoUtils.res2treeDto(allList, roleList);
        }

        /**
         * 保存修改后的权限
         * @param roleList
         * @param roleId
         * @return
         */
        @RequestMapping(value = "set/permission",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
        @ResponseBody
        public MessageBox setRolePermission(@RequestParam(value="roleList[]",required = false)List<Integer> roleList, Integer roleId){
            System.out.println("资源:"+roleList+"角色"+roleId);
            authRoleService.setPermission(roleList, roleId);
            return  MessageBox.getSuccessMsg("修改成功！");
        }

        /**
         * 转换实体类
         * @param userList
         * @return
         */
        public static List<AuthUserDto> toChange(List<User> userList){
            List<AuthUserDto> authlist = new ArrayList<AuthUserDto>();
            for (User user : userList) {
                AuthUserDto au = new AuthUserDto();
                au.setAccount(user.getAccount());
                au.setId(user.getId());
                au.setStatus(user.getIs_lock());
                authlist.add(au);
            }
            return authlist;
        }
}
