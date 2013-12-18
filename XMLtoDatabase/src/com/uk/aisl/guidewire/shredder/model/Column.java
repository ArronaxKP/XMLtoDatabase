package com.uk.aisl.guidewire.shredder.model;

import java.util.ArrayList;

/**
 * The Java model that represents all the object model for a specific column.
 * These are used in the source, error and core database objects
 * 
 * @author Karl Parry
 * 
 */
public class Column {

	private String columnName;
	private String xpath;
	private String type;
	private ArrayList<String> values;
	private String lookUpKey;
	private String sql;
	private String specialValue;

	/**
	 * Constructor that initialises the values ArrayList
	 */
	public Column() {
		this.values = new ArrayList<String>();
	}

	/**
	 * Gets the column name
	 * 
	 * @return The column name
	 */
	public String getColumnName() {
		return columnName;
	}

	/**
	 * Sets the column name
	 * 
	 * @param columnName
	 *            The column name
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	/**
	 * Get the XPath used to get the value for this column.
	 * 
	 * @return The Xpath String
	 */
	public String getXpath() {
		return xpath;
	}

	/**
	 * Sets the Xpath String for the value of this column
	 * 
	 * @param xpath
	 *            The Xpath string
	 */
	public void setXpath(String xpath) {
		this.xpath = xpath;
	}

	/**
	 * Retrieves the Type of the column. This is SQL cast. This string will be
	 * used by replacing the '?' character with the value <br/>
	 * <br/>
	 * e.g. CAST(? AS XML) etc.
	 * 
	 * @return The Type string
	 */
	public String getType() {
		return type;
	}

	/**
	 * Set the Type of the column. This is SQL cast. This string will be used by
	 * replacing the '?' character in the type with the value <br/>
	 * <br/>
	 * e.g. CAST(? AS XML) etc.
	 * 
	 * 
	 * @param type
	 *            The Type string
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Adds a value to the list of values for the column.
	 * 
	 * @param value
	 *            The value to add.
	 */
	public void addValue(String value) {
		this.values.add(value);
	}

	/**
	 * Gets the value from the values ArrayList at the index
	 * 
	 * @param index
	 *            The index of the value to retrieve
	 * @return The value
	 */
	public String getValue(int index) {
		return this.values.get(index);
	}

	/**
	 * The number of values (records) in the column
	 * 
	 * @return Returns 0 if values ArrayList is not initialised otherwise it
	 *         calls values.size();
	 */
	public int size() {
		if (values == null) {
			return 0;
		}
		return values.size();
	}

	/**
	 * Returns the look up key that is used to find the value for the the
	 * column's value
	 * 
	 * @return The Look up key to find the value for the column
	 */
	public String getLookUpKey() {
		return this.lookUpKey;
	}

	/**
	 * Sets the look up key that is used to find the value of the column. This
	 * key is used to find the value for the column in the look up list of
	 * values
	 * 
	 * @param lookUpKey
	 *            The Look up key to find the value for the column
	 */
	public void setLookUpKey(String lookUpKey) {
		this.lookUpKey = lookUpKey;
	}

	/**
	 * Cleans down the value table - initialises a new ArrayList of values
	 */
	public void cleanDown() {
		this.values = new ArrayList<String>();
	}

	/**
	 * Returns the SQL that is used instead of the value e.g. GETDATE().
	 * 
	 * @return the SQL string
	 */
	public String getSql() {
		return sql;
	}

	/**
	 * Sets the SQL String that is used for the value. <br/>
	 * <br/>
	 * **Please ensure it is valid SQL <br/>
	 * e.g. INSERT INTO (COLUMNNAME) VALUES (SQL HERE)
	 * 
	 * @param sql
	 *            The SQL String
	 */
	public void setSql(String sql) {
		this.sql = sql;
	}

	/**
	 * This is the special value that is used for error handling. Only XML and
	 * ERROR values are currently supported.
	 * 
	 * @return
	 */
	public String getSpecialValue() {
		return specialValue;
	}

	/**
	 * Set the special value that is used in the error handling insert.
	 * 
	 * @param specialValue
	 *            Only XML and ERROR supported.
	 */
	public void setSpecialValue(String specialValue) {
		this.specialValue = specialValue;
	}
}
