package kz.epam.airline.menu;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import kz.epam.airline.runner.Runner;

public class Menu {
	private static Logger menuLogger = Logger.getLogger(Menu.class);
	/**
	 * @param args
	 */
	public static void menu() {
		// 			/* START Block of choose language */
		ResourceBundle rb = ResourceBundle.getBundle("text");
		menuLogger.info(rb.getString("kz.epam.airline.runner.Runner.programStarted"));
		System.out.println(rb.getString("kz.epam.airline.runner.Runner.english"));
		System.out.println(rb.getString("kz.epam.airline.runner.Runner.russian"));
		System.out.println(rb.getString("kz.epam.airline.runner.Runner.kazakh"));
		System.out.print(rb.getString("kz.epam.airline.runner.Runner.chooseLacale"));

		String country = "";
		String language = "";
		char choose = 0;
		try {
			choose = (char) System.in.read();
		} catch (IOException e) {
			menuLogger.info(
					rb.getString("kz.epam.airline.runner.Runner.chosenLacaleWrong"));
			e.printStackTrace();
		}
		switch (choose) {
		case '1':
			country = Runner.conf.getString("kz.epam.airline.runner.Runner.COUNTRY_US");
			language = Runner.conf.getString("kz.epam.airline.runner.Runner.LANGUAGE_EN");
			break;

		case '2':
			country = Runner.conf.getString("kz.epam.airline.runner.Runner.COUNTRY_RU");
			language = Runner.conf.getString("kz.epam.airline.runner.Runner.LANGUAGE_RU");
			break;

		case '3':
			country = Runner.conf.getString("kz.epam.airline.runner.Runner.COUNTRY_KZ");
			language = Runner.conf.getString("kz.epam.airline.runner.Runner.LANGUAGE_KZ");
			break;

		default:
			country = rb.getLocale().getCountry();
			language = rb.getLocale().getLanguage();
			menuLogger.info(
					rb.getString("kz.epam.airline.runner.Runner.chosenLacaleWrong"));
			menuLogger.info(
					rb.getString("kz.epam.airline.runner.Runner.chosenLacale")
					+ rb.getLocale().getDisplayLanguage());
			break;
		}
		Locale locale = new Locale(language, country);
		Runner.iface = ResourceBundle.getBundle("text", locale);
		menuLogger.info(
				Runner.iface.getString("kz.epam.airline.runner.Runner.chosenLacale")
				+ locale.getDisplayLanguage(Runner.iface.getLocale()));
		/* END Block of choose language */

	}

}
