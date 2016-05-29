package kz.epam.task2.text.unit;


/**
 * Class keeps allletters of text
 * 
 * @author Artemov Kirill
 * @author student 3 course
 *
 */
public class Letter implements Cloneable {
	private String letter;
	/** Default constructor */
	public Letter() {
		this.letter = "";
	}
	/** init letter */
	public Letter(String aLetter) {
		this.letter = aLetter;
	}
	/** Sets letter */
	public void setLetter(String letter) {
		this.letter = letter;
	}
	/** Returns letter */
	public String getLetter() {
		return this.letter;
	}
	/** Remove letter */
	public String removeLetter() {
		return this.letter = "";
	}
	/** Override toString() */
	@Override
	public String toString() {
		return this.letter;
	}
	/** Cloning letter */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		Letter clone = null;
		try {
			clone = (Letter) super.clone();
			clone.letter = (String) this.letter;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return clone;
	}
}
