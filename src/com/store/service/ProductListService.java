package com.store.service;

import java.sql.SQLException;
import java.util.List;

import com.store.dao.ProductListDao;
import com.store.domain.Product;

public class ProductListService {

	//查询热门商品
	public List<Product> findHotProductList() {
		ProductListDao pDao = new ProductListDao();
		List<Product> hotProductList = null;
		try {
			hotProductList = pDao.findHotProductList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hotProductList;
	}

	//查询最新商品
	public List<Product> findNewProductList() {
		ProductListDao pDao = new ProductListDao();
		List<Product> newProductList = null;
		try {
			newProductList = pDao.findNewProductList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newProductList;
	}

}
