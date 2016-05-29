package kz.epam.airline.utiles.sort;

import java.util.Comparator;

import kz.epam.airline.aircrafts.Aircraft;

/**
 * IN THIS PACKAGE CAN BE DONE REALIZATION SORT FOR ALL FIELDS OF CLASS AIRCRAFT
 * 
 * The class for sorting collections parameterized with class Aircrafts
 * @author Artemov Kirill
 * @author student 3 course
 *
 */
public class SortByMaxSpeed implements Comparator<Aircraft> {

	@Override
	public int compare(Aircraft a1, Aircraft a2) {
		Integer air1 = ((Aircraft) a1).getMaxSpeed();
		Integer air2 = ((Aircraft) a2).getMaxSpeed();
		return air1.compareTo(air2);
	}
}
