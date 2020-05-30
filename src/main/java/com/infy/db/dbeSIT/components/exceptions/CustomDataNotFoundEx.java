package com.infy.db.dbeSIT.components.exceptions;

public class CustomDataNotFoundEx extends Exception {
	private static final long serialVersionUID = 1L;

	public CustomDataNotFoundEx() {
		super();
	}

	public CustomDataNotFoundEx(String errorMsgs) {
		super(errorMsgs);
	}
}
