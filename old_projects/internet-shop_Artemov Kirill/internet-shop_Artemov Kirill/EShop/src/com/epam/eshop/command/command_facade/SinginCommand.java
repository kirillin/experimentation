package com.epam.eshop.command.command_facade;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.eshop.controller.service_locator.ServiceLocator;
import com.epam.eshop.controller.services.CartService;
import com.epam.eshop.controller.services.SinginService;
import com.epam.eshop.controller.services.UserService;
import com.epam.eshop.lib.Validator;
import com.epam.eshop.manager.AttributesEnum;
import com.epam.eshop.manager.ConfigurationManager;
import com.epam.eshop.manager.MessageManager;
import com.epam.eshop.model.entities.Cart;

public class SinginCommand implements Command {

	private static Logger logger = Logger.getLogger(SinginCommand.class);
	/**
	 * Names of fields from authentication form
	 */
	private static final String PARAM_NAME_LOGIN = "login";
	private static final String PARAM_NAME_PASSWORD = "password";

	public SinginCommand() {
	}

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String page = null;
		HttpSession session = request.getSession(true);
		// Checking cookies for exists cart
		Cart cart = null;
		Cookie cookie = null;
		CartService cartService = (CartService) ServiceLocator
				.getService(AttributesEnum.CART_SERVICE.toString());
		// from cookie to cart
		if (request.getCookies() != null) {
			Cookie[] cookies = request.getCookies();
			cookie = cartService.getCookie(cookies, AttributesEnum.CART.toString());
			if (cookie != null) {
				try {
					cart = (Cart) cartService.getCart(session, cookie);
					session.setAttribute(AttributesEnum.CART.toString(), cart);
				} catch (ClassNotFoundException e) {
					logger.fatal("Cart service not found!");
				}
			}
		}
		String login = Validator.stripHarmful(request
				.getParameter(PARAM_NAME_LOGIN));
		String password = Validator.stripHarmful(request
				.getParameter(PARAM_NAME_PASSWORD));
		SinginService singin = (SinginService) ServiceLocator
				.getService(AttributesEnum.SING_IN.toString());
		UserService userService = (UserService) ServiceLocator
				.getService(AttributesEnum.USER_SERVICE.toString());
		if (singin.checkLogin(login, password)) {
			if (userService.isAdmin(login)) {
				session.setAttribute(AttributesEnum.ADMIN.toString(), true);
			}
			session.setAttribute(AttributesEnum.USER_NAME.toString(), login);
			session.setAttribute(AttributesEnum.IS_USER_ENTER.toString(), true);
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.HOME_PAGE);
		} else {
			logger.fatal(MessageManager.getInstance().getProperty(
					MessageManager.LOGIN_ERROR_MESSAGE));
			request.setAttribute(
					AttributesEnum.IS_USER_ENTER.toString(),
					MessageManager.getInstance().getProperty(
							MessageManager.LOGIN_ERROR_MESSAGE));
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.ERROR_PAGE);
		}
		return page;
	}

}
