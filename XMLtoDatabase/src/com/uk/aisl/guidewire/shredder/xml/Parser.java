package com.uk.aisl.guidewire.shredder.xml;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import com.uk.aisl.guidewire.shredder.exception.CrashException;
import com.uk.aisl.guidewire.shredder.model.Column;
import com.uk.aisl.guidewire.shredder.model.Database;
import com.uk.aisl.guidewire.shredder.model.Table;
import com.ximpleware.AutoPilot;
import com.ximpleware.NavException;
import com.ximpleware.ParseException;
import com.ximpleware.VTDGen;
import com.ximpleware.VTDNav;
import com.ximpleware.XPathEvalException;
import com.ximpleware.XPathParseException;

public class Parser {

	private VTDGen vg;
	private VTDNav vn;

	public Parser(String XML) throws CrashException {
		try {
			//String withHeader = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + XML;
			byte[] bytes = XML.getBytes("UTF-8");
			vg = new VTDGen();
			vg.setDoc(bytes);
			vg.parse(false);
			vn = vg.getNav();
		} catch (UnsupportedEncodingException e) {
			throw new CrashException("UTF-8 is the only encoding supported currently.", e);
		} catch (ParseException e) {
			throw new CrashException("Parsing error occured parsing XML", XML, e);
		}
	}

	public Database parseXML(Database database, HashMap<String,String> variableMap) throws CrashException {
		ArrayList<Table> tables = database.getTables();
		this.fillInLookUpValues(database,variableMap);
		for (Table table : tables) {
			try {
				this.fillInTableValues(database, table);
			} catch (XPathEvalException e) {
				throw new CrashException("Failed xpath for payloadXML", e);
			} catch (NavException e) {
				throw new CrashException("VTD-XML navigation failure parsing payloadXML", e);
			} catch (XPathParseException e) {
				throw new CrashException("Failed to parse the payloadXML", e);
			}
		}
		return database;
	}

	private void fillInLookUpValues(Database database, HashMap<String, String> variableMap) throws CrashException {
		try{
			vn.toElement(VTDNav.ROOT);
			ArrayList<String> lookUpKeys = database.getLookUpKeys();
			int totalLookUps = lookUpKeys.size();
			for(int i = 0; i < totalLookUps; i++){
				String value = database.getLookUpValue(lookUpKeys.get(i));
				if(value == null || value.equals("")) {
					String xpath = database.getLookUpXpath(lookUpKeys.get(i));
					if(xpath == null || xpath.equals("")) {
						String variable = database.getLookUpVariable(lookUpKeys.get(i));
						if(variable == null || variable.equals("")) {
							throw new CrashException("Lookup key failed. Key: " + lookUpKeys.get(i));
						} else {
							value = variableMap.get(variable);
						}
					} else {
						AutoPilot apXpath = new AutoPilot();
						apXpath.selectXPath(xpath);
						apXpath.bind(vn);
						value = apXpath.evalXPathToString();
						apXpath.resetXPath();
						
					}
				}
				database.setLookUpValue(lookUpKeys.get(i), value);
			}
		}catch(NavException e){
			throw new CrashException("Unable to complete look ups", e);
		} catch (XPathParseException e) {
			throw new CrashException("Unable to complete look ups", e);
		}
	}

	private void fillInTableValues(Database database, Table table) throws XPathParseException, XPathEvalException, NavException {
		vn.toElement(VTDNav.ROOT);
		AutoPilot ap = new AutoPilot();
		ap.selectXPath(table.getXpathROOT());
		ap.bind(vn);

		while (ap.evalXPath() != -1) {
			ArrayList<Column> columns = table.getColumns();
			for (Column column : columns) {
				this.fillInColumnValues(database, column);
			}
		}
		ap.resetXPath();
	}

	private void fillInColumnValues(Database database, Column column) throws XPathParseException {
		if(column.getXpath() == null || column.getXpath().equals("")){
			column.addValue(database.getLookUpValue(column.getLookUpKey()));
		} else {
			AutoPilot ap = new AutoPilot();
			ap.selectXPath(column.getXpath());
			ap.bind(vn);
			column.addValue(ap.evalXPathToString());
			ap.resetXPath();
		}
	}
}
