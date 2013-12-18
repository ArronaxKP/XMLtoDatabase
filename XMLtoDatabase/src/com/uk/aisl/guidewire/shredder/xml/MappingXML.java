package com.uk.aisl.guidewire.shredder.xml;

import com.uk.aisl.guidewire.shredder.exception.CrashException;
import com.uk.aisl.guidewire.shredder.model.Column;
import com.uk.aisl.guidewire.shredder.model.Database;
import com.uk.aisl.guidewire.shredder.model.ErrorDatabase;
import com.uk.aisl.guidewire.shredder.model.LookUp;
import com.uk.aisl.guidewire.shredder.model.SourceDatabase;
import com.uk.aisl.guidewire.shredder.model.Table;
import com.ximpleware.AutoPilot;
import com.ximpleware.NavException;
import com.ximpleware.VTDGen;
import com.ximpleware.VTDNav;
import com.ximpleware.XPathEvalException;
import com.ximpleware.XPathParseException;

/**
 * Class that wrappers the parsing of the mapping.xml file. The mapping.xml file
 * is the base structure for the entire application.<br/>
 * <br/>
 * The XML parsing is done using the VTD-XML parser.
 * 
 * @author Karl Parry
 * 
 */
public abstract class MappingXML {

	/**
	 * The main method that parses the mapping.xml file. Throws an Exception if
	 * an issue occurs.
	 * 
	 * @param path
	 *            The Path to the mapping.xml file including "/mapping.xml". If
	 *            one is not provided it is assumed it is in the root directory.
	 * @return The initialised Database object with all the look ups, Xpaths and
	 *         special values.
	 * @throws CrashException
	 */
	public static Database parseMappingXML(String path) throws CrashException {
		if (path == null || path.equals("")) {
			path = "mapping.xml";
		}
		Database database = new Database();
		VTDGen vg = new VTDGen();
		if (vg.parseFile(path, false)) {
			// parsing succeeded
			try {
				VTDNav vn = vg.getNav();

				database.setError(MappingXML.getErrorHandling(vn));
				database.setSource(MappingXML.getSourceDatabaseValues(vn));
				MappingXML.getDatabaseValues(database, vn);
				MappingXML.fillInLookUps(database, vn);
				// needs some work
				AutoPilot ap = new AutoPilot();
				ap.selectXPath("//shredder/database/table");
				ap.bind(vn);

				while (ap.evalXPath() != -1) {
					database.addTable(MappingXML.fillInTable(vn));
				}
				ap.resetXPath();
			} catch (XPathEvalException e) {
				throw new CrashException("Failed xpath for MappingXML", e);
			} catch (NavException e) {
				throw new CrashException("VTD-XML navigation failure parsing MappingXML", e);
			} catch (XPathParseException e) {
				throw new CrashException("Failed to parse the table of MappingXML", e);
			}

		} else {
			throw new CrashException("VTD-XML failed to parse the MappingXML. Is the Path correct: " + path);
		}
		return database;
	}

	/**
	 * Gets the error database information from the mapping.xml file.
	 * 
	 * @param vn
	 *            the VTDNav to reset back to root location once completed
	 * @return The ErrorDatabase object with the setup values from the
	 *         mapping.xml
	 * @throws XPathParseException
	 * @throws XPathEvalException
	 * @throws NavException
	 */
	private static ErrorDatabase getErrorHandling(VTDNav vn) throws XPathParseException, XPathEvalException,
			NavException {
		ErrorDatabase errorDatabase = new ErrorDatabase();
		AutoPilot apDBName = new AutoPilot();
		apDBName.selectXPath("//shredder/errordatabase/databasename");
		apDBName.bind(vn);
		errorDatabase.setDatabaseName(apDBName.evalXPathToString());
		apDBName.resetXPath();

		AutoPilot apDBPort = new AutoPilot();
		apDBPort.selectXPath("//shredder/errordatabase/port");
		apDBPort.bind(vn);
		errorDatabase.setPort(apDBPort.evalXPathToString());
		apDBPort.resetXPath();

		AutoPilot apServerName = new AutoPilot();
		apServerName.selectXPath("//shredder/errordatabase/servername");
		apServerName.bind(vn);
		errorDatabase.setServerName(apServerName.evalXPathToString());
		apServerName.resetXPath();

		AutoPilot apSchema = new AutoPilot();
		apSchema.selectXPath("//shredder/errordatabase/schema");
		apSchema.bind(vn);
		errorDatabase.setSchema(apSchema.evalXPathToString());
		apSchema.resetXPath();

		AutoPilot apUsername = new AutoPilot();
		apUsername.selectXPath("//shredder/errordatabase/username");
		apUsername.bind(vn);
		errorDatabase.setUsername(apUsername.evalXPathToString());
		apUsername.resetXPath();

		AutoPilot apPassword = new AutoPilot();
		apPassword.selectXPath("//shredder/errordatabase/password");
		apPassword.bind(vn);
		errorDatabase.setPassword(apPassword.evalXPathToString());
		apPassword.resetXPath();

		Table table = new Table();
		AutoPilot apTableName = new AutoPilot();
		apTableName.selectXPath("//shredder/errordatabase/table/name");
		apTableName.bind(vn);
		table.setTableName(apTableName.evalXPathToString());
		apTableName.resetXPath();

		AutoPilot ap = new AutoPilot();
		ap.selectXPath("//shredder/errordatabase/table/column");
		ap.bind(vn);

		while (ap.evalXPath() != -1) {
			Column column = new Column();
			AutoPilot apColumnName = new AutoPilot();
			apColumnName.selectXPath("name");
			apColumnName.bind(vn);
			column.setColumnName(apColumnName.evalXPathToString());
			apColumnName.resetXPath();

			AutoPilot apSpecialValue = new AutoPilot();
			apSpecialValue.selectXPath("specialvalue");
			apSpecialValue.bind(vn);
			column.setSpecialValue(apSpecialValue.evalXPathToString());
			apSpecialValue.resetXPath();

			AutoPilot apSQL = new AutoPilot();
			apSQL.selectXPath("sql");
			apSQL.bind(vn);
			column.setSql(apSQL.evalXPathToString());
			apSQL.resetXPath();

			AutoPilot apLookUp = new AutoPilot();
			apLookUp.selectXPath("lookup");
			apLookUp.bind(vn);
			column.setLookUpKey(apLookUp.evalXPathToString());
			apLookUp.resetXPath();
			table.addColumn(column);
		}
		errorDatabase.addTable(table);
		vn.toElement(VTDNav.ROOT);
		return errorDatabase;
	}

	/**
	 * Gets the source database information from the mapping.xml file.
	 * 
	 * @param vn
	 *            the VTDNav to reset back to root location once completed
	 * @return The Source Database object with the initialised values from the
	 *         mapping.xml file
	 * @throws XPathParseException
	 * @throws XPathEvalException
	 * @throws NavException
	 */
	private static SourceDatabase getSourceDatabaseValues(VTDNav vn) throws XPathParseException, XPathEvalException,
			NavException {
		SourceDatabase source = new SourceDatabase();
		AutoPilot apDBName = new AutoPilot();
		apDBName.selectXPath("//shredder/sourcedatabase/databasename");
		apDBName.bind(vn);
		source.setDatabaseName(apDBName.evalXPathToString());
		apDBName.resetXPath();

		AutoPilot apDBPort = new AutoPilot();
		apDBPort.selectXPath("//shredder/sourcedatabase/port");
		apDBPort.bind(vn);
		source.setPort(apDBPort.evalXPathToString());
		apDBPort.resetXPath();

		AutoPilot apServerName = new AutoPilot();
		apServerName.selectXPath("//shredder/sourcedatabase/servername");
		apServerName.bind(vn);
		source.setServerName(apServerName.evalXPathToString());
		apServerName.resetXPath();

		AutoPilot apSchema = new AutoPilot();
		apSchema.selectXPath("//shredder/sourcedatabase/schema");
		apSchema.bind(vn);
		source.setSchema(apSchema.evalXPathToString());
		apSchema.resetXPath();

		AutoPilot apUsername = new AutoPilot();
		apUsername.selectXPath("//shredder/sourcedatabase/username");
		apUsername.bind(vn);
		source.setUsername(apUsername.evalXPathToString());
		apUsername.resetXPath();

		AutoPilot apPassword = new AutoPilot();
		apPassword.selectXPath("//shredder/sourcedatabase/password");
		apPassword.bind(vn);
		source.setPassword(apPassword.evalXPathToString());
		apPassword.resetXPath();

		Table table = new Table();
		AutoPilot apTableName = new AutoPilot();
		apTableName.selectXPath("//shredder/sourcedatabase/table/name");
		apTableName.bind(vn);
		table.setTableName(apTableName.evalXPathToString());
		apTableName.resetXPath();

		AutoPilot apTableClause = new AutoPilot();
		apTableClause.selectXPath("//shredder/sourcedatabase/table/clause");
		apTableClause.bind(vn);
		table.setClause(apTableClause.evalXPathToString());
		apTableClause.resetXPath();

		AutoPilot apTableSubset = new AutoPilot();
		apTableSubset.selectXPath("//shredder/sourcedatabase/table/subset");
		apTableSubset.bind(vn);
		table.setSubset(apTableSubset.evalXPathToString());
		apTableSubset.resetXPath();

		AutoPilot ap = new AutoPilot();
		ap.selectXPath("//shredder/sourcedatabase/table/column");
		ap.bind(vn);

		while (ap.evalXPath() != -1) {
			Column column = new Column();
			AutoPilot apColumnName = new AutoPilot();
			apColumnName.selectXPath("name");
			apColumnName.bind(vn);
			column.setColumnName(apColumnName.evalXPathToString());
			apColumnName.resetXPath();

			AutoPilot apVariableLookUpKey = new AutoPilot();
			apVariableLookUpKey.selectXPath("variablelookupkey");
			apVariableLookUpKey.bind(vn);
			column.setLookUpKey(apVariableLookUpKey.evalXPathToString());
			apVariableLookUpKey.resetXPath();
			table.addColumn(column);
		}
		source.addTable(table);
		vn.toElement(VTDNav.ROOT);
		return source;
	}

	/**
	 * Gets the database information from the mapping.xml file.
	 * 
	 * @param database
	 *            The Database object which will be initialised with the values
	 *            from the mapping.xml file
	 * @param vn
	 *            the VTDNav to reset back to root location once completed
	 * @throws NavException
	 * @throws XPathParseException
	 */
	private static void getDatabaseValues(Database database, VTDNav vn) throws NavException, XPathParseException {
		AutoPilot apDBName = new AutoPilot();
		apDBName.selectXPath("//shredder/database/databasename");
		apDBName.bind(vn);
		database.setDatabaseName(apDBName.evalXPathToString());
		apDBName.resetXPath();

		AutoPilot apDBPort = new AutoPilot();
		apDBPort.selectXPath("//shredder/database/port");
		apDBPort.bind(vn);
		database.setPort(apDBPort.evalXPathToString());
		apDBPort.resetXPath();

		AutoPilot apServerName = new AutoPilot();
		apServerName.selectXPath("//shredder/database/servername");
		apServerName.bind(vn);
		database.setServerName(apServerName.evalXPathToString());
		apServerName.resetXPath();

		AutoPilot apSchema = new AutoPilot();
		apSchema.selectXPath("//shredder/database/schema");
		apSchema.bind(vn);
		database.setSchema(apSchema.evalXPathToString());
		apSchema.resetXPath();

		AutoPilot apUsername = new AutoPilot();
		apUsername.selectXPath("//shredder/database/username");
		apUsername.bind(vn);
		database.setUsername(apUsername.evalXPathToString());
		apUsername.resetXPath();

		AutoPilot apPassword = new AutoPilot();
		apPassword.selectXPath("//shredder/database/password");
		apPassword.bind(vn);
		database.setPassword(apPassword.evalXPathToString());
		apPassword.resetXPath();

		vn.toElement(VTDNav.ROOT);
	}

	/**
	 * Gets the database look up information from the mapping.xml file.
	 * 
	 * @param database
	 *            The Database object which will be initialised with the values
	 *            from the mapping.xml file
	 * @param vn
	 *            the VTDNav to reset back to root location once completed
	 * @throws XPathParseException
	 * @throws XPathEvalException
	 * @throws NavException
	 */
	private static void fillInLookUps(Database database, VTDNav vn) throws XPathParseException, XPathEvalException,
			NavException {

		AutoPilot ap = new AutoPilot();
		ap.selectXPath("//shredder/database/lookupfield");
		ap.bind(vn);

		while (ap.evalXPath() != -1) {
			AutoPilot apKey = new AutoPilot();
			apKey.selectXPath("key");
			apKey.bind(vn);
			String key = apKey.evalXPathToString();
			apKey.resetXPath();

			AutoPilot apValue = new AutoPilot();
			apValue.selectXPath("value");
			apValue.bind(vn);
			String value = apValue.evalXPathToString();
			apValue.resetXPath();

			AutoPilot apXpath = new AutoPilot();
			apXpath.selectXPath("xpath");
			apXpath.bind(vn);
			String xpath = apXpath.evalXPathToString();
			apXpath.resetXPath();

			AutoPilot apVariable = new AutoPilot();
			apVariable.selectXPath("variable");
			apVariable.bind(vn);
			String variable = apVariable.evalXPathToString();
			apVariable.resetXPath();

			database.addLookUpValue(key, new LookUp(xpath, value, variable));
		}
		vn.toElement(VTDNav.ROOT);
	}

	/**
	 * Fills in the table information from the mapping.xml file
	 * 
	 * @param vn
	 *            the VTDNav to reset back to root location once completed
	 * @return The initialised table with the values from the mapping.xml file.
	 * @throws XPathParseException
	 * @throws XPathEvalException
	 * @throws NavException
	 */
	private static Table fillInTable(VTDNav vn) throws XPathParseException, XPathEvalException, NavException {
		Table table = new Table();

		AutoPilot apName = new AutoPilot();
		apName.selectXPath("name");
		apName.bind(vn);
		table.setTableName(apName.evalXPathToString());
		apName.resetXPath();

		AutoPilot apROOT = new AutoPilot();
		apROOT.selectXPath("rootxpath");
		apROOT.bind(vn);
		table.setXpathROOT(apROOT.evalXPathToString());
		apROOT.resetXPath();

		AutoPilot ap = new AutoPilot();
		ap.selectXPath("column");
		ap.bind(vn);

		while (ap.evalXPath() != -1) {
			Column column = new Column();

			AutoPilot name = new AutoPilot();
			name.selectXPath("name");
			name.bind(vn);
			column.setColumnName(name.evalXPathToString());
			name.resetXPath();

			AutoPilot xpath = new AutoPilot();
			xpath.selectXPath("xpath");
			xpath.bind(vn);
			column.setXpath(xpath.evalXPathToString());
			xpath.resetXPath();

			AutoPilot type = new AutoPilot();
			type.selectXPath("type");
			type.bind(vn);
			column.setType(type.evalXPathToString());
			type.resetXPath();

			AutoPilot lookUpKey = new AutoPilot();
			lookUpKey.selectXPath("lookup");
			lookUpKey.bind(vn);
			column.setLookUpKey(lookUpKey.evalXPathToString());
			lookUpKey.resetXPath();

			table.addColumn(column);
		}

		return table;
	}
}
