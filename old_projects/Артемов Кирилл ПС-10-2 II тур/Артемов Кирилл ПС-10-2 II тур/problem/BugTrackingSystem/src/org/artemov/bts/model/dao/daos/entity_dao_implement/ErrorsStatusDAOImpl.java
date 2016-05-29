package org.artemov.bts.model.dao.daos.entity_dao_implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.artemov.bts.model.dao.daos.entity_dao_interface.ErrorsStatusDAO;
import org.artemov.bts.model.entities.ErrorsStatus;
import org.artemov.bts.model.manager.DBQueryManager;

public class ErrorsStatusDAOImpl implements ErrorsStatusDAO {
	
	private Connection connection;

	public ErrorsStatusDAOImpl() {
		super();
	}

	public ErrorsStatusDAOImpl(Connection connection) {
		super();
		this.connection = connection;
	}

	@Override
	public Connection releaseConnection() {
		return this.connection;
	}

	@Override
	public List<ErrorsStatus> getErrorsStatusesList() {
		List<ErrorsStatus> errorsStatuses = new ArrayList<ErrorsStatus>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(DBQueryManager.getInstance()
					.getProperty(DBQueryManager.GET_ERRORS_STATUSES_LIST));
			rs = ps.executeQuery();
			try {
				while (rs.next()) {
					ErrorsStatus errorsStatus = new ErrorsStatus();
					errorsStatus.setId(rs.getInt(1));
					errorsStatus.setStatusName(rs.getString(2));					
					errorsStatuses.add(errorsStatus);
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
		return errorsStatuses;
	}
	
	@Override
	public ErrorsStatus getErrorsStatus(int id) {
		ErrorsStatus errorsStatus = null;
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(DBQueryManager.getInstance()
					.getProperty(DBQueryManager.GET_ERRORS_STATUS_BY_ID));
			ps.setInt(1, id);
			ResultSet rs = null;
			try {
				rs = ps.executeQuery();
				if (rs.next()) {
					errorsStatus = new ErrorsStatus();
					errorsStatus.setId(id);
					errorsStatus.setStatusName(rs.getString(2));
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
		return errorsStatus;
	}

}
