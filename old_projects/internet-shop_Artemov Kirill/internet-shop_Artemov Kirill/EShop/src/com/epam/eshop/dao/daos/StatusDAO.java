package com.epam.eshop.dao.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.eshop.dao.daos.inrefaces.InterfaceStatusDAO;
import com.epam.eshop.manager.DBQueryManager;
import com.epam.eshop.model.entities.Status;

public class StatusDAO implements InterfaceStatusDAO {

	private static Logger logger = Logger.getLogger(StatusDAO.class);
	private Connection connection;

	public StatusDAO() {
		super();
	}

	/**
	 * @param connection
	 */
	public StatusDAO(Connection connection) {
		super();
		this.connection = connection;
	}

	@Override
	public List<Status> getStatuses() throws SQLException {
		List<Status> statuses = new LinkedList<Status>();
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(DBQueryManager.getInstance()
					.getProperty(DBQueryManager.GET_STASUSES));
			ResultSet rs = null;
			try {
				rs = ps.executeQuery();
				while (rs.next()) {
					Status status = new Status();
					status.setId(rs.getInt(1));
					status.setStatus(rs.getString(2));
					statuses.add(status);
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
		if (!statuses.isEmpty()) {
			logger.info("List of statuses returns!");
		} else {
			statuses = null;
		}		
		return statuses;
	}

	/**
	 * Returns Status
	 */
	@Override
	public Status getStatusById(int statusId) throws SQLException {
		Status status = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(DBQueryManager.getInstance()
					.getProperty(DBQueryManager.GET_STATUS_BY_ID));
			ps.setInt(1, statusId);
			rs = ps.executeQuery();
			try {
				if (rs.next()) {
					status = new Status();
					status.setId(rs.getInt(1));
					status.setStatus(rs.getString(2));
				}
			} finally {
				if (rs != null)
					rs.close();
			}
		} finally {
			if (ps != null)
				ps.close();
		}
		if (status != null) {
			logger.info("Status returned!");
		}
		return status;
	}

	@Override
	public Connection releaseConnection() {
		return this.connection;
	}

}
