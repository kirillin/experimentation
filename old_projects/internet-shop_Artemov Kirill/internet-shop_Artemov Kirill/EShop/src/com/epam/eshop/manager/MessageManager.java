package com.epam.eshop.manager;

import java.util.ResourceBundle;

/**
 * Manager for ResourceBundle with messages
 * 
 * @author Kirill Artemov
 *
 */
public class MessageManager {
	
	private static MessageManager instance;
	private ResourceBundle resourceBundle;

	private static final String BUNDLE_NAME = "messages";
	
	public static final String LOGIN_ERROR_MESSAGE = "com.epam.eshop.model.manager.MessageManager.login.error";
	public static final String SERVLET_EXCEPTION_ERROR_MESSAGE = "com.epam.eshop.model.manager.MessageManager.servlet.expextion.error";
	public static final String IO_EXCEPTION_ERROR_MESSAGE = "com.epam.eshop.model.manager.MessageManager.io.expextion.error";
	public static final String MAP_INIT = "com.epam.eshop.model.manager.MessageManager.CommandInvoker.map.init";
	public static final String PRODUCTS_NULL = "com.epam.eshop.model.manager.MessageManager.GetProductsCommand.products.null";
	public static final String NO_COMMAND = "com.epam.eshop.model.manager.MessageManager.no.command";
	public static final String REGISTER_ERROR_MESSAGE = "com.epam.eshop.model.manager.MessageManager.register.error";
	public static final String ERR_ADD_PRODUCT = "com.epam.eshop.model.manager.MessageManager.add.product.error";
	public static final String SQL_EXCEPTION_ERROR_MESSAGE = "com.epam.eshop.model.manager.MessageManager.sql.exception.error";
	public static final String EXCEPTION_ERROR_MESSAGE = "com.epam.eshop.model.manager.MessageManager.exception.error";
	public static final String ORDER_EXCEPTION_ERROR_MESSAGE = "com.epam.eshop.model.manager.MessageManager.order.exception.error";
	public static final String CHECKOUT_ERROR_MESSAGE = "com.epam.eshop.model.manager.MessageManager.checkout.error";
	public static final String CART_ERROR = "com.epam.eshop.model.manager.MessageManager.cart.error";
	public static final String SOMETHING_PROBLEM = "com.epam.eshop.model.manager.MessageManager.something.error";
	public static final String ORDERS_NULL= "com.epam.eshop.model.manager.MessageManager.orders.null.error";
	public static final String GET_USER_PROBLEMS = "com.epam.eshop.model.manager.MessageManager.get.user.problems";
	public static final String UPDATE_USER_PROBLEMS = "com.epam.eshop.model.manager.MessageManager.update.user.problems";
	public static final String BLACK_USER = "com.epam.eshop.model.manager.MessageManager.black.user";
	public static final String NOT_FOUND = "com.epam.eshop.model.manager.MessageManager.not.found";
	
	public static MessageManager getInstance() {
		if (instance == null) {
			instance = new MessageManager();
			instance.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
		}
		return instance;
	}
	
	public String getProperty(String key) {
		return (String)resourceBundle.getObject(key);
	}
}
