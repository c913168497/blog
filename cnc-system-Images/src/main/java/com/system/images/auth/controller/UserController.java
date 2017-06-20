package com.system.images.auth.controller;


import com.system.images.auth.entity.User;
import com.system.images.auth.kit.ShiroKit;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 作者： Administrator
 * 创建时间：2017-04-20.
 * 版本：1.0
 */
@Controller
public class UserController {
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){
        return "index";
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String username,String password,Model model){
        System.out.println(username+"---"+password);
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated()){
            User u = (User) subject.getPrincipal();
            if(!username.equals(u.getAccount())){
                subject.logout();
            }else {
                return "redirect:/index";
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
            return "redirect:/index";
        }else {
            model.addAttribute("emsg", emsg);
            return "/login";
        }
    }
}
