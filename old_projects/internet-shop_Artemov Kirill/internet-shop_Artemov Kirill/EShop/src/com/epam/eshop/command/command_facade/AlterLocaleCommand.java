package com.epam.eshop.command.command_facade;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.eshop.lib.Validator;
import com.epam.eshop.manager.AttributesEnum;
import com.epam.eshop.manager.ConfigurationManager;
import com.epam.eshop.manager.MessageManager;

/**
 * The class for changing locale
 * @author Kirill Artemov
 *
 */
public class AlterLocaleCommand implements Command {

	private final String LOCALE_PARAM = "locale";
	private final String RUS = "rus";
	private final String ENG = "eng";
	private final String KAZ = "kaz";

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String page = null;
		String langValue = request.getParameter(LOCALE_PARAM);
		if (langValue != null) {
			Locale locale = null;
			String lang = Validator.stripHarmful(langValue);
			switch (lang) {
			case RUS:
				locale = new Locale("ru", "RU");
				request.getSession().setAttribute("locale", locale);
				System.err.println("изменили на рус");
				break;
			case ENG:
				locale = new Locale("en", "US");
				request.getSession().setAttribute("locale", locale);
				break;
			case KAZ:
				locale = new Locale("kz", "KZ");
				request.getSession().setAttribute("locale", locale);
				break;

			default:
				break;
			}
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.HOME_PAGE);
		} else {
			request.setAttribute(
					AttributesEnum.ERROR_MESSAGE.toString(),
					MessageManager.getInstance().getProperty(
							MessageManager.NOT_FOUND));
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.ERROR_PAGE);
		}

		return page;
	}

}
