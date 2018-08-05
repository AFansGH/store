package com.store.domain;

import java.util.List;

public class PageBean<T> {
	private int currentPage;		//当前页
	private int allPage;			//总页数
	private int onePageCount;		//一页显示的数量		
	private int allProductCount;	//总的产品的数量
	private List<T> onePageProduct;	//一页的产品
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getAllPage() {
		return allPage;
	}
	public void setAllPage(int allPage) {
		this.allPage = allPage;
	}
	public int getOnePageCount() {
		return onePageCount;
	}
	public void setOnePageCount(int onePageCount) {
		this.onePageCount = onePageCount;
	}
	public int getAllProductCount() {
		return allProductCount;
	}
	public void setAllProductCount(int allProductCount) {
		this.allProductCount = allProductCount;
	}
	public List<T> getOnePageProduct() {
		return onePageProduct;
	}
	public void setOnePageProduct(List<T> onePageProduct) {
		this.onePageProduct = onePageProduct;
	}
}
