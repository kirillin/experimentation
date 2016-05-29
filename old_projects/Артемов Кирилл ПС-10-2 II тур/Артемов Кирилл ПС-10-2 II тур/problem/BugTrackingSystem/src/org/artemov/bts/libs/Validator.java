package org.artemov.bts.libs;


/**
 * Class contains a few methods for validation entering data from users
 * 
 * @author Kirill Artemov
 *
 */
public class Validator {

	private static final String IS_NUMBER_REGEX = "[1-9]+[0-9]*([.]?[0-9]*)";
	private static final String STRIP_HARMFUL_REGEX = "['\"\\<>]*[--]*";
	private static final String EMPTY = "";

	public static boolean isNumber(String number) {
		boolean isMatch = false;
		if (number != null) {
			isMatch = number.matches(IS_NUMBER_REGEX);
		}
		return isMatch;
	}

	public static String stripHarmful(String string) {
		String stripted = null;
		if (string != null) {
			stripted = string.replaceAll(STRIP_HARMFUL_REGEX, EMPTY);
		}
		return stripted;
	}
	
}
