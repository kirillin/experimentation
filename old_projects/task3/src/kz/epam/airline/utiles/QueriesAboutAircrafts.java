package kz.epam.airline.utiles;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import kz.epam.airline.aircrafts.Aircraft;
import kz.epam.airline.aircrafts.classC.ClassC;
import kz.epam.airline.aircrafts.classC.PassengerAirplane;
import kz.epam.airline.aircrafts.classC.TransportAirplane;
import kz.epam.airline.utiles.sort.SortByRange;

public class QueriesAboutAircrafts {

	public QueriesAboutAircrafts(){}	

	/**
	 * Returns a new String that contains a total value for the field. 
	 * @param list	List of Aircrafts.
	 * @param value	Field for count total.
	 * @return String contains sum values for field.
	 * <i><br />Returns String because fields can be both text and numeric.
	 * <br />For example: 
	 * <br />model may be returns as list of all models in airline.
	 * <br />and total maxSpeed may be returns only as one number</i>
	 *
	 */
	public String getTotalForValue(LinkedList<Aircraft> list, String field) {
		StringBuffer 	total = new StringBuffer();
		float 			fTemp = 0.0F;
		int 			iTemp = 0;
		
		switch (field) {
		case "model":
			for (int i = 0; i < list.size(); i++) {
				// here is the total list of models
				total.append(list.get(i).getModel());
				total.append("\n");
			}
			break;

		case "maxSpeed":
			for (int i = 0; i < list.size(); i++) {
				// here is the total sum for maxSpeed field 
				// and etc for each field
				iTemp += list.get(i).getMaxSpeed();
			}
			total.append(iTemp);			
			break;

		case "range":
			for (int i = 0; i < list.size(); i++) {
				iTemp += list.get(i).getRange();
			}
			total.append(iTemp);			
			break;

		case "mass":
			for (int i = 0; i < list.size(); i++) {
				fTemp += list.get(i).getMass();
			}
			total.append(fTemp);			
			break;

		case "maxTakeoffWeight":
			for (int i = 0; i < list.size(); i++) {
				fTemp += list.get(i).getMaxTakeoffWeight();
			}
			total.append(fTemp);			
			break;

		case "altitude":
			for (int i = 0; i < list.size(); i++) {
				iTemp += list.get(i).getAltitude();
			}
			total.append(iTemp);			
			break;

		case "crew":
			for (int i = 0; i < list.size(); i++) {
				iTemp += list.get(i).getCrew();
			}
			total.append(iTemp);			
			break;

		case "fuelConsumption":
			for (int i = 0; i < list.size(); i++) {
				fTemp += list.get(i).getFuelConsumption();
			}
			total.append(fTemp);			
			break;
			
		case "wingspan":
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) instanceof ClassC) {
					fTemp += ( (ClassC) list.get(i)).getWingspan();
				}
			}
			total.append(fTemp);			
			break;

		case "takeoffDistance":
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) instanceof ClassC) {
					iTemp += ((ClassC) list.get(i)).getTakeoffDistance();
				}
			}			
			total.append(fTemp);			
			break;
			
		case "quantityEngines":
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) instanceof ClassC) {
					iTemp += ((ClassC) list.get(i)).getQuantityEngines();
				}
			}			
			total.append(fTemp);			
			break;

		case "typeOfEngenes":
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) instanceof PassengerAirplane) {
					total.append( ((PassengerAirplane) list.get(i))
							.getTypeOfEngene());
					total.append("\n");					
				}
			}			
			total.append(fTemp);			
			break;
			
		case "passengersCapacity":
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) instanceof PassengerAirplane) {
					iTemp += ((PassengerAirplane) list.get(i))
								.getPassengersCapacity();
				}
			}			
			total.append(fTemp);			
			break;
			
		case "flightAttendant":
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) instanceof PassengerAirplane) {
					iTemp += ((PassengerAirplane) list.get(i))
								.getFlightAttendant();
				}
			}			
			total.append(fTemp);			
			break;			
			
		case "maxVolumePayload":
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) instanceof TransportAirplane) {
					fTemp += ((TransportAirplane) list.get(i))
								.getMaxVolumePayload();
				}
			}			
			total.append(fTemp);			
			break;	
			
		case "payloadCapacity":
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) instanceof TransportAirplane) {
					fTemp += ((TransportAirplane) list.get(i))
								.getPayloadCapacity();
				}
			}			
			total.append(fTemp);			
			break;				

		default:
			System.out.println("Wrong value format!");			
			break;
		}
		return total.toString();
	}
	/**
	 * Finding aircraft for specified field and range values. 
	 * @param list
	 * @param field
	 * @param from
	 * @param to
	 * @return ArrayList contains aircrafts
	 */
	public LinkedList<Aircraft> findAircraft(LinkedList<Aircraft> list, 
			String field, float from, float to) {

		float 				tempParam = 0;
		int 				sizeFleet = list.size();
		LinkedList<Aircraft> aircrafts = new LinkedList<Aircraft>();
		
		switch (field) {
		case "maxSpeed":
			for (int i = 0; i < sizeFleet; i++) {
				tempParam = list.get(i).getMaxSpeed();
				if ( (tempParam >= from) && (tempParam <= to) ) {
					aircrafts.add(list.get(i));
				}				
			}
			break;

		case "range":
			for (int i = 0; i < sizeFleet; i++) {
				tempParam = list.get(i).getRange();
				if ( (tempParam >= from) && (tempParam <= to) ) {
					aircrafts.add(list.get(i));
				}				
			}
			break;

		case "mass":
			for (int i = 0; i < sizeFleet; i++) {
				tempParam = list.get(i).getMass();
				if ( (tempParam >= from) && (tempParam <= to) ) {
					aircrafts.add(list.get(i));
				}				
			}
			break;

		case "maxTakeoffWeight":
			for (int i = 0; i < sizeFleet; i++) {
				tempParam = list.get(i).getMaxTakeoffWeight();
				if ( (tempParam >= from) && (tempParam <= to) ) {
					aircrafts.add(list.get(i));
				}				
			}
			break;

		case "altitude":
			for (int i = 0; i < sizeFleet; i++) {
				tempParam = list.get(i).getAltitude();
				if ( (tempParam >= from) && (tempParam <= to) ) {
					aircrafts.add(list.get(i));
				}				
			}
			break;

		case "crew":
			for (int i = 0; i < sizeFleet; i++) {
				tempParam = list.get(i).getCrew();
				if ( (tempParam >= from) && (tempParam <= to) ) {
					aircrafts.add(list.get(i));
				}				
			}
			break;

		case "fuelConsumption":
			for (int i = 0; i < sizeFleet; i++) {
				tempParam = list.get(i).getFuelConsumption();
				if ( (tempParam >= from) && (tempParam <= to) ) {
					aircrafts.add(list.get(i));
				}				
			}
			break;
			
		case "wingspan":
			for (int i = 0; i < sizeFleet; i++) {
				if (list.get(i) instanceof ClassC){
					tempParam = ((ClassC) list.get(i)).getWingspan();
				}
				if ( (tempParam >= from) && (tempParam <= to) ) {
					aircrafts.add(list.get(i));
				}				
			}
			break;

		case "takeoffDistance":
			for (int i = 0; i < sizeFleet; i++) {
				if (list.get(i) instanceof ClassC){				
					tempParam = ((ClassC) list.get(i)).getTakeoffDistance();
				}
				if ( (tempParam >= from) && (tempParam <= to) ) {
					aircrafts.add(list.get(i));
				}				
			}
			break;
			
		case "quantityEngines":
			for (int i = 0; i < sizeFleet; i++) {
				if (list.get(i) instanceof ClassC){
					tempParam = ((ClassC) list.get(i)).getQuantityEngines();
				}
				if ( (tempParam >= from) && (tempParam <= to) ) {
					aircrafts.add(list.get(i));
				}				
			}
			break;

		case "typeOfEngenes":
			for (int i = 0; i < sizeFleet; i++) {
				if (list.get(i) instanceof PassengerAirplane){
					tempParam = ((PassengerAirplane) list.get(i))
										.getQuantityEngines();
				}
				if ( (tempParam >= from) && (tempParam <= to) ) {
					aircrafts.add(list.get(i));
				}				
			}
			break;
			
		case "passengersCapacity":
			for (int i = 0; i < sizeFleet; i++) {
				if (list.get(i) instanceof PassengerAirplane){
					tempParam = ((PassengerAirplane) list.get(i))
										.getPassengersCapacity();
				}
				if ( (tempParam >= from) && (tempParam <= to) ) {
					aircrafts.add(list.get(i));
				}				
			}
			break;
			
		case "flightAttendant":
			for (int i = 0; i < sizeFleet; i++) {
				if (list.get(i) instanceof PassengerAirplane){
					tempParam = ((PassengerAirplane) list.get(i))
										.getFlightAttendant();
				}
				if ( (tempParam >= from) && (tempParam <= to) ) {
					aircrafts.add(list.get(i));
				}				
			}
			break;			
			
		case "maxVolumePayload":
			for (int i = 0; i < sizeFleet; i++) {
				if (list.get(i) instanceof TransportAirplane){
					tempParam = ((TransportAirplane) list.get(i))
										.getMaxVolumePayload();
				}
				if ( (tempParam >= from) && (tempParam <= to) ) {
					aircrafts.add(list.get(i));
				}				
			}
			break;	
			
		case "payloadCapacity":
			for (int i = 0; i < sizeFleet; i++) {
				if (list.get(i) instanceof TransportAirplane){
					tempParam = ((TransportAirplane) list.get(i))
										.getPayloadCapacity();
				}
				if ( (tempParam >= from) && (tempParam <= to) ) {
					aircrafts.add(list.get(i));
				}				
			}
			break;				

		default:
			System.out.println("Wrong input format!");			
			break;
		}
		return aircrafts;
	}
	/**
	 * Sorting list aircrafts for specified field
	 * @param list
	 * @return ArrayList contains sorted aircrafts.
	 */
	public LinkedList<Aircraft> getSortedListOfAircrafts(
			LinkedList<Aircraft> list) {
		LinkedList<Aircraft> sortedAircrafts = list;
		// At this location can be realized to choice field for sorting 
		// in perspective
		Comparator<Aircraft> cpr = new SortByRange();
		Collections.sort(sortedAircrafts, cpr);		
		return sortedAircrafts;
	}
	
}