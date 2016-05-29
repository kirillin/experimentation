package com.epam.eshop.dao.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.epam.eshop.dao.daos.inrefaces.InterfaceShippingDAO;
import com.epam.eshop.manager.DBQueryManager;
import com.epam.eshop.model.entities.Shipping;

/**
 * Class for manipulation DB and table shipping_orders.
 * 
 * @author Kirill Artemov
 * 
 */
public class ShippingDAO implements InterfaceShippingDAO {

	private static Logger logger = Logger.getLogger(ShippingDAO.class);
	private Connection connection;
	/**
	 * Default constructor
	 */
	public ShippingDAO() {
		this.connection = null;
	}
	/**
	 * Constructor for seting connection
	 * @param connection
	 */
	public ShippingDAO(Connection connection) {
		this.connection = connection;
	}
	/**
	 * Returns ID of inserts record.
	 */
	@SuppressWarnings("resource")
	@Override
	public int addShipping(int shippingMethodId, int cityId, String adress, 
			String zip, String phone) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int shippingId = -1;
		try {
			connection.setAutoCommit(false);
			ps = connection.prepareStatement(DBQueryManager.getInstance()
					.getProperty(DBQueryManager.ADD_SHIPPING));
			ps.setInt(1, shippingMethodId);
			ps.setInt(2, cityId);
			ps.setString(3, adress);
			ps.setString(4, zip);
			ps.setString(5, phone);
			ps.execute();
			// Find and return ID when added now
			ps = connection.prepareStatement(DBQueryManager.getInstance()
					.getProperty(DBQueryManager.GET_SHIPPING_ID));
			ps.setInt(1, shippingMethodId);
			ps.setInt(2, cityId);
			ps.setString(3, adress);
			ps.setString(4, zip);
			ps.setString(5, phone);
			rs = ps.executeQuery();
			if (rs.next()) {
				shippingId = rs.getInt(1);
				connection.commit();
			}
		} finally {
			if (rs != null) rs.close();
			if (ps != null)
				ps.close();
		}
		if (shippingId >= 0) {
			logger.info("ShippingId was returned!");
		}		
		return shippingId;
	}
	
	@Override
	public Shipping getShippingById(int id) throws SQLException {
		Shipping shipping = null;
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(DBQueryManager.getInstance()
					.getProperty(DBQueryManager.GET_SHIPPING_BY_ID));
			ps.setInt(1, id);
			ResultSet rs =  null;
			try {
				rs = ps.executeQuery();
				if (rs.next()) {
					shipping = new Shipping();
					shipping.setId(rs.getInt(1));
					shipping.setShippingnethodId(rs.getInt(2));
					shipping.setCityId(rs.getInt(3));
					shipping.setAdress(rs.getString(4));
					shipping.setZip(rs.getString(5));
					shipping.setPhone(rs.getString(6));
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
		if (shipping != null) {
			logger.info("Shipping by id was returned!");
		}
		return shipping;
	}
	
	
	@Override
	public Connection releaseConnection() {
		return this.connection;
	}
}
