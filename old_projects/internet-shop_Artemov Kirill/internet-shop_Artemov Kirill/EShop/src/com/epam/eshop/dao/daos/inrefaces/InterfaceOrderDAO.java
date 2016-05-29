package com.epam.eshop.dao.daos.inrefaces;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.epam.eshop.model.entities.Order;

public interface InterfaceOrderDAO {

	List<Order> getOrders() throws SQLException;

	int addOrder(int userId, int paymentId, int shippingId, int statusId,
			long orderDate, float totalPrice) throws SQLException;

	Connection releaseConnection();

	List<Order> getOrders(String log) throws SQLException;

	boolean updateOrder(int orderId, int statusId) throws SQLException;

}
