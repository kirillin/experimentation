package com.epam.eshop.controller.services;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.eshop.dao.daos.BlacklistDAO;
import com.epam.eshop.dao.factory.MySQLDAOFactory;
import com.epam.eshop.model.entities.BlacklistItem;


public class BlacklistService extends Service {

	private static Logger logger = Logger.getLogger(BlacklistService.class);
	
	public BlacklistService() {
		super();
	}

	public BlacklistService(int daoFactory, String nameService) {
		super(daoFactory, nameService);
	}

	@Override
	public String getNameService() {
		return super.getNameService();
	}

	public boolean add(int id, String desc) {
		BlacklistDAO blacklistDAO = null;
		boolean success = false;
		try {
			blacklistDAO = (BlacklistDAO) dao.getBlackListDAO();
			success = blacklistDAO.add(id, desc);
			MySQLDAOFactory.releaseConnection(blacklistDAO.releaseConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return success;
	}
	
	public boolean del(int id) {
		BlacklistDAO blacklistDAO = null;
		boolean success = false;
		try {
			blacklistDAO = (BlacklistDAO) dao.getBlackListDAO();
			success = blacklistDAO.del(id);
			MySQLDAOFactory.releaseConnection(blacklistDAO.releaseConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return success;
	}
	
	public List<BlacklistItem> getBlackList() {
		BlacklistDAO blacklistDAO = null;
		List<BlacklistItem> blacklist = null;
		try {
			blacklistDAO = dao.getBlackListDAO();
			blacklist = blacklistDAO.getBlacklist();
			MySQLDAOFactory.releaseConnection(blacklistDAO.releaseConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return blacklist;
	}
	
	public boolean isInBlacklist(String login) {
		BlacklistDAO blacklistDAO = null;
		boolean isInBlacklist = false;
		try {
			blacklistDAO = dao.getBlackListDAO();
			isInBlacklist = blacklistDAO.isInBlacklist(login);
			MySQLDAOFactory.releaseConnection(blacklistDAO.releaseConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isInBlacklist;
	}
	
}
