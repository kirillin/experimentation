package com.epam.eshop.controller.service_locator;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.eshop.controller.services.Service;

/**
 * Class contains List all executed services 
 * 
 * @author Kirill Artemov
 *
 */
public class CacheServices {

	private static Logger logger = Logger.getLogger(CacheServices.class);
	private List<Service> services;

	public CacheServices() {
		services = new ArrayList<Service>();
	}

	public Service getService(String serviceName) {
		for (Service service : services) {
			if (service.getNameService().equalsIgnoreCase(serviceName)) {
				logger.info("Returning cached  " + serviceName + " object");
				return service;
			}
		}
		return null;
	}

	public void addService(Service newService) {
		boolean exists = false;
		for (Service service : services) {
			if (service.getNameService().equalsIgnoreCase(
					newService.getNameService())) {
				exists = true;
			}
		}
		if (!exists) {
			services.add(newService);
		}
	}
}
