package com.shiro.controller;

import com.shiro.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Login {

    @RequestMapping(value = "/login",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String login(User user){

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());

        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            return e.getMessage();
        }

        if (subject.hasRole("admin")){
            return "有Admin权限";
        }
        return "无Admin权限";
    }

    @RequiresRoles("admin")
    @RequestMapping(value = "/testAdmin",method = RequestMethod.GET)
    @ResponseBody
    public String testRoleAdmin(){
        return "Admin";
    }

    @RequiresRoles("admin")
    @RequestMapping(value = "/testAdmins",method = RequestMethod.GET)
    @ResponseBody
    public String testRoleAdmins(){
        return "Admin";
    }

    @RequiresRoles("user")
    @RequestMapping(value = "/testUser",method = RequestMethod.GET)
    @ResponseBody
    public String testRoleUser(){
        return "User";
    }

    @RequiresRoles("user")
    @RequestMapping(value = "/testUsers",method = RequestMethod.GET)
    @ResponseBody
    public String testRoleUsers(){
        return "User";
    }
}
