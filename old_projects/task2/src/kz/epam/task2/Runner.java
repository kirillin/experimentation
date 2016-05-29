package kz.epam.task2;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Locale;
import java.util.ResourceBundle;

import kz.epam.task2.exception.FileNameError;
import kz.epam.task2.problems.MyProblem;
import kz.epam.task2.text.unit.Text;
import kz.epam.task2.utils.AnalizeText;
import kz.epam.task2.utils.IOFile;
import kz.epam.task2.utils.Regex;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Runner class contains menu and other acts with program.
 * 
 * @author Artemov Kirill
 * @author student 3 course
 *
 */
public class Runner {
	public static final String PATH_LOG4J = ".\\resources\\log4j.property";
	private static Logger runnerLogger = Logger.getLogger(Runner.class);
	public static ResourceBundle iface;

	public static void main(String[] args) {
		PropertyConfigurator.configure(PATH_LOG4J);
		ResourceBundle conf = ResourceBundle.getBundle("config");		
		/* START Block of choose language */
		ResourceBundle rb = ResourceBundle.getBundle("text");
		runnerLogger.info(rb.getString("kz.epam.task2.Runner.programStarted"));
		System.out.println(rb.getString("kz.epam.task2.Runner.english"));
		System.out.println(rb.getString("kz.epam.task2.Runner.russian"));
		System.out.println(rb.getString("kz.epam.task2.Runner.kazakh"));
		System.out.print(rb.getString("kz.epam.task2.Runner.chooseLacale"));

		String country = "";
		String language = "";
		char choose = 0;
		try {
			choose = (char) System.in.read();
		} catch (IOException e) {
			runnerLogger.info(
					rb.getString("kz.epam.task2.Runner.chosenLacaleWrong"));
			e.printStackTrace();
		}
		switch (choose) {
		case '1':
			country = conf.getString("kz.epam.task2.Runner.COUNTRY_US");
			language = conf.getString("kz.epam.task2.Runner.LANGUAGE_EN");
			break;

		case '2':
			country = conf.getString("kz.epam.task2.Runner.COUNTRY_RU");
			language = conf.getString("kz.epam.task2.Runner.LANGUAGE_RU");
			break;

		case '3':
			country = conf.getString("kz.epam.task2.Runner.COUNTRY_KZ");
			language = conf.getString("kz.epam.task2.Runner.LANGUAGE_KZ");
			break;

		default:
			System.out.println(rb.getString("kz.epam.task2.Runner.chosenLacaleWrong"));
			country = rb.getLocale().getCountry();
			language = rb.getLocale().getLanguage();
			runnerLogger.info(
					rb.getString("kz.epam.task2.Runner.chosenLacaleWrong"));
			runnerLogger.info(
					rb.getString("kz.epam.task2.Runner.chosenLacale")
					+ rb.getLocale().getDisplayLanguage());
			break;
		}
		Locale locale = new Locale(language, country);
		iface = ResourceBundle.getBundle("text", locale);
		System.out.println(
				iface.getString("kz.epam.task2.Runner.chosenLacale")
				+ locale.getDisplayLanguage(iface.getLocale()));
		runnerLogger.info(
				iface.getString("kz.epam.task2.Runner.chosenLacale")
				+ locale.getDisplayLanguage(iface.getLocale()));
		/* END Block of choose language */

		String name_file_input = 
				conf.getString("kz.epam.task2.Runner.NAME_INPUT_FILE");
		String name_file_output_collected = 
				conf.getString("kz.epam.task2.Runner.NAME_OUTPUT_FILE_COLLECT");
		String name_file_output_problem = 
			conf.getString(
				"kz.epam.task2.Runner.NAME_OUTPUT_FILE_SOLVED_PROBLEM");
		String name_file_output_afer_clone = 
			conf.getString(
				"kz.epam.task2.Runner.NAME_OUTPUT_FILE_COLLECT_AFTER_CLONE");
		
		/* ************************************************************* */
		/* copy all lines from file to list */
		LinkedList<String> listLines = new LinkedList<String>();
		try {
			listLines = IOFile.readTextFromFile(name_file_input);
		} catch (FileNameError e1) {
			runnerLogger.error(Runner.iface
					.getString("kz.epam.task2.IOFile.FileNameError"));
		}
		runnerLogger.info(Runner.iface
				.getString("kz.epam.task2.Runner.ParseStart"));
		System.out.println(Runner.iface
				.getString("kz.epam.task2.Runner.ParseStart"));
		
		/* ************************************************************* */
		Text text = new Text();
		// Start constructor for init regex expressions :)
		new Regex();
		// Parse all given text to Text objects
		AnalizeText.parse(text, listLines.toString(), 3);
		runnerLogger.info(Runner.iface
				.getString("kz.epam.task2.Runner.ParseComplete"));
		System.out.println(Runner.iface
				.getString("kz.epam.task2.Runner.ParseComplete"));
		runnerLogger.info(Runner.iface
				.getString("kz.epam.task2.Runner.CollectText"));
		System.out.println(Runner.iface
				.getString("kz.epam.task2.Runner.CollectText"));
		// Collect fromText object to String text
		String output =	AnalizeText.collect(text, 3);
		try {
			IOFile.writeTextToFile(name_file_output_collected, output);
//			IOFile.writeTextToFile(null, output);
		} catch (FileNameError e) {
			System.out.println(Runner.iface
					.getString("kz.epam.task2.IOFile.FileNameError"));
			runnerLogger.error(Runner.iface
					.getString("kz.epam.task2.IOFile.FileNameError"));
		}

		/* ************************************************************* */
		/* For solution problem to clone Text */ 
		Text t = text.clone();
		if (t !=null) {
			runnerLogger.info(Runner.iface
					.getString("kz.epam.task2.Runner.CloneTextObj"));
			System.out.println(Runner.iface
					.getString("kz.epam.task2.Runner.CloneTextObj"));
			runnerLogger.info(Runner.iface
					.getString("kz.epam.task2.Runner.SolvingProblem"));
			System.out.println(Runner.iface
					.getString("kz.epam.task2.Runner.SolvingProblem"));
			// Doing somthing!
			MyProblem.solveProblem(t);
			runnerLogger.info(Runner.iface
					.getString("kz.epam.task2.Runner.ProblemSolved"));
			System.out.println(Runner.iface
					.getString("kz.epam.task2.Runner.ProblemSolved"));
			runnerLogger.info(Runner.iface
					.getString("kz.epam.task2.Runner.CollectText"));
			System.out.println(Runner.iface
					.getString("kz.epam.task2.Runner.CollectText"));
			String outputnew =	AnalizeText.collect(t, 3);
			try {
				IOFile.writeTextToFile(name_file_output_problem, outputnew);
			} catch (FileNameError e) {
				System.out.println(Runner.iface
						.getString("kz.epam.task2.IOFile.FileNameError"));
				runnerLogger.error(Runner.iface
						.getString("kz.epam.task2.IOFile.FileNameError"));
			}

		} else { 
			System.out.println(Runner.iface
				.getString("kz.epam.task2.Runner.CloneError"));
			runnerLogger.info(Runner.iface
				.getString("kz.epam.task2.Runner.CloneError"));
		}
		/* ************************************************************* */
		/* Collected Text after clone and write it to new file */		
		System.out.println(Runner.iface
			.getString("kz.epam.task2.Runner.CollectOriginalTextAfterClone"));
		runnerLogger.info(Runner.iface
			.getString("kz.epam.task2.Runner.CollectOriginalTextAfterClone"));
		String output_after_clone =	AnalizeText.collect(text, 3);
		try {
			IOFile.writeTextToFile(name_file_output_afer_clone, output_after_clone);
		} catch (FileNameError e) {
			System.out.println(Runner.iface
					.getString("kz.epam.task2.Runner.CloneError"));
				runnerLogger.error(Runner.iface
					.getString("kz.epam.task2.Runner.CloneError"));
		}
		System.out.println(Runner.iface
				.getString("kz.epam.task2.Runner.programClosed"));
			runnerLogger.info(Runner.iface
				.getString("kz.epam.task2.Runner.programClosed"));
	}
}
