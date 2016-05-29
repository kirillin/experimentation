package kz.epam.airline.utiles.parsers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.LinkedList;

import kz.epam.airline.aircrafts.Aircraft;
import kz.epam.airline.runner.Runner;
import kz.epam.airline.utiles.parsers.handlers.StAXHandler;

import org.apache.log4j.Logger;


public class StAXParser {
	private static Logger StAXPrsr = Logger.getLogger(StAXParser.class);
	
	public static LinkedList<Aircraft> parse(File file) {
		StAXPrsr.info(Runner.iface.getString("kz.epam.airline.utiles.parsers.StAXParser.StAXStarted"));
        StAXHandler parser = new StAXHandler();
        InputStream input = null;
		try {
			input = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			StAXPrsr.error(Runner.iface.getString("kz.epam.airline.runner.Runner.handlers.StAXHandler.fileNotFound") + e.getMessage());
		}
        LinkedList<Aircraft> aircrafts = parser.parse(input);
		StAXPrsr.info(Runner.iface.getString("kz.epam.airline.utiles.parsers.StAXParser.StAXEnded"));
		return aircrafts;
    }
}
