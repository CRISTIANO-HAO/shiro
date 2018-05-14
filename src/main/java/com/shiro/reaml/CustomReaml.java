package com.shiro.reaml;

import com.shiro.dao.UserDao;
import com.shiro.entity.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class CustomReaml extends AuthorizingRealm {

    @Autowired
    private UserDao userDao;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //从认证信息中获取用户名
        String username = (String) principalCollection.getPrimaryPrincipal();
        List<String> roles =  userDao.getRolesByName(username);
        List<String> permissions = userDao.getPermissionByName(username);

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        System.out.println(roles.size());
        System.out.println(permissions.size());
        simpleAuthorizationInfo.setStringPermissions(new HashSet<String>(permissions));
        simpleAuthorizationInfo.setRoles(new HashSet<String>(roles));
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //从认证信息中获取用户名
        String username = (String) authenticationToken.getPrincipal();
        //获取密码
        String password = userDao.getUserByName(username).getPassword();

        if (password == null){
            return null;
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username,password,"customReaml");
        simpleAuthenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(username));
        return simpleAuthenticationInfo;
    }
}
