package kz.epam.airline.aircrafts.classC;

import kz.epam.airline.utiles.validate.Validating;

public class PassengerAirplane extends ClassC {
	private int passengersCapacity;
	private int flightAttendant;

	/**
	 * Default constructor.
	 */
	public PassengerAirplane() {
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
	 * 
	 * @param aPassengersCapacity
	 * @param aFlightAttendant
	 */
	public PassengerAirplane(String id, String aModel, String title,
			String email, String telephone, int aMaxSpeed, int aRange,
			float aMass, float aMaxTakeoffWeight, int aAltitude, int aCrew,
			float aFuelConsumption, float aWingspan, int aTakeoffDistance,
			int aQuantityEngines, TypeEngine aTypeOfEngenes,
			int aPassengersCapacity, int aFlightAttendant) {
		super(id, aModel, title, email, telephone, aMaxSpeed, aRange, aMass,
				aMaxTakeoffWeight, aAltitude, aCrew, aFuelConsumption,
				aWingspan, aTakeoffDistance, aQuantityEngines, aTypeOfEngenes);
		if (Validating.verify(aPassengersCapacity)) {
			this.passengersCapacity = aPassengersCapacity;
		}
		if (Validating.verify(aFlightAttendant)) {
			this.flightAttendant = aFlightAttendant;
		}
	}

	@Override
	public String toString() {
		return super.toString() + "\n\tPassengers Capacity = "
				+ passengersCapacity + "\n\tFlight Attendant = "
				+ flightAttendant;
	}

	/**
	 * Returns passenger capacity
	 * 
	 * @return
	 */
	public int getPassengersCapacity() {
		return passengersCapacity;
	}

	/**
	 * Sets passenger capacity
	 * 
	 * @param passengersCapacity
	 *            passenger capacity
	 */
	public void setPassengersCapacity(int passengersCapacity) {
		if (Validating.verify(passengersCapacity)) {
			this.passengersCapacity = passengersCapacity;
		}
	}
	/**
	 * Returns flight attendant
	 * 
	 * @return
	 */
	public int getFlightAttendant() {
		return flightAttendant;
	}

	/**
	 * Sets flight attendant
	 * 
	 * @param flightAttendant
	 */
	public void setFlightAttendant(int flightAttendant) {
		if (Validating.verify(flightAttendant)) {
			this.flightAttendant = flightAttendant;
		}
	}
}
