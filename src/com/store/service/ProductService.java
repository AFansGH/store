package com.store.service;

import java.sql.SQLException;


import com.store.dao.ProductDao;
import com.store.domain.Product;

public class ProductService {

	public Product findProduct(String pid) throws SQLException {
		ProductDao pDao = new ProductDao();
		Product product = pDao.findProduct(pid);
		return product;
	}

}
