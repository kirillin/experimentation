package com.epam.eshop.dao.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.eshop.dao.daos.inrefaces.InterfaceShippingMethodDAO;
import com.epam.eshop.manager.DBQueryManager;
import com.epam.eshop.model.entities.ShippingMethod;

/**
 * Class for manipulation DB and table shipping_methods.
 * 
 * @author Kirill Artemov
 * 
 */
public class ShippingMethodDAO implements InterfaceShippingMethodDAO {

	private static Logger logger = Logger.getLogger(ShippingMethodDAO.class);
	private Connection connection;
	/**
	 * Default constructor
	 */
	public ShippingMethodDAO() {
		super();		
	}

	/**
	 * @param connection
	 */
	public ShippingMethodDAO(Connection connection) {
		super();
		this.connection = connection;
	}
	/**
	 * Returns list of shipping methods.
	 */
	@Override
	public List<ShippingMethod> getShippingMethods() throws SQLException {
		List<ShippingMethod> methods = new LinkedList<ShippingMethod>();
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(DBQueryManager.getInstance()
					.getProperty(DBQueryManager.GET_SHIPPING_METHODS));
			ResultSet rs = null;			
			try {
				rs = ps.executeQuery();				
				while (rs.next()) {
					ShippingMethod shm = new ShippingMethod();
					shm.setId(rs.getInt(1));
					shm.setShippingMethod(rs.getString(2));
					shm.setCost(rs.getFloat(3));
					shm.setLength(rs.getInt(4));
					shm.setDescription(rs.getString(5));
					methods.add(shm);
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
		if (methods != null) {
			logger.info("List of shipping methods was returned!");
		}
		return methods;
	}

	@Override
	public ShippingMethod getShippingMethodById(int id) throws SQLException {
		ShippingMethod shippingMethod = null;
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(DBQueryManager.getInstance()
					.getProperty(DBQueryManager.GET_SHIPPING_NETHOD_BY_ID));
			ps.setInt(1, id);
			ResultSet rs =  null;
			try {
				rs = ps.executeQuery();
				if (rs.next()) {
					shippingMethod = new ShippingMethod();
					shippingMethod.setId(rs.getInt(1));
					shippingMethod.setShippingMethod(rs.getString(2));
					shippingMethod.setCost(rs.getFloat(3));
					shippingMethod.setLength(rs.getInt(4));
					shippingMethod.setDescription(rs.getString(5));
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
		if (shippingMethod != null) {
			logger.info("Shipping method was returns!");
		}
		return shippingMethod;
	}	
	/**
	 * The method for release connection
	 */
	@Override
	public Connection releaseConnection() {
		return this.connection;
	}

}
