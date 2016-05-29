package com.epam.eshop.filter;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.eshop.controller.service_locator.ServiceLocator;
import com.epam.eshop.controller.services.UserService;
import com.epam.eshop.manager.ConfigurationManager;

/**
 * Filter provids safely access to different directories of the application.
 * 
 * @author Kirill Artemov
 * 
 */
public class AuthorizationFilter implements Filter {

	private static Logger logger = Logger.getLogger(AuthorizationFilter.class);
	private List<String> includeUrls = new LinkedList<String>();
	private List<String> excludeUrls = new LinkedList<String>();

	/**
	 * @see Filter#destroy()
	 */
	@Override
	public void destroy() {
	}

	/**
	 * Add to collections allows and denies directories
	 * 
	 * @see Filter#init(FilterConfig)
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		includeUrls.add("/");
		excludeUrls.add("/jspf");
		excludeUrls.add("/errors");
		excludeUrls.add("/Controller");
		// excludeUrls.add("/images");
		// includeUrls.add("/styles");
	}

	/**
	 * it must be divided
	 * 
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		String page = null;
		if (!(request instanceof HttpServletRequest)) {
			logger.debug("Terrible request!");
			throw new ServletException("Terrible request!");
		}
		if (!(response instanceof HttpServletResponse)) {
			logger.debug("Terrible response!");
			throw new ServletException("Terrible response!");
		}
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String path = httpRequest.getRequestURI().substring(
				httpRequest.getContextPath().length());
		// Cycle for legal URLs
		for (String includeUrl : includeUrls) {
			// if path in request begins for legal palaces
			if (path.startsWith(includeUrl)) {
				HttpSession session = ((HttpServletRequest) request)
						.getSession();
				// then see to collection of illegal places
				for (String excludeUrl : excludeUrls) {
					if (path.startsWith(excludeUrl)) {
						logger.debug("юзер пытается зайти, куда нельзя ему идти! Path: "
								+ excludeUrl);
						String command = (String) httpRequest
								.getParameter("command");
						if (command == null) {
							command = "null";
						}
						switch (command) {
						// if user wants to sing in then allow him it
						case "singin":
							chain.doFilter(request, response);
							return;
							// if user wants to register then allow him it
						case "singup":
							chain.doFilter(request, response);
							return;
						case "alterLocale":
							chain.doFilter(request, response);
							return;
							
						default:
							break;
						}
						// if user already is sing-in then check for entry
						// him in 'admin' or 'user' roles
						String login = (String) session.getAttribute("user");
						if (login != null) {
							UserService userService = (UserService) ServiceLocator
									.getService("userService");
							// if user is not admin then restricting him
							// access to admin functions
							if (!userService.isAdmin(login)) {
								page = ConfigurationManager
										.getInstance()
										.getProperty(
												ConfigurationManager.PROFILE_PAGE);
								switch (command) {
								case "getOrders":
									if (httpRequest.getParameter("user") == null) {
										httpResponse.sendRedirect("." + page);
										return;
									}
									break;

								case "getProducts":
									httpResponse.sendRedirect("." + page);
									return;

								case "getUsers":
									httpResponse.sendRedirect("." + page);
									return;

								case "getBlackList":
									httpResponse.sendRedirect("." + page);
									return;

								default:
									break;
								}
								chain.doFilter(request, response);
								return;
							} else {
								chain.doFilter(request, response);
							}
							return;
						}
						// if user is guest then redirect him to home page
						page = ConfigurationManager.getInstance().getProperty(
								ConfigurationManager.HOME_PAGE);
						httpResponse.sendRedirect("." + page);
						return;
					}
				}
				logger.debug("User is good user and go to the right reference "
						+ "but do not fully trust him!");
			}
		}
		chain.doFilter(request, response);
	}
}
