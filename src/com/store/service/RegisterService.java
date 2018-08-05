package com.store.service;

import java.sql.SQLException;

import com.store.domain.User;
import com.store.dao.RegisterDao;

public class RegisterService {

	public boolean regist(User user) {
		RegisterDao registerDao = new RegisterDao();
		int count = 0;
		try {
			count = registerDao.regist(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count>0?true:false;
	}

}
