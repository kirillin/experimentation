package com.epam.eshop.dao.factory;

import com.epam.eshop.dao.daos.BlacklistDAO;
import com.epam.eshop.dao.daos.CategoryDAO;
import com.epam.eshop.dao.daos.CityDAO;
import com.epam.eshop.dao.daos.OrderDAO;
import com.epam.eshop.dao.daos.OrdersProductsDAO;
import com.epam.eshop.dao.daos.PaymentMethodDAO;
import com.epam.eshop.dao.daos.ProductDAO;
import com.epam.eshop.dao.daos.RoleDAO;
import com.epam.eshop.dao.daos.ShippingDAO;
import com.epam.eshop.dao.daos.ShippingMethodDAO;
import com.epam.eshop.dao.daos.StatusDAO;
import com.epam.eshop.dao.daos.UserDAO;

public abstract class DAOFactory {

	public static final int MYSQL = 1;

	public abstract UserDAO getUserDAO() throws Exception;
	public abstract ProductDAO getProductDAO() throws Exception;
	public abstract CategoryDAO getCategoryDAO() throws Exception;
	public abstract BlacklistDAO getBlackListDAO() throws Exception;
	public abstract OrderDAO getOrderDAO() throws Exception;
	public abstract CityDAO getCityDAO() throws Exception;
	public abstract ShippingMethodDAO getShippingMethodDAO() throws Exception;
	public abstract PaymentMethodDAO getPaymentMethodDAO() throws Exception;
	public abstract StatusDAO getStatusDAO() throws Exception;
	public abstract ShippingDAO getShippingDAO() throws Exception;
	public abstract RoleDAO getRoleDAO() throws Exception;
	public abstract OrdersProductsDAO getOrdersProductsDAO() throws Exception;	

	/**
	 * 
	 * @param whichFactory
	 * @return Factory for concrete data base
	 */
	public static DAOFactory getDAOFactory(int whichFactory) {
		switch (whichFactory) {
		case MYSQL:
			return new MySQLDAOFactory();
		default:
			return null;
		}
	}
}
