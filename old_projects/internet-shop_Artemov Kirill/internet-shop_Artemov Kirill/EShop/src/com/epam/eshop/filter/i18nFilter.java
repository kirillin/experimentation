package com.epam.eshop.filter;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.epam.eshop.i18n.Text;

public class i18nFilter implements Filter {
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

	}

	/**
	 * Sets to request and response encoding
	 * 
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (((HttpServletRequest) request).getSession().getAttribute("locale") == null) {
			Locale locale = Locale.getDefault();	
			((HttpServletRequest) request).getSession().setAttribute("locale", locale);
			System.err.println("pltcm");
		}
		Text.setFor((HttpServletRequest) request);
		chain.doFilter(request, response);
	}

}
