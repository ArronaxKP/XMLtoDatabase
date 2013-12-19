package com.uk.aisl.guidewire.shredder.model;

import java.util.ArrayList;
import java.util.HashMap;

import com.uk.aisl.guidewire.shredder.database.Loader;

/**
 * The master mapping.xml wrapper. It contains all the values, look ups etc.
 * 
 * @author Karl Parry
 * 
 */
public class Database extends SuperDatabase {

	private SourceDatabase source;
	private ErrorDatabase error;

	private HashMap<String, LookUp> lookUps;
	private ArrayList<String> lookUpKeys;
	private String XML;

	/**
	 * Constructor that inistialises the LookUps to a maximum of 45 look ups.
	 * More is supported but may be slower.
	 */
	public Database() {
		this.lookUps = new HashMap<String, LookUp>(97);
		this.lookUpKeys = new ArrayList<String>(45);
	}

	/**
	 * This sets the Lookup object to an attacked key.
	 * 
	 * @param key
	 *            Key for the look up values
	 * @param lookUp
	 *            The look up object containing xpath, value etc.
	 */
	public void addLookUpValue(String key, LookUp lookUp) {
		this.lookUps.put(key, lookUp);
		this.lookUpKeys.add(key);
	}

	/**
	 * Gets the Xpath value using the look up key
	 * 
	 * @param key
	 *            The key for the look up
	 * @return The Xpath string
	 */
	public String getLookUpXpath(String key) {
		return this.lookUps.get(key).getXpath();
	}

	/**
	 * Gets the variable (from the variable from the source database) value
	 * using the look up key
	 * 
	 * @param key
	 *            The key for the look up
	 * @return The variable string
	 */
	public String getLookUpVariable(String key) {
		return this.lookUps.get(key).getVariable();
	}

	/**
	 * Gets the value using the look up key
	 * 
	 * @param key
	 *            The key for the look up
	 * @return The value string
	 */
	public String getLookUpValue(String key) {
		LookUp lookup = this.lookUps.get(key);
		if (lookup == null) {
			return null;
		} else {
			return lookup.getValue();
		}
	}

	/**
	 * Sets the look up value with the Key to the value
	 * 
	 * @param key
	 *            The key for the look up
	 * @param value
	 *            The Value
	 */
	public void setLookUpValue(String key, String value) {
		this.lookUps.get(key).setValue(value);
	}

	/**
	 * Gets the list of all the keys for the look up values, variables and xpath
	 * 
	 * @return List of keys for the look up
	 */
	public ArrayList<String> getLookUpKeys() {
		return this.lookUpKeys;
	}

	/**
	 * Cleans the database object so it is clean for the next XML
	 */
	public void cleanDown() {
		this.XML = null;
		for (String key : this.lookUpKeys) {
			this.lookUps.get(key).setValue(null);
		}
		for (Table table : this.tables) {
			table.cleanDown();
		}
	}

	/**
	 * Gets the source database details
	 * 
	 * @return The SourceDatabase object
	 */
	public SourceDatabase getSource() {
		return source;
	}

	/**
	 * Sets the source database details
	 * 
	 * @param source
	 *            The SourceDatabase
	 */
	public void setSource(SourceDatabase source) {
		this.source = source;
	}

	/**
	 * Gets the error database details
	 * 
	 * @return The ErrorDatabase object
	 */
	public ErrorDatabase getError() {
		return this.error;
	}

	/**
	 * Sets the error database details
	 * 
	 * @param error
	 *            The ErrorDatabase
	 */
	public void setError(ErrorDatabase error) {
		error.setDatabase(this);
		this.error = error;
	}

	/**
	 * Sets the XMLReturn object containing the XML so it can be used in the
	 * ErrorHandling.
	 * 
	 * @param returnXML
	 *            The XMLReturn object
	 */
	public void setXML(XMLReturn returnXML) {
		this.XML = returnXML.getXmlPayload();

	}

	/**
	 * Get the XML in a string form.
	 * 
	 * @return Returns the XML Pay load
	 */
	public String getXML() {
		return XML;
	}

	/**
	 * Creates the insert statements dynamically based on the structure and
	 * order outlined in mapping.xml. <br/>
	 * <br/>
	 * This will correctly map the values to the column/table name and their
	 * corresponding values
	 * 
	 * @return A list of string insert statements
	 */
	public ArrayList<String> createStatements() {
		ArrayList<String> statements = new ArrayList<String>();
		ArrayList<Table> tables = this.getTables();
		StringBuffer buff = new StringBuffer();

		for (Table table : tables) {
			ArrayList<Column> columns = table.getColumns();
			Column currentColumn = columns.get(0);
			int numberOfRows = currentColumn.size();
			if (numberOfRows == 0) {
				continue;
			}
			buff.append("INSERT INTO [" + this.getSchema() + "].[" + table.getName() + "] (");

			for (Column c : columns) {
				buff.append("[" + c.getColumnName() + "],");
			}
			buff.deleteCharAt(buff.length() - 1);
			buff.append(") VALUES ");

			for (int row = 0; row < numberOfRows; row++) {
				buff.append("(");

				for (int columnCounter = 0; columnCounter < columns.size(); columnCounter++) {
					Column column = columns.get(columnCounter);
					if (column.getType() == null || column.getType().equals("")) {
						buff.append(Loader.guessValueType(column.getValue(row)));
					} else {
						buff.append(column.getType().replace("?", "'" + column.getValue(row) + "'") + ",");
					}
				}
				buff.deleteCharAt(buff.length() - 1);
				buff.append("), ");
			}
			buff.deleteCharAt(buff.length() - 2);
			statements.add(buff.toString());
			buff.setLength(0);

		}
		return statements;
	}
}
