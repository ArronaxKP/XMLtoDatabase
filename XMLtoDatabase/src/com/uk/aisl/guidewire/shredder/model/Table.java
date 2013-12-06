package com.uk.aisl.guidewire.shredder.model;

import java.util.ArrayList;

public class Table {
	
	private String tableName;
	private String xpathROOT;
	private ArrayList<Column> columns;
	private String clause;

	public Table() {
		this.columns = new ArrayList<Column>();
	}

	public String getName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getXpathROOT() {
		return xpathROOT;
	}

	public void setXpathROOT(String xpathROOT) {
		this.xpathROOT = xpathROOT;
	}

	public void addColumn(Column column) {
		this.columns.add(column);
	}
	
	public ArrayList<Column> getColumns(){
		return this.columns;
	}

	public void cleanDown() {
		for(Column column: this.columns) {
			column.cleanDown();
		}
	}

	public String getClause() {
		return clause;
	}

	public void setClause(String clause) {
		this.clause = clause;
	}
}
