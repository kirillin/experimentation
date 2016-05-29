package com.epam.eshop.controller.service_locator;

import com.epam.eshop.controller.services.BlacklistService;
import com.epam.eshop.controller.services.CartService;
import com.epam.eshop.controller.services.OrderService;
import com.epam.eshop.controller.services.ProductService;
import com.epam.eshop.controller.services.Service;
import com.epam.eshop.controller.services.SinginService;
import com.epam.eshop.controller.services.SingupService;
import com.epam.eshop.controller.services.UserService;

public class InitialContext {
	/**
	 * 
	 * @param serviceName
	 * @param daoFactory
	 *            - integer from @see com.epam.eshop.dao.factory.DAOFactory
	 * @return
	 */
	public Service lookup(String serviceName, int daoFactory) {
		switch (serviceName) {
		case "singin":
			return (Service) new SinginService(daoFactory, serviceName);
		case "singup":
			return (Service) new SingupService(daoFactory, serviceName);
		case "productService":
			return (Service) new ProductService(daoFactory, serviceName);
		case "cartService":
			return (Service) new CartService(daoFactory, serviceName);
		case "userService":
			return (Service) new UserService(daoFactory, serviceName);
		case "blacklistService":
			return (Service) new BlacklistService(daoFactory, serviceName);	
		case "orderService":
			return (Service) new OrderService(daoFactory, serviceName);
		default:
			return null;
		}
	}
}
