package kz.epam.airline.utiles.sort;

import java.util.Comparator;

import kz.epam.airline.aircrafts.Aircraft;
/**
 * Sorting by range aircrafts
 * @author Artemov Kirill
 * @author student 3 course
 *
 */
public class SortByRange implements Comparator<Aircraft> {

	@Override
	public int compare(Aircraft a1, Aircraft a2) {
		Integer air1 = ((Aircraft) a1).getRange();
		Integer air2 = ((Aircraft) a2).getRange();
		return air1.compareTo(air2);
	}

}
