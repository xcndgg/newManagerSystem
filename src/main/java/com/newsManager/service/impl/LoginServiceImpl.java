package com.newsManager.service.impl;

import java.sql.SQLException;

import com.newsManager.entity.User;
import com.newsManager.dao.impl.LoginDaoImpl;
import com.newsManager.service.LoginService;
/**
 * @author XCDD  邮箱：2207346898@qq.com
 * @description
 * @vision
 */
public class LoginServiceImpl implements LoginService {

    @Override
    public User doLogin(User user) throws SQLException {
        return new LoginDaoImpl().findUser(user.getUname(), user.getUpwd());
    }

}
