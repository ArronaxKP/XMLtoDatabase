package com.uk.aisl.guidewire.shredder.database;

import java.sql.Connection;
import java.sql.SQLException;

import com.microsoft.sqlserver.jdbc.*;
import com.uk.aisl.guidewire.shredder.model.Database;
import com.uk.aisl.guidewire.shredder.model.ErrorDatabase;
import com.uk.aisl.guidewire.shredder.model.SourceDatabase;

/**
 * Connection Manager wrapping class that uses singleton connections to obtain a
 * single connection to either the source (the database for the pay load XML);
 * the error (the database for the error logging) or the target (the database
 * the pay load will be written into) databases.
 * 
 * @author Gareth Edwards
 * @author Karl Parry
 * 
 */
public class ConnectionManager {

	private static ConnectionManager instance = null;
	private SQLServerDataSource targetDatabase = new SQLServerDataSource();
	private SQLServerDataSource sourceDatabase = new SQLServerDataSource();
	private SQLServerDataSource errorDatabase = new SQLServerDataSource();

	/**
	 * Private constructor so can only be instantiated from internally
	 * (Singleton design pattern)
	 * 
	 * @param database
	 *            The database wrapper object that wraps all database
	 *            information (source, error & target) and the destination
	 *            values
	 */
	private ConnectionManager(Database database) {
		this.setUpTargetDatabase(database);
		this.setUpSourceDatabase(database.getSource());
		this.setUpSourceDatabase(database.getError());
	}

	/**
	 * Singleton access method to get the current instantiated instance.
	 * 
	 * @param database
	 *            The database wrapper object that wraps all database
	 *            information (source, error & target) and the destination
	 *            values
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
	 *            information (source, error & target) and the destination
	 *            values
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
	 *            information (source, error & target) and the destination
	 *            values
	 * @return The connection to the source Database
	 * @throws SQLException
	 *             Throws this execution if there was an issue getting the
	 *             connection
	 */
	public static Connection getSourceConnection(Database database) throws SQLException {
		return getInstance(database).sourceDatabase.getConnection();
	}

	/**
	 * Gets the error database connection for writing the error logs
	 * 
	 * @param database
	 *            The database wrapper object that wraps all database
	 *            information (source, error & target) and the destination
	 *            values
	 * @return The connection to the error Database
	 * @throws SQLException
	 *             Throws this execution if there was an issue getting the
	 *             connection
	 */
	public static Connection getErrorConnection(Database database) throws SQLException {
		return getInstance(database).errorDatabase.getConnection();
	}

	/**
	 * Internal method used to setup the values for the target database.
	 * 
	 * @param database
	 *            The database wrapper object that wraps all database
	 *            information (source, error & target) and the destination
	 *            values
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
	 * @param source
	 *            The database wrapper object that wraps source information
	 */
	private void setUpSourceDatabase(SourceDatabase source) {
		sourceDatabase.setServerName(source.getServerName());
		sourceDatabase.setDatabaseName(source.getDatabaseName());
		sourceDatabase.setUser(source.getUsername());
		sourceDatabase.setPassword(source.getPassword());
	}

	/**
	 * Internal method used to setup the values for the error database.
	 * 
	 * @param error
	 *            The database wrapper object that wraps error database
	 *            information
	 */
	private void setUpSourceDatabase(ErrorDatabase error) {
		errorDatabase.setServerName(error.getServerName());
		errorDatabase.setDatabaseName(error.getDatabaseName());
		errorDatabase.setUser(error.getUsername());
		errorDatabase.setPassword(error.getPassword());

	}

}
