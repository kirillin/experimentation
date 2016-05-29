package com.epam.eshop.dao.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.eshop.dao.daos.inrefaces.InterfaceUserDAO;
import com.epam.eshop.manager.DBQueryManager;
import com.epam.eshop.model.entities.User;

/**
 * The class DAO for User entity.
 * 
 * @author Kirill Artemov
 * 
 */
public class UserDAO implements InterfaceUserDAO {

	private static Logger logger = Logger.getLogger(UserDAO.class);
	/**
	 * Connection for using DB
	 */
	private Connection connection;

	/**
	 * Default constructor
	 */
	public UserDAO() {
		this.connection = null;
	}

	/**
	 * Constructor for initialization connection with DB for this DAO
	 * 
	 * @param connection
	 */
	public UserDAO(Connection connection) {
		this.connection = connection;
	}

	/**
	 * The method for creation User in DB with passed fields.
	 * 
	 * return errorCode 0 if user creation is complete, else 1
	 */
	@Override
	public boolean createUser(int roleIdDefault, String login, String password,
			String email, String firstName, String lastName)
			throws SQLException {
		PreparedStatement ps = null;
		boolean success = false;
		try {
			ps = connection.prepareStatement(DBQueryManager.getInstance()
					.getProperty(DBQueryManager.USER_CREATE));
			ps.setInt(1, roleIdDefault);
			ps.setString(2, login);
			ps.setString(3, password);
			ps.setString(4, email);
			ps.setString(5, firstName);
			ps.setString(6, lastName);
			ps.execute();
			success = true;
		} finally {
			if (ps != null)
				ps.close();
		}
		if (success == true) {
			logger.info("User created!");
		}
		return success;
	}

	/**
	 * The method for update user data in DB.
	 * 
	 * return errorCode 0 if user creation is complete, else 1
	 */
	@Override
	public boolean updateUser(String login, String password, String email,
			String firstName, String lastName) throws SQLException {
		PreparedStatement ps = null;
		boolean success = false;
		try {
			ps = connection.prepareStatement(DBQueryManager.getInstance()
					.getProperty(DBQueryManager.USER_UPDATE));
			ps.setString(1, login);
			ps.setString(2, password);
			ps.setString(3, email);
			ps.setString(4, firstName);
			ps.setString(5, lastName);
			ps.setString(6, login);
			ps.execute();
			success = true;
		} finally {
			if (ps != null)
				ps.close();
		}
		if (success == true) {
			logger.info("User updated!");
		}
		return success;
	}

	/**
	 * 
	 * The method returns User if login parameter exists in DB
	 */
	@Override
	public User getUser(String login) throws SQLException {
		User user = null;
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(DBQueryManager.getInstance()
					.getProperty(DBQueryManager.GET_USER));
			ps.setString(1, login);
			ResultSet rs = null;
			try {
				rs = ps.executeQuery();
				if (rs.next()) {
					user = new User();
					user.setId(rs.getInt(1));
					user.setRoleId(rs.getInt(2));
					user.setLogin(rs.getString(3));
					user.setPassword(rs.getString(4));
					user.setEmail(rs.getString(5));
					user.setFirstName(rs.getString(6));
					user.setLastName(rs.getString(7));
				}
			} finally {
				if (rs != null)
					rs.close();
			}
		} finally {
			if (ps != null)
				ps.close();
		}
		if (user != null) {
			logger.info("User returned!");
		}
		return user;
	}

	/**
	 * 
	 * The method returns User if login parameter exists in DB
	 */
	@Override
	public User getUser(int userId) throws SQLException {
		User user = new User();
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(DBQueryManager.getInstance()
					.getProperty(DBQueryManager.GET_USER_BY_ID));
			ps.setInt(1, userId);
			ResultSet rs = null;
			try {
				rs = ps.executeQuery();
				if (rs.next()) {
					user.setId(rs.getInt(1));
					user.setRoleId(rs.getInt(2));
					user.setLogin(rs.getString(3));
					user.setPassword(rs.getString(4));
					user.setEmail(rs.getString(5));
					user.setFirstName(rs.getString(6));
					user.setLastName(rs.getString(7));
				}
			} finally {
				if (rs != null)
					rs.close();
			}
		} finally {
			if (ps != null)
				ps.close();
		}
		if (user != null) {
			logger.info("User returned!");
		}
		return user;
	}

	/**
	 * The method returns list of all users in DB
	 */
	@Override
	public List<User> getUsers() throws SQLException {
		List<User> users = new LinkedList<User>();
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(DBQueryManager.getInstance()
					.getProperty(DBQueryManager.GET_USERS));
			ResultSet rs = null;
			try {
				rs = ps.executeQuery();
				while (rs.next()) {
					User user = new User();
					user.setId(rs.getInt(1));
					user.setRoleId(rs.getInt(2));
					user.setLogin(rs.getString(3));
					user.setPassword(rs.getString(4));
					user.setEmail(rs.getString(5));
					user.setFirstName(rs.getString(6));
					user.setLastName(rs.getString(7));
					users.add(user);
				}
			} finally {
				if (rs != null)
					rs.close();
			}
		} finally {
			if (ps != null)
				ps.close();
		}
		if (!users.isEmpty()) {
			logger.info("List of users returned!");
		} else {
			users = null;
		}
		return users;
	}

	/**
	 * the method returns true if user with the login and password exists.
	 */
	@Override
	public boolean isUser(String login, String password) throws SQLException {
		PreparedStatement ps = null;
		boolean result = false;
		try {
			ps = connection.prepareStatement(DBQueryManager.getInstance()
					.getProperty(DBQueryManager.LOGIN_CHECKING));
			ps.setString(1, login);
			ps.setString(2, password);
			ResultSet rs = null;
			try {
				rs = ps.executeQuery();
				result = rs.next();
			} finally {
				if (rs != null)
					rs.close();
			}
		} finally {
			if (ps != null)
				ps.close();
		}
		if (result == true) {
			logger.info("User " + login + " is exists!");
		}
		return result;
	}
	/**
	 * the method returns true if user with the login exists.
	 */
	@Override
	public boolean isUser(String login) throws SQLException {
		PreparedStatement ps = null;
		boolean result = false;
		try {
			ps = connection.prepareStatement(DBQueryManager.getInstance()
					.getProperty(DBQueryManager.IS_USER));
			ps.setString(1, login);
			ResultSet rs = null;
			try {
				rs = ps.executeQuery();
				result = rs.next();
			} finally {
				if (rs != null)
					rs.close();
			}
		} finally {
			if (ps != null)
				ps.close();
		}
		if (result == true) {
			logger.info("User " + login + "is exists!");
		}
		return result;
	}

	/**
	 * The method returns connection.
	 */
	public Connection releaseConnection() {
		return this.connection;
	}

}
