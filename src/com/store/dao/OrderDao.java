package com.store.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.store.domain.Order;
import com.store.domain.Orderitem;
import com.store.utils.DataSourceUtils;

public class OrderDao {

	public void addOrder(Order order) throws SQLException {
		//添加订单到数据库中,因为使用了同一个事务，所以qr不适用源
		QueryRunner qr = new QueryRunner();
		String sql = "INSERT INTO orders VALUES(?,?,?,?,?,?,?,?)";
		Connection conn = DataSourceUtils.getConnection();
		qr.update(conn,sql,order.getOid(),order.getOrdertime(),order.getTotal(),order.getState(),order.getAddress()
				,order.getName(),order.getOtelephoneid(),order.getUser().getUid());
	}

	public void addOrderitem(List<Orderitem> orderitemList) throws SQLException {
		QueryRunner qr = new QueryRunner();
		String sql = "INSERT INTO orderitem VALUES(?,?,?,?,?)";
		Connection conn = DataSourceUtils.getConnection();
		for(Orderitem orderitem :orderitemList){
			qr.update(conn, sql,orderitem.getItemid(),orderitem.getCount(),orderitem.getSubtotal(),orderitem.getPro().getPid()
					,orderitem.getOrder().getOid());
		}
		
		
	}

	public List<Order> findAllOrders(String uid) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "SELECT * FROM orders WHERE orders.uid = ?";
		List<Order> orders = qr.query(sql, new BeanListHandler<Order>(Order.class),uid);
		return orders;
	}

	public List<Map<String,Object>> findAllOrderItem(String oid) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "SELECT pname,pimage,shop_price,o.count,subtotal FROM orderitem o  INNER JOIN product p ON o.oid = ? AND  o.pid = p.pid ";
		List<Map<String,Object>> orderitemListInfo = qr.query(sql, new MapListHandler(),oid);
		return orderitemListInfo;
	}

}
