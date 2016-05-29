package kz.epam.task2.exception;



/**
 * Throw exception if file name is null
 * 
 * @author Artemov Kirill
 * @author student 3 course
 *
 */
public class FileNameError extends Exception  {
	/** Serial number for serialization */
	private static final long serialVersionUID = 250995481497585503L;
	/** default constructor of Exception */
	public FileNameError() {
		super();
	}
	/** Constructor with message */
	public FileNameError(String mess) {
		super(mess);
	}
}
