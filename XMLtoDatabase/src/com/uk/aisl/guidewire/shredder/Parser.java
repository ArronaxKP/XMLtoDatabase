package com.uk.aisl.guidewire.shredder;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

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

	public Parser(String XML) {
		try {
			String withHeader = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + XML;
			byte[] bytes = withHeader.getBytes("UTF-8");
			vg = new VTDGen();
			vg.setDoc(bytes);
			vg.parse(false);
			vn = vg.getNav();
		} catch (UnsupportedEncodingException | ParseException e) {
			System.err.println("UTF-8 is the only encoding supported currently.");
		}
	}

	public XMLObject parseXML(XMLObject xml) {
		ArrayList<Table> tables = xml.getTables();
		for (Table table : tables) {
			try {
				this.fillInTableValues(table);
			} catch (XPathParseException | XPathEvalException | NavException e) {
				System.err.println("Error filling in the table");
				e.printStackTrace();
			}
		}
		return xml;
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
