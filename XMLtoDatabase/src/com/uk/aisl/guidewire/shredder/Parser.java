package com.uk.aisl.guidewire.shredder;

import java.io.UnsupportedEncodingException;

import com.uk.aisl.guidewire.shredder.exception.CrashException;
import com.uk.aisl.guidewire.shredder.mapper.ColumnMapper;
import com.uk.aisl.guidewire.shredder.mapper.DatabaseMapper;
import com.uk.aisl.guidewire.shredder.mapper.TableMapper;
import com.uk.aisl.guidewire.shredder.model.ColumnValues;
import com.uk.aisl.guidewire.shredder.model.DatabaseValues;
import com.uk.aisl.guidewire.shredder.model.TableValues;
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

	public DatabaseValues parseXML(DatabaseMapper databaseMapper, DatabaseValues database) throws CrashException {
		int numberOfTables = database.getTables().size();
		for (int i = 0; i < numberOfTables; i++) {
			try {
				this.fillInTableValues(databaseMapper.getTable(i), database.getTable(i));
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

	private void fillInTableValues(TableMapper tableMapper, TableValues table) throws XPathParseException, XPathEvalException, NavException {
		AutoPilot ap = new AutoPilot();
		ap.selectXPath(tableMapper.getXpathROOT());
		ap.bind(vn);

		while (ap.evalXPath() != -1) {
			int numberOfColumns = tableMapper.getColumns().size();
			for (int i = 0; i < numberOfColumns; i++) {
				this.fillInColumnValues(tableMapper.getColumn(i), table.getColumn(i));
			}
		}
		ap.resetXPath();
	}

	private void fillInColumnValues(ColumnMapper columnMapper, ColumnValues column) throws XPathParseException {
		AutoPilot ap = new AutoPilot();
		ap.selectXPath(columnMapper.getXpath());
		ap.bind(vn);
		column.addValue(ap.evalXPathToString());
		ap.resetXPath();
	}
}
