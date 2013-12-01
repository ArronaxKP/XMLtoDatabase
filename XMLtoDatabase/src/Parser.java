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
	private Parser parser = this;

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
	
	public XMLObject parseIt() {
		XMLObject xml = new XMLObject();
		try {
			xml.setPolicyPeriodList(this.getListOfPolicyPeriods());
			xml.setCoverageList(this.getListOfCoverages());
			xml.setPersonalAutoLineList(this.getListOfPersonalAutoLines());
			xml.setIncidentsList(this.getListOfIncidents());
			xml.setVehicleList(this.getListOfVehicles());
			xml.setAdditionalInterestsList(this.getListOfAdditionalInterests());
			xml.setPolicyDriversList(this.getListOfPolicyDrivers());
			xml.setAccountLocationList(this.getListOfAccountLocations());
			xml.setPolicyAddressList(this.getListOfPolicyAddress());
			xml.setPolicyContactList(this.getListOfPolicyContacts());			
		} catch (XPathParseException | XPathEvalException | NavException e) {
			System.err.println("Failed to parse XML");
			e.printStackTrace();
			xml = null;
		}
		return xml;
	}
	
	private ArrayList<PolicyPeriod> getListOfPolicyPeriods()throws XPathParseException, XPathEvalException, NavException {
		AutoPilot ap = new AutoPilot();
		ap.selectXPath(PolicyPeriod.ROOT);
	    ap.bind(vn);
	    
		ArrayList<PolicyPeriod> policyPeriodList = new ArrayList<PolicyPeriod>();
	    while (ap.evalXPath() != -1)
	    {
	    	policyPeriodList.add(Shredder.getPolicyPeriod(parser));
	    }
        ap.resetXPath();
        return policyPeriodList;
	}
	
	private ArrayList<Coverage> getListOfCoverages() throws XPathParseException, XPathEvalException, NavException {
		AutoPilot ap = new AutoPilot();
		ap.selectXPath(Coverage.ROOT);
	    ap.bind(vn);
	    
		ArrayList<Coverage> coverageList = new ArrayList<Coverage>();
	    while (ap.evalXPath() != -1)
	    {
	    	coverageList.add(Shredder.getCoverage(parser));
	    }
        ap.resetXPath();
        return coverageList;
	}
	
	private ArrayList<PersonalAutoLine> getListOfPersonalAutoLines() throws XPathParseException, XPathEvalException, NavException {
		AutoPilot ap = new AutoPilot();
		ap.selectXPath(PersonalAutoLine.ROOT);
	    ap.bind(vn);
	    
		ArrayList<PersonalAutoLine> personalAutoLineList = new ArrayList<PersonalAutoLine>();
	    while (ap.evalXPath() != -1)
	    {
	    	personalAutoLineList.add(Shredder.getPersonalAutoLine(parser));
	    }
        ap.resetXPath();
        return personalAutoLineList;
	}

	private ArrayList<Incidents> getListOfIncidents() throws XPathParseException, XPathEvalException, NavException {
		AutoPilot ap = new AutoPilot();
		ap.selectXPath(Incidents.ROOT);
	    ap.bind(vn);
	    
		ArrayList<Incidents> incidentsList = new ArrayList<Incidents>();
	    while (ap.evalXPath() != -1)
	    {
	    	incidentsList.add(Shredder.getIncidents(parser));
	    }
        ap.resetXPath();
        return incidentsList;
	}
	
	private ArrayList<Vehicle> getListOfVehicles() throws XPathParseException, XPathEvalException, NavException {
		AutoPilot ap = new AutoPilot();
		ap.selectXPath(Vehicle.ROOT);
	    ap.bind(vn);
	    
		ArrayList<Vehicle> vehicleList = new ArrayList<Vehicle>();
	    while (ap.evalXPath() != -1)
	    {
	    	vehicleList.add(Shredder.getVehicle(parser));
	    }
        ap.resetXPath();
        return vehicleList;
	}
	
	private ArrayList<AdditionalInterests> getListOfAdditionalInterests() throws XPathParseException, XPathEvalException, NavException {
		AutoPilot ap = new AutoPilot();
		ap.selectXPath(AdditionalInterests.ROOT);
	    ap.bind(vn);
	    
		ArrayList<AdditionalInterests> additionalInterestsList = new ArrayList<AdditionalInterests>();
	    while (ap.evalXPath() != -1)
	    {
	    	additionalInterestsList.add(Shredder.getAdditionalInterests(parser));
	    }
        ap.resetXPath();
        return additionalInterestsList;
	}
	
	private ArrayList<PolicyDrivers> getListOfPolicyDrivers() throws XPathParseException, XPathEvalException, NavException {
		AutoPilot ap = new AutoPilot();
		ap.selectXPath(PolicyDrivers.ROOT);
	    ap.bind(vn);
	    
		ArrayList<PolicyDrivers> policyDriversList = new ArrayList<PolicyDrivers>();
	    while (ap.evalXPath() != -1)
	    {
	    	policyDriversList.add(Shredder.getPolicyDrivers(parser));
	    }
        ap.resetXPath();
        return policyDriversList;
	}
	
	private ArrayList<AccountLocation> getListOfAccountLocations() throws XPathParseException, XPathEvalException, NavException {
		AutoPilot ap = new AutoPilot();
		ap.selectXPath(AccountLocation.ROOT);
	    ap.bind(vn);
	    
		ArrayList<AccountLocation> accountLocationList = new ArrayList<AccountLocation>();
	    while (ap.evalXPath() != -1)
	    {
	    	accountLocationList.add(Shredder.getAccountLocation(parser));
	    }
        ap.resetXPath();
        return accountLocationList;
	}
	
	private ArrayList<PolicyAddress> getListOfPolicyAddress() throws XPathParseException, XPathEvalException, NavException {
		AutoPilot ap = new AutoPilot();
		ap.selectXPath(PolicyAddress.ROOT);
	    ap.bind(vn);
	    
		ArrayList<PolicyAddress> policyAddressList = new ArrayList<PolicyAddress>();
	    while (ap.evalXPath() != -1)
	    {
	    	policyAddressList.add(Shredder.getPolicyAddress(parser));
	    }
        ap.resetXPath();
        return policyAddressList;
	}
	
	private ArrayList<PolicyContact> getListOfPolicyContacts() throws XPathParseException, XPathEvalException, NavException {
		AutoPilot ap = new AutoPilot();
		ap.selectXPath(PolicyContact.ROOT);
	    ap.bind(vn);
	    
		ArrayList<PolicyContact> policyContactList = new ArrayList<PolicyContact>();
	    while (ap.evalXPath() != -1)
	    {
	    	policyContactList.add(Shredder.getPolicyContact(parser));
	    }
        ap.resetXPath();
        return policyContactList;
	}
	
	public String getValue(String xpath){
		String result = null;
		try {		    
		    AutoPilot ap2 = new AutoPilot();
			ap2.selectXPath(xpath);
	        ap2.bind(vn);
	        result = ap2.evalXPathToString();
	        
	        //Debug code
	        //System.out.println(ROOT);
	        //System.out.println(xpath);
	        //System.out.println(result);

	        ap2.resetXPath();
	        //vn.toElement(currentRoot);
        } catch (XPathParseException  e) {
			System.err.println("Error with the xpath parsing: "+xpath);
			e.printStackTrace();
		}
		return result;
	}
}
