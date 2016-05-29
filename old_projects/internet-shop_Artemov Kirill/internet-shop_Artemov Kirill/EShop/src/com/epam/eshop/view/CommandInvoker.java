package com.epam.eshop.view;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.eshop.command.command_facade.AddProductCommand;
import com.epam.eshop.command.command_facade.AddToCartCommand;
import com.epam.eshop.command.command_facade.AlterLocaleCommand;
import com.epam.eshop.command.command_facade.CheckoutCommand;
import com.epam.eshop.command.command_facade.Command;
import com.epam.eshop.command.command_facade.GetBlacklistCommand;
import com.epam.eshop.command.command_facade.GetCartCommand;
import com.epam.eshop.command.command_facade.GetFormDataOrderCommand;
import com.epam.eshop.command.command_facade.GetOrdersCommand;
import com.epam.eshop.command.command_facade.GetProductsCommand;
import com.epam.eshop.command.command_facade.GetUserCommand;
import com.epam.eshop.command.command_facade.GetUsersCommand;
import com.epam.eshop.command.command_facade.NoCommand;
import com.epam.eshop.command.command_facade.SinginCommand;
import com.epam.eshop.command.command_facade.SingupCommand;
import com.epam.eshop.command.command_facade.UpdateUserCommand;
import com.epam.eshop.manager.AttributesEnum;
import com.epam.eshop.manager.MessageManager;

/**
 * 
 * <br />
 * Class contains all actions which can come from Servlet. Implemented in
 * accordance with the Singleton pattern.
 * 
 * @author Kirill Artemov
 * 
 */
public class CommandInvoker {

	/**
	 * <br />
	 * Static variable instance by this class
	 */
	private static CommandInvoker instance;
	private static Logger logger = Logger.getLogger(CommandInvoker.class);
	/**
	 * <br />
	 * The HashMap for 'name of command'-command mapping.
	 */
	private HashMap<String, Command> commands;

	/**
	 * <br />
	 * Private constructor for initialization HashMap with prepared values.
	 */
	private CommandInvoker() {
		commands = new HashMap<String, Command>();
		commands.put(AttributesEnum.SING_IN.toString(), new SinginCommand());
		commands.put(AttributesEnum.SING_UP.toString(), new SingupCommand());
		commands.put(AttributesEnum.GET_CATALOG.toString(), new GetProductsCommand());
		commands.put(AttributesEnum.GET_PRODUCTS.toString(), new GetProductsCommand());
		commands.put(AttributesEnum.ADD_PRODUCT.toString(), new AddProductCommand());		
		commands.put(AttributesEnum.GET_CART.toString(), new GetCartCommand());
		commands.put(AttributesEnum.GET_USER.toString(), new GetUserCommand());
		commands.put(AttributesEnum.GET_USERS.toString(), new GetUsersCommand());
		commands.put(AttributesEnum.GET_BLACKLIST.toString(), new GetBlacklistCommand());
		commands.put(AttributesEnum.GET_FORM_DATA.toString(), new GetFormDataOrderCommand());
		commands.put(AttributesEnum.CHECKOUT.toString(), new CheckoutCommand());
		commands.put(AttributesEnum.GET_ORDERS.toString(), new GetOrdersCommand());
		commands.put(AttributesEnum.ADD_TO_CART.toString(), new AddToCartCommand());
		commands.put(AttributesEnum.UPDATE_USER.toString(), new UpdateUserCommand());
		commands.put(AttributesEnum.ALTER_LOCALE.toString(), new AlterLocaleCommand());
//		commands.put("setOrder", new GetProductsCommand());

		logger.trace(MessageManager.getInstance().getProperty(
				MessageManager.MAP_INIT));
	}

	/**
	 * <br />
	 * Method provides value for the command from HashMap
	 * 
	 * @param request
	 *            - HttpServletRequest object
	 * @return a Command
	 */
	public Command getCommand(HttpServletRequest request) {
		String action = request.getParameter("command");
		logger.trace(action + " action");
		Command command = commands.get(action);
		if (command == null) {
			command = new NoCommand();
		}
		return command;
	}

	/**
	 * <br />
	 * Method provides to invoke method of concrete Command Class
	 * 'execute(HttpServletRequest request, HttpServletResponse response)'
	 * 
	 * @param request
	 *            - HttpServletRequest object
	 * @param response
	 *            - HttpServletResponse object
	 * @return String contains page for forwarding by Servlet.
	 * @throws IOException 
	 * @throws ServletException 
	 */
	public String invoke(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String page = null;
		Command action = this.getCommand(request);
		page = action.execute(request, response);
		logger.trace(page);
		return page;
	}

	/**
	 * <br />
	 * Gets CommandInvoker
	 * 
	 * @return a CommandInvoker
	 * @throws IOException 
	 */
	public static CommandInvoker getInstance() {
		if (instance == null) {
			instance = new CommandInvoker();
		}
		return instance;
	}
}
