package com.epam.eshop.dao.daos.inrefaces;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.epam.eshop.model.entities.User;

public interface InterfaceUserDAO {

	public Connection releaseConnection();

	boolean createUser(int roleIdDefault, String login, String password,
			String email, String firstName, String lastName)
			throws SQLException;

	boolean updateUser(String login, String password, String email,
			String firstName, String lastName) throws SQLException;

	User getUser(String login) throws SQLException;

	List<User> getUsers() throws SQLException;

	boolean isUser(String login, String password) throws SQLException;

	boolean isUser(String login) throws SQLException;

	User getUser(int userId) throws SQLException;
}
