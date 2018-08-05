package com.store.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.store.domain.Category;
import com.store.utils.DataSourceUtils;

public class CategoryDao {

	public List<Category> findAllCategory() throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "SELECT * FROM category";
		List<Category> categoryList = qr.query(sql, new BeanListHandler<Category>(Category.class));
		return categoryList;
	}

}
