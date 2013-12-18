package com.uk.aisl.guidewire.shredder.model;

import java.util.ArrayList;
import java.util.HashMap;

import com.uk.aisl.guidewire.shredder.database.Loader;

public class Database extends SuperDatabase {

	private SourceDatabase source;
	private ErrorDatabase error;

	private HashMap<String, LookUp> lookUps;
	private ArrayList<String> lookUpKeys;
	private String XML;

	public Database() {
		this.lookUps = new HashMap<String, LookUp>(97);
		this.lookUpKeys = new ArrayList<String>(50);
	}

	public void addLookUpValue(String key, LookUp lookUp) {
		this.lookUps.put(key, lookUp);
		this.lookUpKeys.add(key);
	}

	public String getLookUpXpath(String key) {
		return this.lookUps.get(key).getXpath();
	}

	public String getLookUpVariable(String key) {
		return this.lookUps.get(key).getVariable();
	}

	public String getLookUpValue(String key) {
		LookUp lookup = this.lookUps.get(key);
		if (lookup == null) {
			return null;
		} else {
			return lookup.getValue();
		}
	}

	public void setLookUpValue(String key, String value) {
		this.lookUps.get(key).setValue(value);
	}

	public ArrayList<String> getLookUpKeys() {
		return this.lookUpKeys;
	}

	public void cleanDown() {
		this.XML = null;
		for (String key : this.lookUpKeys) {
			this.lookUps.get(key).setValue(null);
		}
		// this.lookUpKeys = new ArrayList<String>(50);
		for (Table table : this.tables) {
			table.cleanDown();
		}
	}

	public SourceDatabase getSource() {
		return source;
	}

	public void setSource(SourceDatabase source) {
		source.setDatabase(this);
		this.source = source;
	}

	public ErrorDatabase getError() {
		return this.error;
	}

	public void setError(ErrorDatabase error) {
		error.setDatabase(this);
		this.error = error;
	}

	public void setXML(XMLReturn returnXML) {
		this.XML = returnXML.getXmlPayload();

	}

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
	 * @param database
	 *            The database wrapper object that wraps all database
	 *            information (source, error & target) and the destination
	 *            values
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
