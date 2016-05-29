package kz.epam.airline.aircrafts.classE;

import kz.epam.airline.aircrafts.Aircraft;
import kz.epam.airline.utiles.validate.Validating;
/**
 * 
 * @author Artemov Kirill
 * @author student 3 course
 *
 * this branch of the hierarchy is not used!!
 * created to show the hierarchy of the whole aircrafts  
 * 
 */
public abstract class ClassE extends Aircraft {
	private float diametrOfRotor;
	private float diametrOfTailRotor;
	private float rateOfClimb;

	public ClassE() {
	}

	public ClassE(String aModel, int aMaxSpeed, int aRange, int aMass,
			int aMaxTakeoffWeight, int aAltitude, int aCrew,
			int aFuelConsumption, float aDiametrOfRotor,
			float aDiametrOfTailRotor, float aRateOfClimb) {
		super(aModel, aModel, aModel, aModel, aModel, aMaxSpeed, aRange, aMass, aMaxTakeoffWeight, aAltitude,
				aCrew, aFuelConsumption);
		if (Validating.verify(aDiametrOfRotor)) {
			this.diametrOfRotor = aDiametrOfRotor;
		}
		if (Validating.verify(aDiametrOfTailRotor)) {
			this.diametrOfTailRotor = aDiametrOfTailRotor;
		}
		if (Validating.verify(aRateOfClimb)) {
			this.rateOfClimb = aRateOfClimb;
		}
	}

	public float getDiametrOfRotor() {
		return diametrOfRotor;
	}

	public void setDiametrOfRotor(float diametrOfRotor) {
		this.diametrOfRotor = diametrOfRotor;
	}

	public float getDiametrOfTailRotor() {
		return diametrOfTailRotor;
	}

	public void setDiametrOfTailRotor(float diametrOfTailRotor) {
		this.diametrOfTailRotor = diametrOfTailRotor;
	}

	public float getRateOfClimb() {
		return rateOfClimb;
	}

	public void setRateOfClimb(float rateOfClimb) {
		this.rateOfClimb = rateOfClimb;
	}

}
