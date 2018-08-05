package com.store.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.store.domain.User;
import com.store.utils.DataSourceUtils;

public class LoginDao {

	public User login(String username, String password) throws SQLException {
		QueryRunner qr  = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
		User user = qr.query(sql, new BeanHandler<User>(User.class),username,password);
		return user;
	}

}
