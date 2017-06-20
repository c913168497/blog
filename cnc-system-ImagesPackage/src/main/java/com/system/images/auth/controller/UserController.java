package com.system.images.auth.controller;


import com.cnc.common.web.util.MessageBox;
import com.system.images.auth.dto.*;
import com.system.images.auth.entity.AuthResource;
import com.system.images.auth.entity.AuthRole;
import com.system.images.auth.entity.AuthUserRole;
import com.system.images.auth.entity.User;
import com.system.images.auth.kit.ShiroKit;
import com.system.images.auth.service.AuthResourceService;
import com.system.images.auth.service.AuthRoleService;
import com.system.images.auth.service.AuthUserRoleService;
import com.system.images.auth.service.UserService;
import com.system.images.auth.utils.DtoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 作者： Administrator
 * 创建时间：2017-04-20.
 * 版本：1.0
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    AuthResourceService authResourceService;
    @Autowired
    AuthRoleService authRoleService;
    @Autowired
    AuthUserRoleService authUserRoleService;


    @RequestMapping(value = "/index",method = RequestMethod.GET,produces="application/json;charset=UTF-8")
    public String index(){
        return "/auth/user_manager";
    }

    @RequestMapping(value = "/list",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<UserDto> userList (){
        return userService.getAllUser();
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public MessageBox addUser (User user){
       if(userService.getByAccount(user.getAccount())!=null){
           return MessageBox.getErrorMsg("已存在该账号名!");
       }
        user.setPassword(ShiroKit.md5(user.getPassword(), user.getAccount() + user.getPassword()));
        userService.insertUser(user);
        return MessageBox.getSuccessMsg("添加成功!");
    }

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public MessageBox deleteUser (@PathVariable Long id){
        userService.deleteById(id);
        return MessageBox.getSuccessMsg("删除成功!");
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public MessageBox modifyUser (User user){
        user.setPassword(ShiroKit.md5(user.getPassword(), user.getAccount() + user.getPassword()));
        userService.update(user);
        return MessageBox.getSuccessMsg("修改成功!");
    }
    /**
     * 获取用户id所拥有资源
     * @param id
     * @return
     */
    @RequestMapping(value = "/userResource/{id}",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public  List<TreeDto> getResourceByUserId(@PathVariable Long id){
        System.out.println("进入" + id);
        return DtoUtils.res2treeDto(authResourceService.listResourseByUserId(id));
    }

    /**
     * 获取所拥有用户ces
     * @param id
     * @return
     */
    @RequestMapping(value = "/userRoleResource/{id}",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<AuthRole> getAuthRoleByUserId(@PathVariable Long id){
        List<AuthRole> authRoleList =  authRoleService.getAuthRoleByUserId(id);
       return authRoleService.getAuthRoleByUserId(id);
    }

    @RequestMapping(value = "/getUserRole/{id}",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<AuthRoleDto> getPermission(@PathVariable Long id){
        List<AuthRole> authRoleList =  authRoleService.getAuthRoleByUserId(id);
        List<AuthRole> authRoleList2 = authRoleService.listAuthRole();
        System.out.println("角色列表"+DtoUtils.roles2roleDto(authRoleList2,authRoleList));
        return DtoUtils.roles2roleDto(authRoleList2,authRoleList);
    }

    @RequestMapping(value = "/setUserRolePermission", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public MessageBox setPermission(@RequestParam(value="userList[]",required = false)List<Long> userList, Long userId){
        System.out.println("这是用户id"+userId);
            authUserRoleService.setUserRole(userList, userId);
            return MessageBox.getSuccessMsg("修改成功！");
    }
}
