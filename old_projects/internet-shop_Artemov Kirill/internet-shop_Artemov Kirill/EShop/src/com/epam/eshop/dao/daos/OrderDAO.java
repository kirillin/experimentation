package com.epam.eshop.dao.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.eshop.dao.daos.inrefaces.InterfaceOrderDAO;
import com.epam.eshop.manager.DBQueryManager;
import com.epam.eshop.model.entities.Order;

/**
 * Class for manipulation DB and table orders.
 * 
 * @author Kirill Artemov
 * 
 */
public class OrderDAO implements InterfaceOrderDAO {

	private static Logger logger = Logger.getLogger(OrderDAO.class);
	private Connection connection;

	/**
	 * Default constructor 
	 */
	public OrderDAO() {
		this.connection = null;
	}
	/**
	 * Constructor for seting connection
	 * @param connection
	 */
	public OrderDAO(Connection connection) {
		this.connection = connection;
	}

	@Override
	public List<Order> getOrders() throws SQLException {
		List<Order> orders = new LinkedList<Order>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(DBQueryManager.getInstance()
					.getProperty(DBQueryManager.GET_ORDERS));
			rs = ps.executeQuery();
			try {
				while (rs.next()) {
					Order order = new Order();
					order.setId(rs.getInt(1));
					order.setUserId(rs.getInt(2));
					order.setPaymentMethodId(rs.getInt(3));
					order.setShippingId(rs.getInt(4));
					order.setStatusId(rs.getInt(5));
					order.setOrderDate(rs.getTimestamp(6));
					order.setTotalPrice(rs.getFloat(7));
					orders.add(order);
				}
			} finally {
				if (rs != null)
					rs.close();
			}
		} finally {
			if (ps != null)
				ps.close();
		}
		if (!orders.isEmpty()) {
			logger.info("List of orders returns!");
		} else {
			orders = null;
		}
		return orders;
	}

	@Override
	public List<Order> getOrders(String log) throws SQLException {
		List<Order> orders = new LinkedList<Order>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(DBQueryManager.getInstance()
					.getProperty(DBQueryManager.GET_ORDERS_BY_LOGIN));
			ps.setString(1, log);
			rs = ps.executeQuery();
			try {
				while (rs.next()) {
					Order order = new Order();
					order.setId(rs.getInt(1));
					order.setUserId(rs.getInt(2));
					order.setPaymentMethodId(rs.getInt(3));
					order.setShippingId(rs.getInt(4));
					order.setStatusId(rs.getInt(5));
					order.setOrderDate(rs.getTimestamp(6));
					order.setTotalPrice(rs.getFloat(7));
					orders.add(order);
				}
			} finally {
				if (rs != null)
					rs.close();
			}
		} finally {
			if (ps != null)
				ps.close();
		}
		if (!orders.isEmpty()) {
			logger.info("List of orders returns!");
		} else {
			orders = null;
		}
		return orders;
	}

	/**
	 * 
	 */
	@Override
	public int addOrder(int userId, int paymentId, int shippingId,
			int statusId, long orderDate, float totalPrice) throws SQLException {
		PreparedStatement ps = null;
		boolean success = false;
		try {
			connection.setAutoCommit(false);
			ps = connection.prepareStatement(DBQueryManager.getInstance()
					.getProperty(DBQueryManager.ADD_ORDER));
			ps.setInt(1, userId);
			ps.setInt(2, paymentId);
			ps.setInt(3, shippingId);
			ps.setInt(4, statusId);
//			ps.setLong(5, orderDate);
			ps.setFloat(5, totalPrice);
			ps.execute();
			success = true;
			connection.commit();
		} finally {
			if (ps != null)
				ps.close();
		}
		// Returns id of added order now
		ResultSet rs = null;
		int orderId = -1;
		try {
			ps = connection.prepareStatement(DBQueryManager.getInstance()
					.getProperty(DBQueryManager.GET_ORDER_ID));
			ps.setInt(1, userId);
			ps.setInt(2, paymentId);
			ps.setInt(3, shippingId);
			ps.setInt(4, statusId);
//			ps.setLong(5, orderDate);
			rs = ps.executeQuery();
			if (rs.next()) {
				orderId = rs.getInt(1);
			}
		} finally {
			if (ps != null)	ps.close();
			if (rs != null)	rs.close();
		}		
		if (success == true) {
			logger.trace("Order was added!");
		} else {
			logger.trace("Order was not added!");
		}
		return orderId;
	}
	@Override
	public boolean updateOrder(int orderId, int statusId) throws SQLException { 
		PreparedStatement ps = null;
		boolean success = false;
		try {
			ps = connection.prepareStatement(
							DBQueryManager.getInstance()
							.getProperty(DBQueryManager.ORDER_UPDATE));
			ps.setInt(1, statusId);
			ps.setInt(2, orderId);
			ps.execute();
			success = true;			
		} finally {
			if (ps != null) ps.close();
		}
		if (success == true) {
			logger.info("Order updated!");
		}
		return success;
	}
	
	@Override
	public Connection releaseConnection() {
		return this.connection;
	}
}
