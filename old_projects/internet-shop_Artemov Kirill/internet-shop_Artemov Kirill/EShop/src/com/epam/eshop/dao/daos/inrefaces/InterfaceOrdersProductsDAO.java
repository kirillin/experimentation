package com.epam.eshop.dao.daos.inrefaces;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import com.epam.eshop.model.entities.Product;

public interface InterfaceOrdersProductsDAO {

	Connection releaseConnection();

	boolean addNodeOrdersProducts(int order_id,
			HashMap<Integer, Integer> pairsIdCount) throws SQLException;

	HashMap<Product, Integer> getNodeOrdersProducts(int orderId)
			throws SQLException;
}
