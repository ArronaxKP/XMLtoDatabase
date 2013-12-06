package com.uk.aisl.guidewire.shredder.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.uk.aisl.guidewire.shredder.exception.CrashException;
import com.uk.aisl.guidewire.shredder.model.Column;
import com.uk.aisl.guidewire.shredder.model.Database;
import com.uk.aisl.guidewire.shredder.model.Table;
import com.uk.aisl.guidewire.shredder.model.XMLReturn;

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
	
	private static void updateXMLTable(Database database) throws CrashException{
		
		try{
			Connection conn = ConnectionManager.getConnection(database);
			PreparedStatement stmnt = conn.prepareStatement("UPDATE table SET ProcessDate = getdate() WHERE TransID = \"" + database.getLookUpValue("TransID") + "\"");
			stmnt.execute();
			conn.close();
		}
		catch(SQLException e){
			throw new CrashException("SQLException in updateXMLTable method!");
		}
		
	}
	
	public static ArrayList<XMLReturn> getXML(Database database) throws CrashException{
		ArrayList<XMLReturn> orderedPairList = new ArrayList<XMLReturn>(); 
		try{
			Connection conn = ConnectionManager.getConnection(database);
			
			PreparedStatement stmnt = conn.prepareStatement("SELECT ID, XMLPayload FROM table WHERE ProcessDate IS NULL");
			ResultSet rs = stmnt.executeQuery();
			while(rs.next()){
				HashMap<String,String> variableMap = new HashMap<String,String>(97);
				variableMap.put("TransID", rs.getString(0));
				orderedPairList.add(new XMLReturn(variableMap, rs.getString(1)));
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
			Connection conn = ConnectionManager.getConnection(database);
			
			ArrayList<String> statements = createStatements("Quotes", database);
			for(String s : statements){
				PreparedStatement stmnt = conn.prepareStatement(s);
				int rowCount = stmnt.executeUpdate();
				if(rowCount == 0){
					success = false;
				}
				else{
					updateXMLTable(database);
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
