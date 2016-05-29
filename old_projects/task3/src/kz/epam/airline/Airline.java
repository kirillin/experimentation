package kz.epam.airline;

import java.io.IOException;

import kz.epam.airline.transportService.TransportService;
import kz.epam.airline.utiles.validate.Validating;


/**
 *
 * @author Artemov Kirill
 * @author student 3 course
 * 
 * <p> The class Airlines represents airline with includes 
 * transport service as well as other services in perspective </p>
 */
public class Airline {
	private String title;
	private String headquarters;
	private int fleetSize; 
	private TransportService transportService;
	
	/** 
	 *  
	 *  Constructor with two parameters
	 * @param aTitle title of our airlines
	 * @param aHeadquarters  city, where is situated main office of company
	 * @throws IOException 
	 */
	public Airline(String aTitle, String aHeadquarters) {	
		this.title = aTitle;
		this.headquarters = aHeadquarters;
		this.fleetSize = 0;
		this.transportService = new TransportService();
	}
	/** 
	 *  
	 *  Constructor with tree parameters
	 * @param aTitle Title of our airlines
	 * @param aHeadquarters  City where is situated main office of company
	 * @param aFleetSize Size of fleet aircrafts
	 * @throws IOException 
	 */
	public Airline(String aTitle, String aHeadquarters, int aFleetSize ) {	
		if (Validating.verify(aTitle)){
			this.title = aTitle;
		}
		if (Validating.verify(aHeadquarters)){		
			this.headquarters = aHeadquarters;
		}
		this.transportService = new TransportService();
		this.fleetSize = 0;
	}
	
	public TransportService getTransportService() {
		return transportService;
	}

	public void setTransportService(TransportService transportService) {
		this.transportService = transportService;
	}
	/** 
	 * Write title of airlines
	 * @return 
	 * Title of airlines 
	 */
	public String getTitle()
	{
		return this.title;
	}
	/**
	 * Sets title of airline.
	 * @param title Title of airline.
	 */
	public void setTitle(String title) {
		if (Validating.verify(title)) {
			this.title = title;
		}
	}
	/**
	 * Write main office of airlines 
	 * @return headquarters 
	 * Main office of airlines
	 */
	public String getHeadquarters()
	{
		return this.headquarters;
	}
	/**
	 * Sets title of main office.
	 * @param headquarters Title of main office.
	 */
	public void setHeadquarters(String headquarters) {
		if (Validating.verify(headquarters)) {
			this.headquarters = headquarters;
		}
	}
	/**
	 * Write size fleet of airlines
	 * @return fleetSize 
	 * Size of fleet aircrafts 
	 */
	public int getFleetSize()
	{
		return this.fleetSize;
	}
	/**
	 * Sets fleet size of airline.
	 * @param fleetSize Fleet size of airline.
	 */
	public void setFleetSize(int fleetSize) {
		if (Validating.verify(fleetSize)) {
			this.fleetSize = fleetSize;
		}
	}
}
