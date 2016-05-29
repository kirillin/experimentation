package org.artemov.bts.model.service_locator;

import org.artemov.bts.model.dao.factory.DAOFactory;
import org.artemov.bts.model.services.Service;



public class ServiceLocator {

	private static CacheServices cache;

	static {
		cache = new CacheServices();
	}

	public static Service getService(String serviceName) {
		Service service = cache.getService(serviceName);
		if (service != null) {
			return service;
		}
		InitialContext context = new InitialContext();
		Service newService = (Service) context.lookup(serviceName,
				DAOFactory.MYSQL);
		cache.addService(newService);
		return newService;
	}
}
