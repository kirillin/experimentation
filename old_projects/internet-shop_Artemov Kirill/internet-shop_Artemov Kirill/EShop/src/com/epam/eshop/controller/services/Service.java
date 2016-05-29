package com.epam.eshop.controller.services;

import com.epam.eshop.dao.factory.DAOFactory;
import com.epam.eshop.dao.factory.MySQLDAOFactory;
import com.epam.eshop.pool.ConnectionPool;

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
