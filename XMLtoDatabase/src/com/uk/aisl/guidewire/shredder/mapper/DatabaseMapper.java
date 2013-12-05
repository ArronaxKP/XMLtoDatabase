package com.uk.aisl.guidewire.shredder.mapper;

import java.util.ArrayList;

import com.uk.aisl.guidewire.shredder.model.ColumnValues;
import com.uk.aisl.guidewire.shredder.model.DatabaseValues;
import com.uk.aisl.guidewire.shredder.model.TableValues;

public class DatabaseMapper {

	private ArrayList<TableMapper> tables;
	private int tableCount = 0;
	
	public DatabaseMapper() {
		this.tables = new ArrayList<TableMapper>();
	}

	public synchronized void addTable(TableMapper table) {
		this.tables.add(table);
		this.tableCount++;
	}

	public synchronized ArrayList<TableMapper> getTables() {
		return this.tables;
	}
	
	public synchronized TableMapper getTable(int index) {
		return this.tables.get(index);
	}
	
	public synchronized DatabaseValues generateBlankDatabase(){
		DatabaseValues database = new DatabaseValues();
		int numberOfTables = this.tableCount;
		for(int tableCount = 0; tableCount < numberOfTables; tableCount++) {
			TableValues table = new TableValues();
			int numberOfColumns = table.getNumberOfColumns();
			for(int columnCount = 0; columnCount < numberOfColumns; columnCount++) {
				ColumnValues column = new ColumnValues();
				table.addColumn(column);
			}
			database.addTable(table);
		}
		
		return database;
	}
}
