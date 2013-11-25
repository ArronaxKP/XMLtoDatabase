
public class PolicyPeriod {
	public String TransID; //LocationUnknown
	public String TransType;
	public String PPID; //(ID/Value)[1]
	public String BasedOnID; //(BasedOn/ID/Value)[1]
	public String CeateTime; // CreateTime[1] Stringadd(mi, Stringdiff(mi,getutcString(),getString())
	public String CreditScore; //showing lookup table result
	public String CurrentPremExt; //CurrentPremExt[1]
	public String EISEditEffString;//Stringadd(mi, Stringdiff(mi,getutcString(),getString()), x.item.value('CreateTime[1]', 'Stringtime'))AS CreateTime,
	public String EISPolicyTermNumber; //EISPolicyTermNumber[1]
	public String FcraNotice; //FcraNotice[1]
	public String JobID; //(Job/ID/Value)[1]
	public String MostRecentModel; //(MostRecentModel)[1]
	public String PolPerpublicID; //(publicID)[1]
	public String PolicyID; //(Policy/ID/Value)[1]
	public String PolicyNumber; //PolicyNumber[1]
	public String PolExpString; //Stringadd(mi, Stringdiff(mi,getutcString(),getString()),x.item.value('(PeriodEnd)[1]', 'Stringtime2(7)')) AS PolExpString,
	public String PolEffString; //Stringadd(mi, Stringdiff(mi,getutcString(),getString()),x.item.value('(PeriodStart)[1]', 'Stringtime2(7)')) AS PolEffString,	
	public String RateAsOfString; //Stringadd(mi, Stringdiff(mi,getutcString(),getString()),x.item.value('(RateAsOfString)[1]', 'Stringtime2(7)')) AS RateAsOfString,
	public String Status; //(Status)[1]
	public String TermNumber; //(TermNumber)[1]
	public String TermPremiumExt; //(TermPremiumAmountExt)[1] --Need to ask Jonathan from where these come
	public String TotalPremiumRPT; //(TotalPremiumRPT)[1]
	public String TransactionCostRPT; //(TransactionCostRPT)[1]
	public String TransactionFeeAmt; //(TransactionFeeAmountExt)[1]
	public String TransactionPremiumRPT; //(TransactionPremiumRPT)[1]
	public String UpStringTime; //Stringadd(mi, Stringdiff(mi,getutcString(),getString()),x.item.value('(UpStringTime)[1]', 'Stringtime')) AS UpStringTime,
	public String ValidQuote; //(ValidQuote)[1]
	public String WrittenString; //Stringadd(mi, Stringdiff(mi,getutcString(),getString()),x.item.value('(WrittenString)[1]', 'Stringtime2(7)')) AS WrittenString,
	public String AccountID; //(Policy/Account/ID/Value)[1]
	public String AccountNumber; //(Policy/Account/AccountNumber)[1]
	public String UpStringUserID; //(UpStringUser/ID/Value)[1]
	public String UpStringUserContactID; //(UpStringUser/Contact/ID/Value)[1]
	public String UpStringUserContactFirstName; //(UpStringUser/Contact/FirstName)[1]
	public String UpStringUserContactLastName; //(UpStringUser/Contact/LastName)[1]
	public String UpStringUserRoleID; //(UpStringUser/Roles/Entry/Role/ID/Value)[1]
	public String RoleName; //(UpStringUser/Roles/Entry/Role/Name)[1]
	public String CreateUserID; //(CreateUser/ID/Value)[1]
	public String CreateUserContactID; //(CreateUser/Contact/ID/Value)[1]
	public String CreateUserContactFirstName; //(CreateUser/Contact/FirstName)[1]
	public String CreateUserContactLastName; //(CreateUser/Contact/LastName)[1]
	public String EmailAddress1; //(EmailAddress1)[1]
	public String AccountCreateUserFirstName; //(Policy/Account/CreateUser/Contact/FirstName)[1]
	public String AccountCreateUserLastName; //(Policy/Account/CreateUser/Contact/LastName)[1]
	public String QuoteTierGroup; //QuoteTierGroupExt[1]
	public String QuoteTierScore; //QuoteTierScoreExt[1]
}