package com.epam.eshop.manager;

public enum AttributesEnum {

	COMMAND("command"),
	GET_CATALOG("getCatalog"),
	GET_PRODUCTS("getProducts"),
	SING_IN("singin"),
	SING_UP("singup"),
	ADD_PRODUCT("addProduct"),
	GET_CART("getCart"),
	GET_USER("getUser"),
	GET_USERS("getUsers"),
	GET_BLACKLIST("getBlackList"),
	GET_FORM_DATA("getFormData"),
	CHECKOUT("checkout"),
	GET_ORDERS("getOrders"),
	ADD_TO_CART("addToCart"),
	UPDATE_USER("updateUser"),
	ALTER_LOCALE("alterLocale"),
	
	USER_ENTERED("userEnt"),
	ADD_TO_BLACKLIST("addedToBlacklist"),
	USERS("users"),
	ADMIN("admin"),
	IS_USER_ENTER("isEnterUser"),
	WELCOME("welcome"),
	UPDATED("updateOk"),
	CATEGORIES("categories"),
	USER_NAME("user"), 
	USER_SERVICE("userService"),
	PRODUCT_SIRVECE("productService"),
	BLACKLIST_SERVICE("blacklistService"),
	BLACKLIST_UPDATE("blacklistUpdated"),
	BLACK_USER("blackUsers"),
	IS_IN_BLACKLIST("isInBlacklist"),
	CLEAR("clear"),
	ADD_ORDER("addOrder"),
	CITIES("cities"),
	SHIPPING_METHODS("shippingMethods"),
	PAYMENT_METHODS("paymentMethods"),
	CART("cart"),
	CART_SERVICE("cartService"),
	CART_MAP("cartMap"),
	ERROR_MESSAGE("errorMessage"),
	ORDERS("orders"),
	STATUSES("statuses"),
	ACTUAL_UPDATED("actualUpdated"),
	PRODUCTS("products"),
	ORDER_UPDATED("orderUpdated"),
	ORDER_SERVICE("orderService");
	

	private final String text;

	/**
	 * @param text
	 * @return
	 */
	private AttributesEnum(final String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}

}
