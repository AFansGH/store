package com.store.service;

import java.sql.SQLException;

import com.store.dao.UserDao;

public class UserService {

	public boolean active(String code) {
		UserDao userDao = new UserDao();
		try {
			userDao.active(code);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean checkUser(String username) {
		UserDao userDao = new UserDao();
		long count = 0L;
		try {
			count = userDao.checkUser(username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count>0?false:true;
	}
}
