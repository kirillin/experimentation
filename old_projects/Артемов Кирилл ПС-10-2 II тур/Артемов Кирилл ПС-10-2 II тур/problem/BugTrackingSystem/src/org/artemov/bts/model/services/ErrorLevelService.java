package org.artemov.bts.model.services;

import java.util.List;

import org.artemov.bts.model.dao.daos.entity_dao_implement.ErrorLevelDAOImpl;
import org.artemov.bts.model.dao.factory.MySQLDAOFactory;
import org.artemov.bts.model.entities.ErrorLevel;

public class ErrorLevelService extends Service {

	public ErrorLevelService() {
		super();
	}

	public ErrorLevelService(int daoFactory, String nameService) {
		super(daoFactory, nameService);
	}

	@Override
	public String getNameService() {
		return super.getNameService();
	}

	public List<ErrorLevel> getErrorLevelsList() {
		ErrorLevelDAOImpl errorLevelDAOImpl = null;
		List<ErrorLevel> errorLevels = null;
		errorLevelDAOImpl = dao.getErrorLevelDAOImpl();
		errorLevels = errorLevelDAOImpl.getErrorLevelsList();
		MySQLDAOFactory.releaseConnection(errorLevelDAOImpl
					.releaseConnection());
		return errorLevels;
	}

	public ErrorLevel getErrorLevel(int id) {
		ErrorLevelDAOImpl errorLevelDAOImpl = null;
		ErrorLevel errorLevel = null;
		errorLevelDAOImpl = dao.getErrorLevelDAOImpl();
		errorLevel = errorLevelDAOImpl.getErrorLevel(id);
		MySQLDAOFactory.releaseConnection(errorLevelDAOImpl
					.releaseConnection());
		return errorLevel;
	}
	
}
