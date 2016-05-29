package kz.epam.airline.aircrafts;

import kz.epam.airline.utiles.validate.Validating;

/**
 * 
 * @author Artemov Kirill
 * @author student 3 course
 * 
 * <p> The class represents abstract aircraft 
 * 	with collection specific properties. </p>
 * <p> Class hierarchy composed according to classification 
 * 	of Federation Aeronautique Internationale (FAI)
 * <a href="http://en.wikipedia.org/wiki/F%C3%A9d%C3%A9ration
 * _A%C3%A9ronautique_Internationale">FAI (wikipedia.org)</a>
 * </p>
 * 
 */
public abstract class Aircraft {
	private String id; 
	private String model;
	private String title;
	private String emailOfOrigin;
	private String telephonNumber;	
	private int maxSpeed;
	private int range;
	private float mass;	
	private float maxTakeoffWeight;	
	private int altitude;
	private int crew;
	private float fuelConsumption;	
	
	public enum TypeAircraft {
		PASSENGER_ARIPLANE, 
		TRANSPORT_AIRPLANE
	}

	/**
	 *  Initializes a newly created Aircraft object so that 
	 *  it represents a default values.
	 */
	public Aircraft(){}
	/**
	 * The constructor for initialization values of params aircrafts. 
	 * @param aModel
	 * <br /> Model of aircraft. Name of company and serial.
	 * <br /> For example "Boing 747"
	 * @param aMaxSpeed
	 * <br /> The maximum speed of the aircraft.
	 * @param aRange
	 * <br /> Range distance
	 * @param aMass
	 * <br /> Mass unloaded aircraft. 
	 * @param aMaxTakeoffWeight
	 * <br /> The maximum weight which can lift.
	 * @param aAltitude
	 * <br /> The maximum height which can fly.
	 * @param aCrew
	 * <br /> Required quantity people in crew
	 * @param aFuelConsumption
	 * <br /> Fuel consumption l/km (liters per kilometer)
	 * 
	 */
	public Aircraft(String id,
					String aModel,
					String title,
					String email,
					String telephone,
					int aMaxSpeed,
					int aRange,
					float aMass,
					float aMaxTakeoffWeight,
					int aAltitude,
					int aCrew,
					float aFuelConsumption) {
		if (Validating.verify(id)) {		
			this.id = id;
		}
		if (Validating.verify(aModel)) {		
			this.model = aModel;
		}
		if (Validating.verify(title)) {		
			this.title = title;
		}
		if (Validating.verify(email)) {		
			this.emailOfOrigin = email;
		}
		if (Validating.verify(telephone)) {		
			this.telephonNumber = telephone;
		}
		if (Validating.verify(aMaxSpeed)) {
			this.maxSpeed = aMaxSpeed;
		}
		if (Validating.verify(aRange)) {		
			this.range = aRange;
		}
		if (Validating.verify(aMass)) {
			this.mass = aMass;
		}
		if (Validating.verify(aMaxTakeoffWeight)) {		
			this.maxTakeoffWeight = aMaxTakeoffWeight;
		}
		if (Validating.verify(aAltitude)) {		
			this.altitude = aAltitude;
		}
		if (Validating.verify(aCrew)) {		
			this.crew = aCrew;
		}
		if (Validating.verify(aFuelConsumption)) {		
			this.fuelConsumption = aFuelConsumption;
		}
	}
	/**
	 * Override method hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + altitude;
		result = prime * result + crew;
		result = prime * result + Float.floatToIntBits(fuelConsumption);
		result = prime * result + Float.floatToIntBits(mass);
		result = prime * result + maxSpeed;
		result = prime * result + Float.floatToIntBits(maxTakeoffWeight);
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + range;
		return result;
	}
	/**
	 * Override method equals()
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aircraft other = (Aircraft) obj;
		if (altitude != other.altitude)
			return false;
		if (crew != other.crew)
			return false;
		if (Float.floatToIntBits(fuelConsumption) != Float
				.floatToIntBits(other.fuelConsumption))
			return false;
		if (Float.floatToIntBits(mass) != Float.floatToIntBits(other.mass))
			return false;
		if (maxSpeed != other.maxSpeed)
			return false;
		if (Float.floatToIntBits(maxTakeoffWeight) != Float
				.floatToIntBits(other.maxTakeoffWeight))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (range != other.range)
			return false;
		return true;
	}
	/**
	 * Override method toString()
	 */
	@Override
	public String toString() {
		
		return "\nModel = " + model + 
				"\n\tMax Speed = " + maxSpeed
				+"\n\tRange = " + range + 
				"\n\tMass = " + mass + 
				"\n\tMax Takeoff Weight = " + maxTakeoffWeight + 
				"\n\tAltitude = " + altitude + 
				"\n\tCrew = " + crew + 
				"\n\tFuel Consumption = " + fuelConsumption;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * Returns the String value of the field Model.
	 * @return model - model of aircraft.
	 */
	public String getModel() {
		return model;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getEmailOfOrigin() {
		return emailOfOrigin;
	}
	public void setEmailOfOrigin(String emailOfOrigin) {
		this.emailOfOrigin = emailOfOrigin;
	}
	public String getTelephonNumber() {
		return telephonNumber;
	}
	public void setTelephonNumber(String telephonNumber) {
		this.telephonNumber = telephonNumber;
	}
	/**
	 * Sets the value for the field <b>model</b>.
	 * @param model - model of aircraft.
	 */
	public void setModel(String model) {
		if (Validating.verify(model)) {
			this.model = model;			
		}
	}
	/**
	 * Returns the Int value of the field <b>maxSpeed</b>.
	 * @return maxSpeed - maximum speed of aircraft.
	 */
	public int getMaxSpeed() {
		return maxSpeed;
	}
	/**
	 * Sets the value for the field maxSpeed.
	 * @param maxSpeed - maximum speed of aircraft.
	 */
	public void setMaxSpeed(int maxSpeed) {
		if (Validating.verify(maxSpeed)) {
			this.maxSpeed = maxSpeed;
		}
	}
	/**
	 * Returns Int values of the field range.
	 * @return range - range of aircraft.
	 */
	public int getRange() {
		return range;
	}
	/**
	 * Sets range of aircraft.
	 * @param range - range of aircraft.
	 */
	public void setRange(int range) {
		if (Validating.verify(range)) {	
			this.range = range;
		}
	}
	/**
	 * Returns Float value of the field mass.
	 * @return mass - mass of aircraft.
	 */
	public float getMass() {
		return mass;
	}
	/**
	 * Sets mass of aircraft.
	 * @param mass - mass of aircraft.
	 */
	public void setMass(float mass) {
		if (Validating.verify(mass)) {
			this.mass = mass;
		}
	}
	/**
	 * Returns Maximum take-off weight of aircraft.
	 * @return maxTakeoffWeight
	 */
	public float getMaxTakeoffWeight() {
		return maxTakeoffWeight;
	}
	/**
	 * Sets maximum take-off weight of aircraft.
	 * @param maxTakeoffWeight
	 */
	public void setMaxTakeoffWeight(float maxTakeoffWeight) {
		if (Validating.verify(maxTakeoffWeight)) {
			this.maxTakeoffWeight = maxTakeoffWeight;
		}
	}
	/**
	 * Returns altitude of aircraft.
	 * @return altitude
	 */
	public int getAltitude() {
		return altitude;
	}
	/**
	 * Sets altitude of aircraft.
	 * @param altitude
	 */
	public void setAltitude(int altitude) {
		if (Validating.verify(altitude)) {		
			this.altitude = altitude;
		}
	}
	/**
	 * Returns crew of aircraft.
	 * @return crew
	 */
	public int getCrew() {
		return crew;
	}
	/**
	 * Sets crew of aircraft.
	 * @param crew
	 */
	public void setCrew(int crew) {
		if (Validating.verify(crew)) {
			this.crew = crew;
		}
	}
	/**
	 * Returns fuel consumption of aircraft.
	 * @return
	 */
	public float getFuelConsumption() {
		return fuelConsumption;
	}
	/**
	 * Sets fuel consumption of aircraft.
	 * @param fuelConsumption
	 */
	public void setFuelConsumption(float fuelConsumption) {
		if (Validating.verify(fuelConsumption)) {
			this.fuelConsumption = fuelConsumption;
		}
	}
}

