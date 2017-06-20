package com.system.images.auth.controller;

import com.system.images.auth.entity.User;
import com.system.images.auth.kit.ShiroKit;
import com.system.images.auth.service.AuthResourceService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 作者： Administrator
 * 创建时间：2017-05-10.
 * 版本：1.0
 */

@Controller
public class LoginController {
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public String login(String username,String password,Model model){

        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated()){
            User u = (User) subject.getPrincipal();
            if(!username.equals(u.getAccount())){
                subject.logout();
            }else {
                if(u.getIs_lock() == 1){
                    return "redirect: /index";
                }else{
                    subject.logout();
                }
            }
        }
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        String emsg = null;
        try{
            subject.login(token);
        }catch (AuthenticationException e){
            e.printStackTrace();
            emsg = "登录失败："+e.getMessage();
        }
        if(ShiroKit.isEmpty(emsg)) {
            return "redirect: /index";
        }else {
            model.addAttribute("emsg", emsg);
            return "login";
        }
    }
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect: /login";
    }


}
