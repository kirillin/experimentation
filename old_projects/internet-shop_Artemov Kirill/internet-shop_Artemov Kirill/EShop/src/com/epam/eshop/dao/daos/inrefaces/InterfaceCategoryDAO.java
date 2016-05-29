package com.epam.eshop.dao.daos.inrefaces;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.epam.eshop.model.entities.Category;

public interface InterfaceCategoryDAO {
	
	Category getCategory(String category) throws SQLException;

	Category getCategoryById(int id) throws SQLException;

	Connection releaseConnection();

	List<Category> getCategories() throws SQLException;
}
