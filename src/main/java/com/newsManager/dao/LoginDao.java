package com.newsManager.dao;


import java.sql.SQLException;

import com.newsManager.entity.User;
/**
 * @author XCDD  邮箱：2207346898@qq.com
 * @description
 * @vision
 */
public interface LoginDao {
	//查找是否登录成功
	public User findUser(String uname, String password)  throws SQLException;
}