package com.epam.eshop.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.eshop.manager.MessageManager;

/**
 * <br />
 * Provides realization methods doGet(), doPost() and processRequest().
 * 
 * @author Kirill Artemov
 * 
 */
public class Controller extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(Controller.class);

	/**
	 * <br />
	 * Call super class.
	 */
	public Controller() {
		super();
//		PropertyConfigurator.configure(ConfigurationManager.getInstance()
//				.getProperty(ConfigurationManager.LOG4J_PROPERTY_PATH));
	}

	/**
	 * <br />
	 * Called by the server to allow a servlet to handle a GET request. <br />
	 * After call of the doGet() it call to processRequest() for process
	 * request.
	 * 
	 * @see javax.servlet.HttpServlet
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");		
		processRequest(request, response);
	}

	/**
	 * Called by the server to allow a servlet to handle a GET request.
	 * 
	 * @see javax.servlet.HttpServlet
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");
		processRequest(request, response);
	}

	/**
	 * <br />
	 * Call CommandInvoket's method invoke() and sent request and response to
	 * it. <br />
	 * invoke() returns path to the page on which Controller forwards the user.
	 * 
	 * @param request
	 *            an {@link HttpServletRequest} object that contains the request
	 *            the client has made of the servlet
	 * 
	 * @param response
	 *            an {@link HttpServletResponse} object that contains the
	 *            response the servlet sends to the client
	 * 
	 * @exception IOException
	 *                if an input or output error is detected when the servlet
	 *                handles the request
	 * 
	 * @exception ServletException
	 *                if the request for the POST could not be handled
	 */
	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) {
		String page = null;
		boolean isTrowable = false;
		if (request.getParameter("throwable") != null) {
			isTrowable = request.getParameter("throwable").equals("yes");
		}
		if (!isTrowable) {
			CommandInvoker commandInvoker = CommandInvoker.getInstance();
			// Sent request to CommandInvoker
			RequestDispatcher dispatcher = null;				
			try {
				page = commandInvoker.invoke(request, response);			
				logger.trace(page);
				dispatcher = getServletContext().getRequestDispatcher(page);
				dispatcher.forward(request, response);
			} catch (ServletException e) {
				logger.fatal(MessageManager.getInstance().getProperty(
						MessageManager.SERVLET_EXCEPTION_ERROR_MESSAGE));
			} catch (IOException e) {
				logger.fatal(MessageManager.getInstance().getProperty(
						MessageManager.IO_EXCEPTION_ERROR_MESSAGE));
			}
		} else {
			processThrow(request, response);
		}
	}
	
	private void processThrow(HttpServletRequest request,
			HttpServletResponse response) {
		  Throwable throwable = (Throwable)
				  request.getAttribute("javax.servlet.error.exception");
	      Integer statusCode = (Integer)
	    		  request.getAttribute("javax.servlet.error.status_code");
	      String requestUri = (String)
	    		  request.getAttribute("javax.servlet.error.request_uri");
	      if (requestUri == null){
	         requestUri = "Unknown";
	      }
		response.setContentType("text/html");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			if ((throwable == null) && (statusCode == null)) {
			     out.println("<h2>Error information is missing</h2>");
			     out.println("Please return to the <a href=\"" + 
			    		 	response.encodeURL("http://localhost:8080/EShop") + 
			    		 		"\">Home Page</a>.");
			} else if (statusCode != null) {
				out.println("<h2>The status code : " + statusCode + "</h2>");
				out.println("<h2>Error information</h2>");
				out.println("</br></br>");
				out.println("Exception Type : " + 
								throwable.getClass( ).getName( ) + 
									"</br></br>");
				out.println("The request URI: " + requestUri + 
								"<br><br>");
				out.println("The exception message: " + 
								throwable.getMessage( ));
			  }	
			out.flush();
		} catch (IOException e) {
			
		} finally {
			out.close();
		}
	}

}
