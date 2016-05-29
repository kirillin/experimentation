package com.epam.eshop.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet Filter implementation class EncodingFilter Implement setting TRUE
 * encoding for request and response.
 * 
 */
public class EncodingFilter implements Filter {

	private static Logger logger = Logger.getLogger(EncodingFilter.class);
	private String encoding;

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * Gets encoding from param-name "encoding" web.xml or if there is not it
	 * then default "UTF-8"
	 * 
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		if (filterConfig != null) {
			encoding = filterConfig.getInitParameter("encoding");
			if (encoding == null) {
				encoding = "UTF-8";
			}
		}
	}

	/**
	 * Sets to request and response encoding
	 * 
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding(encoding);
		response.setCharacterEncoding(encoding);
		logger.debug("Encoding filter perform its mission!");
		chain.doFilter(request, response);
	}
}
