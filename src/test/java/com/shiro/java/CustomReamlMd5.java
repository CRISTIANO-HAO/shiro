package com.shiro.java;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashMap;
import java.util.Map;

public class CustomReamlMd5 extends AuthorizingRealm{

    Map<String ,String > map = new HashMap<>();
    {
        map.put("mark","d2f0e963198965722fd22e9ab414efbc");
        super.setName("customMd5");
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //从认证信息中获取用户名
        String username = (String) authenticationToken.getPrincipal();
        //获取密码
        String password = getPasswordByUsername(username);
        if (password == null){
            return null;
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username,password,"custom");
        //加盐
        simpleAuthenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(username));
        return simpleAuthenticationInfo;
    }

    private String getPasswordByUsername(String username) {
        return map.get("mark");
    }

    public static void main(String[] args) {
        Md5Hash md5Hash = new Md5Hash("123456","rose",5);

        System.out.println(md5Hash.toString());//7057431d5a702aec1d534edf8e00ad9a
    }
}
