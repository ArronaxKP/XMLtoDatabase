package com.uk.aisl.guidewire.shredder.model;

import java.util.ArrayList;

/**
 * Wrapper for the SourceDatabse with the only method being the
 * getSourceSQLQuery that builds the select statement for getting the pay load
 * XML
 * 
 * @author Karl
 * 
 */
public class SourceDatabase extends SuperDatabase {

	/**
	 * Builds the SourceDatabase SQL Query that will be used to get all the XML
	 * pay loads.
	 * 
	 * @return The SQL Select query
	 */
	public String getSourceSQLQuery() {
		Table table = this.getTable();
		ArrayList<Column> columns = table.getColumns();
		StringBuffer buff = new StringBuffer();
		buff.append("SELECT ").append(table.getSubset()).append(" ");
		for (int i = 0; i < columns.size(); i++) {
			if (i != 0) {
				buff.append(", ");
			}
			buff.append(columns.get(i).getColumnName());
		}
		buff.append(" FROM [").append(this.getSchema()).append("].[").append(table.getName()).append("] ")
				.append(table.getClause());
		return buff.toString();
	}
}
