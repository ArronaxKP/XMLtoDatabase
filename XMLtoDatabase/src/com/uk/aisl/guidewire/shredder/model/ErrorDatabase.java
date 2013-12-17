package com.uk.aisl.guidewire.shredder.model;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

import com.uk.aisl.guidewire.shredder.exception.Logger;

public class ErrorDatabase {

	private ArrayList<Column> columns;
	private String errorSQLString;
	private Database database;

	public ErrorDatabase(Database database) {
		this.database = database;
		this.columns = new ArrayList<Column>();
	}

	public void setErrorSQLString(String errorSQLString) {
		this.errorSQLString = errorSQLString;
	}

	public String getErrorSQLString(Exception e) {
		int size = this.columns.size();
		StringBuffer values = new StringBuffer();
		StringBuffer columnNames = new StringBuffer();
		for (int i = 0; i < size; i++) {
			if (i > 1) {
				values.append(",");
				columnNames.append(",");
			}
			Column column = this.columns.get(i);
			columnNames.append("[").append(column.getColumnName()).append("]");
			String sqlString = column.getSql();
			if (!(sqlString == null || sqlString.equals(""))) {
				// Value should be look up value
				values.append(sqlString);
				continue;
			}
			String specialValue = column.getLookUpKey();
			if (!(specialValue == null || specialValue.equals(""))) {
				if (specialValue.equals("XML")) {
					values.append(database.getXML());
					continue;
				}
				if (specialValue.equals("ERROR")) {
					values.append(e.getMessage()).append("\n\r");
					StringWriter errors = new StringWriter();
					e.printStackTrace(new PrintWriter(errors));
					values.append(errors.toString());
					continue;
				}
				values.append("null");
				Logger.error("SpecialValue look up failed (only XML & ERROR supported): " + specialValue);
				continue;
			}

			String lookUpKey = column.getLookUpKey();
			if (!(lookUpKey == null || lookUpKey.equals(""))) {
				// Value should be look up value
				values.append(database.getLookUpValue(lookUpKey));
				continue;
			}

		}
		this.errorSQLString = this.errorSQLString.replace("?VALUES?", values.toString()).replace("?COLUMNS?",
				columnNames.toString());
		return this.errorSQLString;
	}

	public void addColumn(Column column) {
		this.columns.add(column);
	}
}
