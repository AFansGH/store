package com.store.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.store.dao.OrderDao;
import com.store.domain.Order;
import com.store.utils.DataSourceUtils;

public class OrderService {

	public boolean addOrder(Order order) {
		
		boolean flag = true;
		//在这里调用两个Dao层的方法，将数据写入到数据库，他们是同一个事务
		OrderDao orderDao = new OrderDao();
		//开启一个事务
		try {
			DataSourceUtils.startTransaction();
			orderDao.addOrder(order);
			orderDao.addOrderitem(order.getOrderitemList());
		} catch (SQLException e) {
			try {
				//如果抓到异常就回滚，就添加失败
				flag = false;
				DataSourceUtils.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally{
			try {
				DataSourceUtils.commitAndRelease();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	
	//用于查询所有的订单信息
	public List<Order> findAllOrders(String uid) {
		OrderDao orderDao = new OrderDao();
		List<Order> orders = null;
		try {
			orders = orderDao.findAllOrders(uid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}

	//使用oid查询数据
	public List<Map<String,Object>> findAllOrderItem(String oid) {
		OrderDao orderDao = new OrderDao();
		List<Map<String, Object>> orderitemListInfo = null ;
		try {
			orderitemListInfo = orderDao.findAllOrderItem(oid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderitemListInfo;
	}

}
