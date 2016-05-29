package org.artemov.bts.model.dao.daos.entity_dao_interface;

import java.sql.Connection;
import java.util.List;

import org.artemov.bts.model.entities.ErrorsStatus;

public interface ErrorsStatusDAO {

	public Connection releaseConnection();
	
	public List<ErrorsStatus> getErrorsStatusesList();

	public ErrorsStatus getErrorsStatus(int id);
}
