package org.artemov.bts.model.dao.factory;

import java.sql.Connection;

import org.artemov.bts.model.dao.daos.entity_dao_implement.DepartmentDAOImpl;
import org.artemov.bts.model.dao.daos.entity_dao_implement.EmployeeDAOImpl;
import org.artemov.bts.model.dao.daos.entity_dao_implement.ErrorDAOImpl;
import org.artemov.bts.model.dao.daos.entity_dao_implement.ErrorLevelDAOImpl;
import org.artemov.bts.model.dao.daos.entity_dao_implement.ErrorsStatusDAOImpl;
import org.artemov.bts.model.dao.daos.entity_dao_implement.ProjectDAOImpl;
import org.artemov.bts.model.dao.daos.entity_dao_implement.StatusDAOImpl;
import org.artemov.bts.model.pool.ConnectionPool;

/**
 * The class contains methods for getting DAOs and methods for work with
 * connection pool
 * 
 * @author Kirill Artemov
 * 
 */
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
	 * 
	 * @return
	 */
	public static synchronized Connection createConnection() {
		Connection connection = null;
		connection = pool.takeConnection();
		return connection;
	}

	/**
	 * Release connection and sets it in pool
	 * 
	 * @param connection
	 */
	public static synchronized void releaseConnection(Connection connection) {
		pool.release(connection);
	}

	@Override
	public EmployeeDAOImpl getEmployeeDAOImpl() {
		Connection connection = createConnection();
		if (connection == null) {
			System.err.println("Can not take connection!");
		}
		return new EmployeeDAOImpl(connection);
	}

	@Override
	public ErrorDAOImpl getErrorDAOImpl() {
		Connection connection = createConnection();
		if (connection == null) {
			System.err.println("Can not take connection!");
		}
		return new ErrorDAOImpl(connection);
	}

	@Override
	public DepartmentDAOImpl getDepartmentDAOImpl() {
		Connection connection = createConnection();
		if (connection == null) {
			System.err.println("Can not take connection!");
		}
		return new DepartmentDAOImpl(connection);
	}

	@Override
	public ErrorsStatusDAOImpl getErrorsStatusDAOImpl() {
		Connection connection = createConnection();
		if (connection == null) {
			System.err.println("Can not take connection!");
		}
		return new ErrorsStatusDAOImpl(connection);
	}

	@Override
	public ErrorLevelDAOImpl getErrorLevelDAOImpl() {
		Connection connection = createConnection();
		if (connection == null) {
			System.err.println("Can not take connection!");
		}
		return new ErrorLevelDAOImpl(connection);
	}

	@Override
	public ProjectDAOImpl getProjectDAOImpl() {
		Connection connection = createConnection();
		if (connection == null) {
			System.err.println("Can not take connection!");
		}
		return new ProjectDAOImpl(connection);
	}

	@Override
	public StatusDAOImpl getStatusDAOImpl() {
		Connection connection = createConnection();
		if (connection == null) {
			System.err.println("Can not take connection!");
		}
		return new StatusDAOImpl(connection);
	}

}
