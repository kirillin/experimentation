package kz.epam.airline.transportService;

import java.io.IOException;
import java.util.LinkedList;

import kz.epam.airline.aircrafts.Aircraft;
import kz.epam.airline.utiles.QueriesAboutAircrafts;

/**  
 * 
 *  <br />The class represent some service which responsible for 
 *  all aircrafts of airline. 
 *  <br />Implements different methods for interacting with aircrafts.
 */
public class TransportService {
	private LinkedList<Aircraft>	listAircrafts;
	private QueriesAboutAircrafts 	queres;
	
	public TransportService() {
		this.listAircrafts = new LinkedList<Aircraft>();
		this.queres = new QueriesAboutAircrafts();
		this.listAircrafts = null;
	}

	/**
	 * Constructs a fleet aircrafts with defined qty. aircrafts.
	 * @throws IOException 
	 */ 
	public TransportService(LinkedList<Aircraft> list) throws IOException {
		this.listAircrafts = new LinkedList<Aircraft>();
		this.queres = new QueriesAboutAircrafts();
		this.listAircrafts = list;
	}
	
	public LinkedList<Aircraft> getListAircrafts() {
		return listAircrafts;
	}
	
	public void setListAircrafts(LinkedList<Aircraft> listAircrafts) {
		this.listAircrafts = listAircrafts;
	}
	/**
	 * Just print list aircrafts in airline.
	 */
	public void showAircrafts() {
		for (int i = 0; i < listAircrafts.size(); i++) {
			System.out.println(listAircrafts.get(i));			
		}
	}
	/**
	 * Print specified list aircrafts.
	 * @param list
	 */
	public void showAircrafts(LinkedList<Aircraft> list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));			
		}
	}
	/**
	 * Count total value for specified field.
	 * @param field 
	 * @return <i>String</i> contains total value
	 * for example for type String it will be list values 
	 * 				for type int it will be summ values
	 * @see DataOperations  
	 */
	public String getTotalForField(String field) {
//		return queres.getTotalForValue(listAircrafts, field);
		return null;
	}
	/**
	 * Finding aircrafts for specified field and range values.
	 * @param field
	 * @param from
	 * @param to
	 * 
	 * @see DataOperations
	 */
	public void findAircrafts(String field, float from, float to) {
//		showAircrafts(queres.findAircraft(
//						listAircrafts, field, from, to));
	}
	/**
	 * Creating new collecting and sets there sorted list of aircrafts
	 * for specified value 
	 * @see DataOperations
	 */
	public void sortAircrafts() {
		LinkedList<Aircraft> l = 
				queres.getSortedListOfAircrafts(listAircrafts);
		for (Aircraft list : l) {
			System.out.println(list);
		}
	}
}
