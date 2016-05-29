package kz.epam.airline.utiles.parsers;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import kz.epam.airline.aircrafts.Aircraft;
import kz.epam.airline.runner.Runner;
import kz.epam.airline.utiles.parsers.handlers.SAXHandler;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

public class SAXParser {
	private static Logger SAXPrsr = Logger.getLogger(SAXParser.class);
	
	public static LinkedList<Aircraft> parse(File file) {
		SAXPrsr.info(Runner.iface.getString("kz.epam.airline.utiles.parsers.SAXParser.SAXStarted"));
		/* SAX parser */
        javax.xml.parsers.SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setValidating(false);
        javax.xml.parsers.SAXParser sp = null;
		try {
			sp = spf.newSAXParser();
		} catch (ParserConfigurationException e) {
			SAXPrsr.error(Runner.iface.getString("kz.epam.airline.utiles.parsers.SAXParser.SAXConfig") + e.getMessage());
		} catch (SAXException e) {
		}
        SAXHandler handler = new SAXHandler();
        try {
			sp.parse(file, handler);
		} catch (SAXException e) {
			SAXPrsr.error(Runner.iface.getString("kz.epam.airline.utiles.parsers.SAXParser.SAXConfig") + e.getMessage());
		} catch (IOException e) {
			SAXPrsr.error(Runner.iface.getString("kz.epam.airline.utiles.parsers.SAXParser.SAXIO") + e.getMessage());
		}
        LinkedList<Aircraft> aircrafts = handler.getPlans();
		SAXPrsr.info(Runner.iface.getString("kz.epam.airline.utiles.parsers.SAXParser.SAXComplete"));
		return aircrafts;
	}
}
