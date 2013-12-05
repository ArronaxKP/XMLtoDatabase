package com.uk.aisl.guidewire.shredder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import com.na.aisl.db.ConnectionManager;
import com.uk.aisl.guidewire.shredder.mapper.ColumnMapper;
import com.uk.aisl.guidewire.shredder.mapper.DatabaseMapper;
import com.uk.aisl.guidewire.shredder.mapper.TableMapper;
import com.uk.aisl.guidewire.shredder.model.DatabaseValues;
import com.uk.aisl.guidewire.shredder.model.TableValues;

public class Loader {
	
	private static ArrayList<String> createStatements(String sprint, DatabaseMapper databaseMapper, DatabaseValues databaseValues){
		
		ArrayList<String> statements = new ArrayList<String>();
		StringBuffer buff = new StringBuffer();
		int numberOfTables = databaseValues.getNumberOfTables();
		for(int tableCounter = 0; tableCounter < numberOfTables; tableCounter++ ) {
			
			TableMapper tableMapper = databaseMapper.getTable(tableCounter);
			buff.append("INSERT INTO [" + sprint + "]." + tableMapper.getTableName() + "(");
			
			for(ColumnMapper columnMapper : tableMapper.getColumns()){
				buff.append(columnMapper.getColumnName() + ",");
			}
			buff.deleteCharAt(buff.length()-1);
			buff.append(") ");
			
			TableValues tableValues = databaseValues.getTable(tableCounter);
			int numberOfRows = databaseValues.getTable(0).getColumn(0).size();
			for(int row = 0; row < numberOfRows; row++){
				buff.append("(");
				//record count
				int columnSize = tableValues.getNumberOfColumns();
				
				for(int columnCounter = 0; columnCounter < columnSize; columnCounter++){
					buff.append(tableValues.getColumn(columnCounter).getValue(row) + ",");
				}
				
				buff.deleteCharAt(buff.length() - 1);
				buff.append("), ");
				
			}
			
			statements.add(buff.toString());
			buff.setLength(0);
		
		}
		return statements;
		
	}
	
	public static boolean whatIsAQuote(DatabaseMapper databaseMapper, DatabaseValues databaseValues){
		boolean success = true;
		
		try{
			Connection conn = ConnectionManager.getConnection();
			
			ArrayList<String> statements = createStatements("Quotes", databaseMapper, databaseValues);
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
