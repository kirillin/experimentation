package com.epam.eshop.dao.daos.inrefaces;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.epam.eshop.model.entities.ShippingMethod;

public interface InterfaceShippingMethodDAO {

	Connection releaseConnection();

	List<ShippingMethod> getShippingMethods() throws SQLException;

	ShippingMethod getShippingMethodById(int id) throws SQLException;

}
