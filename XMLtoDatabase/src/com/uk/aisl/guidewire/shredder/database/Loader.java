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
import com.uk.aisl.guidewire.shredder.model.SourceDatabase;
import com.uk.aisl.guidewire.shredder.model.Table;
import com.uk.aisl.guidewire.shredder.model.XMLReturn;

public class Loader {

	private static ArrayList<String> createStatements(Database database) {

		ArrayList<String> statements = new ArrayList<String>();
		ArrayList<Table> tables = database.getTables();
		StringBuffer buff = new StringBuffer();

		for (Table t : tables) {

			buff.append("INSERT INTO [" + database.getSchema() + "].[" + t.getName() + "] (");

			ArrayList<Column> columns = t.getColumns();

			for (Column c : columns) {
				buff.append("[" + c.getColumnName() + "],");
			}
			buff.deleteCharAt(buff.length() - 1);
			buff.append(") VALUES ");

			int numberOfRows = columns.get(0).size();
			for (int row = 0; row < numberOfRows; row++) {
				buff.append("(");

				for (int column = 0; column < columns.size(); column++) {
					String value = columns.get(column).getValue(row);
					if(value == null) {
						buff.append(value + ",");
					} else {
						if(value.equals("")){
							buff.append(null + ",");
						} else {
							try{
								Integer.parseInt(value);
								buff.append(value + ",");
							}catch(NumberFormatException e){
								if(value.equals("null")){
									buff.append(null + ",");
								} else {
									if(value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false")) {
										buff.append(value + ",");
									} else {
										buff.append("'" + value + "',");
									}
								}
							}
						}
					}
					
				}

				buff.deleteCharAt(buff.length() - 1);
				buff.append("), ");

			}
			buff.deleteCharAt(buff.length() - 2);
			statements.add(buff.toString());
			buff.setLength(0);

		}
		return statements;

	}

	private static void updateXMLTable(Database database) throws CrashException {
		/*
		try {
			Connection conn = ConnectionManager.getSourceConnection(database);
			PreparedStatement stmnt = conn
					.prepareStatement("UPDATE table SET ProcessDate = getdate() WHERE TransID = \""
							+ database.getLookUpValue("TransID") + "\"");
			stmnt.execute();
			conn.close();
		} catch (SQLException e) {
			throw new CrashException("SQLException in updateXMLTable method!");
		}*/
		System.out.println("Updating old record");
	}

	public static ArrayList<XMLReturn> getXML(Database database) throws CrashException {
		ArrayList<XMLReturn> orderedList = new ArrayList<XMLReturn>();
		try {
			Connection conn = ConnectionManager.getSourceConnection(database);
			SourceDatabase source = database.getSource();
			Table table = source.getTable();
			ArrayList<Column> columns = table.getColumns();
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT ");
			for (int i = 0; i < columns.size(); i++) {
				if (i != 0) {
					buff.append(", ");
				}
				buff.append(columns.get(i).getColumnName());
			}
			buff.append(" FROM [" + source.getSchema() + "].[" + table.getName() + "] " + table.getClause());
			PreparedStatement stmnt = conn.prepareStatement(buff.toString());
			ResultSet rs = stmnt.executeQuery();
			while (rs.next()) {
				HashMap<String, String> variableMap = new HashMap<String, String>(97);
				String xmlPayload = null;
				for (int i = 0; i < columns.size(); i++) {
					if (columns.get(i).getLookUpKey().equals("XML")) {
						xmlPayload = rs.getString(i+1);
					} else {
						variableMap.put(columns.get(i).getLookUpKey(), rs.getString(i+1));
					}
				}
				orderedList.add(new XMLReturn(variableMap, xmlPayload));
			}
			conn.close();
			rs.close();
			stmnt.close();

		} catch (SQLException e) {
			throw new CrashException("SQL Exception in getXML method!", e);
		}
		return orderedList;
	}

	public static boolean insertToStaging(Database database) throws CrashException {
		boolean success = true;

		try {
			Connection conn = ConnectionManager.getTargetConnection(database);

			ArrayList<String> statements = createStatements(database);
			for (String s : statements) {
				System.out.println(s);
				PreparedStatement stmnt = conn.prepareStatement(s);
				int rowCount = stmnt.executeUpdate();
				if (rowCount == 0) {
					success = false;
				} else {
					updateXMLTable(database);
				}
			}

			conn.close();

		} catch (SQLException e) {
			throw new CrashException("SQL Exception in insertToStaging method!", e);
		}

		return success;
	}

}
