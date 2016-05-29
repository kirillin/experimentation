package kz.epam.airline.utiles.parsers.handlers;

import java.util.LinkedList;

import kz.epam.airline.aircrafts.Aircraft;
import kz.epam.airline.aircrafts.Aircraft.TypeAircraft;
import kz.epam.airline.aircrafts.classC.ClassC;
import kz.epam.airline.aircrafts.classC.ClassC.TypeEngine;
import kz.epam.airline.aircrafts.classC.PassengerAirplane;
import kz.epam.airline.aircrafts.classC.TransportAirplane;
import kz.epam.airline.aircrafts.classE.ClassE;
import kz.epam.airline.runner.Runner;

import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;


public class SAXHandler extends DefaultHandler {
	private static Logger SAXHndlr = Logger.getLogger(SAXHandler.class);
	private StringBuffer acc = new StringBuffer();
	private LinkedList<Aircraft> listAircrafts;
	private int currentAircraft;

	public SAXHandler() {
		currentAircraft = 0;
	}

	@Override
	public void startDocument() throws SAXException {
		listAircrafts = new LinkedList<Aircraft>();
		SAXHndlr.debug(Runner.iface.getString("kz.epam.airline.utiles.parsers.handlers.SAXHandler.parseStarted"));
		super.startDocument();
	}

	@Override
	public void endDocument() throws SAXException {
		SAXHndlr.debug(Runner.iface.getString("kz.epam.airline.utiles.parsers.handlers.SAXHandler.parseEnded"));
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        acc.setLength(0);
        SAXHndlr.debug(Runner.iface.getString("kz.epam.airline.utiles.parsers.handlers.SAXHandler.newElement") + qName);
        if (qName.equals("aircraft")) {
        	String id = attributes.getValue("id");
        	String model = attributes.getValue("model");
        	String type = attributes.getValue("typeOfAircraft");
        	TypeAircraft choice = TypeAircraft.valueOf(type);
        	switch (choice) {
			case PASSENGER_ARIPLANE:
				PassengerAirplane passengerAirplane = new PassengerAirplane();
				passengerAirplane.setId(id);
				passengerAirplane.setModel(model);
				listAircrafts.add(passengerAirplane);
				break;
				
			case TRANSPORT_AIRPLANE:
				TransportAirplane transportAirplane = new TransportAirplane();
				transportAirplane.setId(id);
				transportAirplane.setModel(model);
				listAircrafts.add(transportAirplane);
				break;
				
			default:
				SAXHndlr.debug(Runner.iface.getString("kz.epam.airline.utiles.parsers.handlers.SAXHandler.typeEntityError"));
				break;
			}
        }
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (qName.equals("aircrafts")) {
		} else		
		if (qName.equals("title")) {
			listAircrafts.get(currentAircraft).setTitle(acc.toString());
		} else if(qName.equals("emailOfOrigin")) {
			listAircrafts.get(currentAircraft).setEmailOfOrigin(acc.toString());
		} else if(qName.equals("telephonNumber")) {
			listAircrafts.get(currentAircraft).setTelephonNumber(acc.toString());
		} else if(qName.equals("maxSpeed")) {
			listAircrafts.get(currentAircraft).setMaxSpeed(Integer.parseInt(acc.toString()));
		} else if(qName.equals("range")) {
			listAircrafts.get(currentAircraft).setRange(Integer.parseInt(acc.toString()));
		} else if(qName.equals("mass")) {
			listAircrafts.get(currentAircraft).setMass(Float.parseFloat(acc.toString()));
		} else if(qName.equals("maxTakeoffWeight")) {
			listAircrafts.get(currentAircraft).setMaxTakeoffWeight(Float.parseFloat(acc.toString()));
		} else if(qName.equals("altitude")) {
			listAircrafts.get(currentAircraft).setAltitude(Integer.parseInt(acc.toString()));
		} else if(qName.equals("crew")) {
			listAircrafts.get(currentAircraft).setCrew(Integer.parseInt(acc.toString()));
		} else if(qName.equals("fuelConsumption")) {
			listAircrafts.get(currentAircraft).setFuelConsumption(Float.parseFloat(acc.toString()));
		} else	
			// if class C of airplanes
			if (listAircrafts.get(currentAircraft) instanceof ClassC) {
				if(qName.equals("wingspan")) {
				((ClassC) listAircrafts.get(currentAircraft)).setWingspan(Float.parseFloat(acc.toString()));
				} else if(qName.equals("takeoffDistance")) {
					((ClassC) listAircrafts.get(currentAircraft)).setTakeoffDistance(Integer.parseInt(acc.toString()));
				} else if(qName.equals("quantityEngines")) {
					((ClassC) listAircrafts.get(currentAircraft)).setQuantityEngines(Integer.parseInt(acc.toString()));
				} else if(qName.equals("typeOfEngines")) {
					((ClassC) listAircrafts.get(currentAircraft)).setTypeOfEngene(TypeEngine.valueOf(acc.toString()));
				} else	
						// if passenger airplane
						if (listAircrafts.get(currentAircraft).getClass() == PassengerAirplane.class) {
							if(qName.equals("passengersCapacity")) {
								((PassengerAirplane) listAircrafts.get(currentAircraft)).setPassengersCapacity(Integer.parseInt(acc.toString()));
							} else if(qName.equals("flightAttendant")) {
								((PassengerAirplane) listAircrafts.get(currentAircraft)).setFlightAttendant(Integer.parseInt(acc.toString()));
							}
						// if transport airplane
						} else if (listAircrafts.get(currentAircraft).getClass() == TransportAirplane.class) {
							if(qName.equals("maxVolumePayload")) {
								((TransportAirplane) listAircrafts.get(currentAircraft)).setMaxVolumePayload(Float.parseFloat(acc.toString()));
							} else if(qName.equals("payloadCapacity")) {
								((TransportAirplane) listAircrafts.get(currentAircraft)).setPayloadCapacity(Integer.parseInt(acc.toString()));
							}
						}
			// if class E (and may be others classes)
			} else if (listAircrafts.get(currentAircraft) instanceof ClassE) {
				// no realization, because no time :) 
			} 
		if (qName.equals("aircraft")) {
        	currentAircraft++;
		}		

	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
        acc.append(ch, start, length);	
    }

	@Override
	public void warning(SAXParseException e) throws SAXException {
		SAXHndlr.warn(Runner.iface.getString("kz.epam.airline.utiles.parsers.handlers.SAXHandler.SAXWar") + e.getLineNumber() + ": " + e.getMessage());
	}

	@Override
	public void error(SAXParseException e) throws SAXException {
		SAXHndlr.error(Runner.iface.getString("kz.epam.airline.utiles.parsers.handlers.SAXHandler.SAXErr") + e.getLineNumber() + ": " + e.getMessage());
	}

	@Override
	public void fatalError(SAXParseException e) throws SAXException {
		SAXHndlr.fatal(Runner.iface.getString("kz.epam.airline.utiles.parsers.handlers.SAXHandler.SAXFatal") + e.getLineNumber() + ": " + e.getMessage());
        throw (e);
	}
	
	public LinkedList<Aircraft> getPlans() {
		return listAircrafts;
	}
}

