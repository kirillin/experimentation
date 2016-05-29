package com.epam.eshop.dao.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.eshop.dao.daos.inrefaces.InterfaceCategoryDAO;
import com.epam.eshop.manager.DBQueryManager;
import com.epam.eshop.model.entities.Category;

/**
 * Class for manipulation DB and table category. 
 * 
 * @author Kirill Artemov
 *
 */
public class CategoryDAO implements InterfaceCategoryDAO {

	private static Logger logger = Logger.getLogger(CategoryDAO.class);
	private Connection connection;
	
	public CategoryDAO() {
		super();		
	}

	/**
	 * @param connection
	 */
	public CategoryDAO(Connection connection) {
		super();
		this.connection = connection;
	}
	/**
	 * Returns category for category name
	 */
	@Override
	public Category getCategory(String categoryName) throws SQLException {
		Category category = null;		
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(DBQueryManager.getInstance()
					.getProperty(DBQueryManager.GET_CATEGORY_BY_NAME));
			ps.setString(1, categoryName);
			ResultSet rs = null;			
			try {
				rs = ps.executeQuery();				
				if (rs.next()) {
					category = new Category();
					category.setId(rs.getInt(1));
					category.setCategory(rs.getString(2));
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
		if (category != null) {
			logger.info("Category returned!");
		}				
		return category;
	}
	/**
	 * Returns category for category id
	 */
	@Override
	public Category getCategoryById(int id) throws SQLException {
		Category category = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(DBQueryManager.getInstance()
					.getProperty(DBQueryManager.GET_CATEGORY_BY_ID));
			ps.setInt(1, id);
			rs = ps.executeQuery();
			try {
				if (rs.next()) {
					category = new Category();
					category.setId(rs.getInt(1));
					category.setCategory(rs.getString(2));
				}
			} finally {
				if (rs != null)
					rs.close();
			}
		} finally {
			if (ps != null)
				ps.close();
		}
		if (category != null) {
			logger.info("Category returned!");
		}		
		return category;
	}
	

	@Override
	public List<Category> getCategories() throws SQLException {
		List<Category> categories = new LinkedList<Category>();
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(DBQueryManager.getInstance()
					.getProperty(DBQueryManager.GET_CATEGORIES));
			ResultSet rs = null;
			try {
				rs = ps.executeQuery();
				while (rs.next()) {
					Category c = new Category();
					c.setId(rs.getInt(1));
					c.setCategory(rs.getString(2));
					categories.add(c);
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
		if (!categories.isEmpty()) {
			logger.info("List of payment methods was returns!");
		} else {
			categories = null;
		}
		return categories;
	}

	@Override
	public Connection releaseConnection() {
		return this.connection;
	}
}
