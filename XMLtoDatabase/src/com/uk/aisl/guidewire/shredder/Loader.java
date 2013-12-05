package com.uk.aisl.guidewire.shredder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.na.aisl.db.ConnectionManager;
import com.uk.aisl.guidewire.shredder.exception.CrashException;

public class Loader {
	
	private static ArrayList<String> createStatements(String sprint, Database database){
		
		ArrayList<String> statements = new ArrayList<String>();
		ArrayList<Table> tables = database.getTables();
		StringBuffer buff = new StringBuffer();
		
		for(Table t: tables){
			
			buff.append("INSERT INTO [" + sprint + "]." + t.getTableName() + "(");
			
			ArrayList<Column> columns = t.getColumns();
			
			for(Column c : columns){
				buff.append(c.getColumnName() + ",");
			}
			buff.deleteCharAt(buff.length()-1);
			buff.append(") ");
			
			int numberOfRows = columns.get(0).size();
			for(int row = 0; row < numberOfRows; row++){
				buff.append("(");
				
				for(int column = 0; column < columns.size(); column++){
					buff.append(columns.get(column).getValue(row) + ",");
				}
				
				buff.deleteCharAt(buff.length() - 1);
				buff.append("), ");
				
			}
			
			statements.add(buff.toString());
			buff.setLength(0);
		
		}
		return statements;
		
	}
	
	private static void updateXMLTable(String transID) throws CrashException{
		
		try{
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement stmnt = conn.prepareStatement("UPDATE table SET ProcessDate = getdate() WHERE TransID = \"" + transID + "\"");
			stmnt.execute();
			conn.close();
		}
		catch(SQLException e){
			throw new CrashException("SQLException in updateXMLTable method!");
		}
		
	}
	
	public static ArrayList<Pair<String, String>> getXML() throws CrashException{
		ArrayList<Pair<String, String>> orderedPairList = new ArrayList<Pair<String, String>>(); 
		try{
			Connection conn = ConnectionManager.getConnection();
			
			PreparedStatement stmnt = conn.prepareStatement("SELECT ID, XMLPayload FROM table WHERE ProcessDate IS NULL");
			ResultSet rs = stmnt.executeQuery();
			while(rs.next()){
				orderedPairList.add(new Pair<String,String>(rs.getString(0), rs.getString(1)));
			}
			conn.close();
			rs.close();
			stmnt.close();
			
		}
		catch(SQLException e){
			throw new CrashException("SQL Exception in getXML method!", e);
		}
		return orderedPairList;
	}
	
	public static boolean insertToStaging(Database database) throws CrashException{
		boolean success = true;
		
		try{
			Connection conn = ConnectionManager.getConnection();
			
			ArrayList<String> statements = createStatements("Quotes", database);
			for(String s : statements){
				PreparedStatement stmnt = conn.prepareStatement(s);
				int rowCount = stmnt.executeUpdate();
				if(rowCount == 0){
					success = false;
				}
				else{
					updateXMLTable("databaseTransID");
				}
			}
			
			conn.close();
			
		}
		catch(SQLException e){
			throw new CrashException("SQL Exception in insertToStaging method!", e);
		}
		
		return success;
	}

}
