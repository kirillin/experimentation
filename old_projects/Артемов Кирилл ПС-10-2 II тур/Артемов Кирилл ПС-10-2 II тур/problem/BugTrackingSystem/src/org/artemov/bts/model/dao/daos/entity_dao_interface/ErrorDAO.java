package org.artemov.bts.model.dao.daos.entity_dao_interface;

import java.sql.Connection;
import java.util.List;
import org.artemov.bts.model.entities.Error;

public interface ErrorDAO {

	public Connection releaseConnection();

	public Error getError(int id);

	public List<Error> getErrorsList();

	public void addError(Error error);

	public void updateError(Error error);
}
