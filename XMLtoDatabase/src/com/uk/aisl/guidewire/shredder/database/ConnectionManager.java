package com.uk.aisl.guidewire.shredder.database;

import java.sql.Connection;
import java.sql.SQLException;

import com.microsoft.sqlserver.jdbc.*;
import com.uk.aisl.guidewire.shredder.model.Database;

public class ConnectionManager {
	
	private static ConnectionManager instance = null;
	private static SQLServerDataSource dataSource = new SQLServerDataSource();
	
	private ConnectionManager(Database database){
		setUpDataSource(database);
	};
	
	private static ConnectionManager getInstance(Database database){
		if(instance == null){
			instance = new ConnectionManager(database);
		}
		return instance;
	}
	
	public static Connection getConnection(Database database) throws SQLException{
		getInstance(database);
		return dataSource.getConnection();
	}
	
	private static void setUpDataSource(Database database){
			dataSource.setServerName(database.getServerName());
			dataSource.setDatabaseName(database.getDatabaseName());
			dataSource.setPortNumber(Integer.parseInt(database.getPort()));
	}
	
}
