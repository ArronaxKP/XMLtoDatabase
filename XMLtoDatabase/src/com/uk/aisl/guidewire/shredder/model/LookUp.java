package com.uk.aisl.guidewire.shredder.model;

/**
 * The LookUp value that is used to link the variable from the database, xpath
 * for the XML and the value.
 * 
 * @author Karl Parry
 * 
 */
public class LookUp {

	private String xpath = null;
	private String value = null;
	private String variable = null;

	/**
	 * Constructor for the LookUp object
	 * 
	 * @param xpath
	 *            The Xpath value for the lookup (from the ROOT node)
	 * @param value
	 *            The actually value
	 * @param variable
	 *            The variable from the source database select
	 */
	public LookUp(String xpath, String value, String variable) {
		this.xpath = xpath;
		this.value = value;
		this.setVariable(variable);
	}

	/**
	 * Returns the Xpath String from the ROOT directory
	 * 
	 * @return The Xpath string
	 */
	public String getXpath() {
		return xpath;
	}

	/**
	 * Sets the Xpath String
	 * 
	 * @param xpath
	 *            Xpath from the ROOT element
	 */
	public void setXpath(String xpath) {
		this.xpath = xpath;
	}

	/**
	 * Gets the value
	 * 
	 * @returnv The value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the value
	 * 
	 * @param value
	 *            The value
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Gets the variable from the Database Look up
	 * 
	 * @returnv The variable
	 */
	public String getVariable() {
		return variable;
	}

	/**
	 * Sets the variable from the Database Look up
	 * 
	 * @param variable
	 *            The variable
	 */
	public void setVariable(String variable) {
		this.variable = variable;
	}

	/**
	 * Cleans down the look up value so it can re-set with the next XML.
	 */
	public void resetValue() {
		if (value == null) {
			if (this.xpath == null || this.xpath.equals("")) {
				if (this.variable == null || this.variable.equals("")) {
					// The value was from mapper.xml so do not reset
				} else {
					this.value = null;
				}
			}
		}
	}
}
