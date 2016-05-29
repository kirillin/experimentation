package org.artemov.bts.model.dao.daos.entity_dao_interface;

import java.sql.Connection;
import java.util.List;

import org.artemov.bts.model.entities.Status;

public interface StatusDAO {

	public Connection releaseConnection();
	
	public List<Status> getStatusesList();

	public void addStatus(Status status);

	public void updateStatus(Status status);
}
