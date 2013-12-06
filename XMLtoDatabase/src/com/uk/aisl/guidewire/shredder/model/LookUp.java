package com.uk.aisl.guidewire.shredder.model;

public class LookUp {

	private String xpath = null;
	private String value = null;
	private String variable = null;

	public LookUp(String xpath, String value, String variable) {
		this.xpath = xpath;
		this.value = value;
		this.setVariable(variable);
	}

	public String getXpath() {
		return xpath;
	}

	public void setXpath(String xpath) {
		this.xpath = xpath;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public void resetValue() {
		if(value == null) {
			if(this.xpath == null || this.xpath.equals("")) {
				if(this.variable == null || this.variable.equals("")){
					//The value was from mapper.xml so do not reset
				} else {
					this.value = null;
				}
			}
		}
	}

	public String getVariable() {
		return variable;
	}

	public void setVariable(String variable) {
		this.variable = variable;
	}

}
