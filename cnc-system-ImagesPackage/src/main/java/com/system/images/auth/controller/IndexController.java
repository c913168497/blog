package com.system.images.auth.controller;

import com.system.images.auth.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.jws.soap.SOAPBinding;

/**
 * 作者： Administrator
 * 创建时间：2017-05-10.
 * 版本：1.0
 */
@Controller
public class IndexController {
    @RequestMapping(value={"","/","/index"},method = RequestMethod.GET)
    public String index(Model model) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        model.addAttribute("userInfo",user);
        return "index";
    }

    @RequestMapping(value = "/error",method = RequestMethod.GET)
    public String error(){
        return "error";
    }

    @RequestMapping(value="/unAuth",method=RequestMethod.GET)
    public String unAuth() {
        return "unAuth";
    }
}
