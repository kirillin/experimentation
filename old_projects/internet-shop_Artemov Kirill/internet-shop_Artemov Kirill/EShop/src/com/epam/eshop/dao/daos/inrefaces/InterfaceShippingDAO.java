package com.epam.eshop.dao.daos.inrefaces;

import java.sql.Connection;
import java.sql.SQLException;

import com.epam.eshop.model.entities.Shipping;

public interface InterfaceShippingDAO {

	int addShipping(int shippingMId, int cityId, String adress, String zip,
			String phone) throws SQLException;

	Connection releaseConnection();

	Shipping getShippingById(int id) throws SQLException;

}
