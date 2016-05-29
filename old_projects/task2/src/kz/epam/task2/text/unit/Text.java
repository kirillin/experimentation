package kz.epam.task2.text.unit;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Object of class Text keeps list of all paragraphs.
 * 
 * @author Artemov Kirill
 * @author student 3 course
 *
 */
public class Text implements Cloneable {
	private LinkedList<Paragraph> paragraphs;
	/** Default constructor */
	public Text() {
		this.paragraphs = new LinkedList<Paragraph>();
	}
	/** Sets one paragraph to list paragraphs  */
	public void addParagraph(Paragraph p) {
		this.paragraphs.add(p);
	}
	/** Returns <i>LinkedList</i> contains paragraphs of text */
	public LinkedList<Paragraph> getListParagraphs() {
		return this.paragraphs;
	}
	/** Override default method toString() of LinkedList */
	@Override
	public String toString() {
		return this.paragraphs.toString();
	}
	/**
	 *  Cloning Text object and all nested lists units 
	 *  of text in paragraphs list
	 */
	@Override
	public Text clone() {
		Text clone = null;
		try {
			clone = (Text) super.clone();
			LinkedList<Paragraph> p = new LinkedList<Paragraph>();
			for (Iterator<Paragraph> it = this.paragraphs.iterator(); 
					it.hasNext();) {
				Paragraph paragraph = (Paragraph) it.next();
				p.add((Paragraph) paragraph.clone());
				
			}
			clone.paragraphs = p;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return clone;		
	}
}
