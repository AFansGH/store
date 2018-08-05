package com.store.dao;

import java.sql.SQLException;


import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.store.domain.Product;
import com.store.utils.DataSourceUtils;

public class ProductDao {

	public Product findProduct(String pid) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "SELECT * FROM product WHERE pid = ?;";
		Product product = qr.query(sql, new BeanHandler<Product>(Product.class),pid);
		return product;
	}

}
