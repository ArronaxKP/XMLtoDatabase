package com.uk.aisl.guidewire.shredder.exception;

import com.ximpleware.ParseException;

/**
 * This is a wrapper exception to wrap all exception that we require to cause
 * the program to crash gracefully
 * 
 * @author Karl Parry
 * 
 */
public class CrashException extends Exception {

	/**
	 * Required to be a valid Java Exception. It is an auto-generated number.
	 */
	private static final long serialVersionUID = 5419858507020792849L;

	public CrashException(String message, Exception exception) {
		super(message, exception);
	}

	public CrashException(String message) {
		super(message);
	}

	public CrashException(String message, String XML, ParseException exception) {
		super(message, exception);
	}

}
