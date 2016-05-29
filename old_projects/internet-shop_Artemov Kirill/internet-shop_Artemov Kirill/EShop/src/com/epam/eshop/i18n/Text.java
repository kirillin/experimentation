package com.epam.eshop.i18n;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

/**
 * Class for i18n. If in property file ololo=Hello, World! than ${text['ololo']}
 * returns Hello, World!
 * 
 * @author Kirill Artemov
 * 
 */
public class Text extends ResourceBundle {

	/** the parameter = bundle name of .properties files */
	private static final String TEXT_ATTRIBUTE_NAME = "text";
	/** path where location .properties files */
	private static final String TEXT_BASE_NAME = "text";

	private Text(Locale locale) {
		setLocale(locale);
	}

	/**
	 * Sets to request session attribute 'text' with locale for browser
	 * 
	 * @param request
	 */
	public static void setFor(HttpServletRequest request) {
		if (request.getSession().getAttribute(TEXT_ATTRIBUTE_NAME) == null) {
			request.getSession().setAttribute(TEXT_ATTRIBUTE_NAME,
//					new Text(request.getLocale()));
					new Text((Locale) request.getSession().getAttribute("locale")));
		}
		request.getSession().setAttribute(TEXT_ATTRIBUTE_NAME,
//				new Text(request.getLocale()));
				new Text((Locale) request.getSession().getAttribute("locale")));
	}

	/**
	 * 
	 * @param request
	 * @return instance of this class
	 */
	public static Text getCurrentInstance(HttpServletRequest request) {
		return (Text) request.getSession().getAttribute(TEXT_ATTRIBUTE_NAME);
	}

	/**
	 * Sets locale in ResourceBundle
	 * 
	 * @param locale
	 */
	public void setLocale(Locale locale) {
		if (parent == null || !parent.getLocale().equals(locale)) {
			setParent(getBundle(TEXT_BASE_NAME, locale));
		}
	}

	/**
	 * return keys from property file
	 */
	@Override
	public Enumeration<String> getKeys() {
		return parent.getKeys();
	}

	/**
	 * return value from property file for key
	 */
	@Override
	protected Object handleGetObject(String key) {
		return parent.getObject(key);
	}
}