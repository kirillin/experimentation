package org.artemov.bts.model.services;

import org.artemov.bts.model.dao.factory.DAOFactory;
import org.artemov.bts.model.dao.factory.MySQLDAOFactory;
import org.artemov.bts.model.pool.ConnectionPool;

public abstract class Service {

	private String nameService;
	private ConnectionPool pool;
	protected DAOFactory dao;

	public Service() {
		super();
	}
	
	public Service(String nameService) {
		super();
		this.nameService = nameService;
	}

	/**
	 * @param daoFactory
	 * @param nameService
	 */
	public Service(int daoFactory, String nameService) {
		super();
		pool = ConnectionPool.getInstance();
		dao = DAOFactory.getDAOFactory(daoFactory);
		if (dao instanceof MySQLDAOFactory) {
			MySQLDAOFactory myMysql = (MySQLDAOFactory) dao;
			myMysql.setConnectionPool(pool);
		}
		this.nameService = nameService;
	}
	
	public String getNameService() {
		return nameService;
	}
}
