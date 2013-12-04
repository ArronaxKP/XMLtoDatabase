package com.uk.aisl.guidewire.shredder;

public class Logger {
	public static void error(String errorMessage){
		System.err.println(errorMessage);
	}
	public static void log(String message){
		System.out.println(message);
	}
	public static void crash(Exception exception) {
		Logger.crash(exception.getMessage(), exception);
	}
	
	public static void crash(String Message, Exception exception) {
		System.err.println(exception.getMessage());
		exception.printStackTrace();
	}
}
