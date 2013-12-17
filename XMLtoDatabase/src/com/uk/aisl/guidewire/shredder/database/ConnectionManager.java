package com.uk.aisl.guidewire.shredder.database;

import java.sql.Connection;
import java.sql.SQLException;

import com.microsoft.sqlserver.jdbc.*;
import com.uk.aisl.guidewire.shredder.model.Database;
import com.uk.aisl.guidewire.shredder.model.SourceDatabase;

/**
 * Connection Manager wrapping class that uses singleton connections to obtain a
 * single connection to either the source (the database for the pay load XML) or
 * the target (the database the pay load will be written into) databases.
 * 
 * @author Gareth Edwards
 * @author Karl Parry
 * 
 */
public class ConnectionManager {

	private static ConnectionManager instance = null;
	private SQLServerDataSource targetDatabase = new SQLServerDataSource();
	private SQLServerDataSource sourceDatabase = new SQLServerDataSource();

	/**
	 * Private constructor so can only be instantiated from internally
	 * (Singleton design pattern)
	 * 
	 * @param database
	 *            The database wrapper object that wraps all database
	 *            information (source & target) and the destination values
	 */
	private ConnectionManager(Database database) {
		this.setUpTargetDatabase(database);
		this.setUpSourceDatabase(database.getSource());
	}

	/**
	 * Singleton access method to get the current instantiated instance.
	 * 
	 * @param database
	 *            The database wrapper object that wraps all database
	 *            information (source & target) and the destination values
	 * @return The instantiated ConnectionManager
	 */
	private static ConnectionManager getInstance(Database database) {
		if (instance == null) {
			instance = new ConnectionManager(database);
		}
		return instance;
	}

	/**
	 * Gets the target database connection for placing the values of the XML
	 * payload
	 * 
	 * @param database
	 *            The database wrapper object that wraps all database
	 *            information (source & target) and the destination values
	 * @return The connection to the target Database
	 * @throws SQLException
	 *             Throws this execution if there was an issue getting the
	 *             connection
	 */
	public static Connection getTargetConnection(Database database) throws SQLException {
		return getInstance(database).targetDatabase.getConnection();
	}
	
	/**
	 * Gets the source database connection for getting the XML payload
	 * 
	 * @param database
	 *            The database wrapper object that wraps all database
	 *            information (source & target) and the destination values
	 * @return The connection to the source Database
	 * @throws SQLException
	 *             Throws this execution if there was an issue getting the
	 *             connection
	 */
	public static Connection getSourceConnection(Database database) throws SQLException {
		return getInstance(database).sourceDatabase.getConnection();
	}

	/**
	 * Internal method used to setup the values for the target database.
	 * 
	 * @param database
	 *            The database wrapper object that wraps all database
	 *            information (source & target) and the destination values
	 */
	private void setUpTargetDatabase(Database database) {
		targetDatabase.setServerName(database.getServerName());
		targetDatabase.setDatabaseName(database.getDatabaseName());
		targetDatabase.setUser(database.getUsername());
		targetDatabase.setPassword(database.getPassword());
	}

	/**
	 * Internal method used to setup the values for the source database.
	 * 
	 * @param database
	 *            The database wrapper object that wraps all database
	 *            information (source & target) and the destination values
	 */
	private void setUpSourceDatabase(SourceDatabase source) {
		sourceDatabase.setServerName(source.getServerName());
		sourceDatabase.setDatabaseName(source.getDatabaseName());
		sourceDatabase.setUser(source.getUsername());
		sourceDatabase.setPassword(source.getPassword());
	}
	
	

}
