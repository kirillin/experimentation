package kz.epam.airline.utiles.parsers.handlers;

import java.io.IOException;
import java.util.LinkedList;

import kz.epam.airline.aircrafts.Aircraft;
import kz.epam.airline.aircrafts.Aircraft.TypeAircraft;
import kz.epam.airline.aircrafts.classC.ClassC.TypeEngine;
import kz.epam.airline.aircrafts.classC.PassengerAirplane;
import kz.epam.airline.aircrafts.classC.TransportAirplane;
import kz.epam.airline.runner.Runner;

import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class DOMHandler {
	private static Logger DOMHndlr = Logger.getLogger(DOMHandler.class);

    public static LinkedList<Aircraft> listBuilder(Element root)
            throws SAXException, IOException {
        LinkedList<Aircraft> aircrafts = new LinkedList<Aircraft>();
        NodeList aircraftNodes = root.getElementsByTagName("aircraft");
		DOMHndlr.debug(Runner.iface.getString("kz.epam.airline.utiles.parsers.handlers.DOMHandler.parseStarted"));
        for (int i = 0; i < aircraftNodes.getLength(); i++) {
        	Element aircraftElement = (Element) aircraftNodes.item(i);
        	String id = aircraftElement.getAttribute("id");
        	String model = aircraftElement.getAttribute("model");
        	String typeOfAircraft = aircraftElement.getAttribute("typeOfAircraft");
        	TypeAircraft type = TypeAircraft.valueOf(typeOfAircraft);
        	Element elementOrigin = getBaby(aircraftElement, "Origin");
        	String title = getBabyValue(elementOrigin, "title");
        	String emailOfOrigin = getBabyValue(elementOrigin, "emailOfOrigin");
        	String telephonNumber = getBabyValue(elementOrigin, "telephonNumber");
        	int maxSpeed = Integer.parseInt(getBabyValue(aircraftElement, "maxSpeed"));
        	int range = Integer.parseInt(getBabyValue(aircraftElement, "range"));
        	float mass = Float.parseFloat(getBabyValue(aircraftElement, "mass"));
        	float maxTakeoffWeight = Float.parseFloat(getBabyValue(aircraftElement, "maxTakeoffWeight"));
        	int altitude = Integer.parseInt(getBabyValue(aircraftElement, "altitude"));
        	int crew = Integer.parseInt(getBabyValue(aircraftElement, "crew"));
        	float fuelConsumption = Float.parseFloat(getBabyValue(aircraftElement, "fuelConsumption"));
        	float wingspan = Float.parseFloat(getBabyValue(aircraftElement, "wingspan"));
        	int takeoffDistance = Integer.parseInt(getBabyValue(aircraftElement, "takeoffDistance"));        	
        	int quantityEngines = Integer.parseInt(getBabyValue(aircraftElement, "quantityEngines"));
        	String typeEngines = getBabyValue(aircraftElement, "typeOfEngines");
        	TypeEngine typeOfEngines = TypeEngine.valueOf(typeEngines);
        	Aircraft aircraft = null;
        	switch (type) {
			case PASSENGER_ARIPLANE:
	        	int passengersCapacity = Integer.parseInt(getBabyValue(aircraftElement, "passengersCapacity"));        	
	        	int flightAttendant = Integer.parseInt(getBabyValue(aircraftElement, "flightAttendant"));
				aircraft = new PassengerAirplane(id, model, title,
						emailOfOrigin, telephonNumber, maxSpeed, range, mass,
						maxTakeoffWeight, altitude, crew, fuelConsumption,
						wingspan, takeoffDistance, quantityEngines,
						typeOfEngines, passengersCapacity, flightAttendant);
				break;
				
			case TRANSPORT_AIRPLANE:
	        	float maxVolumePayload = Float.parseFloat(getBabyValue(aircraftElement, "maxVolumePayload"));
	        	int payloadCapacity = Integer.parseInt(getBabyValue(aircraftElement, "payloadCapacity"));
				aircraft = new TransportAirplane(id, model, title,
						emailOfOrigin, telephonNumber, maxSpeed, range, mass,
						maxTakeoffWeight, altitude, crew, fuelConsumption,
						wingspan, takeoffDistance, quantityEngines,
						typeOfEngines, maxVolumePayload, payloadCapacity);
				break;
			default:

				break;			
			}
            aircrafts.add(aircraft);        	
		}
        DOMHndlr.debug(Runner.iface.getString("kz.epam.airline.utiles.parsers.handlers.DOMHandler.parseEnded"));
        return aircrafts;
    }

    private static Element getBaby(Element parent, String childName) {
        NodeList nlist = parent.getElementsByTagName(childName);
        Element child = (Element) nlist.item(0);
        DOMHndlr.debug(Runner.iface.getString("kz.epam.airline.utiles.parsers.handlers.DOMHandler.newElement") + child.getNodeName() );
        return child;
    }

    private static String getBabyValue(Element parent, String childName) {
        Element child = getBaby(parent, childName);
        Node node = child.getFirstChild();
        String value = node.getNodeValue();
        return value;
    }

}
