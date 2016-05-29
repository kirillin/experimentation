package kz.epam.task2.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import kz.epam.task2.Runner;
import kz.epam.task2.exception.FileNameError;

import org.apache.log4j.Logger;

/**
 * Class IOFile contains realizations for work with files.
 * <br /> Contains two methods:
 * 	<ul>
 * 		<li>LinkedList<String> readTextFromFile(String fileName)</li>
 * 		<li>void writeTextToFile(String fileName, String sLine)</li>
 *	</ul>
 * 
 * @author Artemov Kirill
 * @author student 3 course
 * 
 */
public class IOFile {
	private static Logger ioFileLogger = Logger.getLogger(IOFile.class);
	
	/**
	 * Returns List of lines from required file.
	 * 
	 * @param fileName - path for required file.
	 * @return <i>LinkedList<String></i>
	 * @throws FileNameError if filename is null
	 * @throws FileNotFoundException if filenot found
	 */
	public static LinkedList<String> readTextFromFile(String fileName) 
			throws FileNameError {
		LinkedList<String> listLines = new LinkedList<String>(){
			/**Serial number for serialization. */
			private static final long serialVersionUID = -5174911815003050588L;
			/**
			 * Necessary for comfortably using collection.
			 */
			@Override
			public String toString() {
		        Iterator<String> it = this.iterator();
		        if (! it.hasNext())
		            return "";
		        StringBuffer sb = new StringBuffer();
		        while (it.hasNext()) {
		        	String s = it.next();
		        	sb.append(s);
	                sb.append('\n').toString();
		        }
		        return sb.toString();
			}
		};

		File file = null;
		if (fileName != null) {
			file = new File(fileName);
			if (!file.exists()) {
				System.out.println(Runner.iface
						.getString("kz.epam.task2.IOFile.FileNotFound"));
				ioFileLogger.error(Runner.iface
						.getString("kz.epam.task2.IOFile.FileNotFound"));
			} else {
				System.out.printf("%s%s\n", Runner.iface
						.getString("kz.epam.task2.IOFile.PathToFileIntput"),
						file.getAbsolutePath());

				System.out.println(Runner.iface
						.getString("kz.epam.task2.IOFile.ReadingFile"));
				ioFileLogger.info(Runner.iface
						.getString("kz.epam.task2.IOFile.ReadingFile"));
				try (	FileReader fr = new FileReader(file);
						BufferedReader br = new BufferedReader(fr);) {
					String sLine = "";
					while ((sLine = br.readLine()) != null) {
						listLines.add(sLine);
					}
				} catch (FileNotFoundException fnfe) {
					ioFileLogger.error(Runner.iface
							.getString("kz.epam.task2.IOFile.FileNotFound")
							+ fnfe.getMessage());
				} catch (IOException ioe) {
					ioFileLogger.error(Runner.iface
							.getString("kz.epam.task2.IOFile.IOError")
							+ ioe.getMessage());
				}
			}
		} else {
			throw new FileNameError(Runner.iface
					.getString("kz.epam.task2.IOFile.FileNameError"));
		} 
		System.out.println(Runner.iface
				.getString("kz.epam.task2.IOFile.ReadFileComplete"));
		ioFileLogger.info(Runner.iface
				.getString("kz.epam.task2.IOFile.ReadFileComplete"));
		return listLines;
	}
	
	/**
	 * In file with required fileName recorded text from <i>String</i> sLine  
	 * 
	 * @param fileName <i>String</i> contains name of file
	 * @param sLine <i>String</i> contains text
	 * @throws FileNameError if name of file is null
	 */
	public static void writeTextToFile(String fileName, String sLine)
			throws FileNameError {
		File file = null;
		if (fileName != null) {
			file = new File(fileName);

			if (!file.exists()) {
				System.out.println(Runner.iface
						.getString("kz.epam.task2.IOFile.FileNotFound"));
				ioFileLogger.error(Runner.iface
						.getString("kz.epam.task2.IOFile.FileNotFound"));
				try {
						if (file.createNewFile()) {
							System.out.println(Runner.iface
								.getString("kz.epam.task2.IOFile.FileCreated"));
							ioFileLogger.info(Runner.iface
								.getString("kz.epam.task2.IOFile.FileCreated"));
							System.out.println(Runner.iface
								.getString("kz.epam.task2.IOFile.WritingFile"));
							ioFileLogger.info(Runner.iface
								.getString("kz.epam.task2.IOFile.WritingFile"));
						} else {
							ioFileLogger.error(Runner.iface
									.getString(
									"kz.epam.task2.IOFile.FileCreateError"));
							throw new SecurityException(); 
						}							
				} catch (SecurityException  e) {
					ioFileLogger.error(Runner.iface
							.getString("kz.epam.task2.IOFile.IOError"));
					e.printStackTrace();
				} catch (IOException e) {
					ioFileLogger.error(Runner.iface
							.getString("kz.epam.task2.IOFile.IOError"));
				}
			}
			if (file.exists()) {
				try {
					try (FileWriter fw = new FileWriter(file);
							BufferedWriter bw = new BufferedWriter(fw);) {
						bw.write(sLine);
					} catch (FileNotFoundException fnfe) {
						ioFileLogger
								.error(Runner.iface
									.getString(
									"kz.epam.task2.IOFile.FileNotFound"));
					}
					System.out
							.println(Runner.iface
									.getString(
									"kz.epam.task2.IOFile.WriteFileComplete"));
					ioFileLogger
							.info(Runner.iface
									.getString(
									"kz.epam.task2.IOFile.WriteFileComplete"));
					System.out
							.printf("%s%s\n",
									Runner.iface.getString(
									"kz.epam.task2.IOFile.PathToFileOutput"),
									file.getAbsolutePath());
				} catch (IOException e) {
					ioFileLogger.error(Runner.iface
							.getString("kz.epam.task2.IOFile.IOError"));
				}
			}
			
		} else {
			throw new FileNameError(Runner.iface
					.getString("kz.epam.task2.IOFile.FileNameError"));
		} 
	}
}
