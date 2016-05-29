package org.artemov.bts.model.services;

import java.util.List;

import org.artemov.bts.model.dao.daos.entity_dao_implement.ErrorDAOImpl;
import org.artemov.bts.model.dao.factory.MySQLDAOFactory;
import org.artemov.bts.model.entities.Error;

public class ErrorService extends Service {

	public ErrorService() {
		super();
	}

	public ErrorService(int daoFactory, String nameService) {
		super(daoFactory, nameService);
	}

	@Override
	public String getNameService() {
		return super.getNameService();
	}

	public List<Error> getErrorsList() {
		ErrorDAOImpl errorDAOImpl = null;
		List<Error> errors = null;
		errorDAOImpl = dao.getErrorDAOImpl();
		errors = errorDAOImpl.getErrorsList();
		MySQLDAOFactory.releaseConnection(errorDAOImpl.releaseConnection());
		return errors;
	}
	
	public Error getError(int id) {
		ErrorDAOImpl errorDAOImpl = null;
		Error error = null;
		errorDAOImpl = dao.getErrorDAOImpl();
		error = errorDAOImpl.getError(id);
		MySQLDAOFactory.releaseConnection(errorDAOImpl.releaseConnection());
		return error;
	}

	public void addError(Error error) {
		ErrorDAOImpl errorDAOImpl = null;
		errorDAOImpl = dao.getErrorDAOImpl();
		errorDAOImpl.addError(error);
		MySQLDAOFactory.releaseConnection(errorDAOImpl.releaseConnection());
	}

	public void updateError(Error error) {
		ErrorDAOImpl errorDAOImpl = null;
		errorDAOImpl = dao.getErrorDAOImpl();
		errorDAOImpl.updateError(error);
		MySQLDAOFactory.releaseConnection(errorDAOImpl.releaseConnection());
	}

}
