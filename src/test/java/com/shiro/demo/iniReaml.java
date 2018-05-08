package com.shiro.demo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class iniReaml {

    @Test
    public void testIniReaml(){
        IniRealm iniRealm = new IniRealm("classpath:user.ini");
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(iniRealm);

        UsernamePasswordToken token = new UsernamePasswordToken("mark","123456");

        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        subject.login(token);

        System.out.println("authentication: " + subject.isAuthenticated());

    }
}
