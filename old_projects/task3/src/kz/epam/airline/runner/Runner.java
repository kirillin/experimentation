package kz.epam.airline.runner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.xml.parsers.ParserConfigurationException;

import kz.epam.airline.Airline;
import kz.epam.airline.aircrafts.Aircraft;
import kz.epam.airline.menu.Menu;
import kz.epam.airline.transportService.TransportService;
import kz.epam.airline.utiles.parsers.DOMParser;
import kz.epam.airline.utiles.parsers.SAXParser;
import kz.epam.airline.utiles.parsers.StAXParser;
import kz.epam.airline.utiles.validate.XMLValidation;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.xml.sax.SAXException;

public class Runner {
	public static final String PATH_LOG4J = ".\\resources\\log4j.property";
	private static Logger runnerLogger = Logger.getLogger(Runner.class);
	public static ResourceBundle iface;
	public static ResourceBundle conf;
	
	/**
	 * The class for visualization worked with airline
	 * @param
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */

	public static void main(String[] args){
			PropertyConfigurator.configure(PATH_LOG4J);
			//configuration file for locale and input files
			conf = ResourceBundle.getBundle("config");
			//printing menu and choose local
			Menu.menu();
			// initializes files XSD and XML
			String NAME_XSD = conf.getString("kz.epam.airline.runner.Runner.NAME_FILE_XMLSchema");
			String NAME_XML = conf.getString("kz.epam.airline.runner.Runner.NAME_FILE_XMLDocument");
			
			LinkedList<Aircraft> listAircrafts = null;
			if (XMLValidation.validate(NAME_XSD, NAME_XML)) {

				LinkedList<Aircraft> listOfSAX = SAXParser.parse(new File(NAME_XML));
//				System.out.println(listOfSAX.toString());
				
				LinkedList<Aircraft> listOfDOM = DOMParser.parse(new File(NAME_XML));
//				System.out.println(listOfDOM.toString());
				LinkedList<Aircraft> listOfStAX = StAXParser.parse(new File(NAME_XML));
//				System.out.println(listOfStAX.toString());
				
				/**
				 *  Here may be change the parser  
				 *	Default is SAX parser! 
				 */
				listAircrafts = listOfSAX;
			
				Airline airline = new Airline("AirlinesKZ", "Karaganda");
				TransportService transportservice = airline.getTransportService();
				transportservice.setListAircrafts(listAircrafts);
				
				System.out.println("********* All aircrafts in airline **********");
				transportservice.showAircrafts();
				
				System.out.println("\n********* Total value fuel consumprion **********");		
				System.out.printf("Total fuel consumption for all aircrafts " +
						"in airline: %s l/km\n",
						transportservice.getTotalForField("fuelConsumption"));
		
				System.out.println("\n********* list aircrafts **********");	
				System.out.println("********* fuel consumption is" +
									" range from 5 to 10 **********");		
				System.out.println("The list of aircrafts having " +
						"fuel consumption in range for 5 to 10:");
				transportservice.findAircrafts("fuelConsumption", 5, 10);
				
				System.out.println("\n********* Sorting for range **********");		
				System.out.println("Sorting list of aircrafts by range:");
				transportservice.sortAircrafts();
			} else {
				runnerLogger.error(iface.getString("kz.epam.airline.runner.Runner.notValid"));
			}
	}
}
