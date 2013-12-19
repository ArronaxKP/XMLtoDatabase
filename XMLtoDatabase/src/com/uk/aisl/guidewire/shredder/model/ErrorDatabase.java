package com.uk.aisl.guidewire.shredder.model;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.uk.aisl.guidewire.shredder.database.Loader;

/**
 * The ErrorDatabase wrapping object
 * 
 * @author Karl Parry
 *
 */
public class ErrorDatabase extends SuperDatabase {

	private static Logger logger = LogManager.getLogger(ErrorDatabase.class.getName());

	private Database database;

	/**
	 * Links the error database to the database object
	 * 
	 * @param database
	 *            The database object
	 */
	public void setDatabase(Database database) {
		this.database = database;
	}

	/**
	 * Creates the error SQL string to insert into the error XML
	 * 
	 * @param e
	 * @return
	 */
	public String getErrorSQLString(Exception exception) {
		ArrayList<Column> columns = this.getTable().getColumns();
		int size = columns.size();
		StringBuffer values = new StringBuffer();
		StringBuffer columnNames = new StringBuffer();
		for (int i = 0; i < size; i++) {
			if (i > 0) {
				values.append(",");
				columnNames.append(",");
			}
			Column column = columns.get(i);
			columnNames.append("[").append(column.getColumnName()).append("]");
			String sqlString = column.getSql();
			if (!(sqlString == null || sqlString.equals(""))) {
				// Value should be look up value
				values.append(sqlString);
				continue;
			}
			String lookUpKey = column.getLookUpKey();
			if (!(lookUpKey == null || lookUpKey.equals(""))) {
				values.append(Loader.guessValueTypeNoComma(database.getLookUpValue(lookUpKey)));
				continue;
			}
			String specialValue = column.getSpecialValue();
			if (!(specialValue == null || specialValue.equals(""))) {
				if (specialValue.equals("XML")) {
					values.append("'").append(database.getXML().replace("'", "''")).append("'");
					continue;
				}
				if (specialValue.equals("ERROR")) {
					StringWriter errors = new StringWriter();
					exception.printStackTrace(new PrintWriter(errors));
					String error = exception.getMessage() + ":" + errors.toString();
					if (error.contains("duplicate key")) {
						values.append("'").append("Duplicate Record").append("'");
					} else {
						values.append("'").append("Error Loading table").append("'");
					}
					continue;
				}
				values.append("null");
				logger.error("SpecialValue look up failed (only XML & ERROR supported): " + specialValue);
				continue;
			}
		}
		StringBuffer buff = new StringBuffer();
		buff.append("INSERT INTO [" + this.databaseName + "].[").append(this.schema).append("].[")
				.append(this.getTable().getName()).append("] (");
		buff.append(columnNames.toString()).append(") VALUES (");
		buff.append(values.toString()).append(")");
		return buff.toString();
	}
}
