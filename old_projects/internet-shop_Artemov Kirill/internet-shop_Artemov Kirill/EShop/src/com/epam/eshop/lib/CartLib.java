package com.epam.eshop.lib;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * Class for methods are not directly connected with the application.
 * 
 * @author Kirill Artemov
 *
 */
public class CartLib {

	private final static String ENCODING_CP1251 = "cp1251";
	public CartLib() {
	}

	/**
	 * Read the object from string.
	 * @throws UnsupportedEncodingException 
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object fromString(String s) throws UnsupportedEncodingException {
		byte[] data = s.getBytes(Charset.forName(ENCODING_CP1251));
		ByteArrayInputStream bais = null;
		ObjectInputStream ois = null;
		Object o = null;
		try {
			try {
				bais = new ByteArrayInputStream(data);
				ois = new ObjectInputStream(bais);
				o = (Object) ois.readObject(); // cast to Cart !!
			} finally {
				if (ois != null)
					ois.close();
				if (bais != null)
					bais.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return o;
	}

	/** 
	 * Returns the object to string. 
	 * 
	 */
	public static String toString(Serializable o) throws IOException {
		try {
			ByteArrayOutputStream tmpBinOut = new ByteArrayOutputStream();
			try {
				ObjectOutputStream out = new ObjectOutputStream(tmpBinOut);
				out.writeObject(o);
				out.flush();
				out.close();
                String result = tmpBinOut.toString(ENCODING_CP1251); 
                return result; 
			} finally {
				if (tmpBinOut != null) {
					tmpBinOut.close();
				}
			}
		} catch (IOException exc) {
			exc.printStackTrace();
		}
		return null;
	}

}
