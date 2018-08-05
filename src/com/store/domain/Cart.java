package com.store.domain;

import java.util.HashMap;
import java.util.Map;

//这是购物车对象，本对象拥有所有的加入购物车产品对象，还应该拥有总计的金额参数
//加入购车的对象根据业务需求，应该用map集合来存，这样取得时候可以通过key好取。存的时候同一件产品也不会有多个栏显示
public class Cart {
	private Map<String,CartProduct> cartmap = new HashMap<String,CartProduct>();
	private double total;
	
	
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public Map<String,CartProduct> getCartmap() {
		return cartmap;
	}
	public void setCartmap(Map<String,CartProduct> cartmap) {
		this.cartmap = cartmap;
	}
	
}
