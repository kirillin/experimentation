package org.artemov.bts.model.dao.daos.entity_dao_implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.artemov.bts.model.dao.daos.entity_dao_interface.ErrorDAO;
import org.artemov.bts.model.entities.Error;
import org.artemov.bts.model.manager.DBQueryManager;

public class ErrorDAOImpl implements ErrorDAO {

	private Connection connection;

	public ErrorDAOImpl() {
		super();
	}

	public ErrorDAOImpl(Connection connection) {
		super();
		this.connection = connection;
	}

	@Override
	public Connection releaseConnection() {
		return this.connection;
	}

	@Override
	public Error getError(int id) {
		Error error = null;
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(DBQueryManager.getInstance()
					.getProperty(DBQueryManager.GET_ERROR_BY_ID));
			ps.setInt(1, id);
			ResultSet rs = null;
			try {
				rs = ps.executeQuery();
				if (rs.next()) {
					error = new Error();
					error.setId(rs.getInt(1));
					error.setStatusId(rs.getInt(2));
					error.setErrorLevelId(rs.getInt(3));
					error.setProjectId(rs.getInt(4));
					error.setEmployeeId(rs.getInt(5));
					error.setRegisterDate(rs.getTimestamp(6));
					error.setDescription(rs.getString(7));
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
		return error;

	}

	@Override
	public List<Error> getErrorsList() {
		List<Error> errors = new ArrayList<Error>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(DBQueryManager.getInstance()
					.getProperty(DBQueryManager.GET_ERRORS_LIST));
			rs = ps.executeQuery();
			try {
				while (rs.next()) {
					Error error = new Error();
					error.setId(rs.getInt(1));
					error.setStatusId(rs.getInt(2));
					error.setErrorLevelId(rs.getInt(3));
					error.setProjectId(rs.getInt(4));
					error.setEmployeeId(rs.getInt(5));
					error.setRegisterDate(rs.getTimestamp(6));
					error.setDescription(rs.getString(7));
					errors.add(error);
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
		return errors;
	}

	@Override
	public void addError(Error error) {
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(DBQueryManager.getInstance()
					.getProperty(DBQueryManager.ADD_ERROR));
			ps.setInt(1, error.getId());
			ps.setInt(2, error.getErrorLevelId());
			ps.setInt(3, error.getProjectId());
			ps.setInt(4, error.getEmployeeId());
			ps.setTimestamp(5, error.getRegisterDate());
			ps.setString(6, error.getDescription());
			ps.execute();
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
	}

	@Override
	public void updateError(Error error) {
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(DBQueryManager.getInstance()
					.getProperty(DBQueryManager.UPDATE_ERROR_BY_ID));
			ps.setInt(1, error.getStatusId());
			ps.setInt(2, error.getErrorLevelId());
			ps.setInt(3, error.getProjectId());
			ps.setInt(4, error.getEmployeeId());
			ps.setTimestamp(5, error.getRegisterDate());
			ps.setString(6, error.getDescription());
			ps.setInt(7, error.getId());			
			ps.execute();
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
	}

}
