package com.uk.aisl.guidewire.shredder.model;

import java.util.ArrayList;

public class TableValues {
	
	private ArrayList<ColumnValues> columns;

	public TableValues() {
		this.columns = new ArrayList<ColumnValues>();
	}

	public void addColumn(ColumnValues column) {
		this.columns.add(column);
	}
	
	public ArrayList<ColumnValues> getColumns(){
		return this.columns;
	}

	public ColumnValues getColumn(int index) {
		return this.columns.get(index);
	}
	
	public int getNumberOfColumns() {
		return this.columns.size();
	}
}
