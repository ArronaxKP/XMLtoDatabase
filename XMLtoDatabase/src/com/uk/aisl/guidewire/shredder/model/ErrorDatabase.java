package com.uk.aisl.guidewire.shredder.model;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

import com.uk.aisl.guidewire.shredder.exception.Logger;

public class ErrorDatabase {

	private Database database;
	private String databaseName;
	private String serverName;
	private String port;
	private String schema;
	private String username;
	private String password;
	private Table table;

	public ErrorDatabase(Database database) {
		this.database = database;
		//this.columns = new ArrayList<Column>();
	}
	
	public String getErrorSQLString(Exception e) {
		ArrayList<Column> columns = this.table.getColumns();
		int size = columns.size();
		StringBuffer values = new StringBuffer();
		StringBuffer columnNames = new StringBuffer();
		for (int i = 0; i < size; i++) {
			if (i > 1) {
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
		//TODO change this to dynamic built.
		StringBuffer buff = new StringBuffer();
		buff.append("INSERT INTO [").append(database.getSchema()).append("].[").append(this.table.getName()).append("] (");
		buff.append(columnNames.toString()).append(") VALUES (");
		buff.append(values.toString()).append(")");
		return buff.toString();
	}
	
	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setTable(Table table) {
		this.table = table;
	}
}
