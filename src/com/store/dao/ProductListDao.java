package com.store.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.store.domain.Product;
import com.store.utils.DataSourceUtils;

public class ProductListDao {

	public List<Product> findHotProductList() throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "SELECT * FROM product WHERE is_hot = ? LIMIT ?,?";
		List<Product> hotProductList = qr.query(sql, new BeanListHandler<Product>(Product.class),1,0,9);
		return hotProductList;
	}

	public List<Product> findNewProductList() throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "SELECT * FROM product ORDER BY pdate DESC LIMIT ?,?";
		List<Product> newProductList = qr.query(sql, new BeanListHandler<Product>(Product.class),0,9);
		return newProductList;
	}

}
