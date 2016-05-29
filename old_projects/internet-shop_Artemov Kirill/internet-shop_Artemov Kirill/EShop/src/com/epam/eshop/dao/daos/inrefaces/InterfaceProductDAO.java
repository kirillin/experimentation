package com.epam.eshop.dao.daos.inrefaces;

import java.sql.SQLException;
import java.util.List;

import com.epam.eshop.model.entities.Product;

public interface InterfaceProductDAO {

	Product getProductById(int id) throws SQLException;

	List<Product> getProducts() throws SQLException;

	boolean addProduct(int categoryID, String productName, float productPrice,
			int unitsQuantity, String description) throws SQLException;

	List<Product> getProductsByListId(List<Integer> ids) throws SQLException;

}
