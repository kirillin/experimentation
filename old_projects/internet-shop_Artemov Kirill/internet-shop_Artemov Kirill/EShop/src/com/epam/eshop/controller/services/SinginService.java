package com.epam.eshop.controller.services;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.epam.eshop.dao.daos.UserDAO;
import com.epam.eshop.dao.factory.MySQLDAOFactory;

public class SinginService extends Service {

	private static Logger logger = Logger.getLogger(SinginService.class);
	
	public SinginService() {
		super();
	}

	public SinginService(int daoFactory, String nameService) {
		super(daoFactory, nameService);
	}

	@Override
	public String getNameService() {
		return super.getNameService();
	}
	
	public boolean checkLogin(String login, String password) {
		UserDAO userDAO = null;
		boolean resultsCheck = false;
		try {
			userDAO = (UserDAO) dao.getUserDAO();
			resultsCheck = userDAO.isUser(login, password);
			MySQLDAOFactory.releaseConnection(userDAO.releaseConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultsCheck;
	}

}
