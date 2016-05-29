package com.epam.eshop.controller.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.epam.eshop.dao.daos.UserDAO;
import com.epam.eshop.dao.factory.MySQLDAOFactory;

public class SingupService extends Service {

	private static Logger logger = Logger.getLogger(SingupService.class);
	
	private final String LOGIN = "login";
	private final String PASSWORD = "password";
	private final String EMAIL = "email";
	private final String LOGIN_PATTERN = "[_A-Za-z0-9]{3,}";
	private final String PASSWORD_PATTERN = "[A-Za-z0-9]{3,}";
	private final String EMAIL_PATTERN = "[_A-Za-z]+(\\.[_A-Za-z0-9]+)*@"
			+ "[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})";
	private final int ROLE_ID_DEFAULT = 2; // "users"
	/**
	 * Default constructor
	 */
	public SingupService() {
		super();
	}

	public SingupService(int daoFactory, String nameService) {
		super(daoFactory, nameService);
	}

	@Override
	public String getNameService() {
		return super.getNameService();
	}
	
	public boolean isValid(String param, String type) {
		Pattern p = null;
		Matcher m = null;
		switch (type) {
		case LOGIN:
			p = Pattern.compile(LOGIN_PATTERN);
			m = p.matcher(param);
			return m.matches();

		case PASSWORD:
			p = Pattern.compile(PASSWORD_PATTERN);
			m = p.matcher(param);
			return m.matches();

		case EMAIL:
			p = Pattern.compile(EMAIL_PATTERN);
			m = p.matcher(param);
			return m.matches();

		default:
			return false;
		}
	}

	public boolean createUser(String login, String password, String email,
			String firstName, String lastName) {
		UserDAO userDAO = null;
		boolean success = false;
		try {
			userDAO = dao.getUserDAO();
			success = userDAO.createUser(ROLE_ID_DEFAULT, login, password, email,
					firstName, lastName);
			MySQLDAOFactory.releaseConnection(userDAO.releaseConnection());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (success == true) {
			logger.trace("User created and add to DB");
		}
		return success;
	}
}
