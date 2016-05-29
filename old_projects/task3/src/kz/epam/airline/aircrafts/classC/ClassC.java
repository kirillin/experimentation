package kz.epam.airline.aircrafts.classC;

import kz.epam.airline.aircrafts.Aircraft;
import kz.epam.airline.utiles.validate.Validating;

/**
 * 
 * <p>
 * All aircrafts having fixed-wing are Class C
 * </p>
 * <p>
 * Class hierarchy composed according to classification of Federation
 * Aeronautique Internationale (FAI) <a
 * href="http://en.wikipedia.org/wiki/F%C3%A9d%C3%A9ration
 * _A%C3%A9ronautique_Internationale">FAI (wikipedia.org)</a>
 * </p>
 * <br />
 * It inherits from Aircraft properties
 * 
 */
public abstract class ClassC extends Aircraft {
	private float wingspan;
	private int takeoffDistance;
	private int quantityEngines;
	private TypeEngine typeOfEngene;

	public enum TypeEngine {
		PISTON, TURBOFUN, TURBOJET, JET
	}

	/**
	 * Default constructor.
	 */
	public ClassC() {
	}

	/**
	 * 
	 * The constructor for initialization created objects
	 * 
	 * @param aModel
	 * <br /> Model of aircraft. Name of company and serial. <br />
	 * For example "Boing 747"
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
	 * @param aWingspan
	 * <br /> Wingspan of airplane.
	 * @param aTakeoffDistance
	 * <br /> Required take-off distance.
	 * @param aQuantityEngines
	 * <br /> Quantity engines of airplane.
	 * @param aTypeOfEngenes
	 * <br /> Type engine of airplane.
	 */
	public ClassC(String id, String aModel, String title, String email,
			String telephone, int aMaxSpeed, int aRange, float aMass,
			float aMaxTakeoffWeight, int aAltitude, int aCrew,
			float aFuelConsumption, float aWingspan, int aTakeoffDistance,
			int aQuantityEngines, TypeEngine aTypeOfEngenes) {
		super(id, aModel, title, email, telephone, aMaxSpeed, aRange, aMass,
				aMaxTakeoffWeight, aAltitude, aCrew, aFuelConsumption);
		if (Validating.verify(aWingspan)) {
			this.wingspan = aWingspan;
		}
		if (Validating.verify(aTakeoffDistance)) {
			this.takeoffDistance = aTakeoffDistance;
		}
		if (Validating.verify(aQuantityEngines)) {
			this.quantityEngines = aQuantityEngines;
		}
		this.typeOfEngene = aTypeOfEngenes;
	}

	/**
	 * Override method toString()
	 */
	@Override
	public String toString() {
		return super.toString() + "\n\tWingspan = " + wingspan
				+ "\n\tTakeoff Distance = " + takeoffDistance
				+ "\n\tQuantity Engines = " + quantityEngines
				+ "\n\tType Of Engene = " + typeOfEngene;
	}

	/**
	 * 
	 * @return wingspan - wingspan of airplanes
	 */
	public float getWingspan() {
		return wingspan;
	}

	/**
	 * Sets wingspan of airplanes.
	 * 
	 * @param wingspan
	 *            wingspan of airplanes
	 */
	public void setWingspan(float wingspan) {
		if (Validating.verify(wingspan)) {
			this.wingspan = wingspan;
		}
	}

	/**
	 * Returns take-off distance of airplane
	 * 
	 * @return takeoffDistance
	 */
	public int getTakeoffDistance() {
		return takeoffDistance;
	}

	/**
	 * Sets take-off distance of airplane
	 * 
	 * @param takeoffDistance
	 *            take-off distance of airplane
	 */
	public void setTakeoffDistance(int takeoffDistance) {
		if (Validating.verify(takeoffDistance)) {
			this.takeoffDistance = takeoffDistance;
		}
	}

	/**
	 * Returns quantity engines.
	 * 
	 * @return quantityEngines - quantity engines.
	 */
	public int getQuantityEngines() {
		return quantityEngines;
	}

	/**
	 * Sets quantity engines.
	 * 
	 * @param quantityEngines
	 *            quantity engines.
	 */
	public void setQuantityEngines(int quantityEngines) {
		if (Validating.verify(quantityEngines)) {
			this.quantityEngines = quantityEngines;
		}
	}

	/**
	 * Returns type of engine engines.
	 * 
	 * @return typeOfEngine type of engines.
	 */
	public TypeEngine getTypeOfEngene() {
		return typeOfEngene;
	}

	/**
	 * Sets type of engine
	 * 
	 * @param typeOfEngene
	 */
	public void setTypeOfEngene(TypeEngine typeOfEngene) {
		this.typeOfEngene = typeOfEngene;
	}

}
