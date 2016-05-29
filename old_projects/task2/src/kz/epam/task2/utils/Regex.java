package kz.epam.task2.utils;

import java.util.LinkedList;
import java.util.ResourceBundle;

import kz.epam.task2.Runner;

import org.apache.log4j.Logger;

/**
 * Class to separate parts of the text to the levels.
 * It is necessary for analize of text.
 * 
 * @author Artemov Kirill
 * @author student 3 course
 *
 */
public class Regex {
	private static Logger regexLogger = Logger.getLogger(Regex.class);
	private static LinkedList<String> arlist;

	/**
	 * Each regex for unit text separated to levels in List from 0 to 3.
	 */
	public Regex() {
		Regex.arlist = new LinkedList<String>();
		ResourceBundle conf = ResourceBundle.getBundle("config");
		Regex.arlist.add(0, 
				conf.getString("kz.epam.task2.Regex.REG_LETTERS"));
		Regex.arlist.add(1,
				conf.getString("kz.epam.task2.Regex.REG_PARTS_OF_SENTENCE"));
		Regex.arlist
				.add(2, conf.getString("kz.epam.task2.Regex.REG_SENTENCES"));
		Regex.arlist
				.add(3, conf.getString("kz.epam.task2.Regex.REG_PARAGRAPH"));
		regexLogger.info(Runner.iface
				.getString("kz.epam.task2.Regex.initRegexComplete"));
	}
	/**
	 * Returns regex for required level
	 * @param level
	 * @return <i>String</i> regex
	 */
	public static String getRegex(int level) {
		return Regex.arlist.get(level).toString();
	}

	public static String getRegWord() {
		ResourceBundle conf = ResourceBundle.getBundle("config");
		return conf.getString("kz.epam.task2.Regex.REG_WORD");
	}
}
