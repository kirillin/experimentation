package com.epam.eshop.dao.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.eshop.dao.daos.inrefaces.InterfaceProductDAO;
import com.epam.eshop.manager.DBQueryManager;
import com.epam.eshop.model.entities.Product;

/**
 * The class contains methods for Product
 * 
 * @author Kirill Artemov
 *
 */
public class ProductDAO implements InterfaceProductDAO {

	private static Logger logger = Logger.getLogger(ProductDAO.class);
	private Connection connection;
	/**
	 * Default constructor
	 */
	public ProductDAO() {
		this.connection = null;
	}
	/**
	 * Constructor for initialization connection 
	 * @param connection
	 */
	public ProductDAO(Connection connection) {
		this.connection = connection;
	}
	/**
	 * The method returns Product entity from DB if ID exists there.
	 */
	@Override
	public Product getProductById(int id) throws SQLException {
		Product product = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(DBQueryManager.getInstance()
					.getProperty(DBQueryManager.GET_PRODUCT_BY_ID));
			ps.setInt(1, id);
			rs = ps.executeQuery();
			try {
				if (rs.next()) {
					product = new Product();
					product.setId(rs.getInt(1));
					product.setCategoryId(rs.getInt(2));
					product.setProductName(rs.getString(3));
					product.setProductPrice(rs.getFloat(4));
					product.setQuantity(rs.getInt(5));
					product.setDescription(rs.getString(6));
					product.setActual(rs.getBoolean(7));
				}
			} finally {
				if (rs != null)
					rs.close();
			}
		} finally {
			if (ps != null)
				ps.close();
		}
		if (product != null) {
			logger.info("Product returned!");
		}
		return product;
	}
	/**
	 * The method returns list of all products from DB
	 */
	@Override
	public List<Product> getProducts() throws SQLException {
		List<Product> products = new LinkedList<Product>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(DBQueryManager.getInstance()
					.getProperty(DBQueryManager.GET_PRODUCTS));
			rs = ps.executeQuery();
			try {
				while (rs.next()) {
					Product product = new Product();
					product = new Product();
					product.setId(rs.getInt(1));
					product.setCategoryId(rs.getInt(2));
					product.setProductName(rs.getString(3));
					product.setProductPrice(rs.getFloat(4));
					product.setQuantity(rs.getInt(5));
					product.setDescription(rs.getString(6));
					product.setActual(rs.getBoolean(7));
					products.add(product);
				}
			} finally {
				if (rs != null)
					rs.close();
			}
		} finally {
			if (ps != null)
				ps.close();
		}
		if (!products.isEmpty()) {
			logger.trace("List of products returned from DB!");
		} else {
			products = null;
		}
		return products;
	}
	/**
	 * The method returns products only if it is in list of id.
	 */
	@Override
	public List<Product> getProductsByListId(List<Integer> ids) 
			throws SQLException {
		List<Product> products = new LinkedList<Product>();
		for (Iterator<Integer> iterator = ids.iterator(); iterator.hasNext();) {
			int	id = (int) iterator.next();
			Product product = getProductById(id);
			products.add(product);			
		}
		if (!products.isEmpty()) {
			logger.trace("List of products returned from DB!");
		} else {
			products = null;
		}
		return products;
	}
	/**
	 * The method add product to DB and returns 0 if all right!
	 */
	@Override
	public boolean addProduct(int categoryId, String productName,
			float productPrice, int quantity, String description)
			throws SQLException {
		PreparedStatement ps = null;
		boolean success = false;
		try {
			ps = connection.prepareStatement(DBQueryManager.getInstance()
					.getProperty(DBQueryManager.ADD_PRODUCT));
			ps.setInt(1, categoryId);
			ps.setString(2, productName);
			ps.setFloat(3, productPrice);
			ps.setInt(4, quantity);
			ps.setString(5, description);
			ps.execute();
			success = true;
		} finally {
			if (ps != null)
				ps.close();
		}
		return success;
	}
	
	public boolean updateProduct(int productId, boolean actual) 
			throws SQLException {
		PreparedStatement ps = null;
		boolean allRight = false;
		try {
			ps = connection.prepareStatement(DBQueryManager.getInstance()
					.getProperty(DBQueryManager.UPDATE_ACTUAL_PRODUCT));
			ps.setBoolean(1, actual);
			ps.setInt(2, productId);
			ps.execute();
			allRight = true;
		} finally {
			if (ps != null)
				ps.close();
		}
		return allRight;		
	}
	
	/**
	 * The method release connection
	 * @return
	 */
	public Connection releaseConnection() {
		return this.connection;
	}
}
