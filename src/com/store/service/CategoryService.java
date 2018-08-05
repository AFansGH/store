package com.store.service;

import java.sql.SQLException;
import java.util.List;

import com.store.dao.CategoryDao;
import com.store.domain.Category;

public class CategoryService {

	public List<Category> findAllCategory() {
		CategoryDao categoryDao = new CategoryDao();
		List<Category> categoryList = null;
		try {
			categoryList = categoryDao.findAllCategory();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categoryList;
	}

}
