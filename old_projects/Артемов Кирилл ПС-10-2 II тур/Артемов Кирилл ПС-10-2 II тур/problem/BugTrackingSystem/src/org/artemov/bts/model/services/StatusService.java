package org.artemov.bts.model.services;

import java.util.List;

import org.artemov.bts.model.dao.daos.entity_dao_implement.StatusDAOImpl;
import org.artemov.bts.model.dao.factory.MySQLDAOFactory;
import org.artemov.bts.model.entities.Status;

public class StatusService extends Service {

	public StatusService() {
		super();
	}

	public StatusService(int daoFactory, String nameService) {
		super(daoFactory, nameService);
	}

	@Override
	public String getNameService() {
		return super.getNameService();
	}

	public List<Status> getStatusesList() {
		StatusDAOImpl statusDAOImpl = null;
		List<Status> statuses = null;
		statusDAOImpl = dao.getStatusDAOImpl();
		statuses = statusDAOImpl.getStatusesList();
		MySQLDAOFactory.releaseConnection(statusDAOImpl
					.releaseConnection());
		return statuses;
	}
	
	public void addStatus(Status status) {
		StatusDAOImpl statusDAOImpl = null;
		statusDAOImpl = dao.getStatusDAOImpl();
		statusDAOImpl.addStatus(status);
		MySQLDAOFactory.releaseConnection(statusDAOImpl.releaseConnection());
	}

	public void updateStatus(Status status) {
		StatusDAOImpl statusDAOImpl = null;
		statusDAOImpl = dao.getStatusDAOImpl();
		statusDAOImpl.updateStatus(status);
		MySQLDAOFactory.releaseConnection(statusDAOImpl.releaseConnection());
	}


}
