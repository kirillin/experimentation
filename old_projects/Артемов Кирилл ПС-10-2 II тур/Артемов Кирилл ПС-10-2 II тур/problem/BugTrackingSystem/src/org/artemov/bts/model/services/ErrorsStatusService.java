package org.artemov.bts.model.services;

import java.util.List;

import org.artemov.bts.model.dao.daos.entity_dao_implement.ErrorsStatusDAOImpl;
import org.artemov.bts.model.dao.factory.MySQLDAOFactory;
import org.artemov.bts.model.entities.ErrorsStatus;

public class ErrorsStatusService extends Service {

	public ErrorsStatusService() {
		super();
	}

	public ErrorsStatusService(int daoFactory, String nameService) {
		super(daoFactory, nameService);
	}

	@Override
	public String getNameService() {
		return super.getNameService();
	}

	public List<ErrorsStatus> getErorsStatusesList() {
		ErrorsStatusDAOImpl errorsStatusDAOImpl = null;
		List<ErrorsStatus> errorsStatus = null;
		errorsStatusDAOImpl = dao.getErrorsStatusDAOImpl();
		errorsStatus = errorsStatusDAOImpl.getErrorsStatusesList();
		MySQLDAOFactory.releaseConnection(errorsStatusDAOImpl.releaseConnection());
		return errorsStatus;
	}
	
	public ErrorsStatus getErrorsStatus(int id) {
		ErrorsStatusDAOImpl errorsStatusDAOImpl = null;
		ErrorsStatus errorsStatus = null;
		errorsStatusDAOImpl = dao.getErrorsStatusDAOImpl();
		errorsStatus = errorsStatusDAOImpl.getErrorsStatus(id);
		MySQLDAOFactory.releaseConnection(errorsStatusDAOImpl.releaseConnection());
		return errorsStatus;
	}

}
