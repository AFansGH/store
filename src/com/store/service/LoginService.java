package com.store.service;

import java.sql.SQLException;

import com.store.dao.LoginDao;
import com.store.domain.User;

public class LoginService {

	public User login(String username, String password) {
		LoginDao loginDao = new LoginDao();
		User user = null;
		try {
			user = loginDao.login(username,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

}
