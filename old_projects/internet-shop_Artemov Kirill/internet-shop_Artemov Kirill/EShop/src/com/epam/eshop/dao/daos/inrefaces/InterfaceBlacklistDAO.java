package com.epam.eshop.dao.daos.inrefaces;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.epam.eshop.model.entities.BlacklistItem;

public interface InterfaceBlacklistDAO {

	boolean add(int userId, String desc) throws SQLException;

	boolean del(int userId) throws SQLException;

	List<BlacklistItem> getBlacklist() throws SQLException;

	Connection releaseConnection();

	boolean isInBlacklist(String login) throws SQLException;
}
