package com.epam.eshop.command.command_facade;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.eshop.controller.service_locator.ServiceLocator;
import com.epam.eshop.controller.services.OrderService;
import com.epam.eshop.controller.services.UserService;
import com.epam.eshop.lib.Validator;
import com.epam.eshop.manager.AttributesEnum;
import com.epam.eshop.manager.ConfigurationManager;
import com.epam.eshop.manager.MessageManager;
import com.epam.eshop.model.entities.Cart;

/**
 * Class for checkout orders
 * 
 * @author Kirill Artemov
 * 
 */
public class CheckoutCommand implements Command {

	private static Logger logger = Logger.getLogger(CheckoutCommand.class);
	/**
	 * Names fields of form
	 */
	private static final String PARAM_NAME_CITY = "cityid";
	private static final String PARAM_NAME_SHIPPING_METHOD = "shippingid";
	private static final String PARAM_NAME_PAYMANT_METHOD = "paymentid";
	private static final String PARAM_NAME_ADRESS = "adress";
	private static final String PARAM_NAME_ZIP = "zip";
	private static final String PARAM_NAME_PHONE = "phone";

	/**
	 * Default constructor
	 */
	public CheckoutCommand() {
	}

	/**
	 * Method implements transfer control to services
	 */
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String page = null;
		HttpSession session = request.getSession(true);
		// Validation of params from form
		boolean success = false;
		int cityId = -1;
		int shippingMethodId = -1;
		int paymentId = -1;
		Calendar c = new GregorianCalendar(TimeZone.getDefault());
		long order_date = c.getTimeInMillis();
		// Get the data from form and processing it
		String cityIdValue = Validator.stripHarmful(request
				.getParameter(PARAM_NAME_CITY));
		if (cityIdValue != null) {
			if (Validator.isNumber(cityIdValue)) {
				cityId = Integer.parseInt(cityIdValue);
				success = true;
			}
		}
		String shippingIdValue = Validator.stripHarmful(request
				.getParameter(PARAM_NAME_SHIPPING_METHOD));
		if ((shippingIdValue != null) && (success == true)) {
			success = false;
			if (Validator.isNumber(shippingIdValue)) {
				shippingMethodId = Integer.parseInt(shippingIdValue);
				success = true;
			}
		}
		String paymentIdValue = Validator.stripHarmful(request
				.getParameter(PARAM_NAME_PAYMANT_METHOD));
		if ((paymentIdValue != null) && (success == true)) {
			success = false;
			if (Validator.isNumber(paymentIdValue)) {
				paymentId = Integer.parseInt(paymentIdValue);
				success = true;
			}
		}
		String adress = Validator.stripHarmful(request
				.getParameter(PARAM_NAME_ADRESS));
		String zip = Validator.stripHarmful(request
				.getParameter(PARAM_NAME_ZIP));
		String phoneValue = Validator.stripHarmful(request
				.getParameter(PARAM_NAME_PHONE));
		String phone = null;
		if (Validator.isPhoneNumber(phoneValue) && (success == true)) {
			phone = phoneValue;
			success = true;
		} else {
			success = false;
		}
		// Get cart
		// Get from cart total price
		// and HashMap<id, count> for table orders_products
		Cart cart = Cart.getInstance(session);
		Float totalPrice = cart.getTotalPrice();
		HashMap<Integer, Integer> pairsIdCount = cart.getHashMap();
		// Get login of user-order
		String login = (String) session.getAttribute(
				AttributesEnum.USER_NAME.toString());
		UserService userService = (UserService) ServiceLocator
				.getService(AttributesEnum.USER_SERVICE.toString());
		int userId = userService.getUser(login).getId();
		int statusId = 1; // default "in process"
		OrderService orderService = (OrderService) ServiceLocator
				.getService(AttributesEnum.ORDER_SERVICE.toString());
		// orderService.addOrder(fields...)
		// Transfer fields for TWO TABLES: orders and shipping_orders
		int orderId = -1;
		if (success = true) {
			orderId = orderService.addOrder(shippingMethodId, cityId, adress,
					zip, phone, userId, paymentId, order_date, totalPrice,
					statusId, pairsIdCount);
		}
		if ((orderId != -1)) {
			session.setAttribute(AttributesEnum.ADD_ORDER.toString(), true);
			session.removeAttribute(AttributesEnum.CART.toString());
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.CHECKOUT_PAGE);
		} else {
			session.setAttribute(AttributesEnum.ADD_ORDER.toString(), false);
			logger.trace(MessageManager.getInstance().getProperty(
					MessageManager.CHECKOUT_ERROR_MESSAGE));
			request.setAttribute(AttributesEnum.ERROR_MESSAGE.toString(), 
					MessageManager.getInstance()
					.getProperty(MessageManager.CHECKOUT_ERROR_MESSAGE));
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.ERROR_PAGE);
		}
		return page;
	}
}
