package kz.epam.task2.text.unit;

import java.util.Iterator;
import java.util.LinkedList;

import kz.epam.task2.utils.AnalizeText;

/**
 * Sentence which contains list of words and punctuation.
 * Adding words and punctuation in lists of class Sentence, considered 
 * its location in a sentence.
 * 
 * @author Artemov Kirill
 * @author student 3 course
 */
public class Sentence implements Cloneable {
	private LinkedList<Word> words;
	private LinkedList<Punctuation> punctuation;
	/**
	 * The constructor initialized lists of words and punctuation.
	 */
	public Sentence() {
		this.words = new LinkedList<Word>();
		this.punctuation = new LinkedList<Punctuation>();
	}
	/** Add one word to list words */
	public void addWord(Word word) {
		this.words.add(word);
	}
	/** Returns list of words  */
	public LinkedList<Word> getListWord() {
		return this.words;
	}
	/** Sets one sign tolist punctuation */
	public void addPunctuation(Punctuation p) {
		this.punctuation.add(p);
	}
	/** Returns list punctuation */
	public LinkedList<Punctuation> getListPunctuation() {
		return this.punctuation;
	}
	
	@Override
	public String toString() {
		return AnalizeText.collect(this, 1).toString();
	}
	/**
	 *  Cloning Sentence object and all nested lists units 
	 *  of text in words and punctuation lists
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		Sentence clone = null;
		try {
			clone = (Sentence) super.clone();
			LinkedList<Punctuation> punct =
					new LinkedList<Punctuation>();
			for (Iterator<Punctuation> it = this.punctuation.iterator(); 
					it.hasNext();) {
				Punctuation punctuation = (Punctuation) it.next();
				punct.add((Punctuation) punctuation.clone());
			}
			LinkedList<Word> w = new LinkedList<Word>();
			for (Iterator<Word> it = this.words.iterator(); it.hasNext();) {
				Word word = (Word) it.next();
				w.add((Word) word.clone());
			}
			clone.punctuation = punct;
			clone.words = w;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return clone;	
	}
}
