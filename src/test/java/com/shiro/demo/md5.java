package com.shiro.demo;


import com.shiro.java.CustomReamlMd5;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class md5 {

    @Test
    public void md5(){
        //自定义Reaml
        CustomReamlMd5 customReamlMd5 = new CustomReamlMd5();
        System.out.println(customReamlMd5.getName());

        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(customReamlMd5);

        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //设置加密算法名称
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        //设置加密次数
        hashedCredentialsMatcher.setHashIterations(1);
        //自定义reaml引入加密算法
        customReamlMd5.setCredentialsMatcher(hashedCredentialsMatcher);

        SecurityUtils.setSecurityManager(defaultSecurityManager);

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("mark","12345");

        subject.login(token);
    }
}
