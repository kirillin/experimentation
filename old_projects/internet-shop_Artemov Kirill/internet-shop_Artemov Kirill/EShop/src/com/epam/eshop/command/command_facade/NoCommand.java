package com.epam.eshop.command.command_facade;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.eshop.manager.ConfigurationManager;
import com.epam.eshop.manager.MessageManager;

/**
 * The class for processing empty commands
 * 
 * @author Kirill Artemov
 * 
 */
public class NoCommand implements Command {

	private static Logger logger = Logger.getLogger(NoCommand.class);

	/**
	 * Default constructor
	 */
	public NoCommand() {
	}

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String page = ConfigurationManager.getInstance().getProperty(
				ConfigurationManager.HOME_PAGE);
		logger.trace(MessageManager.getInstance().getProperty(
				MessageManager.NO_COMMAND));
		return page;
	}

}
