package kz.epam.airline.utiles.validate;


import java.io.File;
import java.io.IOException;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import kz.epam.airline.runner.Runner;
import kz.epam.airline.utiles.parsers.handlers.SAXHandler;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

public class XMLValidation {
	private static Logger xmlVld = Logger.getLogger(SAXHandler.class);
	
	public static boolean validate(String xsdDoc, String xmlDoc) {
		boolean isValid = false;
		SchemaFactory factory = 
				SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
		File schemaLocation = new File(xsdDoc);
		File file = new File(xmlDoc);
		if (schemaLocation.exists() && file.exists()) {
			try {
				Schema schema = factory.newSchema(schemaLocation);
				Validator validator = schema.newValidator();				
				Source source = new StreamSource(file);
				validator.validate(source);
				isValid = true;
				xmlVld.info("\"" + file.getName() + "\" " + Runner.iface.getString("kz.epam.airline.utiles.validate.Valid"));
			} catch (SAXException e) {
				xmlVld.error("\"" + file.getName() + "\" " + Runner.iface.getString("kz.epam.airline.utiles.validate.notValid") + e.getMessage());
			} catch (IOException e) {
				xmlVld.error(Runner.iface.getString("kz.epam.airline.utiles.validate.errorReadFile") + file.getName() + e.getMessage());
			} 		
		} else {
			xmlVld.error(Runner.iface.getString("kz.epam.airline.utiles.validate.fileNotFound"));
		}
		return isValid;
	}
}
