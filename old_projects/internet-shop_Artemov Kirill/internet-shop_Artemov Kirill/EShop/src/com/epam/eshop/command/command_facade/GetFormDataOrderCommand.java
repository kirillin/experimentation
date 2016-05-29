package com.epam.eshop.command.command_facade;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.eshop.controller.service_locator.ServiceLocator;
import com.epam.eshop.controller.services.OrderService;
import com.epam.eshop.manager.AttributesEnum;
import com.epam.eshop.manager.ConfigurationManager;
import com.epam.eshop.manager.MessageManager;
import com.epam.eshop.model.entities.City;
import com.epam.eshop.model.entities.PaymentMethod;
import com.epam.eshop.model.entities.ShippingMethod;

public class GetFormDataOrderCommand implements Command {

	private static Logger logger = Logger
			.getLogger(GetFormDataOrderCommand.class);

	/**
	 * Default constructor
	 */
	public GetFormDataOrderCommand() {
	}

	/**
	 * Get from DB lists of cities, shipping methods etc.
	 */
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String page = null;
		HttpSession session = request.getSession(true);
		List<City> cities = null;
		List<ShippingMethod> shm = null;
		List<PaymentMethod> pm = null;
		OrderService orderService = (OrderService) ServiceLocator
				.getService(AttributesEnum.ORDER_SERVICE.toString());
		cities = orderService.getCities();
		shm = orderService.getShippingMethods();
		pm = orderService.getPaymentMethods();

		if ((cities != null) && (shm != null) && (pm != null)) {
			session.setAttribute(AttributesEnum.CITIES.toString(), cities);
			session.setAttribute(AttributesEnum.SHIPPING_METHODS.toString(), shm);
			session.setAttribute(AttributesEnum.PAYMENT_METHODS.toString(), pm);
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.CHECKOUT_PAGE);
		} else {
			logger.trace(MessageManager.getInstance().getProperty(
					MessageManager.SOMETHING_PROBLEM));
			request.setAttribute(
					AttributesEnum.ERROR_MESSAGE.toString(),
					MessageManager.getInstance().getProperty(
							MessageManager.SOMETHING_PROBLEM));
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.ERROR_PAGE);
		}
		return page;
	}
}
