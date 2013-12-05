package com.uk.aisl.guidewire.shredder.model;

import java.util.ArrayList;

public class DatabaseValues {

	private ArrayList<TableValues> tables;

	public DatabaseValues() {
		this.tables = new ArrayList<TableValues>();
	}

	public void addTable(TableValues table) {
		this.tables.add(table);
	}

	public ArrayList<TableValues> getTables() {
		return this.tables;
	}
	
	public TableValues getTable(int index) {
		return this.tables.get(index);
	}

	public int getNumberOfTables() {
		return this.tables.size();
	}
}
