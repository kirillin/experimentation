package kz.epam.task2.text.unit;

/**
 * Class keeps all punctuation of text
 * 
 * @author Artemov Kirill
 * @author student 3 course
 *
 */
public class Punctuation implements Cloneable {
	private int	indexInSentence;
	private String sign;
	/** Default constructor*/
	public Punctuation() {
		this.sign = "";
		this.indexInSentence = -1;
	}
	/** init only sign */
	public Punctuation(String sign) {
		this.sign = sign;
		this.indexInSentence = -1;		
	}
	/** init sign and index in sentence */
	public Punctuation(String sign, int aIndex) {
		this.sign = sign;
		this.indexInSentence = aIndex;
	}
	/** Sets index */
	public void setIndexInSentence(int indexInSentence) {
		this.indexInSentence = indexInSentence;
	}
	/** Returns index */
	public int getIndexInSentence() {
		return this.indexInSentence;
	}
	/** Sets sign */
	public void setSign(String sign) {
		this.sign = sign;
	}
	/** Returns sign */
	public String getSign() {
		return sign;
	}
	/** Override toString() */
	@Override
	public String toString() {
		return this.sign;
	}
	/** Cloning punctuation */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		Punctuation clone = null;
		try {
			clone = (Punctuation) super.clone();
			clone.sign = (String) this.sign;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return clone;
	}
}
