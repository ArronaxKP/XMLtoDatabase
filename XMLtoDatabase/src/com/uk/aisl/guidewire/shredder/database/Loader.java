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
			ArrayList<Column> columns = t.getColumns();
			Column currentColumn = columns.get(0);
			int numberOfRows = currentColumn.size();
			if (numberOfRows == 0) {
				continue;
			}
			buff.append("INSERT INTO [" + database.getSchema() + "].[" + t.getName() + "] (");

			for (Column c : columns) {
				buff.append("[" + c.getColumnName() + "],");
			}
			buff.deleteCharAt(buff.length() - 1);
			buff.append(") VALUES ");

			for (int row = 0; row < numberOfRows; row++) {
				buff.append("(");

				for (int columnCounter = 0; columnCounter < columns.size(); columnCounter++) {
					Column column = columns.get(columnCounter);
					if (column.getType() == null || column.getType().equals("")) {
						buff.append(Loader.guessValueType(column.getValue(row)));
					} else {
						buff.append(column.getType().replace("?", "'" + column.getValue(row) + "'") + ",");
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

	private static String guessValueType(String value) {
		if (value == null) {
			return value + ",";
		} else {
			if (value.equals("")) {
				return null + ",";
			} else {
				try {
					Integer.parseInt(value);
					return value + ",";
				} catch (NumberFormatException e) {
					if (value.equals("null")) {
						return null + ",";
					} else {
						if (value.equalsIgnoreCase("true")) {
							return 1 + ",";
						} else {
							if (value.equalsIgnoreCase("false")) {
								return 0 + ",";
							} else {
								return "'" + value + "',";
							}
						}
					}
				}
			}
		}
	}

	private static void updateXMLTable(Database database) throws CrashException {
		try {
			Connection conn = ConnectionManager.getSourceConnection(database);
			SourceDatabase source = database.getSource();
			StringBuffer buff = new StringBuffer();
			buff.append("Update " + "[" + source.getSchema() + "].");
			buff.append("[" + source.getTable().getName() + "] SET ");
			buff.append("[EDWProcessTime] = getDate() WHERE TransID = \"");
			buff.append(database.getLookUpValue("TransID"));
			buff.append("\"");
			PreparedStatement stmnt = conn.prepareStatement(buff.toString());
			stmnt.execute();
			conn.close();
		} catch (SQLException e) {
			throw new CrashException("SQLException in updateXMLTable method!");
		}
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
						xmlPayload = rs.getString(i + 1);
					} else {
						variableMap.put(columns.get(i).getLookUpKey(), rs.getString(i + 1));
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
		Connection conn = null;
		try {
			conn = ConnectionManager.getTargetConnection(database);

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
				stmnt.close();
			}

			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// No need to worry
				}
			}
		}

		return success;
	}

}
