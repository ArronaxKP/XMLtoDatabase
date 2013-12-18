package com.uk.aisl.guidewire.shredder.model;

import java.util.ArrayList;

public abstract class SuperDatabase {

	protected ArrayList<Table> tables;
	protected String databaseName;
	protected String serverName;
	protected String port;
	protected String schema;
	protected String username;
	protected String password;

	public SuperDatabase(){
		this.tables = new ArrayList<Table>();
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

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
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

	public ArrayList<Table> getTables() {
		return tables;
	}

	public void setTables(ArrayList<Table> tables) {
		this.tables = tables;
	}
	
	public void addTable(Table table) {
		this.tables.add(table);
	}
	
	public Table getTable(){
		if(tables.size()>0){
			return this.tables.get(0);
		} else {
			return null;
		}
	}
}
