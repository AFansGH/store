package com.store.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.store.domain.Product;
import com.store.utils.DataSourceUtils;

public class ProductListByIdDao {

	public int findAllProductCountById(String cid) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "SELECT COUNT(*) FROM product WHERE cid = ?";
		Long count = (Long) qr.query(sql, new ScalarHandler(),cid);
		return Integer.parseInt(count+"");
	}

	public List<Product> findOnePageProductListById(String cid, int index, int onePageCount) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		//查询limit需要开始的索引 
		String sql = "SELECT * FROM product WHERE cid = ? LIMIT ?,?";
		List<Product> productList = qr.query(sql, new BeanListHandler<Product>(Product.class),cid,index,onePageCount);
		return productList;
	}

}
