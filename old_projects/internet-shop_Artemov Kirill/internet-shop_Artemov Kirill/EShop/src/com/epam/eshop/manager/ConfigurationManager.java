package com.epam.eshop.manager;

import java.util.ResourceBundle;

/**
 * Manager for ResourceBundle with configurations and pages of the application
 * 
 * @author Kirill Artemov
 *
 */
public class ConfigurationManager {

	private static ConfigurationManager instance;
	private ResourceBundle resourceBundle;

	private static final String BUNDLE_NAME = "config";
	
	public static final String DATABASE_DRIVER_NAME = "com.epam.eshop.model.manager.ConfigurationManager.jdbc.driver";
	public static final String DATABASE_URL = "com.epam.eshop.model.manager.ConfigurationManager.db.url";
	public static final String DATABASE_USER = "com.epam.eshop.model.manager.ConfigurationManager.db.user";
	public static final String DATABASE_PASSWORD = "com.epam.eshop.model.manager.ConfigurationManager.db.password";
	public static final String CONNECTION_POOL_SIZE = "com.epam.eshop.model.manager.ConfigurationManager.connection.pool.size";
	
	public static final String LOG4J_PROPERTY_PATH = "com.epam.eshop.model.manager.ConfigurationManager.log4j.property.path";

	public static final String BLACKLIST_PAGE = "com.epam.eshop.model.manager.ConfigurationManager.blacklist.page.path";
	public static final String USERS_PAGE = "com.epam.eshop.model.manager.ConfigurationManager.users.page.path";
	public static final String PROFILE_EDIT_PAGE = "com.epam.eshop.model.manager.ConfigurationManager.profile.edit.page.path";
	public static final String SHOP_PAGE = "com.epam.eshop.model.manager.ConfigurationManager.shop.page.path";
	public static final String CATALOG_PAGE = "com.epam.eshop.model.manager.ConfigurationManager.products.page.path";
	public static final String PROFILE_PAGE = "com.epam.eshop.model.manager.ConfigurationManager.profile.page.path";
	public static final String GOODBAY_PAGE = "com.epam.eshop.model.manager.ConfigurationManager.goodbay.page.path";
	public static final String WELCOME_PAGE = "com.epam.eshop.model.manager.ConfigurationManager.welcome.page.path";
	public static final String ERROR_PAGE = "com.epam.eshop.model.manager.ConfigurationManager.error.page.path";
	public static final String HOME_PAGE = "com.epam.eshop.model.manager.ConfigurationManager.home.page.path";
	public static final String SINGIN_PAGE = "com.epam.eshop.model.manager.ConfigurationManager.singin.page.path";	
	public static final String SINGUP_PAGE = "com.epam.eshop.model.manager.ConfigurationManager.singup.page.path";
	public static final String ADD_PRODUCT_PAGE = "com.epam.eshop.model.manager.ConfigurationManager.add.product.page.path";
	public static final String CART_PAGE = "com.epam.eshop.model.manager.ConfigurationManager.cart.page.path";
	public static final String CHECKOUT_PAGE = "com.epam.eshop.model.manager.ConfigurationManager.checkout.page.path";
	public static final String ORDERS_PAGE = "com.epam.eshop.model.manager.ConfigurationManager.orders.page.path";
	public static final String GET_CATALOG = "com.epam.eshop.model.manager.ConfigurationManager.get.catalog.page";
	
	public static ConfigurationManager getInstance() {
		if (instance == null) {
			instance = new ConfigurationManager();
			instance.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
		}
		return instance;
	}
	
	public String getProperty(String key) {
		return (String)resourceBundle.getObject(key);
	}

}
