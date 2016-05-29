package kz.epam.task2.problems;

import java.util.Iterator;
import java.util.LinkedList;

import org.apache.log4j.Logger;

import kz.epam.task2.Runner;
import kz.epam.task2.text.unit.Letter;
import kz.epam.task2.text.unit.Paragraph;
import kz.epam.task2.text.unit.Sentence;
import kz.epam.task2.text.unit.Text;
import kz.epam.task2.text.unit.Word;
import kz.epam.task2.utils.AnalizeText;

/**
 * 
 * Class with solution task 2 problem 15.
 * 
 * @author Artemov Kirill
 * @author student 3 course
 *
 */
public class MyProblem {
	/** Logger for this class */
	private static Logger myProblemLogger = 
			Logger.getLogger(MyProblem.class);
	/**
	 * 
	 * <br /> Method deletes the first (last) symbol 
	 * <b>next (previous) occurrence of </b> the specified word.
	 * <br /> That is the first (last) character of word is not deleted.
	 * @param word <i>String</i> for processing.
	 * @return <i>String</i> with processing word.
	 */
	public static String processingWord(String word) {
		StringBuffer res = new StringBuffer();
		if (!word.isEmpty()) {
			char firstLetter = word.charAt(0);
			char lastLetter = word.charAt(word.length()-1);
			int i = 1;
			if ((!word.isEmpty()) && (word.length() > 1)) {
				res.append(firstLetter);
				while (word.length()-1 > i) {
					if ((word.charAt(i) != firstLetter)
					&& (word.charAt(i) != lastLetter)) {
						res.append(word.charAt(i));
					}
					i++;
				}
				res.append(lastLetter);
			} else 	res.append(word.charAt(0));
		} else res.append("");
		myProblemLogger.debug(
				Runner.iface.getString(
						"kz.epam.task2.MyProblem.Processedword")
				+ "|" + word + "|=>|" + res.toString() + "|");
		return res.toString();
	}
	
	/**
	 * <br /> Method collects each word from <i>Text</i>
	 * that replaced word returned by <b>processingWord(String)</b> 
	 * with changed the outer object of <i>Text</i>.
	 * @param text <i>Text</i> for solution problem
	 */
	public static void solveProblem(Text text) {
		StringBuffer tempWord = new StringBuffer();
		LinkedList<Paragraph> p = text.getListParagraphs();
		for (Iterator<Paragraph> iterator = p.iterator(); iterator.hasNext();) {
			Paragraph paragraph = (Paragraph) iterator.next();
			LinkedList<Sentence> s = paragraph.getListSentences();
			for (Iterator<Sentence> iterator2 = s.iterator(); iterator2.hasNext();) {
				Sentence sentence = (Sentence) iterator2.next();
				// List words from each sentence of paragraph of text
				LinkedList<Word> w = sentence.getListWord();
				for (Iterator<Word> iterator3 = w.iterator(); iterator3.hasNext();) {
					Word word = (Word) iterator3.next();
					LinkedList<Letter> l = word.getListLetters();
					for (Iterator<Letter> iterator4 = l.iterator(); iterator4.hasNext();) {
						Letter letter = (Letter) iterator4.next();
						// Collect word toStringBuffer and remove from obj
						tempWord.append(letter.getLetter());
						letter.removeLetter();
					}
					// Start solve problem for it word
					String solved = 
							MyProblem.processingWord(tempWord.toString());
					// Parse new word to it obj
					AnalizeText.parse(word, solved, 0);
					tempWord.setLength(0);
				}
			}
		}
	}
}
