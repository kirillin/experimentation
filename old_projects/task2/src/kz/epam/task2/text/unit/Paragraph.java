package kz.epam.task2.text.unit;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * 
 * @author Artemov Kirill
 * @author student 3 course
 *
 * Paragraph which contains list of sentences.
 */
public class Paragraph implements Cloneable {
	private LinkedList<Sentence> sentances;

	/** The constructor initializes the list of sentences. */
	public Paragraph() {
		this.sentances = new LinkedList<Sentence>();
	}
	/**
	 * Add sentence to list.
	 * @param s the new <i>Sentence</i>
	 */
	public void addSentance(Sentence s) {
		this.sentances.add(s);
	}
	/**
	 * Returns list of sentences current paragraph. 			
	 * @return {@link LinkedList} contains sentences. 
	 */
	public LinkedList<Sentence> getListSentences() {
		return this.sentances;
	}
	
	@Override
	public String toString() {
		return this.sentances.toString();
	}
	/**
	 *  Cloning Paragraph object and all nested lists units 
	 *  of text in sentences list
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		Paragraph clone = null;
		try {
			clone = (Paragraph) super.clone();
			LinkedList<Sentence> s = new LinkedList<Sentence>();
			for (Iterator<Sentence> it = this.sentances.iterator(); it
					.hasNext();) {
				Sentence sentence = (Sentence) it.next();
				s.add((Sentence) sentence.clone());
			}
			clone.sentances = s;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return clone;		
	}
}
