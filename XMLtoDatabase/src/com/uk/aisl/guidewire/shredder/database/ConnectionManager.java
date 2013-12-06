package com.uk.aisl.guidewire.shredder.database;

import java.sql.Connection;
import java.sql.SQLException;

import com.microsoft.sqlserver.jdbc.*;
import com.uk.aisl.guidewire.shredder.model.Database;
import com.uk.aisl.guidewire.shredder.model.SourceDatabase;

public class ConnectionManager {

	private static ConnectionManager instance = null;
	private SQLServerDataSource targetDatabase = new SQLServerDataSource();
	private SQLServerDataSource sourceDatabase = new SQLServerDataSource();

	private ConnectionManager(Database database) {
		this.setUpTargetDatabase(database);
		this.setUpSourceDatabase(database.getSource());
	}

	private static ConnectionManager getInstance(Database database) {
		if (instance == null) {
			instance = new ConnectionManager(database);
		}
		return instance;
	}

	public static Connection getSourceConnection(Database database) throws SQLException {
		return getInstance(database).sourceDatabase.getConnection();
	}

	public static Connection getTargetConnection(Database database) throws SQLException {
		return getInstance(database).targetDatabase.getConnection();
	}

	private void setUpTargetDatabase(Database database) {
		sourceDatabase.setServerName(database.getServerName());
		sourceDatabase.setDatabaseName(database.getDatabaseName());
		sourceDatabase.setPortNumber(Integer.parseInt(database.getPort()));
	}

	private void setUpSourceDatabase(SourceDatabase source) {
		sourceDatabase.setServerName(source.getServerName());
		sourceDatabase.setDatabaseName(source.getDatabaseName());
		sourceDatabase.setPortNumber(Integer.parseInt(source.getPort()));
	}

}
