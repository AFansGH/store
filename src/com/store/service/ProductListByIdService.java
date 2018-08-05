package com.store.service;

import java.sql.SQLException;
import java.util.List;

import com.store.dao.ProductListByIdDao;
import com.store.domain.PageBean;
import com.store.domain.Product;

public class ProductListByIdService {

	public PageBean<Product> findProductListById(String cid, int currentPage, int onePageCount) {
		
		ProductListByIdDao productListByIdDao = new ProductListByIdDao();
		//在这里封装数据
		PageBean<Product> pagebean = new PageBean<Product>();
		//封装当前页
		pagebean.setCurrentPage(currentPage);
		//封装一页显示的数据
		pagebean.setOnePageCount(onePageCount);
		//封装总产品数量
		int allProductCount = 0;
		try {
			allProductCount = productListByIdDao.findAllProductCountById(cid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		pagebean.setAllProductCount(allProductCount);
		//封装总页数
		int allPage = (int) Math.ceil(1.0*allProductCount/onePageCount);
		pagebean.setAllPage(allPage);
		//去数据库拿一页应该放的产品,index为从数据库查询的分页索引
		int index = (currentPage-1)*onePageCount;
		List<Product> onePageProduct = null;
		try {
			onePageProduct = productListByIdDao.findOnePageProductListById(cid,index,onePageCount);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//封装一页的Product数据
		pagebean.setOnePageProduct(onePageProduct);
		return pagebean;
	}

}
