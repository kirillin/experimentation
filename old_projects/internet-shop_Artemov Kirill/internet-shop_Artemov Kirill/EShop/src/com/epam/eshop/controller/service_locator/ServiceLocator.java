package com.epam.eshop.controller.service_locator;

import com.epam.eshop.controller.services.Service;
import com.epam.eshop.dao.factory.DAOFactory;

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
