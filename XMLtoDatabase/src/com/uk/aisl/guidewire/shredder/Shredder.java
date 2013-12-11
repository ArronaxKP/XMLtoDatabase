package com.uk.aisl.guidewire.shredder;

import java.util.ArrayList;

import com.uk.aisl.guidewire.shredder.database.Loader;
import com.uk.aisl.guidewire.shredder.designer.Printer;
import com.uk.aisl.guidewire.shredder.exception.CrashException;
import com.uk.aisl.guidewire.shredder.exception.Logger;
import com.uk.aisl.guidewire.shredder.model.Database;
import com.uk.aisl.guidewire.shredder.model.XMLReturn;
import com.uk.aisl.guidewire.shredder.xml.MapperXML;
import com.uk.aisl.guidewire.shredder.xml.Parser;

public class Shredder {	

	public static void main(String args[]) {
		Printer.printTheShredder();
		long start = System.currentTimeMillis();
		Database database = null;
		try {
			database = MapperXML.parseMapperXML("mapping.xml");
			try {
				ArrayList<XMLReturn> list = Loader.getXML(database);
				for(XMLReturn returnXML: list){
					Parser parser = new Parser(returnXML.getXmlPayload());
					parser.parseXML(database, returnXML.getVariableMap());
					if(Loader.insertToStaging(database)) {
						//Inserting success
					} else {
						System.out.println("Failure occured");
					}
					database.cleanDown();
				}
			} catch (CrashException e) {
				Logger.crash(e);
				System.exit(1);
			}
		} catch (CrashException e) {
			Logger.crash(e);
			System.exit(1);
		} catch (Exception e) {
			Logger.crash("Unknown Error occurred",e);
			System.exit(1);
		}
		long end = System.currentTimeMillis();
		Logger.log("Job ended in: " + (end - start) + " ms");
		System.exit(0);
	}
}
