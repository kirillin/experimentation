package com.epam.eshop.tags.exception;

/**
 * Appear if in iterate tag sets collection equals null
 * 
 * @author Kirill Artemov
 *
 */
public class TagException extends Exception {


	private static final long serialVersionUID = 1L;

	public TagException() {
		super();
	}

	public TagException(String msg) {
		super(msg);
	}
}
