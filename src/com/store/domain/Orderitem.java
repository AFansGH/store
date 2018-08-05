package com.store.domain;

public class Orderitem {

	
	private String itemid;				//单个订单项的id
	private int count;					//购买数量
	private double subtotal;			//小计金额
	
	//根据面向对象的思想，我们在创建表对象时，对于外键应该封装为对象
	private Product pro;					//产品的id
	private Order order;					//所属的订单的id
	
	
	
	public String getItemid() {
		return itemid;
	}
	public void setItemid(String itemid) {
		this.itemid = itemid;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public Product getPro() {
		return pro;
	}
	public void setPro(Product pro) {
		this.pro = pro;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	
	
}
