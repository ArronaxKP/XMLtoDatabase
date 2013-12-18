package com.uk.aisl.guidewire.shredder.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.uk.aisl.guidewire.shredder.exception.CrashException;
import com.uk.aisl.guidewire.shredder.model.Column;
import com.uk.aisl.guidewire.shredder.model.Database;
import com.uk.aisl.guidewire.shredder.model.SourceDatabase;
import com.uk.aisl.guidewire.shredder.model.Table;
import com.uk.aisl.guidewire.shredder.model.XMLReturn;

public class Loader {

	private static Logger logger = LogManager.getLogger(Loader.class.getName());
	
	/**
	 * Creates the insert statements dynamically based on the structure and
	 * order outlined in mapping.xml. <br/>
	 * <br/>
	 * This will correctly map the values to the column/table name and their
	 * corresponding values
	 * 
	 * @param database
	 *            The database wrapper object that wraps all database
	 *            information (source & target) and the destination values
	 * @return A list of string insert statements
	 */
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

	/**
	 * Method that attempts to guess the data type that needs to be inserted.
	 * Depending on the data type different wrappers need to be used in the
	 * insert statement.<br/>
	 * <br/>
	 * Examples: <br/>
	 * VARCHAR = 'Value'<br/>
	 * Integer = 1 <br/>
	 * Bit (True) = 1<br/>
	 * Bit (False) = 0<br/>
	 * Empty String ('') = null<br/>
	 * null = null
	 * 
	 * @param value
	 *            The value that needs to be used to identify it's type.
	 * @return The String of the correctly formed values to be used in the
	 *         insert statement
	 */
	private static String guessValueType(String value) {
		if (value == null) {
			return "null,";
		} else {
			if (value.equals("")) {
				return "null,";
			} else {
				try {
					Integer.parseInt(value);
					return value + ",";
				} catch (NumberFormatException e) {
					if (value.equals("null")) {
						return "null,";
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

	/**
	 * This method gets the XML pay load from the source database. Using clause
	 * to subset the return set.
	 * 
	 * @param database
	 *            The database wrapper object that wraps all database
	 *            information (source & target) and the destination values
	 * @return The list of XMLReturn objects with their XML pay loads for
	 *         parsing
	 * @throws CrashException
	 */
	public static ArrayList<XMLReturn> getXML(Database database) throws CrashException {
		ArrayList<XMLReturn> orderedList = new ArrayList<XMLReturn>();
		Connection conn = null;
		PreparedStatement stmnt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionManager.getSourceConnection(database);
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
			logger.debug(buff.toString());
			stmnt = conn.prepareStatement(buff.toString());
			rs = stmnt.executeQuery();
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
			rs.close();
			stmnt.close();
			conn.close();

		} catch (SQLException e) {
			throw new CrashException("Unable to get list of XML pay loads. Is there an issue with the database?", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {}
			}
			if (stmnt != null) {
				try {
					stmnt.close();
				} catch (SQLException e) {}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}

		}
		return orderedList;
	}

	/**
	 * Method executes the statements created by the createStatement method.
	 * This method also update the process date of the source XML pay load table
	 * if it successfully inserted all records.
	 * 
	 * @param database
	 *            The database wrapper object that wraps all database
	 *            information (source & target) and the destination values
	 * @return True if inserting succeeded and update or else false
	 * @throws CrashException
	 */
	public static void insertToStaging(Database database) throws CrashException {
		boolean success = true;
		Connection conn = null;
		PreparedStatement stmnt = null;
		try {
			conn = ConnectionManager.getTargetConnection(database);
			conn.setAutoCommit(false);
			ArrayList<String> statements = createStatements(database);
			for (String s : statements) {
				logger.debug(s);
				stmnt = conn.prepareStatement(s);
				try {
					int rowCount = stmnt.executeUpdate();
					if (rowCount == 0) {
						success = false;
					}
				} catch (SQLException e) {
					stmnt.close();
					conn.close();
					throw new CrashException("Failed to execute Insert record.", e);
				}
				stmnt.close();
			}
			if (success) {
				conn.commit();
				boolean updateSuccessful = updateXMLTable(database);
				if (!updateSuccessful) {
					throw new CrashException("Insert was a success but update process date failed");
				}
			} else {
				conn.rollback();
			}
			conn.close();
		} catch (SQLException e) {
			throw new CrashException("Error occured with opening a connection to the database.", e);
		} finally {
			if (stmnt != null) {
				try {
					stmnt.close();
				} catch (SQLException e) {}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}

	/**
	 * Updates the source table with process date = getDate() SQL method.
	 * 
	 * @param database
	 *            The database wrapper object that wraps all database
	 *            information (source & target) and the destination values
	 * @throws CrashException
	 */
	private static boolean updateXMLTable(Database database) {
		boolean success = false;
		Connection conn = null;
		PreparedStatement stmnt = null;
		try {
			conn = ConnectionManager.getSourceConnection(database);
			conn.setAutoCommit(false);
			SourceDatabase source = database.getSource();
			StringBuffer buff = new StringBuffer();
			buff.append("Update " + "[" + source.getSchema() + "].");
			buff.append("[" + source.getTable().getName() + "] SET ");
			buff.append("[EDWProcessTime] = getDate() WHERE TransID = \"");
			buff.append(database.getLookUpValue("TransID"));
			buff.append("\"");
			stmnt = conn.prepareStatement(buff.toString());
			int rowsAffected = stmnt.executeUpdate();
			if (rowsAffected == 1) {
				success = true;
				conn.commit();
			} else {
				conn.rollback();
			}
			stmnt.close();
			conn.close();
		} catch (SQLException e) {
			logger.error("Failed to update the record after a successful update", e);
		} finally {
			if (stmnt != null) {
				try {
					stmnt.close();
				} catch (SQLException e) {}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}

		}
		return success;
	}

	public static void logError(Database database, CrashException e) {
		Connection conn = null;
		PreparedStatement stmnt = null;
		try {
			conn = ConnectionManager.getTargetConnection(database);
			conn.setAutoCommit(false);
			String sqlCommand = database.getError().getErrorSQLString(e);
			logger.debug(sqlCommand);
			stmnt = conn.prepareStatement(sqlCommand);
			int rowCount = stmnt.executeUpdate();
			if (rowCount != 1) {
				String ID = database.getLookUpValue("transid");
				logger.fatal("Failed to add error entry. TransID = "+ID);
			} else {
				conn.commit();
			}
		} catch (SQLException ex) {
			String ID = database.getLookUpValue("transid");
			logger.fatal("Failed to add error entry. TransID = "+ID, ex);
		} finally {
			if (stmnt != null) {
				try {
					stmnt.close();
				} catch (SQLException ex) {}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {}
			}
		}

	}
}
