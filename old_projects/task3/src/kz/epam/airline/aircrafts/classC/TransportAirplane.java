package kz.epam.airline.aircrafts.classC;

import kz.epam.airline.utiles.validate.Validating;

public class TransportAirplane extends ClassC {
	private float maxVolumePayload;	// объем груза
	private int payloadCapacity;	// грузоподъемность
	/**
	 * Default constructor
	 */
	public TransportAirplane()
	{}
	/**
	 * 
	 * The constructor for initialization created objects 
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
	 * @param aWingspan
	 * <br /> Wingspan of airplane.
	 * @param aTakeoffDistance
	 * <br /> Required take-off distance.
	 * @param aQuantityEngines
	 * <br /> Quantity engines of airplane.
	 * @param aTypeOfEngenes
	 * <br /> Type engine of airplane.

	 * @param aMaxVolumePayload
	 * @param aPayloadCapacity
	 */
	public TransportAirplane(String id, String aModel, String title,
			String email, String telephone, int aMaxSpeed, int aRange,
			float aMass, float aMaxTakeoffWeight, int aAltitude, int aCrew,
			float aFuelConsumption, float aWingspan, int aTakeoffDistance,
			int aQuantityEngines, TypeEngine aTypeOfEngenes,
			float aMaxVolumePayload, int aPayloadCapacity) {
		super(id, aModel, title, email, telephone, aMaxSpeed, aRange, aMass,
				aMaxTakeoffWeight, aAltitude, aCrew, aFuelConsumption,
				aWingspan, aTakeoffDistance, aQuantityEngines, aTypeOfEngenes);
		if (Validating.verify(aMaxVolumePayload)) {
			this.maxVolumePayload = aMaxVolumePayload;
		}
		if (Validating.verify(aPayloadCapacity)) {
			this.payloadCapacity = aPayloadCapacity;
		}
	}

	@Override
	public String toString() {
		return super.toString() + "\n\tMax Volume Payload = "
				+ maxVolumePayload + "\n\tPayload Capacity = "
				+ payloadCapacity;
	}
	/**
	 * Returns maximum volume payload
	 * 
	 * @return
	 */
	public float getMaxVolumePayload() {
		return maxVolumePayload;
	}
	/**
	 * Sets maximum volume payload
	 * 
	 * @param maxVolumePayload
	 *            maximum volume payload
	 */
	public void setMaxVolumePayload(float maxVolumePayload) {
		if (Validating.verify(maxVolumePayload)) {
			this.maxVolumePayload = maxVolumePayload;
		}
	}
	/**
	 * returns payload capacity
	 * 
	 * @return
	 */
	public int getPayloadCapacity() {
		return payloadCapacity;
	}
	/**
	 * Sets payload capacity
	 * 
	 * @param payloadCapacity
	 *            payload capacity
	 */
	public void setPayloadCapacity(int payloadCapacity) {
		if (Validating.verify(payloadCapacity)) {
			this.payloadCapacity = payloadCapacity;
		}
	}
}
