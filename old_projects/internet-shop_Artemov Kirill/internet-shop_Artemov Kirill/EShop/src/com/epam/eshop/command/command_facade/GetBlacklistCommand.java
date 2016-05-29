package com.epam.eshop.command.command_facade;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;

import org.omg.CORBA.Request;

import com.epam.eshop.controller.service_locator.ServiceLocator;
import com.epam.eshop.controller.services.BlacklistService;
import com.epam.eshop.controller.services.UserService;
import com.epam.eshop.lib.Validator;
import com.epam.eshop.manager.AttributesEnum;
import com.epam.eshop.manager.ConfigurationManager;
import com.epam.eshop.model.entities.BlacklistItem;
import com.epam.eshop.model.entities.User;
import com.epam.eshop.view.views.BlacklistView;

/**
 * I'm not racist!
 * 
 * @author Kirill Artemov
 * 
 */
public class GetBlacklistCommand implements Command {

	private final String DEL = "del";

	/**
	 * default constructor
	 */
	public GetBlacklistCommand() {
	}

	/**
	 * The method when processing {@link Request} and {@link Response} for
	 * getting blacklist.
	 * 
	 */
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String page = null;
		HttpSession session = request.getSession(true);
		List<BlacklistView> blackusers = new LinkedList<BlacklistView>();
		BlacklistService blacklistService = (BlacklistService) ServiceLocator
				.getService(AttributesEnum.BLACKLIST_SERVICE.toString());
		UserService userService = (UserService) ServiceLocator
				.getService(AttributesEnum.USER_SERVICE.toString());
		// if click reference for remove record
		session.removeAttribute(AttributesEnum.BLACKLIST_UPDATE.toString());
		if (request.getParameter(DEL) != null) {
			String userIdValue = Validator.stripHarmful(request
					.getParameter(DEL));
			int userId = -1;
			// Get user id
			if (Validator.isNumber(userIdValue)) {
				userId = Integer.parseInt(userIdValue);
			}
			if (userId != -1) {
				if (blacklistService.del(userId)) {
					session.setAttribute(
							AttributesEnum.BLACKLIST_UPDATE.toString(), true);
				} else {
					session.setAttribute(
							AttributesEnum.BLACKLIST_UPDATE.toString(), false);
				}
			}
		}
		List<BlacklistItem> blacklist = blacklistService.getBlackList();
		if (blacklist != null) {
			// Through the whole blacklist and for each id in blacklist
			// geting user from DB and adding it in new listView
			for (Iterator<BlacklistItem> iterator = blacklist.iterator(); iterator
					.hasNext();) {
				BlacklistItem blacklistItem = (BlacklistItem) iterator.next();
				int userId = blacklistItem.getUserId();
				User user = userService.getUser(userId);
				BlacklistView blacklistView = new BlacklistView();
				blacklistView.setId(user.getId());
				blacklistView.setLogin(user.getLogin());
				blacklistView.setEmail(user.getEmail());
				blackusers.add(blacklistView);
			}
			// add listView in session
			session.setAttribute(AttributesEnum.BLACK_USER.toString(), blackusers);
		} else {
			blackusers = null;
			session.setAttribute(AttributesEnum.BLACK_USER.toString(), blackusers);
		}
		// And anyway returned page with table blacklist
		page = ConfigurationManager.getInstance().getProperty(
				ConfigurationManager.BLACKLIST_PAGE);
		return page;
	}

}
