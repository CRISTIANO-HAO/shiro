package com.shiro.java;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CustomReaml extends AuthorizingRealm {
    Map<String ,String > map = new HashMap<>();
    {
        map.put("mark","12345");
        super.setName("custom");
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //从认证信息中获取用户名
        String username = (String) principalCollection.getPrimaryPrincipal();
        System.out.println("authorization: " + username);
        Set<String> roles = getRolesByName(username);
        Set<String> permissions = getPermissionByName(username);

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(permissions);
        simpleAuthorizationInfo.setRoles(roles);
        return simpleAuthorizationInfo;
    }

    private Set<String> getPermissionByName(String username) {
        Set<String> set = new HashSet<>();
        set.add("user:update");
        set.add("admin:delete");
        return set;
    }

    private Set<String> getRolesByName(String username) {
        Set<String> set = new HashSet<>();
        set.add("user");
        set.add("admin");
        return set;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //从认证信息中获取用户名
        String username = (String) authenticationToken.getPrincipal();
        System.out.println(username);
        //获取密码
        String password = getPasswordByUsername(username);
        if (password == null){
            return null;
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username,password,"custom");
        return simpleAuthenticationInfo;
    }

    private String getPasswordByUsername(String username) {
        return map.get("mark");
    }
}
