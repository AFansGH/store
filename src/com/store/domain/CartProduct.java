package com.store.domain;

//这个类是加入购物车的对象封装，拥有字段为产品对象，购买数量，金额小计

public class CartProduct {
	private Product pro;
	private int buyNum;
	private double subtotal;
	public Product getPro() {
		return pro;
	}
	public void setPro(Product pro) {
		this.pro = pro;
	}
	public int getBuyNum() {
		return buyNum;
	}
	public void setBuyNum(int buyNum) {
		this.buyNum = buyNum;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	
}
