package com.epam.eshop.dao.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.epam.eshop.dao.daos.inrefaces.InterfaceRoleDAO;
import com.epam.eshop.manager.DBQueryManager;
import com.epam.eshop.model.entities.Role;

public class RoleDAO implements InterfaceRoleDAO {
	
	private static Logger logger = Logger.getLogger(RoleDAO.class);
	private Connection connection;
	
	public RoleDAO() {
		super();		
	}
	/**
	 * @param connection
	 */
	public RoleDAO(Connection connection) {
		super();
		this.connection = connection;
	}

	/**
	 * Returns category for category id
	 */
	@Override
	public Role getRoleById(int roleId) throws SQLException {
		Role role = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(DBQueryManager.getInstance()
					.getProperty(DBQueryManager.GET_ROLE_BY_ID));
			ps.setInt(1, roleId);
			rs = ps.executeQuery();
			try {
				if (rs.next()) {
					role = new Role();
					role.setId(rs.getInt(1));
					role.setRole(rs.getString(2));
				}
			} finally {
				if (rs != null)
					rs.close();
			}
		} finally {
			if (ps != null)
				ps.close();
		}
		if (role != null) {
			logger.info("Role returned!");
		}		
		return role;
	}
	
	
	@Override
	public Connection releaseConnection() {
		return this.connection;
	}
}
