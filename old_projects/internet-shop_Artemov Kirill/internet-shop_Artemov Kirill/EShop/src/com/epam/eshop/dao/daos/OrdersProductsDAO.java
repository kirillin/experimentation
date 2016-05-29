package com.epam.eshop.dao.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;

import com.epam.eshop.dao.daos.inrefaces.InterfaceOrdersProductsDAO;
import com.epam.eshop.manager.DBQueryManager;
import com.epam.eshop.model.entities.Product;

public class OrdersProductsDAO implements InterfaceOrdersProductsDAO {

	private static Logger logger = Logger.getLogger(OrdersProductsDAO.class);
	private Connection connection;

	/**
	 * Default constructor
	 */
	public OrdersProductsDAO() {
		this.connection = null;
	}

	/**
	 * Constructor for seting connection
	 * 
	 * @param connection
	 */
	public OrdersProductsDAO(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public boolean addNodeOrdersProducts(int orderId, 
			HashMap<Integer, Integer> pairsIdCount) throws SQLException {
		PreparedStatement ps = null;
		boolean success = false;
		connection.setAutoCommit(false);
		// inserts all product_id for the order_id
		Iterator it = pairsIdCount.entrySet().iterator();
		while (it.hasNext()) {
		    Map.Entry pairs = (Map.Entry)it.next();
			success = false;
			// insert one product_id
			try {
				ps = connection.prepareStatement(DBQueryManager.getInstance()
						.getProperty(DBQueryManager.ADD_NODE_ORDERS_PRODUCTS));
				    ps.setInt(1, orderId);
				    ps.setInt(2, (int) pairs.getKey());
				    ps.setInt(3, (int) pairs.getValue());
					ps.execute();
					success = true;					
			} finally {
				if (ps != null)
					ps.close();
			}
			if (success == true) {
				logger.trace("orders_products item was added!");
				success = false;
			} else {
				logger.trace("orders_products item was not added!");
			}
			// gets quantity from product in table products
			ResultSet rs = null;
			int quantityIs = -1;
			try {
				ps = connection.prepareStatement(DBQueryManager.getInstance()
						.getProperty(DBQueryManager.GET_PRODUCT_BY_ID));
					ps.setInt(1, (int) pairs.getKey());
					rs = ps.executeQuery();
					if (rs.next()) {
						quantityIs = rs.getInt(5);
					}
			} finally {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			}
			// and update new qty in the product
			try {
				ps = connection.prepareStatement(DBQueryManager.getInstance()
						.getProperty(DBQueryManager.UPDATE_QUANTITY_PRODUCT));
				if (quantityIs != -1) {
					// deducted from available quantity bought
					// and add as remaining qty
					ps.setInt(1, (quantityIs - ((int) pairs.getValue())));
					ps.setInt(2, (int) pairs.getKey());
					ps.execute();
					success = true;
				}
			} finally {
				if (ps != null)
					ps.close();
			}
			if (success == true) {
				logger.trace("Products qty was updated!");
			} else {
				logger.trace("Products qty was not updated!");
			}
		}
		connection.commit();
		return success;
	}

	/**
	 * Returns HashMap of pairs productId-qty from orders_products table 
	 */
	@Override
	public HashMap<Product, Integer> getNodeOrdersProducts(int orderId)
			throws SQLException {
		PreparedStatement ps = null;
		HashMap<Product, Integer> pairsProductIdQty = new HashMap<Product, Integer>();
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(DBQueryManager.getInstance()
					.getProperty(DBQueryManager.GET_PRODUCTS_BY_ORDER_ID));
				ps.setInt(1, orderId);
				rs = ps.executeQuery();
				while (rs.next()) {
					Product product = new Product();
					product.setId(rs.getInt(1));
					product.setCategoryId(rs.getInt(2));
					product.setProductName(rs.getString(3));
					product.setProductPrice(rs.getFloat(4));
					product.setQuantity(rs.getInt(5));
					product.setDescription(rs.getString(6));
					product.setActual(rs.getBoolean(7));
					int quantity = rs.getInt(2);
					pairsProductIdQty.put(product, quantity);
				}
		} finally {
			if (ps != null)
				ps.close();
			if (rs != null)
				rs.close();
		}
		if (!pairsProductIdQty.isEmpty()) {
			logger.trace("HashMap with pairs Product-Qty was returned!");
		} else {
			logger.trace("HashMap with pairs Product-Qty was not returned!");
			pairsProductIdQty = null;
		}
		return pairsProductIdQty;
	}

	@Override
	public Connection releaseConnection() {
		return this.connection;
	}

}
