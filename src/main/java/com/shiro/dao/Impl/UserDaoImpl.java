package com.shiro.dao.Impl;

import com.shiro.dao.UserDao;
import com.shiro.entity.User;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

@Repository
public class UserDaoImpl implements UserDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public User getUserByName(String username) {
        String sql = "select name,password from users where name = ?";
        List<User> list = jdbcTemplate.query(sql, new String[]{username}, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                user.setUsername(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        });

        if (CollectionUtils.isEmpty(list)){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<String> getRolesByName(String username) {
        String sql = "select role from roles where name = ?";
        List<String> list = jdbcTemplate.query(sql, new String[]{username}, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("role");
            }
        });

        if (CollectionUtils.isEmpty(list)){
            return null;
        }
        return list;
    }

    @Override
    public List<String> getPermissionByName(String username) {
        String sql = "select permission from permissions where name = ?";
        List<String> list =jdbcTemplate.query(sql, new String[]{username}, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("permission");
            }
        });

        if (CollectionUtils.isEmpty(list)){
            return null;
        }
        return list;
    }
}
