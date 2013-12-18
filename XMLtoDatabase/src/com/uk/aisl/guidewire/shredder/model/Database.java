package com.uk.aisl.guidewire.shredder.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Database {

	private ArrayList<Table> tables;
	private SourceDatabase source;
	private ErrorDatabase error;
	
	private String databaseName;
	private String serverName;
	private String port;
	private String schema;
	private String username;
	private String password;
	
	private HashMap<String, LookUp> lookUps;
	private ArrayList<String> lookUpKeys;
	private String XML;

	public Database() {
		this.tables = new ArrayList<Table>();
		this.lookUps = new HashMap<String, LookUp>(97);
		this.lookUpKeys = new ArrayList<String>(50);
	}

	public void addTable(Table table) {
		this.tables.add(table);
	}

	public ArrayList<Table> getTables() {
		return this.tables;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}
	
	public void addLookUpValue(String key, LookUp lookUp) {
		this.lookUps.put(key, lookUp);
		this.lookUpKeys.add(key);
	}
	
	public String getLookUpXpath(String key) {
		return this.lookUps.get(key).getXpath();
	}
	
	public String getLookUpVariable(String key) {
		return this.lookUps.get(key).getVariable();
	}
		
	public String getLookUpValue(String key) {
		LookUp lookup = this.lookUps.get(key);
		if(lookup == null) {
			return null;
		} else {
			return lookup.getValue();
		}
	}
	
	public void setLookUpValue(String key, String value) {
		this.lookUps.get(key).setValue(value);
	}
	
	public ArrayList<String> getLookUpKeys() {
		return this.lookUpKeys;
	}

	public void cleanDown() {
		this.XML = null;
		for(String key: this.lookUpKeys) {
			this.lookUps.get(key).setValue(null);
		}
		//this.lookUpKeys = new ArrayList<String>(50);
		for(Table table: this.tables) {
			table.cleanDown();
		}
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}
	
	public String getSchema() {
		return this.schema;
	}

	public SourceDatabase getSource() {
		return source;
	}

	public void setSource(SourceDatabase source) {
		this.source = source;
	}
	
	public ErrorDatabase getError() {
		return this.error;
	}
	
	public void setError(ErrorDatabase error) {
		this.error = error;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setXML(XMLReturn returnXML) {
		this.XML = returnXML.getXmlPayload();
		
	}

	public String getXML() {
		return XML;
	}
}
