package com.epam.eshop.command.command_facade;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.eshop.controller.service_locator.ServiceLocator;
import com.epam.eshop.controller.services.UserService;
import com.epam.eshop.manager.AttributesEnum;
import com.epam.eshop.manager.ConfigurationManager;
import com.epam.eshop.manager.MessageManager;
import com.epam.eshop.model.entities.User;

public class GetUserCommand implements Command {

	private static Logger logger = Logger.getLogger(GetUserCommand.class);

	/**
	 * Default constructor
	 */
	public GetUserCommand() {
	}

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String page = null;
		HttpSession session = request.getSession();
		UserService userService = (UserService) ServiceLocator
				.getService(AttributesEnum.USER_SERVICE.toString());
		String login = (String) session.getAttribute(AttributesEnum.USER_NAME
				.toString());
		User user = userService.getUser(login);
		if (user != null) {
			// data about user
			request.setAttribute(AttributesEnum.USER_ENTERED.toString(), user);
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.PROFILE_EDIT_PAGE);
		} else {
			logger.fatal(MessageManager.getInstance().getProperty(
					MessageManager.GET_USER_PROBLEMS));
			request.setAttribute(
					AttributesEnum.ERROR_MESSAGE.toString(),
					MessageManager.getInstance().getProperty(
							MessageManager.GET_USER_PROBLEMS));
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.ERROR_PAGE);
		}
		return page;
	}

}
