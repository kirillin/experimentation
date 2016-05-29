package org.artemov.bts.model.pool;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.artemov.bts.model.manager.ConfigurationManager;



/**
 * The class contains all functions for the word with conections
 * 
 * @author Kirill Artemov
 * 
 */
public class ConnectionPool {

	private static ConnectionPool instance;
	/**
	 * Queue contains free connections
	 */
	private BlockingQueue<Connection> connectionQueue;

	private static String driver;
	private static String url;
	private static String username;
	private static String password;
	private static int poolSize;

	private ConnectionPool(String driver, String url, String username,
			String password, int poolSize) {
		loadDriver();
		connectionQueue = new ArrayBlockingQueue<Connection>(poolSize);
		for (int i = 0; i < poolSize; i++) {
			Connection connection = null;
			try {
				connection = DriverManager.getConnection(url, username,
						password);
			} catch (SQLException e) {
				System.err.println(e.getLocalizedMessage() + "Check MySQL server!");
			}
			connectionQueue.offer(connection);
		}
	}

	private void loadDriver() {
		Driver dr = null;
		try {
			dr = (Driver) Class.forName(
					ConfigurationManager.getInstance().getProperty(
							ConfigurationManager.DATABASE_DRIVER_NAME))
					.newInstance();
			try {
				DriverManager.registerDriver(dr);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection takeConnection() {
		Connection connection = null;
		try {
			connection = connectionQueue.take();
		} catch (InterruptedException e) {
			System.err.println("Await free connections. Now all connections busy!" 
						+ e.getStackTrace());
		}
		System.err.println("Free conections: " + connectionQueue.size());
		return connection;
	}

	public void release(Connection connection) {
		try {
			if (!connection.isClosed()) {
				connectionQueue.offer(connection);
			} else {
				connectionQueue.remove(connection);
				connection = DriverManager.getConnection(url, username,
						password);
				connectionQueue.offer(connection);
			}
		} catch (SQLException e) {
			System.err.println("Connection is not release!" 
					+ e.getStackTrace());
		}
		System.err.println("Free conections: " + connectionQueue.size());
	}

	public static synchronized ConnectionPool getInstance() {
		if (instance == null) {
			ConfigurationManager cnfMgr = ConfigurationManager.getInstance();
			ConnectionPool.driver = cnfMgr
					.getProperty(ConfigurationManager.DATABASE_DRIVER_NAME);
			ConnectionPool.url = cnfMgr
					.getProperty(ConfigurationManager.DATABASE_URL);
			ConnectionPool.username = cnfMgr
					.getProperty(ConfigurationManager.DATABASE_USER);
			ConnectionPool.password = cnfMgr
					.getProperty(ConfigurationManager.DATABASE_PASSWORD);
			ConnectionPool.poolSize = Integer.parseInt(cnfMgr
					.getProperty(ConfigurationManager.CONNECTION_POOL_SIZE));
			instance = new ConnectionPool(driver, url, username, password,
					poolSize);
		}
		return instance;
	}

}