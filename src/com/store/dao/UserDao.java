package com.store.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.store.utils.DataSourceUtils;

public class UserDao {

	public void active(String code) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "UPDATE USER SET state = ? WHERE code = ?";
		qr.update(sql,1,code);
		
	}

	public long checkUser(String username) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "SELECT COUNT(*) FROM USER WHERE username = ?";
		Long count = (Long) qr.query(sql, new ScalarHandler(),username);
		return count;
	}

}
