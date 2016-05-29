package com.epam.eshop.dao.daos.inrefaces;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.epam.eshop.model.entities.Status;

public interface InterfaceStatusDAO {
	Status getStatusById(int statusId) throws SQLException;

	Connection releaseConnection();

	List<Status> getStatuses() throws SQLException;
}
