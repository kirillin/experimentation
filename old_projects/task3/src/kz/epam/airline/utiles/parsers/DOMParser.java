package kz.epam.airline.utiles.parsers;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import kz.epam.airline.aircrafts.Aircraft;
import kz.epam.airline.runner.Runner;
import kz.epam.airline.utiles.parsers.handlers.DOMHandler;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class DOMParser {
	private static Logger DOMPrsr = Logger.getLogger(DOMParser.class);
	
	public static LinkedList<Aircraft> parse(File file) {
		LinkedList<Aircraft> aircrafts = null;
		try {
	        DOMPrsr.info(Runner.iface.getString("kz.epam.airline.utiles.parsers.DOMParser.DOMStarted"));
		    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		    DocumentBuilder db;
			db = dbf.newDocumentBuilder();
		    Document document = db.parse(file);
		    Element root = document.getDocumentElement();
		    aircrafts = DOMHandler.listBuilder(root);
	        DOMPrsr.info(Runner.iface.getString("kz.epam.airline.utiles.parsers.DOMParser.DOMEnded"));
		} catch (ParserConfigurationException e) {
	        DOMPrsr.error(Runner.iface.getString("kz.epam.airline.utiles.parsers.DOMParser.DOMConfig") + e.getMessage());
		} catch (SAXException e) {
	        DOMPrsr.error(Runner.iface.getString("kz.epam.airline.utiles.parsers.DOMParser.SAXException") + e.getMessage());
		} catch (IOException e) {
	        DOMPrsr.error(Runner.iface.getString("kz.epam.airline.utiles.parsers.DOMParser.DOMIO") + e.getMessage());
		}
        return aircrafts;
	}
}
