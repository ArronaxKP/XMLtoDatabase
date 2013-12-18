package com.uk.aisl.guidewire.shredder;

import java.util.ArrayList;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;

import com.uk.aisl.guidewire.shredder.database.Loader;
import com.uk.aisl.guidewire.shredder.designer.Printer;
import com.uk.aisl.guidewire.shredder.exception.CrashException;
import com.uk.aisl.guidewire.shredder.model.Database;
import com.uk.aisl.guidewire.shredder.model.XMLReturn;
import com.uk.aisl.guidewire.shredder.xml.MappingXML;
import com.uk.aisl.guidewire.shredder.xml.Parser;

/**
 * Main class that holds only the main method to kick off the shreding of the
 * pay load XML
 * 
 * @author Karl Parry
 * @author Gareth Edwards
 * 
 */
public class Shredder {
	private static Logger logger = LogManager.getLogger(Shredder.class.getName());

	/**
	 * Main method that starts the shredder. The Shredder is designed to read
	 * the mapping.xml file (located at the same root directory as the jar) and
	 * parse the XML pay load defined in the mapping XML. <br/>
	 * <br/>
	 * It is designed to parse the XML from PolicyCenter's GX model to
	 * Elephant.com's DataWarehouse originally. It is mostly dynamic but could
	 * be further refined to be more dynamic using the mapping.xml
	 * 
	 * @param args
	 *            -X flag will enable Debug mode and turn logging to all setting
	 *            otherwise it will only log Error or above<br/>
	 */
	public static void main(String args[]) {
		Shredder.checkArgs(args);
		Printer.printTheShredder();
		long start = System.currentTimeMillis();
		Database database = null;
		try {
			logger.trace("Beginning parsing mapping.xml");
			database = MappingXML.parseMappingXML("mapping.xml");
			logger.trace("Finished parsing mapping.xml");
			logger.trace("Beginning to get payload xml from Database");
			ArrayList<XMLReturn> list = Loader.getXML(database);
			logger.trace("Finished getting payload xml from Database");
			for (XMLReturn returnXML : list) {
				logger.trace("Beginning parsing payload xml for " + database.getLookUpValue("TransID"));
				try {
					database.setXML(returnXML);
					Parser parser = new Parser(returnXML.getXmlPayload());
					database = parser.parseXML(database, returnXML.getVariableMap());
					Loader.insertToStaging(database);
				} catch (CrashException e) {
					Loader.logError(database, e);
				}
				logger.trace("Finished parsing payload xml for " + database.getLookUpValue("TransID"));
				database.cleanDown();
			}
		} catch (CrashException e) {
			logger.fatal("Crash exception occured", e);
			System.exit(1);
		} catch (Exception e) {
			logger.fatal("Unknown Error occurred", e);
			System.exit(1);
		}
		long end = System.currentTimeMillis();
		logger.trace("Job ended in: " + (end - start) + " ms");
		System.exit(0);
	}

	/**
	 * Checks the Arguments list passed to main for the Debug flag. If other
	 * flags are desired add the checks to this method.
	 * 
	 * @param args List of arguments 
	 */
	private static void checkArgs(String[] args) {
		if (args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				if (args[i].equals("-X")) {
					LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
					Configuration config = ctx.getConfiguration();
					LoggerConfig loggerConfig = config.getLoggerConfig(LogManager.ROOT_LOGGER_NAME);
					loggerConfig.setLevel(Level.ALL);
					ctx.updateLoggers();
					logger.debug("Debug mode enabled with -X flag");
				}
			}
		}
	}
}
