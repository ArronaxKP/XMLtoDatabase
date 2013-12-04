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
				this.fillInTableValues(table);
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

	private void fillInTableValues(Table table) throws XPathParseException, XPathEvalException, NavException {
		AutoPilot ap = new AutoPilot();
		ap.selectXPath(table.getXpathROOT());
		ap.bind(vn);

		while (ap.evalXPath() != -1) {
			ArrayList<Column> columns = table.getColumns();
			for (Column column : columns) {
				this.fillInColumnValues(column);
			}
		}
		ap.resetXPath();
	}

	private void fillInColumnValues(Column column) throws XPathParseException {
		AutoPilot ap = new AutoPilot();
		ap.selectXPath(column.getXpath());
		ap.bind(vn);
		column.addValue(ap.evalXPathToString());
		ap.resetXPath();
	}
}
