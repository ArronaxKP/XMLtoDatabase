import java.util.ArrayList;

public class XMLObject {
	private ArrayList<PolicyPeriod> policyPeriodList;
	private ArrayList<Coverage> coverageList;
	private ArrayList<PersonalAutoLine> personalAutoLineList;
	private ArrayList<Incidents> incidentsList;
	private ArrayList<Vehicle> vehicleList;
	private ArrayList<AdditionalInterests> additionalInterestsList;
	private ArrayList<PolicyDrivers> policyDriversList;
	private ArrayList<AccountLocation> accountLocationList;
	private ArrayList<PolicyAddress> policyAddressList;
	private ArrayList<PolicyContact> policyContactList;

	public ArrayList<PolicyPeriod> getPolicyPeriodList() {
		return policyPeriodList;
	}

	public void setPolicyPeriodList(ArrayList<PolicyPeriod> policyPeriodList) {
		this.policyPeriodList = policyPeriodList;
	}

	public ArrayList<Coverage> getCoverageList() {
		return coverageList;
	}

	public void setCoverageList(ArrayList<Coverage> coverageList) {
		this.coverageList = coverageList;
	}

	public ArrayList<PersonalAutoLine> getPersonalAutoLineList() {
		return personalAutoLineList;
	}

	public void setPersonalAutoLineList(ArrayList<PersonalAutoLine> personalAutoLineList) {
		this.personalAutoLineList = personalAutoLineList;
	}

	public ArrayList<Incidents> getIncidentsList() {
		return incidentsList;
	}

	public void setIncidentsList(ArrayList<Incidents> incidentsList) {
		this.incidentsList = incidentsList;
	}

	public ArrayList<Vehicle> getVehicleList() {
		return vehicleList;
	}

	public void setVehicleList(ArrayList<Vehicle> vehicleList) {
		this.vehicleList = vehicleList;
	}

	public ArrayList<AdditionalInterests> getAdditionalInterestsList() {
		return additionalInterestsList;
	}

	public void setAdditionalInterestsList(ArrayList<AdditionalInterests> additionalInterestsList) {
		this.additionalInterestsList = additionalInterestsList;
	}

	public ArrayList<PolicyDrivers> getPolicyDriversList() {
		return policyDriversList;
	}

	public void setPolicyDriversList(ArrayList<PolicyDrivers> policyDriversList) {
		this.policyDriversList = policyDriversList;
	}

	public ArrayList<AccountLocation> getAccountLocationList() {
		return accountLocationList;
	}

	public void setAccountLocationList(ArrayList<AccountLocation> accountLocationList) {
		this.accountLocationList = accountLocationList;
	}

	public ArrayList<PolicyAddress> getPolicyAddressList() {
		return policyAddressList;
	}

	public void setPolicyAddressList(ArrayList<PolicyAddress> policyAddressList) {
		this.policyAddressList = policyAddressList;
	}

	public ArrayList<PolicyContact> getPolicyContactList() {
		return policyContactList;
	}

	public void setPolicyContactList(ArrayList<PolicyContact> policyContactList) {
		this.policyContactList = policyContactList;
	}
}
