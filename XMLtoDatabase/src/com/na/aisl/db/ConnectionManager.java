package com.na.aisl.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.microsoft.sqlserver.jdbc.*;

public class ConnectionManager {
	
	private static ConnectionManager instance = null;
	private static SQLServerDataSource dataSource = new SQLServerDataSource();
	
	private ConnectionManager(){
		setUpDataSource();
	};
	
	private static ConnectionManager getInstance(){
		if(instance == null){
			instance = new ConnectionManager();
		}
		return instance;
	}
	
	public static Connection getConnection() throws SQLException{
		getInstance();
		return dataSource.getConnection();
	}
	
	private static void setUpDataSource(){
		Properties prop = new Properties();
		try{
			prop.load(ConnectionManager.class.getResourceAsStream("connection.properties"));
			dataSource.setServerName(prop.getProperty("SERVER"));
			dataSource.setDatabaseName(prop.getProperty("DATABASE"));
			dataSource.setPortNumber(Integer.parseInt(prop.getProperty("PORT")));
		}
		catch(IOException e){
			
		}
	}
	
}
