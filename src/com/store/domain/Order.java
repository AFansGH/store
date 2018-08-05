package com.store.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
	
	
	private String oid;				//订单id
	private Date ordertime;			//创建时间
	private double total;			//订单金额
	private int state;				//付款的状态
	
	private String address;			//收货人地址
	private String name;			//收货人名称
	private String otelephoneid;	//联系电话
	
	private User user;				//订单所属用户
	
	
	//这里为了方便订单项数据的传递写入数据库，我们在这里封装订单项对象的引用
	private List<Orderitem> orderitemList = new ArrayList<Orderitem>();
	
	
	
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public Date getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOtelephoneid() {
		return otelephoneid;
	}
	public void setOtelephoneid(String otelephoneid) {
		this.otelephoneid = otelephoneid;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Orderitem> getOrderitemList() {
		return orderitemList;
	}
	public void setOrderitemList(List<Orderitem> orderitemList) {
		this.orderitemList = orderitemList;
	}
	
	
}
