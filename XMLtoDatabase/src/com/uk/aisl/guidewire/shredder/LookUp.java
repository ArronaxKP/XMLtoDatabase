package com.uk.aisl.guidewire.shredder;

public class LookUp {

	private String xpath = null;
	private String value = null;

	public LookUp(String xpath, String value) {
		this.xpath = xpath;
		this.value = value;
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

}
