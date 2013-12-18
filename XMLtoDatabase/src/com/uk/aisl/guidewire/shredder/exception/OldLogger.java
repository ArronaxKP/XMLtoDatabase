package com.uk.aisl.guidewire.shredder.exception;

public class OldLogger {
	
	public static void info(String message) {
		System.out.println("INFO : "+message);
	}

	public static void debug(String message) {
		System.out.println("DEBUG: "+message);
	}
	
	public static void error(String errorMessage) {
		System.err.println(errorMessage);
	}
	
	public static void error(String errorMessage, Exception exception) {
		System.err.println(errorMessage);
		exception.printStackTrace();
	}

	public static void crash(String message, Exception exception) {
		System.err.println("CRASH: "+message);
		exception.printStackTrace();
	}

	public static void superError(String message) {
		System.out.println("SUPER: "+message);
	}
	
	public static void superError(String errorMessage, Exception exception) {
		System.err.println("SUPER:"+errorMessage);
		exception.printStackTrace();
	}
}
