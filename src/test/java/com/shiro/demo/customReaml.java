package com.shiro.demo;

import com.shiro.java.CustomReaml;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class customReaml {

    @Test
    public void customReamlTest(){
        //自定义Reaml
        CustomReaml customReaml = new CustomReaml();
        System.out.println(customReaml.getName());

        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(customReaml);

        SecurityUtils.setSecurityManager(defaultSecurityManager);

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("mark","12345");

        subject.login(token);

        subject.checkRoles("user","admin");

        subject.checkPermissions("user:update","admin:delete");
    }
}
