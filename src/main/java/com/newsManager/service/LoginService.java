package com.newsManager.service;

import java.sql.SQLException;

import com.newsManager.entity.User;
/**
 * @author XCDD  邮箱：2207346898@qq.com
 * @description
 * @vision
 */
public interface LoginService {
    public User doLogin(User user) throws SQLException;
}
