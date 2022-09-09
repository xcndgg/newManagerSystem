package com.newsManager.dao.impl;

import java.sql.SQLException;

import com.newsManager.dao.BaseDao;
import com.newsManager.entity.User;
import com.newsManager.dao.LoginDao;
/**
 * @author XCDD  邮箱：2207346898@qq.com
 * @description
 * @vision
 */
public class LoginDaoImpl extends BaseDao implements LoginDao {


    public User findUser(String uname, String password) throws SQLException {
        // 根据用户名密码查找匹配的用户
        String sql = "select * from NEWS_USERS where uname=? and upwd=?";
        return queryForOne(User.class, sql, uname, password);
    }
}
