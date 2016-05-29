package org.artemov.bts.model.dao.daos.entity_dao_implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.artemov.bts.model.dao.daos.entity_dao_interface.ErrorLevelDAO;
import org.artemov.bts.model.entities.ErrorLevel;
import org.artemov.bts.model.manager.DBQueryManager;

public class ErrorLevelDAOImpl implements ErrorLevelDAO {

	private Connection connection;

	public ErrorLevelDAOImpl() {
		super();
	}

	public ErrorLevelDAOImpl(Connection connection) {
		super();
		this.connection = connection;
	}

	@Override
	public Connection releaseConnection() {
		return this.connection;
	}

	@Override
	public List<ErrorLevel> getErrorLevelsList() {
		List<ErrorLevel> errorLevels = new ArrayList<ErrorLevel>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(DBQueryManager.getInstance()
					.getProperty(DBQueryManager.GET_ERROR_LEVELS_LIST));
			rs = ps.executeQuery();
			try {
				while (rs.next()) {
					ErrorLevel errorLevel = new ErrorLevel();
					errorLevel.setId(rs.getInt(1));
					errorLevel.setLevelName(rs.getString(2));					
					errorLevels.add(errorLevel);
				}
			} finally {
				if (rs != null)
					rs.close();
			}
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		} finally {
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					System.out.println(e.getLocalizedMessage());
				}
		}
		return errorLevels;
	}
	
	@Override
	public ErrorLevel getErrorLevel(int id) {
		ErrorLevel errorLevel = null;
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(DBQueryManager.getInstance()
					.getProperty(DBQueryManager.GET_ERROR_LEVEL_BY_ID));
			ps.setInt(1, id);
			ResultSet rs = null;
			try {
				rs = ps.executeQuery();
				if (rs.next()) {
					errorLevel = new ErrorLevel();
					errorLevel.setId(id);
					errorLevel.setLevelName(rs.getString(2));
				}
			} finally {
				if (rs != null)
					rs.close();
			}
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		} finally {
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					System.out.println(e.getLocalizedMessage());
				}
		}
		return errorLevel;
	}

}
