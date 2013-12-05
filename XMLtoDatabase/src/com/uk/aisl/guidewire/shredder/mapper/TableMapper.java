package com.uk.aisl.guidewire.shredder.mapper;

import java.util.ArrayList;

public class TableMapper {
	
	private String tableName;
	private String xpathROOT;
	private ArrayList<ColumnMapper> columns;
	private int columnCount = 0;


	public TableMapper() {
		this.columns = new ArrayList<ColumnMapper>();
	}

	public synchronized void addColumn(ColumnMapper column) {
		this.columns.add(column);
		this.columnCount++;
	}
	
	public synchronized ArrayList<ColumnMapper> getColumns(){
		return this.columns;
	}
	
	public synchronized String getTableName() {
		return tableName;
	}

	public synchronized void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public synchronized String getXpathROOT() {
		return xpathROOT;
	}

	public synchronized void setXpathROOT(String xpathROOT) {
		this.xpathROOT = xpathROOT;
	}

	public synchronized ColumnMapper getColumn(int index) {
		return this.columns.get(index);
	}
	
	public synchronized int getColumnCount(){
		return this.columnCount;
	}

}
