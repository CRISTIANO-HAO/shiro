package com.shiro.dao;

import com.shiro.entity.User;

import java.util.List;
import java.util.Set;

public interface UserDao {
    User getUserByName(String username);

    List<String> getRolesByName(String username);

    List<String> getPermissionByName(String username);
}
