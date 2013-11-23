import java.math.BigInteger;
import java.util.Date;

public class PolicyPeriod {
	public BigInteger TransID; //LocationUnknown
	public char[] TransType = new char[20];
	public BigInteger PPID; //(ID/Value)[1]
	public BigInteger BasedOnID; //(BasedOn/ID/Value)[1]
	public Date CeateTime; // CreateTime[1] dateadd(mi, datediff(mi,getutcdate(),getdate())
	public char[]CreditScore = new char[20]; //showing lookup table result
	public Decimal CurrentPremExt; //CurrentPremExt[1]
	public Date EISEditEffDate;//dateadd(mi, datediff(mi,getutcdate(),getdate()), x.item.value('CreateTime[1]', 'datetime'))AS CreateTime,
	public int EISPolicyTermNumber; //EISPolicyTermNumber[1]
	public boolean FcraNotice; //FcraNotice[1]
	public BigInteger JobID; //(Job/ID/Value)[1]
	public boolean MostRecentModel; //(MostRecentModel)[1]
	public char[] PolPerPublicID = new char[20]; //(PublicID)[1]
	public BigInteger PolicyID; //(Policy/ID/Value)[1]
	public char[] PolicyNumber = new char[40]; //PolicyNumber[1]
	public Date PolExpDate; //dateadd(mi, datediff(mi,getutcdate(),getdate()),x.item.value('(PeriodEnd)[1]', 'datetime2(7)')) AS PolExpDate,
	public Date PolEffDate; //dateadd(mi, datediff(mi,getutcdate(),getdate()),x.item.value('(PeriodStart)[1]', 'datetime2(7)')) AS PolEffDate,	
	public Date RateAsOfDate; //dateadd(mi, datediff(mi,getutcdate(),getdate()),x.item.value('(RateAsOfDate)[1]', 'datetime2(7)')) AS RateAsOfDate,
	public char[] Status = new char[256]; //(Status)[1]
	public int TermNumber; //(TermNumber)[1]
	public Decimal TermPremiumExt; //(TermPremiumAmountExt)[1] --Need to ask Jonathan from where these come
	public Decimal TotalPremiumRPT; //(TotalPremiumRPT)[1]
	public Decimal TransactionCostRPT; //(TransactionCostRPT)[1]
	public Decimal TransactionFeeAmt; //(TransactionFeeAmountExt)[1]
	public Decimal TransactionPremiumRPT; //(TransactionPremiumRPT)[1]
	public Date UpdateTime; //dateadd(mi, datediff(mi,getutcdate(),getdate()),x.item.value('(UpdateTime)[1]', 'datetime')) AS UpdateTime,
	public boolean ValidQuote; //(ValidQuote)[1]
	public Date WrittenDate; //dateadd(mi, datediff(mi,getutcdate(),getdate()),x.item.value('(WrittenDate)[1]', 'datetime2(7)')) AS WrittenDate,
	public BigInteger AccountID; //(Policy/Account/ID/Value)[1]
	public char[] AccountNumber = new char[255]; //(Policy/Account/AccountNumber)[1]
	public char[] UpdateUserID = new char[20]; //(UpdateUser/ID/Value)[1]
	public BigInteger UpdateUserContactID; //(UpdateUser/Contact/ID/Value)[1]
	public char[] UpdateUserContactFirstName = new char[30]; //(UpdateUser/Contact/FirstName)[1]
	public char[] UpdateUserContactLastName = new char[30]; //(UpdateUser/Contact/LastName)[1]
	public BigInteger UpdateUserRoleID; //(UpdateUser/Roles/Entry/Role/ID/Value)[1]
	public char[] RoleName = new char[60]; //(UpdateUser/Roles/Entry/Role/Name)[1]
	public char[] CreateUserID = new char[20]; //(CreateUser/ID/Value)[1]
	public BigInteger CreateUserContactID; //(CreateUser/Contact/ID/Value)[1]
	public char[] CreateUserContactFirstName = new char[30]; //(CreateUser/Contact/FirstName)[1]
	public char[] CreateUserContactLastName = new char[30]; //(CreateUser/Contact/LastName)[1]
	public char[] EmailAddress1 = new char[60]; //(EmailAddress1)[1]
	public char[] AccountCreateUserFirstName = new char[255]; //(Policy/Account/CreateUser/Contact/FirstName)[1]
	public char[] AccountCreateUserLastName = new char[255]; //(Policy/Account/CreateUser/Contact/LastName)[1]
	public BigInteger QuoteTierGroup; //QuoteTierGroupExt[1]
	public BigInteger QuoteTierScore; //QuoteTierScoreExt[1]
}