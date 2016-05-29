package com.epam.eshop.controller.services;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.eshop.dao.daos.CategoryDAO;
import com.epam.eshop.dao.daos.ProductDAO;
import com.epam.eshop.dao.factory.MySQLDAOFactory;
import com.epam.eshop.model.entities.Category;
import com.epam.eshop.model.entities.Product;
import com.epam.eshop.view.views.ProductsView;

/**
 * Logic for processing products requests 
 * 
 * @author Kirill Artemov
 *
 */
public class ProductService extends Service {

	private static Logger logger = Logger.getLogger(ProductService.class);

	public ProductService() {
		super();
	}

	public ProductService(int daoFactory, String nameService) {
		super(daoFactory, nameService);
	}

	@Override
	public String getNameService() {
		return super.getNameService();
	}
	
	public List<ProductsView> getProducts() {
		ProductDAO productDAO = null;
		CategoryDAO categoryDAO = null;
		List<Product> products = null;
		List<ProductsView> viewProducts = null;		
		try {
			productDAO = dao.getProductDAO();
			products = productDAO.getProducts();
			MySQLDAOFactory.releaseConnection(productDAO.releaseConnection());
			if (products != null) {
				viewProducts = new LinkedList<ProductsView>();
				for (Iterator<Product> iterator = products.iterator(); iterator
						.hasNext();) {
					Product product = (Product) iterator.next();
					categoryDAO = dao.getCategoryDAO();
					int id = product.getCategoryId();
					String category = categoryDAO.getCategoryById(id).getCategory();
					MySQLDAOFactory.releaseConnection(categoryDAO.releaseConnection());
					ProductsView productView = new ProductsView();				
					productView.setProductId(product.getId());
					productView.setCategory(category);
					productView.setProductName(product.getProductName());
					productView.setProductPrice(product.getProductPrice());
					productView.setQuantity(product.getQuantity());
					productView.setDescription(product.getDescription());
					productView.setActual(product.isActual());
					viewProducts.add(productView);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (viewProducts != null) {
			logger.info("Products view OK");
		}
		return viewProducts;
	}

	public boolean addProduct(int categoryId, String productName,
			float productPrice, int quantity, String description) {
		ProductDAO productDAO = null;
		boolean result = false;
		try {
			productDAO = dao.getProductDAO();
			result = productDAO.addProduct(categoryId, productName, productPrice,
					quantity, description);
			MySQLDAOFactory.releaseConnection(productDAO.releaseConnection());
		} catch (SQLException e) {
			logger.trace("SQL EXCEPTION ");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (result != false) {
			logger.trace("Product added!");
		} else {
			logger.trace("Product didn't add!");
		}
		return result;
	}

	public boolean updateProduct(int productId, boolean actual) {
		ProductDAO productDAO = null;
		boolean success = false;
		try {
			productDAO = dao.getProductDAO();
			success = productDAO.updateProduct(productId, actual);
			MySQLDAOFactory.releaseConnection(productDAO.releaseConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return success;
	}

	public List<Category> getCategories() {
		CategoryDAO	categoryDAO = null;
		List<Category> categories = new LinkedList<Category>();
		try {
			categoryDAO = dao.getCategoryDAO();
			categories = categoryDAO.getCategories();
			MySQLDAOFactory.releaseConnection(categoryDAO.releaseConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return categories;
	}
}
