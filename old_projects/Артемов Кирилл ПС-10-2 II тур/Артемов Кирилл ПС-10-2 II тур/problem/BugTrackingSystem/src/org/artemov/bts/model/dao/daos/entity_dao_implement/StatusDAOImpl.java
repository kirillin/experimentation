package org.artemov.bts.model.dao.daos.entity_dao_implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.artemov.bts.model.dao.daos.entity_dao_interface.StatusDAO;
import org.artemov.bts.model.entities.Status;
import org.artemov.bts.model.manager.DBQueryManager;

public class StatusDAOImpl implements StatusDAO {

	private Connection connection;

	public StatusDAOImpl() {
		super();
	}

	public StatusDAOImpl(Connection connection) {
		super();
		this.connection = connection;
	}

	@Override
	public Connection releaseConnection() {
		return this.connection;
	}

	@Override
	public List<Status> getStatusesList() {
		List<Status> statuses = new ArrayList<Status>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(DBQueryManager.getInstance()
					.getProperty(DBQueryManager.GET_STATUSES_LIST));
			rs = ps.executeQuery();
			try {
				while (rs.next()) {
					Status status = new Status();
					status.setId(rs.getInt(1));
					status.setErrorId(rs.getInt(2));
					status.setErrorsStatusId(rs.getInt(3));
					status.setEmployeeId(rs.getInt(4));
					status.setDateSetting(rs.getTimestamp(5));					
					statuses.add(status);
					System.out.println("service");
					System.out.println(statuses);
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
		return statuses;
	}

	@Override
	public void addStatus(Status status) {
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(DBQueryManager.getInstance()
					.getProperty(DBQueryManager.ADD_STATUS));
			ps.setInt(1, status.getErrorId());
			ps.setInt(2, status.getErrorsStatusId());
			ps.setInt(3, status.getEmployeeId());
			ps.setTimestamp(4, status.getDateSetting());
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
	public void updateStatus(Status status) {
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(DBQueryManager.getInstance()
					.getProperty(DBQueryManager.UPDATE_STAUS_BY_ID));
			ps.setInt(1, status.getErrorId());
			ps.setInt(2, status.getErrorsStatusId());
			ps.setInt(3, status.getEmployeeId());
			ps.setTimestamp(4, status.getDateSetting());
			ps.setInt(5, status.getId());
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
