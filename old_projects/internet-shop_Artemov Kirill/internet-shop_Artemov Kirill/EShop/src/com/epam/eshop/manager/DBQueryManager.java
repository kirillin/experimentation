package com.epam.eshop.manager;

import java.util.ResourceBundle;

/**
 * Manager for ResourceBundle with DBQueries
 * @author Kirill Artemov
 *
 */
public class DBQueryManager {

	private static DBQueryManager instance;
	private ResourceBundle resourceBundle;

	private static final String BUNDLE_NAME = "db_queries";
	
	public static final String LOGIN_CHECKING = "com.epam.eshop.model.manager.DBQueryManager.login.cheking";
	public static final String USER_CREATE = "com.epam.eshop.model.manager.DBQueryManager.user.create";
	public static final String USER_UPDATE = "com.epam.eshop.model.manager.DBQueryManager.user.update";
	public static final String GET_USER = "com.epam.eshop.model.manager.DBQueryManager.get.user.by.login";
	public static final String GET_USERS = "com.epam.eshop.model.manager.DBQueryManager.get.users";
	public static final String GET_ROLE_BY_ID = "com.epam.eshop.model.manager.DBQueryManager.get.role.by.id";
	public static final String GET_PRODUCTS = "com.epam.eshop.model.manager.DBQueryManager.get.products";
	public static final String ADD_PRODUCT = "com.epam.eshop.model.manager.DBQueryManager.add.product";
	public static final String GET_CATEGORY_BY_NAME = "com.epam.eshop.model.manager.DBQueryManager.get.category.by.name";
	public static final String GET_PRODUCT_BY_ID = "com.epam.eshop.model.manager.DBQueryManager.get.product.by.id";
	public static final String GET_BLACKLIST = "com.epam.eshop.model.manager.DBQueryManager.get.blacklist";
	public static final String ADD_TO_BLACKLIST = "com.epam.eshop.model.manager.DBQueryManager.add.to.blacklist";
	public static final String DEL_FROM_BLACKLIST = "com.epam.eshop.model.manager.DBQueryManager.del.from.blacklist";
	public static final String GET_BLACKUSER_BY_ID = "com.epam.eshop.model.manager.DBQueryManager.get.blacklist.by.id";
	public static final String GET_CITIES = "com.epam.eshop.model.manager.DBQueryManager.get.cities";
	public static final String GET_SHIPPING_METHODS = "com.epam.eshop.model.manager.DBQueryManager.get.shipping.methods";
	public static final String GET_PAYMENT_METHODS = "com.epam.eshop.model.manager.DBQueryManager.get.payment.methods";
	public static final String GET_STATUS_BY_ID = "com.epam.eshop.model.manager.DBQueryManager.get.status.by.id";	
	public static final String ADD_SHIPPING = "com.epam.eshop.model.manager.DBQueryManager.add.shipping";
	public static final String GET_SHIPPING_ID = "com.epam.eshop.model.manager.DBQueryManager.get.shipping.id";
	public static final String ADD_ORDER = "com.epam.eshop.model.manager.DBQueryManager.add.order";
	public static final String GET_ORDERS = "com.epam.eshop.model.manager.DBQueryManager.get.orders";
	public static final String GET_CATEGORY_BY_ID = "com.epam.eshop.model.manager.DBQueryManager.get.categoty.by.id";
	public static final String GET_USER_BY_ID = "com.epam.eshop.model.manager.DBQueryManager.get.user.by.id";
	public static final String GET_CATEGORIES = "com.epam.eshop.model.manager.DBQueryManager.get.categories";
	public static final String GET_PAYMENT_METHOD_BY_ID = "com.epam.eshop.model.manager.DBQueryManager.get.payment.method.by.id";
	public static final String GET_SHIPPING_BY_ID = "com.epam.eshop.model.manager.DBQueryManager.get.shipping.by.id";
	public static final String GET_SHIPPING_NETHOD_BY_ID = "com.epam.eshop.model.manager.DBQueryManager.get.shipping.method.by.id";
	public static final String GET_CITY_BY_ID = "com.epam.eshop.model.manager.DBQueryManager.get.city.by.id";
	public static final String GET_ORDERS_BY_LOGIN = "com.epam.eshop.model.manager.DBQueryManager.get.orders.by.login";
	public static final String GET_STASUSES = "com.epam.eshop.model.manager.DBQueryManager.get.statuses";
	public static final String ORDER_UPDATE = "com.epam.eshop.model.manager.DBQueryManager.order.update";
	public static final String IS_IN_BLACKLIST = "com.epam.eshop.model.manager.DBQueryManager.is.in.blacklist";
	public static final String UPDATE_ACTUAL_PRODUCT = "com.epam.eshop.model.manager.DBQueryManager.update.actual.product";
	public static final String ADD_NODE_ORDERS_PRODUCTS = "com.epam.eshop.model.manager.DBQueryManager.add.node.orders.products";
	public static final String UPDATE_QUANTITY_PRODUCT = "com.epam.eshop.model.manager.DBQueryManager.update.quantity.product";
	public static final String GET_ORDER_ID = "com.epam.eshop.model.manager.DBQueryManager.get.order.id";
	public static final String IS_USER = "com.epam.eshop.model.manager.DBQueryManager.is.user";
	public static final String GET_PRODUCTS_BY_ORDER_ID = "com.epam.eshop.model.manager.DBQueryManager.get.products.by.order.id";
	
	
	public static DBQueryManager getInstance() {
		if (instance == null) {
			instance = new DBQueryManager();
			instance.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
		}
		return instance;
	}
	
	public String getProperty(String key) {
		return (String)resourceBundle.getObject(key);
	}

}
