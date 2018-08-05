package com.store.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import com.store.domain.User;
import com.store.utils.DataSourceUtils;

public class RegisterDao {

	public int regist(User user) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "INSERT INTO USER VALUES (?,?,?,?,?,?,?,?,?,?)";
		int count = qr.update(sql, user.getUid(),user.getUsername(),user.getPassword(),user.getName(),user.getEmail()
				,user.getTelephone(),user.getBirthday(),user.getSex(),user.getState(),user.getCode());
		return count;
	}

}
