package com.epam.eshop.command.command_facade;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.eshop.controller.service_locator.ServiceLocator;
import com.epam.eshop.controller.services.SingupService;
import com.epam.eshop.controller.services.UserService;
import com.epam.eshop.lib.Validator;
import com.epam.eshop.manager.AttributesEnum;
import com.epam.eshop.manager.ConfigurationManager;
import com.epam.eshop.manager.MessageManager;

public class SingupCommand implements Command {

	private static Logger logger = Logger.getLogger(SingupCommand.class);

	/**
	 * Names of fields from form for registration
	 */
	// private static final String PARAM_NAME_ROLE = "roleId";
	private final String PARAM_NAME_LOGIN = "login";
	private final String PARAM_NAME_PASSWORD = "password";
	private final String PARAM_NAME_EMAIL = "email";
	private final String PARAM_NAME_FIRST_NAME = "firstName";
	private final String PARAM_NAME_LAST_NAME = "lastName";

	/**
	 * Default constructor
	 */
	public SingupCommand() {
	}

	/**
	 * if user registration is success then user automatic authorization
	 */
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String page = null;
		HttpSession session = request.getSession(true);
		String login = Validator.stripHarmful(request
				.getParameter(PARAM_NAME_LOGIN));
		String password = Validator.stripHarmful(request
				.getParameter(PARAM_NAME_PASSWORD));
		String email = Validator.stripHarmful(request
				.getParameter(PARAM_NAME_EMAIL));
		String firstName = Validator.stripHarmful(request
				.getParameter(PARAM_NAME_FIRST_NAME));
		String lastName = Validator.stripHarmful(request
				.getParameter(PARAM_NAME_LAST_NAME));
		SingupService singup = (SingupService) ServiceLocator
				.getService(AttributesEnum.SING_UP.toString());
		UserService userService = (UserService) ServiceLocator
				.getService(AttributesEnum.USER_SERVICE.toString());
		if (singup.isValid(login, PARAM_NAME_LOGIN)
				&& singup.isValid(password, PARAM_NAME_PASSWORD)
				&& singup.isValid(email, PARAM_NAME_EMAIL)) {
			singup.createUser(login, password, email, firstName, lastName);
			if (userService.isAdmin(login)) {
				session.setAttribute(AttributesEnum.ADMIN.toString(), true);
			}
			session.setAttribute(AttributesEnum.USER_NAME.toString(), login);
			session.setAttribute(AttributesEnum.IS_USER_ENTER.toString(), true);
			request.setAttribute(AttributesEnum.WELCOME.toString(), login);
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.HOME_PAGE);
		} else {
			logger.trace(MessageManager.getInstance().getProperty(
					MessageManager.REGISTER_ERROR_MESSAGE));
			request.setAttribute(
					AttributesEnum.ERROR_MESSAGE.toString(),
					MessageManager.getInstance().getProperty(
							MessageManager.REGISTER_ERROR_MESSAGE));
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.ERROR_PAGE);
		}
		return page;
	}

}
