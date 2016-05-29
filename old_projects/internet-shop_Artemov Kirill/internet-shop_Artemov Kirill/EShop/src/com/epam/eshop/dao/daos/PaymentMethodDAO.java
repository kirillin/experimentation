package com.epam.eshop.dao.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.eshop.dao.daos.inrefaces.InterfacePaymentMethodDAO;
import com.epam.eshop.manager.DBQueryManager;
import com.epam.eshop.model.entities.PaymentMethod;

/**
 * Class for manipulation DB and table payment_methods.
 * 
 * @author Kirill Artemov
 * 
 */
public class PaymentMethodDAO implements InterfacePaymentMethodDAO {

	private static Logger logger = Logger.getLogger(PaymentMethodDAO.class);
	private Connection connection;

	public PaymentMethodDAO() {
		super();
	}

	/**
	 * @param connection
	 */
	public PaymentMethodDAO(Connection connection) {
		super();
		this.connection = connection;
	}

	@Override
	public List<PaymentMethod> getPaymentMethods() throws SQLException {
		List<PaymentMethod> methods = new LinkedList<PaymentMethod>();
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(DBQueryManager.getInstance()
					.getProperty(DBQueryManager.GET_PAYMENT_METHODS));
			ResultSet rs = null;
			try {
				rs = ps.executeQuery();
				while (rs.next()) {
					PaymentMethod pm = new PaymentMethod();
					pm.setId(rs.getInt(1));
					pm.setPaymentMethod(rs.getString(2));
					pm.setDescription(rs.getString(3));
					methods.add(pm);
				}
			} finally {
				if (rs != null) {
					rs.close();
				}
			}
		} finally {
			if (ps != null) {
				ps.close();
			}
		}
		if (!methods.isEmpty()) {
			logger.info("List of payment methods was returns!");
		} else {
			methods = null;
		}
		return methods;
	}
	
	@Override
	public PaymentMethod getPaymentMethodById(int id) throws SQLException {
		PaymentMethod paymentMethod = null;
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(DBQueryManager.getInstance()
					.getProperty(DBQueryManager.GET_PAYMENT_METHOD_BY_ID));
			ps.setInt(1, id);
			ResultSet rs =  null;
			try {
				rs = ps.executeQuery();
				if (rs.next()) {
					paymentMethod = new PaymentMethod();
					paymentMethod.setId(rs.getInt(1));
					paymentMethod.setPaymentMethod(rs.getString(2));
					paymentMethod.setDescription(rs.getString(3));
				}
			} finally {
				if (rs != null) {
					rs.close();
				}
			}
		} finally {
			if (ps != null) {
				ps.close();
			}
		}
		if (paymentMethod != null) {
			logger.info("List of payment methods was returns!");
		}
		return paymentMethod;
	}

	/**
	 * The method for release connection
	 */
	@Override
	public Connection releaseConnection() {
		return this.connection;
	}
}
