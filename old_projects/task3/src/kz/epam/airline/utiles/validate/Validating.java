package kz.epam.airline.utiles.validate;

/**
 * The class for validating data.
 * Realized some static methods for some types.
 *   
 * @author Artemov Kirill
 * @author student 3 course
 *
 */
public class Validating {
	
	private static void validateFailed(){
		System.out.println("Wrong format of value!");
	}
	
	public static boolean verify(String s) {
		if (s.length() > 0) {
			return true;				
		}
		validateFailed();		
		return false;
    }
	
	public static boolean verify(int i) {
		if (i > 0) {
			return true;				
		}
		validateFailed();		
		return false;
    }

	public static boolean verify(Float f) {
		if (f > 0) {
			return true;				
		}
		validateFailed();		
		return false;
    }
}
