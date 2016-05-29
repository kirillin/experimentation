package com.epam.eshop.command.command_facade;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.eshop.controller.service_locator.ServiceLocator;
import com.epam.eshop.controller.services.BlacklistService;
import com.epam.eshop.controller.services.UserService;
import com.epam.eshop.lib.Validator;
import com.epam.eshop.manager.AttributesEnum;
import com.epam.eshop.manager.ConfigurationManager;
import com.epam.eshop.manager.MessageManager;
import com.epam.eshop.view.views.UsersView;

public class GetUsersCommand implements Command {

	private static Logger logger = Logger.getLogger(GetUsersCommand.class);
	private final String ADD_TO_BLACKLIST = "addblacklist";
	private final String DESCRIPTION = "desc";
	

	public GetUsersCommand() {
	}

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String page = null;
		HttpSession session = request.getSession(true);
		session.removeAttribute(ADD_TO_BLACKLIST);
		// handler for click on reference for adding to black list
		if (request.getParameter(ADD_TO_BLACKLIST) != null) {
			String userIdValue = request
					.getParameter(ADD_TO_BLACKLIST);
			int userId = -1;
			if (userIdValue != null) {
				if (Validator.isNumber(userIdValue)) {
					userId = Integer.parseInt(userIdValue);
				}
			}
			String description = "Bad user!"; // temporary here!
			if (request.getParameter(DESCRIPTION) != null) {
				description = request.getParameter(DESCRIPTION);
			}
			BlacklistService blacklistService = (BlacklistService) ServiceLocator
					.getService(AttributesEnum.BLACKLIST_SERVICE.toString());
			if (userId != -1) {
				if (blacklistService.add(userId, description)) {
					session.setAttribute(
							AttributesEnum.ADD_TO_BLACKLIST.toString(), true);
				} else {
					session.setAttribute(
							AttributesEnum.ADD_TO_BLACKLIST.toString(), false);
				}
			}
		}
		UserService userService = (UserService) ServiceLocator
				.getService(AttributesEnum.USER_SERVICE.toString());
		List<UsersView> users = userService.getUsers();
		if (users != null) {
			session.setAttribute(AttributesEnum.USERS.toString(), users);
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.USERS_PAGE);
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
