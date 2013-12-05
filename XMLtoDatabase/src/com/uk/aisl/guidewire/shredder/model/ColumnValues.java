package com.uk.aisl.guidewire.shredder.model;

import java.util.ArrayList;

public class ColumnValues {
	private ArrayList<String> values;

	public void addValue(String value) {
		if (this.values == null) {
			this.values = new ArrayList<String>();
		}
		this.values.add(value);
	}

	public synchronized String getValue(int index) {
		String tempValue = this.values.get(index);
		this.values.set(index, null);
		return tempValue;
	}

	public synchronized int size() {
		return values.size();
	}
}
