package kz.epam.task2.utils;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import kz.epam.task2.Runner;
import kz.epam.task2.text.unit.Letter;
import kz.epam.task2.text.unit.Paragraph;
import kz.epam.task2.text.unit.Punctuation;
import kz.epam.task2.text.unit.Sentence;
import kz.epam.task2.text.unit.Text;
import kz.epam.task2.text.unit.Word;

import org.apache.log4j.Logger;

/**
 * 
 * Class for parsing and collecting <i>Text</i> 
 * 
 * @author Artemov Kirill
 * @author student 3 course
 *
 */
public class AnalizeText {
	/** Logger for this class */
	private static Logger analizeTextLogger = 
			Logger.getLogger(AnalizeText.class);
	
	/**
	 * 
	 * Method for parse <i>String</i> on <i>Text</i>
	 * 
	 * @param unit one of units of text as specific objects 
	 * located in <code>kz.epam.task2.unit</code>
	 * @param input <i>String</i> contains content for unit of sentence
	 * @param level number unit of text. Discription in {@link Regex}  
	 */
	public static void parse(Object unit, String input, int level) {
		Pattern pattern = Pattern.compile(Regex.getRegex(level),
					Pattern.UNICODE_CHARACTER_CLASS);
		analizeTextLogger.debug(Runner.iface
			.getString("kz.epam.task2.AnalizeText.Parsing"));

		Matcher matcher = pattern.matcher(input);
		int indexLocationInSentence = 0;
		while (matcher.find()) {
			matcher.group();
			switch (level) {
			case 0:
				Letter letter = new Letter(matcher.group());
				((Word) unit).addLetter(letter);
				break;

			case 1:
				if ("...:;-,.!\\(\\)?@#$%&*№~+=\\/^\"[]{}|".contains(matcher.group())) {
					Punctuation punctuation = 
							new Punctuation(matcher.group(), 
									indexLocationInSentence);
					((Sentence) unit).addPunctuation(punctuation);
					indexLocationInSentence += 1;
				} else {
					Word word = new Word(indexLocationInSentence);
					((Sentence) unit).addWord(word);
					parse(word, matcher.group(), level-1);
					indexLocationInSentence += 1;
				}
				break;

			case 2:
				Sentence sentence = new Sentence();
				((Paragraph) unit).addSentance(sentence);
				parse(sentence, matcher.group(), level-1);
				break;

			case 3:
				Paragraph paragraph = new Paragraph();
				((Text) unit).addParagraph(paragraph);
				parse(paragraph, matcher.group(), level-1);
				break;

			default:
				analizeTextLogger.debug(Runner.iface
					.getString("kz.epam.task2.AnalizeText.UnknowParsingError"));
				break;
			}
		}
		analizeTextLogger.debug(Runner.iface
				.getString("kz.epam.task2.AnalizeText.ParseComplete"));
	}

	/**
	 * 
	 * Method for collected <i>Text</i> to <i>String</i>
	 * Logic: 	Each Paragraph collects sentences
	 * 				Sentences collects words and punctuation
	 * 					Words collects letters
	 * 		It's all added to <i>StrinBuffer</i> and returns. 
	 * 
	 * @param unit one of units of text as specific objects 
	 * located in <code>kz.epam.task2.unit</code>
	 * @param level number unit of text. Discription in {@link Regex}  
	 * @return <i>String</i> collects text
	 */
	public static String collect(Object unit, int level) {
		StringBuffer output = new StringBuffer();
		analizeTextLogger.debug(Runner.iface
				.getString("kz.epam.task2.AnalizeText.Collecting"));
		switch (level) {
		case 3:
			LinkedList<Paragraph> p = ((Text) unit).getListParagraphs();
			for (Iterator<Paragraph> it = p.iterator(); it.hasNext();) {
				Paragraph paragraph = (Paragraph) it.next();
				output.append(collect(paragraph, level-1));
				output.append("\n");
			}
			break;

		case 2:
			LinkedList<Sentence> s = ((Paragraph) unit).getListSentences();
			for (Iterator<Sentence> it = s.iterator(); it.hasNext();) {
				Sentence sentence = (Sentence) it.next();
				output.append(collect(sentence, level-1));
			}
			break;

		case 1:
			LinkedList<Word> w = 	((Sentence) unit).getListWord();
			LinkedList<Punctuation> punct = 
									((Sentence) unit).getListPunctuation();
			// save count units of specific sentence
			int sizeSentence = w.size() + punct.size();
			for (int i = 0; i < sizeSentence; i++) {
				// Start iterator for words because words in sentence is not
				// less or equal then punctuation( may be :) )
				//Search word with index from 0 to sizeSentence 
				for (Iterator<Word> itw = w.iterator(); itw.hasNext();) {
					Word word = (Word) itw.next();
					if (word.getIndexInSentence() == i) {
						output.append(collect(word, level-1));

						boolean flag = false;
						for (Iterator<Punctuation> itp = punct.iterator(); itp
								.hasNext();) {
							Punctuation punctuation = (Punctuation) itp.next();
							if ((punctuation.getIndexInSentence() == i + 1)
								&& !("(\"-".contains(punctuation.getSign()))) {
								flag = true;
							}
						}
						if (!flag) {
							output.append(" ");
						}
						
					}
				}
				//Search punct with index from 0 to sizeSentence
				for (Iterator<Punctuation> itp = punct.iterator(); itp
						.hasNext();) {
					Punctuation punctuation = (Punctuation) itp.next();
					if (punctuation.getIndexInSentence() == i) {
						String temp = punctuation.getSign();
						output.append(temp);
						if (itp.hasNext()) {
							if (!(itp.next().getIndexInSentence() == i + 1)) {
								output.append(" ");
							}
						}
					}
				}
			}
			break;

		case 0:
			LinkedList<Letter> l = 	((Word) unit).getListLetters();
			for (Iterator<Letter> it = l.iterator(); it.hasNext();) {
				Letter letter = (Letter) it.next();
				output.append(letter.getLetter());
//				System.out.print(letter.getLetter());
			}
			break;

		default:
			analizeTextLogger.debug(Runner.iface
				.getString("kz.epam.task2.AnalizeText.UnknowCollectingError"));
			break;
		}
		analizeTextLogger.debug(Runner.iface
				.getString("kz.epam.task2.AnalizeText.CollectComplete"));
		return output.toString();		
	}
}
