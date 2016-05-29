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
import com.epam.eshop.lib.Validator;
import com.epam.eshop.manager.AttributesEnum;
import com.epam.eshop.manager.ConfigurationManager;
import com.epam.eshop.manager.MessageManager;
import com.epam.eshop.model.entities.Status;
import com.epam.eshop.view.views.OrdersView;

public class GetOrdersCommand implements Command {

	private static Logger logger = Logger.getLogger(GetOrdersCommand.class);
	private final String STATUS_ID = "statusId";
	private final String ORDER_ID = "orderId";

	/**
	 * Default constructor
	 */
	public GetOrdersCommand() {
	}

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String page = null;
		HttpSession session = request.getSession(true);
		List<OrdersView> orders = null;
		List<Status> statuses = null;
		OrderService orderService = (OrderService) ServiceLocator
				.getService(AttributesEnum.ORDER_SERVICE.toString());
		String statusIdValue = Validator.stripHarmful(request
				.getParameter(STATUS_ID));
		String orderIdValue = Validator.stripHarmful(request
				.getParameter(ORDER_ID));
		session.removeAttribute(AttributesEnum.ORDER_UPDATED.toString());
		if ((statusIdValue != null) && (orderIdValue != null)) {
			int statusId = -1;
			int orderId = -1;
			if (Validator.isNumber(statusIdValue)) {
				statusId = Integer.parseInt(statusIdValue);
			}
			if (Validator.isNumber(orderIdValue)) {
				orderId = Integer.parseInt(orderIdValue);
			}
			if ((statusId != -1) && (orderId != -1)) {
				if (orderService.updateOrder(orderId, statusId)) {
					session.setAttribute(AttributesEnum.ORDER_UPDATED.toString(),
							true);
				} else {
					session.setAttribute(AttributesEnum.ORDER_UPDATED.toString(),
							false);
				}
			}
		}
		if (request.getParameter(AttributesEnum.USER_NAME.toString()) != null) {
			String login = (String) session
					.getAttribute(AttributesEnum.USER_NAME.toString());
			orders = orderService.getOrders(login);
		} else {
			orders = orderService.getOrders();
		}
		statuses = orderService.getStatuses();
		if (orders != null) {
			session.setAttribute(AttributesEnum.ORDERS.toString(), orders);
			session.setAttribute(AttributesEnum.STATUSES.toString(), statuses);
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.ORDERS_PAGE);
		} else {
			logger.fatal(MessageManager.getInstance().getProperty(
					MessageManager.ORDERS_NULL));
			request.setAttribute(
					AttributesEnum.ERROR_MESSAGE.toString(),
					MessageManager.getInstance().getProperty(
							MessageManager.ORDERS_NULL));
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.ORDERS_PAGE);
		}
		return page;
	}

}
