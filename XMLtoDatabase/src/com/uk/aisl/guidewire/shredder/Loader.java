package com.uk.aisl.guidewire.shredder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import com.na.aisl.db.ConnectionManager;

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
				//record count
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
	
	public static boolean whatIsAQuote(Database database){
		boolean success = true;
		
		try{
			Connection conn = ConnectionManager.getConnection();
			
			ArrayList<String> statements = createStatements("Quotes", database);
			for(String s : statements){
				PreparedStatement stmnt = conn.prepareStatement(s);
				int winner = stmnt.executeUpdate();
				if(winner == 0){
					success = false;
				}
			}
			
		}
		catch(SQLException e){
			
		}
		
		
		return success;
	}

}
