package com.epam.eshop.command.command_facade;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.eshop.controller.service_locator.ServiceLocator;
import com.epam.eshop.controller.services.UserService;
import com.epam.eshop.lib.Validator;
import com.epam.eshop.manager.AttributesEnum;
import com.epam.eshop.manager.ConfigurationManager;
import com.epam.eshop.manager.MessageManager;

public class UpdateUserCommand implements Command {

	private static Logger logger = Logger.getLogger(UpdateUserCommand.class);
	/**
	 * Names fields from form
	 */
	private static final String PARAM_NAME_LOGIN = "login";
	private static final String PARAM_NAME_PASSWORD = "password";
	private static final String PARAM_NAME_EMAIL = "email";
	private static final String PARAM_NAME_FIRST_NAME = "firstName";
	private static final String PARAM_NAME_LAST_NAME = "lastName";

	/**
	 * Default constructor
	 */
	public UpdateUserCommand() {
	}

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String page = null;
		UserService userService = (UserService) ServiceLocator
				.getService("userService");
		String login = Validator.stripHarmful(request
				.getParameter(PARAM_NAME_LOGIN));
		String password = Validator.stripHarmful(request
				.getParameter(PARAM_NAME_PASSWORD));
		String firstName = Validator.stripHarmful(request
				.getParameter(PARAM_NAME_FIRST_NAME));
		String lastName = Validator.stripHarmful(request
				.getParameter(PARAM_NAME_LAST_NAME));
		String email = Validator.stripHarmful(request
				.getParameter(PARAM_NAME_EMAIL));
		if (userService.updateUser(login, password, email, firstName, 
				lastName)) {
			request.setAttribute(AttributesEnum.UPDATED.toString(), true);
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.PROFILE_EDIT_PAGE);
		} else {
			logger.trace(MessageManager.getInstance().getProperty(
					MessageManager.UPDATE_USER_PROBLEMS));
			request.setAttribute(AttributesEnum.UPDATED.toString(), false);
		}
		return page;
	}

}
