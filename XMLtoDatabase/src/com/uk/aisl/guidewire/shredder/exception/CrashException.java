package com.uk.aisl.guidewire.shredder.exception;

import com.ximpleware.ParseException;

public class CrashException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5419858507020792849L;

	public CrashException(String message, Exception exception){
		super(message, exception);
		//STUBBED
	}
	public CrashException(String message) {
		super(message);
		//STUBBED
	}
	public CrashException(String message, String XML, ParseException exception) {
		super(message, exception);
		//STUBBED
	}
	
}
