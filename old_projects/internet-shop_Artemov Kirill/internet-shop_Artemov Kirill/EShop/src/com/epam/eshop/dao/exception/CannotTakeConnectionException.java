package com.epam.eshop.dao.exception;

/**
 * The exception throws if in DAO method that returned concrete DAO 
 * connection == null 
 * 
 * @author Kirill Artemov
 *
 */
public class CannotTakeConnectionException extends Exception {

	private static final long serialVersionUID = 1L;

	public CannotTakeConnectionException() {
		super();
	}

	public CannotTakeConnectionException(String msg) {
		super(msg);
	}

}
