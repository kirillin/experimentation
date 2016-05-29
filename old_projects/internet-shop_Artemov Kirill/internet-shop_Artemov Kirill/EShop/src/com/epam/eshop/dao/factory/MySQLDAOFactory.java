package com.epam.eshop.dao.factory;

import java.sql.Connection;

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
import com.epam.eshop.dao.exception.CannotTakeConnectionException;
import com.epam.eshop.pool.ConnectionPool;

public class MySQLDAOFactory extends DAOFactory {

	public static ConnectionPool pool;
	
	public MySQLDAOFactory() {
		MySQLDAOFactory.pool = null;
	}
	/**
	 * Constructor with initialization connection pool
	 */
	public void setConnectionPool(ConnectionPool pool) {
		MySQLDAOFactory.pool = pool;
	}
	/**
	 * Returns one connection from pool
	 * @return
	 */
	public static synchronized Connection createConnection() {
		Connection connection = null;
		connection = pool.takeConnection();
		return connection;
	}
	/**
	 * Release connection and sets it in pool
	 * @param connection
	 */
	public static synchronized void releaseConnection(Connection connection) {
		pool.release(connection);
	}
	// methods for returning concrete DAOs
	@Override	
	public UserDAO getUserDAO() throws Exception {
		Connection connection = createConnection();
		if (connection == null) {
			throw new CannotTakeConnectionException();
		}
		return new UserDAO(connection);
	}

	@Override
	public ProductDAO getProductDAO() throws Exception {
		Connection connection = createConnection();
		if (connection == null) {
			throw new CannotTakeConnectionException();
		}
		return new ProductDAO(connection);
	}
	
	@Override
	public CategoryDAO getCategoryDAO() throws Exception {
		Connection connection = createConnection();
		if (connection == null) {
			throw new CannotTakeConnectionException();
		}
		return new CategoryDAO(connection);
	}

	@Override
	public BlacklistDAO getBlackListDAO() throws Exception {
		Connection connection = createConnection();
		if (connection == null) {
			throw new CannotTakeConnectionException();
		}
		return new BlacklistDAO(connection);
	}
	
	@Override
	public OrderDAO getOrderDAO() throws Exception {
		Connection connection = createConnection();
		if (connection == null) {
			throw new CannotTakeConnectionException();
		}
		return new OrderDAO(connection);
	}
	
	@Override
	public CityDAO getCityDAO() throws Exception {
		Connection connection = createConnection();
		if (connection == null) {
			throw new CannotTakeConnectionException();
		}
		return new CityDAO(connection);
	}

	@Override
	public ShippingMethodDAO getShippingMethodDAO() throws Exception {
		Connection connection = createConnection();
		if (connection == null) {
			throw new CannotTakeConnectionException();
		}
		return new ShippingMethodDAO(connection);
	}

	@Override
	public PaymentMethodDAO getPaymentMethodDAO() throws Exception {
		Connection connection = createConnection();
		if (connection == null) {
			throw new CannotTakeConnectionException();
		}
		return new PaymentMethodDAO(connection);
	}

	@Override
	public StatusDAO getStatusDAO() throws Exception {
		Connection connection = createConnection();
		if (connection == null) {
			throw new CannotTakeConnectionException();
		}
		return new StatusDAO(connection);
	}

	@Override
	public ShippingDAO getShippingDAO() throws Exception {
		Connection connection = createConnection();
		if (connection == null) {
			throw new CannotTakeConnectionException();
		}
		return new ShippingDAO(connection);
	}

	@Override
	public RoleDAO getRoleDAO() throws Exception {
		Connection connection = createConnection();
		if (connection == null) {
			throw new CannotTakeConnectionException();
		}
		return new RoleDAO(connection);
	}

	@Override
	public OrdersProductsDAO getOrdersProductsDAO() throws Exception {
		Connection connection = createConnection();
		if (connection == null) {
			throw new CannotTakeConnectionException();
		}
		return new OrdersProductsDAO(connection);
	}
}
