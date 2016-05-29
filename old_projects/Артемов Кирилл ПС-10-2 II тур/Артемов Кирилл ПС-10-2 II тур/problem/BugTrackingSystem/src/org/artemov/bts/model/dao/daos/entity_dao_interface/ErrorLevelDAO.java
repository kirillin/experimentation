package org.artemov.bts.model.dao.daos.entity_dao_interface;

import java.sql.Connection;
import java.util.List;

import org.artemov.bts.model.entities.ErrorLevel;

public interface ErrorLevelDAO {

	public Connection releaseConnection();
	
	public List<ErrorLevel> getErrorLevelsList();

	public ErrorLevel getErrorLevel(int id);
} 
