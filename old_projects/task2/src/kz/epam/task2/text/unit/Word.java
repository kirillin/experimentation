package kz.epam.task2.text.unit;

import java.util.Iterator;
import java.util.LinkedList;

import kz.epam.task2.utils.AnalizeText;

/**
 * class Word contains located index in sentence and list of letters 
 * 
 * @author Artemov Kirill
 * @author student 3 course
 *
 */
public class Word implements Cloneable {
	private int	indexInSentence;
	private LinkedList<Letter> letters;
	/** Defualt constructor */
	public Word() {
		this.letters = new LinkedList<Letter>();
		this.indexInSentence = -1;		
	}
	/** init index insentence */
	public Word(int aIndex) {
		this.letters = new LinkedList<Letter>();
		this.indexInSentence = aIndex;
	}
	/** Sets index */
	public void setIndexInSentence(int indexInSentence) {
		this.indexInSentence = indexInSentence;
	}
	/** Returns index */
	public int getIndexInSentence() {
		return indexInSentence;
	}
	/** Add letter to list */
	public void addLetter(Letter letter) {
		this.letters.add(letter);
	}
	/** Returns list of letters  */
	public LinkedList<Letter> getListLetters() {
		return letters;
	}
	
	@Override
	public String toString() {
		return AnalizeText.collect(this, 0);		
	}
	/** Cloning Word and list letters that contains letters */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		Word clone = null;
		try {
			clone = (Word) super.clone();
			clone.indexInSentence = this.indexInSentence;
			LinkedList<Letter> l = new LinkedList<Letter>();
			for (Iterator<Letter> it = this.letters.iterator(); 
					it.hasNext();) {
				Letter letter = (Letter) it.next();
				l.add((Letter) letter.clone());
			}
			clone.letters = l;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return clone;	
	}
}
