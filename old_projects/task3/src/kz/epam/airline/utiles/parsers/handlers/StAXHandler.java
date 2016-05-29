package kz.epam.airline.utiles.parsers.handlers;

import java.io.InputStream;
import java.util.LinkedList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import kz.epam.airline.aircrafts.Aircraft;
import kz.epam.airline.aircrafts.Aircraft.TypeAircraft;
import kz.epam.airline.aircrafts.classC.ClassC;
import kz.epam.airline.aircrafts.classC.ClassC.TypeEngine;
import kz.epam.airline.aircrafts.classC.PassengerAirplane;
import kz.epam.airline.aircrafts.classC.TransportAirplane;
import kz.epam.airline.runner.Runner;
import kz.epam.airline.utiles.parsers.handlers.enums.AircraftsEnum;

import org.apache.log4j.Logger;

public class StAXHandler {
	private static Logger StAXHndlr = Logger.getLogger(StAXHandler.class);
	private LinkedList<Aircraft> listAircrafts;
	private int currentAircraft;
    String text;
    
	public StAXHandler() {
		listAircrafts = null;
        currentAircraft = 0;
        text = null;
	}
    public LinkedList<Aircraft> parse(InputStream input) {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        try {
            XMLStreamReader reader = inputFactory.createXMLStreamReader(input);
            process(reader);
        } catch (XMLStreamException e) {
        	StAXHndlr.debug(Runner.iface.getString("kz.epam.airline.utiles.parsers.handlers.StAXHandler.XMLStreamError") + e.getMessage());
        }
		return listAircrafts;
    }
    
    private void process(XMLStreamReader reader) throws XMLStreamException {
        String startTag = null;
        String endTag = null;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    startTag = reader.getLocalName();
                    StAXHndlr.debug(Runner.iface.getString("kz.epam.airline.utiles.parsers.handlers.StAXHandler.newElement") + startTag);
                    AircraftsEnum enumName = AircraftsEnum.valueOf(startTag.toUpperCase());
                    aircraftsInfoHandle(enumName, reader);
                    break;
                case XMLStreamConstants.END_ELEMENT:
                	endTag = reader.getLocalName();
                	AircraftsEnum enume = AircraftsEnum.valueOf(endTag.toUpperCase());
                	aircraftsInfoHandle(enume, reader);
                	break;
                case XMLStreamConstants.CHARACTERS:
               		text = reader.getText().trim();
                    break;
            }
        }
    }

    private void aircraftsInfoHandle(AircraftsEnum enumName, XMLStreamReader reader) {
        switch (enumName) {
            case AIRCRAFTS:
            	if (reader.isStartElement()) {
                    listAircrafts = new LinkedList<Aircraft>();    
                	StAXHndlr.debug(Runner.iface.getString("kz.epam.airline.utiles.parsers.handlers.StAXHandler.parseStarted"));
					
            	} else if (reader.isEndElement()) {
                	StAXHndlr.debug(Runner.iface.getString("kz.epam.airline.utiles.parsers.handlers.StAXHandler.parseEnded"));            		
            	}
                break;
                
            case AIRCRAFT:
            	if (reader.isStartElement()) {
	                String id = reader.getAttributeValue(null, "id");
	                String model = reader.getAttributeValue(null, "model");
	                String type = reader.getAttributeValue(null, "typeOfAircraft");
	            	TypeAircraft choice = TypeAircraft.valueOf(type);
	            	switch (choice) {
	    			case PASSENGER_ARIPLANE:
	    				PassengerAirplane passengerAirplane = new PassengerAirplane();
	    				StAXHndlr.debug(Runner.iface.getString("kz.epam.airline.utiles.parsers.handlers.SAXHandler.passengerAirplaneCreated"));
	    				passengerAirplane.setId(id);
	    				passengerAirplane.setModel(model);
	    				listAircrafts.add(passengerAirplane);
	    				break;
	    				
	    			case TRANSPORT_AIRPLANE:
	    				TransportAirplane transportAirplane = new TransportAirplane();
	    				StAXHndlr.debug(Runner.iface.getString("kz.epam.airline.utiles.parsers.handlers.SAXHandler.transportAirplaneCreated"));
	    				transportAirplane.setId(id);
	    				transportAirplane.setModel(model);
	    				listAircrafts.add(transportAirplane);
	    				break;
	           	
	    			default:
	    				StAXHndlr.debug(Runner.iface.getString("kz.epam.airline.utiles.parsers.handlers.SAXHandler.typeEntityError"));
	    				break;
	    			}
            	} else if (reader.isEndElement()) {
            		currentAircraft++;
            	}
            	break;

            case ORIGIN:
            	break;
            	
            case TITLE:
            	if (reader.isEndElement()) {
        			listAircrafts.get(currentAircraft).setTitle(text);
				}
            	break;

            case EMAILOFORIGIN:
            	if (reader.isEndElement()) {
	    			listAircrafts.get(currentAircraft).setEmailOfOrigin(text);
            	}
            	break;
            	
            case TELEPHONNUMBER:
            	if (reader.isEndElement()) {
	    			listAircrafts.get(currentAircraft).setTelephonNumber(text);
            	}
            	break;
            	
            case MAXSPEED:
            	if (reader.isEndElement()) {
	               	listAircrafts.get(currentAircraft).setMaxSpeed(Integer.parseInt(text));
            	}
            	break;
            case RANGE:
            	if (reader.isEndElement()) {
            		listAircrafts.get(currentAircraft).setRange(Integer.parseInt(text));
            	}
            	break;
            case MASS:
            	if (reader.isEndElement()) {
            		listAircrafts.get(currentAircraft).setMass(Float.parseFloat(text));
            	}
            	break;
            case MAXTAKEOFFWEIGHT:
            	if (reader.isEndElement()) {            	
            		listAircrafts.get(currentAircraft).setMaxTakeoffWeight(Float.parseFloat(text));
            	}
            	break;
            case ALTITUDE:
            	if (reader.isEndElement()) {
            		listAircrafts.get(currentAircraft).setAltitude(Integer.parseInt(text));
            	}
            	break;
            case CREW:
            	if (reader.isEndElement()) {
            		listAircrafts.get(currentAircraft).setCrew(Integer.parseInt(text));
            	}
            	break;
            case FUELCONSUMPTION:
            	if (reader.isEndElement()) {
            		listAircrafts.get(currentAircraft).setFuelConsumption(Float.parseFloat(text));
            	}
            	break;        	
            	
            case WINGSPAN:
            	if (reader.isEndElement()) {
            		((ClassC) listAircrafts.get(currentAircraft)).setWingspan(Float.parseFloat(text));
            	}
            	break;        	
            case TAKEOFFDISTANCE:
            	if (reader.isEndElement()) {
            		((ClassC) listAircrafts.get(currentAircraft)).setTakeoffDistance(Integer.parseInt(text));
            	}
            	break;        	
            case QUANTITYENGINES:
            	if (reader.isEndElement()) {            	
            		((ClassC) listAircrafts.get(currentAircraft)).setQuantityEngines(Integer.parseInt(text));
            	}
            	break;        	
            case TYPEOFENGINES:
            	if (reader.isEndElement()) {
            		((ClassC) listAircrafts.get(currentAircraft)).setTypeOfEngene(TypeEngine.valueOf(text));
            	}
            	break;        	
            	
            	
            case PASSENGERSCAPACITY:
            	if (reader.isEndElement()) {
            		((PassengerAirplane) listAircrafts.get(currentAircraft)).setPassengersCapacity(Integer.parseInt(text));
            	}
            	break;   
            case FLIGHTATTENDANT:
            	if (reader.isEndElement()) {
            		((PassengerAirplane) listAircrafts.get(currentAircraft)).setFlightAttendant(Integer.parseInt(text));
            	}
            	break;   
            	
            case MAXVOLUMEPAYLOAD:
            	if (reader.isEndElement()) {
            		((TransportAirplane) listAircrafts.get(currentAircraft)).setMaxVolumePayload(Float.parseFloat(text));
            	}
            	break;   
            case PAYLOADCAPACITY:
            	if (reader.isEndElement()) {
            		((TransportAirplane) listAircrafts.get(currentAircraft)).setPayloadCapacity(Integer.parseInt(text));
            	}
            	break;
		default:
			StAXHndlr.error(Runner.iface.getString("kz.epam.airline.utiles.parsers.handlers.StAXHandler.elementNotFound"));
			break;   
            	
        }
    }

	
}
