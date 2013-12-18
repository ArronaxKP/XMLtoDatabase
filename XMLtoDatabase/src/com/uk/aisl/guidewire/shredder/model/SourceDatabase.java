package com.uk.aisl.guidewire.shredder.model;

import java.util.ArrayList;

public class SourceDatabase extends SuperDatabase {

	private Database database;
	
	public String getSourceSQLQuery(){
		SourceDatabase source = database.getSource();
		Table table = source.getTable();
		ArrayList<Column> columns = table.getColumns();
		StringBuffer buff = new StringBuffer();
		buff.append("SELECT ").append(table.getSubset()).append(" ");
		for (int i = 0; i < columns.size(); i++) {
			if (i != 0) {
				buff.append(", ");
			}
			buff.append(columns.get(i).getColumnName());
		}
		buff.append(" FROM [").append(source.getSchema()).append("].[").append(table.getName()).append("] ")
				.append(table.getClause());
		return buff.toString();
	}

	public void setDatabase(Database database) {
		this.database = database;
		
	}
	
}
