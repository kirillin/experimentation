package com.epam.eshop.command.command_facade;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

import org.omg.CORBA.Request;

/**
 * Interface for all commands
 * 
 * @author Kirill Artemov
 * 
 */
public interface Command {

	/**
	 * The method when get {@link Request} and {@link Response} and processing
	 * it accordance itself functions
	 * 
	 * @param request
	 * @param response
	 * @return page where user go after different actions
	 * @throws ServletException
	 * @throws IOException
	 */
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException;
}
