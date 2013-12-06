package com.uk.aisl.guidewire.shredder.model;

import java.util.ArrayList;

public class Column {
	private String columnName;
	private String xpath;
	private String type;
	private ArrayList<String> values;
	private String lookUpKey;

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getXpath() {
		return xpath;
	}

	public void setXpath(String xpath) {
		this.xpath = xpath;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void addValue(String value) {
		if (this.values == null) {
			this.values = new ArrayList<String>();
		}
		this.values.add(value);
	}

	public String getValue(int index) {
		String tempValue = this.values.get(index);
		this.values.set(index, null);
		return tempValue;
	}

	public int size() {
		return values.size();
	}

	public String getLookUpKey() {
		return this.lookUpKey;
	}
	
	public void setLookUpKey(String lookUpKey) {
		this.lookUpKey = lookUpKey;
	}

	public void cleanDown() {
		this.values = null;
	}
}
