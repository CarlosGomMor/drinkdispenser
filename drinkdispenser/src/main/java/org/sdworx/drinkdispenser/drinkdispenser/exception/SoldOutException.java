package org.sdworx.drinkdispenser.drinkdispenser.exception;

public class SoldOutException extends Exception {
	private static final long serialVersionUID = 709173058635931320L;

	public SoldOutException(String errorMessage) {
		super(errorMessage);
	}


}
