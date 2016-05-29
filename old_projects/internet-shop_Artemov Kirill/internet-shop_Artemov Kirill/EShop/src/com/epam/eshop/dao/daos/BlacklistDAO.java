package com.epam.eshop.dao.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.eshop.dao.daos.inrefaces.InterfaceBlacklistDAO;
import com.epam.eshop.manager.DBQueryManager;
import com.epam.eshop.model.entities.BlacklistItem;

/**
 * The class for working with blacklist of users.
 * 
 * @author Kirill Artemov
 *
 */
public class BlacklistDAO implements InterfaceBlacklistDAO {


	private static Logger logger = Logger.getLogger(BlacklistDAO.class);
	
	private Connection connection;
	/**
	 * Default constructor
	 */
	public BlacklistDAO() {
		this.connection = null;	
	}

	/**
	 * @param connection
	 */
	public BlacklistDAO(Connection connection) {
		this.connection = connection;
	}
	
	/**
	 * The method for adding user to blacklist.
	 * 
	 * @param id userId
	 * @param desc
	 * @throws SQLException
	 */
	@Override
	public boolean add(int userId, String desc) throws SQLException {
		PreparedStatement ps = null;
		// psCheck and rs used to check for exists the user in blacklist
		PreparedStatement psCheck = null;
		ResultSet rs = null;
		boolean success = false;
		try {
			psCheck = connection.prepareStatement(DBQueryManager.getInstance()
					.getProperty(DBQueryManager.GET_BLACKUSER_BY_ID));
			psCheck.setInt(1, userId);
			rs = psCheck.executeQuery();
			// if the user has not blacklisted that add it else return error 
			if (!rs.next()) { 
				ps = connection.prepareStatement(DBQueryManager.getInstance()
						.getProperty(DBQueryManager.ADD_TO_BLACKLIST));
				ps.setInt(1, userId);
				ps.setString(2, desc);
				ps.execute();
				success = true;
			}
		} finally {
			if (ps != null)
				ps.close();
		}
		if (success == true) {
			logger.info("User was added to blacklist!");
		}
		return success;
	}
	/**
	 * The method for removing user from blacklist.
	 * 
	 * @param userId 
	 * @throws SQLException
	 */
	@Override
	public boolean del(int userId) throws SQLException {
		PreparedStatement ps = null;
		boolean success = false;
		try {
			ps = connection.prepareStatement(DBQueryManager.getInstance()
					.getProperty(DBQueryManager.DEL_FROM_BLACKLIST));
			ps.setInt(1, userId);
			ps.execute();
			success = true;
		} finally {
			if (ps != null)
				ps.close();
		}
		return success;
	}
	
	/**
	 * The method returns list records of blacklist. 
	 */
	@Override
	public List<BlacklistItem> getBlacklist() throws SQLException {
		List<BlacklistItem> blacklist = new LinkedList<BlacklistItem>();
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(DBQueryManager.getInstance()
					.getProperty(DBQueryManager.GET_BLACKLIST));
			ResultSet rs = null;			
			try {
				rs = ps.executeQuery();				
				while (rs.next()) {
					BlacklistItem blacklistItem = new BlacklistItem();
					blacklistItem.setId(rs.getInt(1));	
					blacklistItem.setUserId(rs.getInt(2));
					blacklistItem.setDescription(rs.getString(3));
					blacklist.add(blacklistItem);
				}
			} finally {
				if (rs != null) {
					rs.close();
				}
			}
		} finally {
			if (ps != null) {
				ps.close();
			}
		}
		if (!blacklist.isEmpty()) {
			logger.info("List of users in black list returned!");
		} else {
			blacklist = null;
		}
		return blacklist;
	}
	
	@Override
	public boolean isInBlacklist(String login) throws SQLException {
		boolean isInBlacklist = false;
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(DBQueryManager.getInstance()
					.getProperty(DBQueryManager.IS_IN_BLACKLIST));
			ps.setString(1, login);
			ResultSet rs = null;			
			try {
				rs = ps.executeQuery();				
				if (rs.next()) {
					isInBlacklist = true;
				}
			} finally {
				if (rs != null) {
					rs.close();
				}
			}
		} finally {
			if (ps != null) {
				ps.close();
			}
		}
		if (isInBlacklist) {
			logger.info("Blacklist has the user with such id!");
		}
		return isInBlacklist;
	}	
	
	/**
	 * The method for release connection
	 */
	@Override
	public Connection releaseConnection() {
		return this.connection;
	}
}
