package com.uk.aisl.guidewire.shredder;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import com.uk.aisl.guidewire.shredder.exception.CrashException;
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
			String withHeader = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + XML;
			byte[] bytes = withHeader.getBytes("UTF-8");
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

	public Database parseXML(Database database) throws CrashException {
		ArrayList<Table> tables = database.getTables();
		for (Table table : tables) {
			try {
				this.fillInTableValues(database, table);
				this.fillInLookUpValues(database);
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

	private void fillInLookUpValues(Database database) throws XPathParseException, NavException {
		vn.toElement(VTDNav.ROOT);
		ArrayList<String> lookUpKeys = database.getLookUpKeys();
		int totalLookUps = lookUpKeys.size();
		for(int i = 0; i < totalLookUps; i++){
			String value = database.getLookUpValue(lookUpKeys.get(i));
			if(value == null || value.equals("")) {
				String xpath = database.getLookUpXpath(lookUpKeys.get(i));
				if(xpath == null || xpath.equals("")) {
					value = "";//TODO assign variable
				} else {
					AutoPilot apXpath = new AutoPilot();
					apXpath.selectXPath(database.getLookUpXpath(lookUpKeys.get(i)));
					apXpath.bind(vn);
					value = apXpath.evalXPathToString();
					apXpath.resetXPath();
					
				}
			}
			database.setLookUpValue(lookUpKeys.get(i), value);
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
