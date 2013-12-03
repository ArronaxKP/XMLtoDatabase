package com.uk.aisl.guidewire.shredder;

import java.util.ArrayList;

public class Database {
	private ArrayList<Table> tables;

	public Database() {
		this.tables = new ArrayList<Table>();
	}

	public void addTable(Table table) {
		this.tables.add(table);
	}

	public ArrayList<Table> getTables() {
		return this.tables;
	}
}
