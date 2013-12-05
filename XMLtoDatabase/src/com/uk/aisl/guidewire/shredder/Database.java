package com.uk.aisl.guidewire.shredder;

import java.util.ArrayList;
import java.util.HashMap;

public class Database {

	private ArrayList<Table> tables;
	
	private String databaseName;
	private String serverName;
	private String port;
	
	private HashMap<String,String> lookUps;

	public Database() {
		this.tables = new ArrayList<Table>();
		lookUps = new HashMap<String,String>(97);
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
	
	public void addLookUpValue(String key, String value) {
		this.lookUps.put(key, value);
	}
	
	public String getLookUpValue(String key) {
		return this.lookUps.get(key);
	}
}
