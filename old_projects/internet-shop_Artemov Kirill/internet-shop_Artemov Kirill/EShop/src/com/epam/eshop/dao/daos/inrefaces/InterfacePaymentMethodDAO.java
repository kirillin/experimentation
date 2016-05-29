package com.epam.eshop.dao.daos.inrefaces;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.epam.eshop.model.entities.PaymentMethod;

public interface InterfacePaymentMethodDAO {

	Connection releaseConnection();

	List<PaymentMethod> getPaymentMethods() throws SQLException;

	PaymentMethod getPaymentMethodById(int id) throws SQLException;

}
