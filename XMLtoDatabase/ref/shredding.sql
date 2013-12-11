USE [PC_Staging]
GO
/****** Object:  StoredProcedure [Quotes].[usp_XMLShredQuotes]    Script Date: 11/22/2013 1:41:54 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


ALTER PROCEDURE [Quotes].[usp_XMLShredQuotes]
		@TransID BIGINT,
		--@XMLTxt VARCHAR(MAX),
		@XML XML,
		@Type varchar(20)
AS
BEGIN




DECLARE-- @XML XML,
		@PolPdID BIGINT,
		@AcctID BIGINT,
		@existingRecord VARCHAR(20)


	;WITH XMLNAMESPACES (DEFAULT 'eis/pc/policyperiod') --policyPeriod (All trans)
	SELECT @existingRecord = x.item.value('PolicyNumber[1]','varchar(40)') 
	FROM @xml.nodes('//PolicyPeriod') AS x(item)
	INNER JOIN QUotes.PolicyPeriod pp ON pp.PolicyNumber = x.item.value('PolicyNumber[1]','varchar(40)') 
	AND pp.createtime =  dateadd(mi, datediff(mi,getutcdate(),getdate()), x.item.value('CreateTime[1]', 'datetime')) 
	AND pp.updatetime = dateadd(mi, datediff(mi,getutcdate(),getdate()),x.item.value('(UpdateTime)[1]', 'datetime')) 
	AND pp.[TransType] = @Type
	;
IF @existingRecord IS NOT NULL
BEGIN
INSERT INTO [ErrorTracking].[dbo].[ErrorXMLTransactions]
        ( XMLPayload, ID, Type, DataLoadDate )
VALUES  ( @XML,
         @TransID,
          'Duplicate Record',
          GETDATE()
          )
END
IF @existingRecord IS NULL
BEGIN
--INSERT queries follow:
BEGIN TRY;
BEGIN TRAN;
	WITH XMLNAMESPACES (DEFAULT 'eis/pc/policyperiod') --policyPeriod (All trans)
	INSERT into [Quotes].PolicyPeriod
	(TransID, TransType, PPID, BasedOnID, CreateTime, CreditScore, CurrentPremExt, EISEditEffDate, EISPolicyTermNumber, 
	 FcraNotice, JobID, MostRecentModel, polPerPublicID, PolicyID, PolicyNumber, PolExpDate, PolEffDate,  RateAsOfDate, 
	 Status, TermNumber, TermPremiumExt, TotalPremiumRPT, TransactionCostRPT, TransactionFeeAmt, TransactionPremiumRPT, 
	 UpdateTime, ValidQuote, WrittenDate, AccountID, AccountNumber, UpdateUserID, UpdateUserContactID, 
	 UpdateUserContactFirstName, UpdateUserContactLastName, UpdateUserRoleID, RoleName, CreateUserID, CreateUserContactID, 
	 CreateUserContactFirstName, CreateUserContactLastName, EmailAddress1, AccountCreateUserFirstName, AccountCreateUserLastName,
	 QuoteTierGroupExt, QuoteTierScoreExt
	)

	SELECT
	@TransID as TransID,
	@Type as TransType,
	x.item.value('(ID/Value)[1]', 'bigint') AS PPID,
	x.item.value('(BasedOn/ID/Value)[1]', 'bigint') AS BasedOnID,
	dateadd(mi, datediff(mi,getutcdate(),getdate()), x.item.value('CreateTime[1]', 'datetime'))AS CreateTime,
	x.item.value('CreditScore[1]','varchar(20)') AS CreditScore, --showing lookup table result
	x.item.value('CurrentPremExt[1]', 'decimal(18,2)') AS CurrentPremExt,
	dateadd(mi, datediff(mi,getutcdate(),getdate()), x.item.value('EISEditEffectiveDateExt[1]', 'datetime'))AS EISEditEffDate,
	x.item.value('EISPolicyTermNumber[1]','int') AS EISPolicyTermNumber,
	x.item.value('FcraNotice[1]', 'bit') AS FcraNotice,
	x.item.value('(Job/ID/Value)[1]', 'bigint') AS JobID,
	x.item.value('(MostRecentModel)[1]','bit') AS MostRecentModel,
	x.item.value('(PublicID)[1]', 'Varchar(20)') AS polPerPublicID,
	x.item.value('(Policy/ID/Value)[1]', 'bigint') AS PolicyID,
	x.item.value('PolicyNumber[1]','varchar(40)') AS PolicyNumber,
	dateadd(mi, datediff(mi,getutcdate(),getdate()),x.item.value('(PeriodEnd)[1]', 'datetime2(7)')) AS PolExpDate,
	dateadd(mi, datediff(mi,getutcdate(),getdate()),x.item.value('(PeriodStart)[1]', 'datetime2(7)')) AS PolEffDate,	
	dateadd(mi, datediff(mi,getutcdate(),getdate()),x.item.value('(RateAsOfDate)[1]', 'datetime2(7)')) AS RateAsOfDate,
	x.item.value('(Status)[1]', 'varchar(256)') AS [Status],
	x.item.value('(TermNumber)[1]', 'int') AS TermNumber,
	x.item.value('(TermPremiumAmountExt)[1]', 'decimal(18,2)') AS TermPremiumExt,--Need to ask Jonathan from where these come
	x.item.value('(TotalPremiumRPT)[1]', 'decimal(18,2)') AS TotalPremiumRPT,
	x.item.value('(TransactionCostRPT)[1]', 'decimal(18,2)') AS TransactionCostRPT,
	x.item.value('(TransactionFeeAmountExt)[1]', 'decimal(18,2)') AS TransactionFeeAmt,
	x.item.value('(TransactionPremiumRPT)[1]', 'decimal(18,2)') AS TransactionPremiumRPT,
	dateadd(mi, datediff(mi,getutcdate(),getdate()),x.item.value('(UpdateTime)[1]', 'datetime')) AS UpdateTime,
	x.item.value('(ValidQuote)[1]', 'bit') AS ValidQuote,
	dateadd(mi, datediff(mi,getutcdate(),getdate()),x.item.value('(WrittenDate)[1]', 'datetime2(7)')) AS WrittenDate,
	x.item.value('(Policy/Account/ID/Value)[1]', 'bigint') AS AccountID,
	x.item.value('(Policy/Account/AccountNumber)[1]', 'VARCHAR(255)') AS AccountNumber,
	x.item.value('(UpdateUser/ID/Value)[1]', 'VARCHAR(20)') AS UpdateUserID,
	x.item.value('(UpdateUser/Contact/ID/Value)[1]', 'bigint') AS UpdateUserContactID,
	x.item.value('(UpdateUser/Contact/FirstName)[1]', 'VARCHAR(30)') AS UpdateUserContactFirstName,
	x.item.value('(UpdateUser/Contact/LastName)[1]', 'VARCHAR(30)') AS UpdateUserContactLastName,
	x.item.value('(UpdateUser/Roles/Entry/Role/ID/Value)[1]', 'bigint') AS UpdateUserRoleID,
	x.item.value('(UpdateUser/Roles/Entry/Role/Name)[1]', 'VARCHAR(60)') AS RoleName,
	x.item.value('(CreateUser/ID/Value)[1]', 'VARCHAR(20)') AS CreateUserID,
	x.item.value('(CreateUser/Contact/ID/Value)[1]', 'bigint') AS CreateUserContactID,
	x.item.value('(CreateUser/Contact/FirstName)[1]', 'VARCHAR(30)') AS CreateUserContactFirstName,
	x.item.value('(CreateUser/Contact/LastName)[1]', 'VARCHAR(30)') AS CreateUserContactLastName,
	x.item.value('(EmailAddress1)[1]', 'VARCHAR(60)') AS EmailAddress1,
	x.item.value('(Policy/Account/CreateUser/Contact/FirstName)[1]', 'VARCHAR(255)') AS AccountCreateUserFirstName,
	x.item.value('(Policy/Account/CreateUser/Contact/LastName)[1]', 'VARCHAR(255)') AS AccountCreateUserLastName,
	x.item.value('QuoteTierGroupExt[1]', 'bigint') AS QuoteTierGroup,
	x.item.value('QuoteTierScoreExt[1]', 'bigint') AS QuoteTierScore

	FROM @xml.nodes('//PolicyPeriod') AS x(item);

	--WITH XMLNAMESPACES (DEFAULT 'eis/pc/policyperiod') --policyPeriod/AllCost (All trans)
	--insert into AllCosts
	--SELECT
	--@TransID as TransID,
	--@Type as TransType,
	--@PolPdID as PolicyPeriodID,
	--@AcctID as AccountID,
	--x.item.value('(ID/Value)[1]', 'bigint') AS AllCostID,
	--x.item.value('(FixedId/Value)[1]', 'bigint') AS AllCostFixedID,
	--x.item.value('(BasedOn/ID/Value)[1]', 'bigint') AS AllCostBasedOnID,
	--x.item.value('(BasedOn/FixedId/Value)[1]', 'bigint') AS AllCostBasedOnFixedID,
	--dateadd(mi, datediff(mi,getutcdate(),getdate()),x.item.value('(CreateTime)[1]', 'datetime2(7)')) AS AllCostCreateTime,
	--x.item.value('(ActualAdjRate)[1]', 'decimal(18,2)') AS ActualAdjRate,
	--x.item.value('(ActualTermAmount)[1]', 'decimal(18,2)') AS ActualTermAmount,
	--x.item.value('(ChargePattern/Code)[1]', 'VARCHAR(50)') AS ChargePattern,
	--x.item.value('(PublicID)[1]', 'VARCHAR(64)') AS AllCostPublicID,
	--x.item.value('(Prorated)[1]', 'bit') AS Prorated, --calculated?
	--x.item.value('(Proration)[1]', 'VARCHAR(20)') AS Proration, --calculated?
	--x.item.value('(Subtype/Code)[1]', 'VARCHAR(65)') AS Subtype,
	--dateadd(mi, datediff(mi,getutcdate(),getdate()),x.item.value('(UpdateTime)[1]', 'datetime2(7)')) AS AllCostUpdateTime
	--FROM @XMLML.nodes('//PolicyPeriod/AllCosts/Entry') AS x(item);

	WITH XMLNAMESPACES (DEFAULT 'eis/pc/policyperiod') --policyPeriod (All trans)
	SELECT
	@PolPdID = x.item.value('(ID/Value)[1]', 'bigint'),
	@AcctID = x.item.value('(Policy/Account/ID/Value)[1]', 'bigint')
	FROM @XML.nodes('//PolicyPeriod') AS x(item);

	WITH XMLNAMESPACES (DEFAULT 'eis/pc/policyperiod' ) --PATransactions/Entry (needed for NB, Cancel, reinstate, rewrite, and renewal) Everything but quotes (check amendments)
	INSERT INTO [Quotes].Coverage
	(TransID, PolicyPeriodID, AccountID, PATransID, FixedID, Amount, Charged, CreateTime,
	 EffDate, ExpDate, PostedDate, PATransPublicID, ToBeAccrued, UpdateTime, WrittenDate, 
	 PACostID, PACostFixedID, PACostBasedOnID, PACostBasedOnFixedID, PACostCreateTime, ActualAdjRate, 
	 ActualTermAmount, ChargePattern, PACostPublicID, Prorated, Proration, Subtype, PACostUpdateTime, 
	 CovID, CovPatternCode, DisplayValue, PatternCode, ValueTypeName, VehID, FixedVehID, UpdateUserID, ContactID, RoleID, RoleName
	)
	SELECT
	@TransID as TransID,
	@PolPdID AS PolicyPeriodID,
	@AcctID AS AccountID,
	x.item.value('(ID/Value)[1]', 'bigint') AS PATransID,
	--x.item.value('(BasedOn/ID/Value)[1]', 'VARCHAR(20)') AS BasedOnID,
	x.item.value('(FixedId/Value)[1]', 'VARCHAR(20)') AS FixedID,
	x.item.value('(Amount)[1]', 'decimal(18,2)') AS Amount,
	x.item.value('(Charged)[1]', 'bit') AS Charged,
	dateadd(mi, datediff(mi,getutcdate(),getdate()),x.item.value('(CreateTime)[1]', 'datetime2(7)')) AS CreateTime,
	dateadd(mi, datediff(mi,getutcdate(),getdate()),x.item.value('(EffDate)[1]', 'datetime2(7)')) AS EffDate,
	dateadd(mi, datediff(mi,getutcdate(),getdate()),x.item.value('(ExpDate)[1]', 'datetime2(7)')) AS ExpDate,
	dateadd(mi, datediff(mi,getutcdate(),getdate()),x.item.value('(PostedDate)[1]', 'datetime2(7)')) AS PostedDate,
	x.item.value('(PublicID)[1]', 'VARCHAR(64)') AS PATransPublicID,
	x.item.value('(ToBeAccrued)[1]', 'bit') AS ToBeAccrued,
	dateadd(mi, datediff(mi,getutcdate(),getdate()),x.item.value('(UpdateTime)[1]', 'datetime2(7)')) AS UpdateTime,
	dateadd(mi, datediff(mi,getutcdate(),getdate()),x.item.value('(WrittenDate)[1]', 'datetime2(7)')) AS WrittenDate,
	x.item.value('(PACost/ID/Value)[1]', 'bigint') AS PACostID,
	x.item.value('(PACost/FixedId/Value)[1]', 'bigint') AS PACostFixedID,
	x.item.value('(PACost/BasedOn/ID/Value)[1]', 'bigint') AS PACostBasedOnID,
	x.item.value('(PACost/BasedOn/FixedId/Value)[1]', 'bigint') AS PACostBasedOnFixedID,
	dateadd(mi, datediff(mi,getutcdate(),getdate()),x.item.value('(PACost/CreateTime)[1]', 'datetime2(7)')) AS PACostCreateTime,
	x.item.value('(PACost/ActualAdjRate)[1]', 'decimal(14,4)') AS ActualAdjRate,
	x.item.value('(PACost/ActualTermAmount)[1]', 'decimal(18,2)') AS ActualTermAmount,
	x.item.value('(PACost/ChargePattern/Code)[1]', 'VARCHAR(50)') AS ChargePattern,
	x.item.value('(PACost/PublicID)[1]', 'VARCHAR(64)') AS PACostPublicID,
	x.item.value('(PACost/Prorated)[1]', 'bit') AS Prorated, --calculated
	x.item.value('(PACost/Proration)[1]', 'VARCHAR(20)') AS Proration, --calculated
	x.item.value('(PACost/Subtype/Code)[1]', 'VARCHAR(65)') AS Subtype,
	dateadd(mi, datediff(mi,getutcdate(),getdate()),x.item.value('(PACost/UpdateTime)[1]', 'datetime2(7)')) AS PACostUpdateTime,
	x.item.value('(PACost/Coverage/ID/Value)[1]', 'bigint') AS CovID,
	x.item.value('(PACost/Coverage/PatternCode)[1]', 'VARCHAR(50)') AS CovPatternCode,
	x.item.value('(PACost/Coverage/CovTerms/Entry/DisplayValue)[1]', 'varchar(20)') AS DisplayValue,
	x.item.value('(PACost/Coverage/CovTerms/Entry/PatternCode)[1]', 'varchar(50)') AS PatternCode,
	x.item.value('(PACost/Coverage/CovTerms/Entry/ValueTypeName)[1]', 'varchar(50)') AS ValueTypeName,
	x.item.value('(PACost/Vehicle/ID/Value)[1]', 'bigint') AS VehID,
	x.item.value('(PACost/Vehicle/FixedId/Value)[1]', 'bigint') AS FixedVehID,
	x.item.value('(UpdateUser/ID/Value)[1]','bigint'	) AS UpdateUserID,
	x.item.value('(UpdateUser/Contact/ID/Value)[1]', 'bigint') AS ContactID,
	x.item.value('(UpdateUser/Roles/Entry/Role/ID/Value)[1]', 'bigint') AS RoleID,
	x.item.value('(UpdateUser/Roles/Entry/Role/Name)[1]', 'VARCHAR(60)') AS RoleName
	FROM @XML.nodes('//PolicyPeriod/PATransactions/Entry') AS x(item);

	WITH XMLNAMESPACES (DEFAULT 'eis/pc/policyperiod') --PersonalAutoLine (all transactions, including quotes)
	INSERT INTO [Quotes].PersonalAutoLine
		(TransID, PolicyPeriodID, AccountID, PALineID, BasedOnID, affinityExt, affinityGroupExt, AllowTextExt, ApplicantID, AppFixedID, atFaultExt, 
	 BaseState, ChannelExt, ChildOnPolicyExt, CreateTime, CreditScoreExt, CurrentBILimits, CurrentCarrierExt, CurrentPremExt, EmailAddress1, 
	 ESignatureExt, Guest_PIP, HasCurrInsurExt, HomeownerExt, LapseInCoverageExt, minorChildExt, MultiCarExt, NonChargIncidents, numDriversExt, 
	 OnlineDiscountExt, PaidInFullExt, PaperlessDiscountExt, PolicyCapRatioExt, PromoCode, PALinePublicID, ReapplicationDiscountExt, 
	 ResidencyExt, ResponsibleDriverExt, SourceOfBusinessExt, TierExt, TierScoreExt, UpdateTime, WebToPhoneDiscountExt, 
	 YearsWithProviderExt, youthfulExt, youthfulPNIExt
	)
	SELECT
	@TransID as TransID,
	@PolPdID AS PolicyPeriodID,
	@AcctID AS AccountID,
	x.item.value('(ID/Value)[1]', 'bigint') AS PALineID,
	x.item.value('(BasedOn/ID/Value)[1]', 'bigint') AS BasedOnID,
	x.item.value('AffinityExt[1]', 'varchar(50)') AS affinityExt,
	x.item.value('AffinityGroupExt[1]', 'VARCHAR(512)') AS affinityGroupExt,
	x.item.value('AllowTextExt[1]', 'bit') AS AllowTextExt,
	x.item.value('(ApplicantExt/ID/Value)[1]', 'bigint') AS ApplicantID,
	x.item.value('(ApplicantExt/FixedId/Value)[1]', 'bigint') AS AppFixedID,
	x.item.value('AtFaultExt[1]', 'varchar(50)') AS atFaultExt, --Need to ask Jonathan from where this comes
	x.item.value('(BaseState)[1]', 'varchar(20)') AS BaseState,
	x.item.value('(ChannelEXT)[1]', 'varchar(20)') AS ChannelExt,
	x.item.value('ChildOnPolicyExt[1]', 'bit') AS ChildOnPolicyExt, --calculated?
	dateadd(mi, datediff(mi,getutcdate(),getdate()),x.item.value('(CreateTime)[1]', 'datetime2(7)')) AS CreateTime,
	x.item.value('(ApplicantExt/CreditScoreExt)[1]', 'varchar(50)') AS CreditScoreExt, 
	----x.item.value('(creditTierEXT)[1]', 'VARCHAR(20)') AS creditTierExt, --not in current GX Model, now on PolicyDrivers
	x.item.value('(CurrentInjuryLimitsExt)[1]', 'varchar(50)') AS CurrentBILimits,
	x.item.value('(CurrentCarrier_Ext)[1]', 'varchar(50)') AS CurrentCarrierExt,
	x.item.value('CurrentPremiumExt[1]', 'decimal(18,2)') AS CurrentPremExt, --Now on PolicyPeriod/Nope, this is actually where this is stored.
	x.item.value('(EmailAddress1)[1]', 'varchar(60)') AS EmailAddress1,
	x.item.value('(ESignatureExt)[1]', 'bit') AS ESignatureExt,
	x.item.value('(PAPIP_MD/CovTerms/Entry/DisplayValue)[2]', 'VARCHAR(5)') as Guest_PIP,
	x.item.value('(HasAutoInsuranceExt)[1]', 'varchar(50)') AS HasCurrInsurExt,
	x.item.value('(homeownerExt)[1]', 'bit') AS HomeownerExt, --Need to ask Jonathan from where this comes
	x.item.value('(LapseInCoverageExt)[1]', 'varchar(50)') AS LapseInCoverageExt,
	x.item.value('(MinorChildExt)[1]', 'bit') AS minorChildExt,--Need to ask Jonathan from where this comes
	x.item.value('(MultiCarExt)[1]', 'bit') AS MultiCarExt,
	x.item.value('(nCIncidents)[1]', 'varchar(20)') AS NonChargIncidents,
	x.item.value('(NumDriversExt)[1]', 'int') AS numDriversExt,
	x.item.value('(OnlineDiscountExt)[1]', 'bit') AS OnlineDiscountExt,
	x.item.value('(PaidInFullExt)[1]', 'bit') AS PaidInFullExt,
	x.item.value('(PaperlessDiscountExt)[1]', 'bit') AS PaperlessDiscountExt,
	x.item.value('(PolicyCapRatioExt)[1]', 'decimal(4,3)') AS PolicyCapRatioExt,
	--x.item.value('(promoEXT)[1]', 'varchar(50)') AS PromoExt, --missing from current GX Model
	x.item.value('(PromoCode)[1]', 'varchar(20)') AS PromoCode,
	x.item.value('(PublicID)[1]', 'VARCHAR(64)') AS PALinePublicID,
	x.item.value('(ReapplicationDiscountExt)[1]', 'bit') AS ReapplicationDiscountExt,
	x.item.value('(ResidencyExt)[1]', 'varchar(50)') AS ResidencyExt,
	x.item.value('(responsibleDriverExt)[1]', 'bit') AS ResponsibleDriverExt,
	x.item.value('(SourceOfBusinessExt)[1]', 'varchar(50)') AS SourceOfBusinessExt,
	x.item.value('(TierExt)[1]', 'varchar(50)') AS TierExt,
	x.item.value('(TierScoreExt)[1]', 'varchar(50)') AS TierScoreExt,
	--x.item.value('(TotalTAIPAPremiumExt)[1]', 'varchar(20)') AS TotalTAIPAPremiumExt, Not needed
	dateadd(mi, datediff(mi,getutcdate(),getdate()),x.item.value('(UpdateTime)[1]', 'datetime2(7)')) AS UpdateTime,
	x.item.value('(WebToPhoneDiscountExt)[1]', 'bit') AS WebToPhoneDiscountExt,
	--x.item.value('(YearsLicensedExt)[1]', 'varchar(50)') AS YearsLicensedExt,
	x.item.value('(YearsWithProviderExt)[1]', 'varchar(50)') AS YearsWithProviderExt,
	x.item.value('(YouthfulEXT)[1]', 'bit') AS youthfulExt, --Need to ask Jonathan from where this comes
	x.item.value('(YouthfulPNIExt)[1]', 'bit') AS youthfulPNIExt  
	FROM @XML.nodes('//PolicyPeriod/PersonalAutoLine') AS x(item);

	--WITH XMLNAMESPACES (DEFAULT 'eis/pc/policyperiod') --PersonalAutoLine (all coverages)
	--INSERT INTO dbo.AllCoverages
	--SELECT
	--@TransID as TransID,
	--@PolPdID AS PolicyPeriodID,
	--@AcctID AS AccountID,
	--x.item.value('(CovTerms/Entry/DisplayValue)[1]', 'VARCHAR(20)') AS AllCoveragesDisplayValue, --start here
	--x.item.value('(CovTerms/Entry/PatternCode)[1]', 'VARCHAR(50)') AS AllCoveragesCovTermsPatternCode, --thisdone
	--x.item.value('(CovTerms/Entry/ValueTypeName)[1]', 'VARCHAR(50)') AS AllCoveragesValueTypeName, --need this
	--dateadd(mi, datediff(mi,getutcdate(),getdate()),x.item.value('(CreateTime)[1]', 'datetime2(7)')) AS AllCoveragesCreateTime,
	--x.item.value('(ID/Value)[1]', 'bigint') AS AllCoveragesID,
	--x.item.value('(PatternCode)[1]', 'VARCHAR(50)') AS AllCoveragesPatternCode,
	--x.item.value('(PublicID)[1]', 'VARCHAR(64)') AS AllCoveragesPublicID,
	--dateadd(mi, datediff(mi,getutcdate(),getdate()),x.item.value('(UpdateTime)[1]', 'datetime2(7)')) AS AllCoveragesUpdateTime
	--FROM @XML.nodes('//PolicyPeriod/PersonalAutoLine/AllCoverages/Entry') AS x(item);

	WITH XMLNAMESPACES (DEFAULT 'eis/pc/policyperiod') --PersonalAutoLine (all coverages)
		INSERT INTO [Quotes].Incidents
	(TransID, PolicyPeriodID, AccountID, IncidentID, CreateTime, IncidentDescription, OccurrenceDate, OtherDriver, 
	 IncidentPublicID, Remarks, Subtype, UpdateTime, PolicyDriverID, AccountContactRoleID, AccountContactID, ContactID)
	SELECT
	@TransID as TransID,
	@PolPdID AS PolicyPeriodID,
	@AcctID AS AccountID,
	x.item.value('(ID/Value)[1]', 'bigint') AS IncidentID,
	dateadd(mi, datediff(mi,getutcdate(),getdate()),x.item.value('(CreateTime)[1]', 'datetime2(7)')) AS CreateTime,
	x.item.value('(IncidentDescription)[1]', 'VARCHAR(50)') AS IncidentDescription, 
	dateadd(mi, datediff(mi,getutcdate(),getdate()),x.item.value('(OccurenceDate)[1]', 'datetime2(7)')) AS OccurenceDate, 
	x.item.value('(OtherDriver)[1]', 'VARCHAR(20)') AS OtherDriver,
	x.item.value('(PublicID)[1]', 'VARCHAR(20)') AS IncidentPublicID,
	x.item.value('(Remarks)[1]', 'VARCHAR(50)') AS Remarks,
	x.item.value('(Subtype)[1]', 'VARCHAR(50)') AS SubType,
	dateadd(mi, datediff(mi,getutcdate(),getdate()),x.item.value('(UpdateTime)[1]', 'datetime2(7)')) AS UpdateTime, 
	x.item.value('(PolicyDriver/ID/Value)[1]', 'VARCHAR(64)') AS PolicyDriverID,
	x.item.value('(PolicyDriver/AccountContactRole/ID/Value)[1]', 'VARCHAR(64)') AS AccountContactRoleID, --25440
	x.item.value('(PolicyDriver/AccountContactRole/AccountContact/ID/Value)[1]', 'VARCHAR(64)') AS AccountContactID, --15233
	x.item.value('(PolicyDriver/AccountContactRole/AccountContact/Contact/ID/Value)[1]', 'VARCHAR(64)') AS ContactID --15435
	FROM @XML.nodes('//PolicyPeriod/PersonalAutoLine/Incidents/Entry') AS x(item);
	
	WITH XMLNAMESPACES ( DEFAULT 'eis/pc/policyperiod') --PolicyPeriod/PersonalAutoLine/Vehicles/Entry'
	INSERT into [Quotes].Vehicle
	(TransID, PolicyPeriodID, AccountID, VehID, FixedVehID, VehAge, BodyType, AssocPrimDriverID, AcctContRoleID, CostNew, 
	 CoverableDate, CreateTime, DMAExt, ExistingDamageExt, ExoticVehicleExt, ExpDate, GarageLocationCounty, GarageLocationPostalCode, IgnoreVINExt, LeaseOrRent, LicenseState, 
	 Make, Model, PrimaryUse, TerritoryExt, VehPublicID, VehNum, VehType, VehYear, UpdateTime, VIN
	)
	SELECT 
	@TransID as TransID,
	@PolPdID AS PolicyPeriodID,
	@AcctID AS AccountID,
	x.item.value('(ID/Value)[1]', 'bigint') AS VehID,
	x.item.value('(FixedId/Value)[1]', 'bigint') AS FixedVehID,
	x.item.value('Age[1]', 'int') AS VehAge,
	x.item.value('(BodyType)[1]', 'varchar(50)') AS BodyType, 
	x.item.value('(AssociatedPrimaryDriverExt/ID/Value)[1]', 'bigint') AS AssocPrimDriverID, 
	x.item.value('(AssociatedPrimaryDriverExt/AccountContactRole/ID/Value)[1]', 'bigint') AS AcctContRoleID,
	x.item.value('CostNew[1]', 'decimal(18,2)') AS CostNew,
	x.item.value('CoverableReferenceDate[1]', 'datetime2(7)') AS CoverableDate,
	dateadd(mi, datediff(mi,getutcdate(),getdate()),x.item.value('CreateTime[1]', 'datetime2(7)')) AS CreateTime,
	x.item.value('(DMAExt)[1]', 'int') AS DMAExt, --???
	x.item.value('ExistingDamageExt[1]', 'bit') AS ExistingDamageExt,
	x.item.value('ExoticVehicleExt[1]', 'bit') AS ExoticVehicleExt,
	dateadd(mi, datediff(mi,getutcdate(),getdate()),x.item.value('ExpirationDate[1]', 'datetime2(7)')) AS ExpDate,
	x.item.value('(GarageLocation/County)[1]', 'VARCHAR(50)') AS GarageLocationCounty, --CMD 9/9/2013
	x.item.value('(GarageLocation/PostalCode)[1]', 'VARCHAR(10)') as GarageLocationCounty, --CMD 9/9/2013
	--x.item.value('(GarageLocations/Entry/County)[1]', 'VARCHAR(50)') AS GarageLocationCounty, --added 8/23/2013 SRK
	--x.item.value('(GarageLocations/Entry/PostalCode)[1]', 'VARCHAR(50)') AS GarageLocationPostalCode, --added 8/23/2013 SRK
	x.item.value('IgnoreVINExt[1]', 'bit') AS IgnoreVINExt,          
	x.item.value('LeaseOrRent[1]', 'bit') AS LeaseOrRent,
	x.item.value('LicenseState[1]', 'varchar(10)') AS LicenseState,
	x.item.value('Make[1]', 'varchar(32)') AS Make,
	x.item.value('Model[1]', 'varchar(32)') AS Model,
	x.item.value('PrimaryUse[1]', 'varchar(50)') AS PrimaryUse,
	x.item.value('(TerritoryExt)[1]', 'int') AS TerritoryExt,
	x.item.value('PublicID[1]', 'varchar(64)') AS VehPublicID,
	x.item.value('VehicleNumber[1]', 'int') AS VehNum,
	x.item.value('VehicleType[1]', 'varchar(50)') AS VehType,
	x.item.value('Year[1]', 'int') AS VehYear,
	dateadd(mi, datediff(mi,getutcdate(),getdate()),x.item.value('UpdateTime[1]', 'datetime2(7)')) AS UpdateTime,
	x.item.value('(Vin)[1]', 'VARCHAR(40)') AS VIN
	FROM @XML.nodes('//PolicyPeriod/PersonalAutoLine/Vehicles/Entry') AS x(item);

	--WITH XMLNAMESPACES ( DEFAULT 'eis/pc/policyperiod') --PolicyPeriod/PersonalAutoLine/Vehicles/Entry'
	--insert into  [Quotes].AdditionalInterests
	--(TransID, PolicyPeriodID, AccountID, VehID, VehFixedID, ID, FixedID, AddlInterestType, CreateTime, 
	-- AccountContactID, ContactID, AccountContactRoleID, UpdateTime
	--)
	--SELECT 
	--@TransID as TransID,
	--@PolPdID AS PolicyPeriodID,
	--@AcctID AS AccountID,
	--x.item.value('(ID/Value)[1]', 'bigint') AS VehID,
	--x.item.value('(FixedId/Value)[1]', 'bigint') AS VehFixedID,
	--x.item.value('(AdditionalInterests/Entry/ID/Value)[1]', 'bigint') AS ID,
	--x.item.value('(AdditionalInterests/Entry/FixedId/Value)[1]', 'bigint') AS FixedID,
	--x.item.value('(AdditionalInterests/Entry/AdditionalInterestType/Code)[1]', 'varchar(50)') AS AddlInterestType,
	--dateadd(mi, datediff(mi,getutcdate(),getdate()),x.item.value('CreateTime[1]', 'datetime2(7)')) AS CreateTime,
	--x.item.value('(AdditionalInterests/Entry/PolicyAddlInterest/AccountContactRole/AccountContact/ID/Value)[1]', 'bigint') AS AccountContactID,
	--x.item.value('(AdditionalInterests/Entry/PolicyAddlInterest/AccountContactRole/AccountContact/Contact/ID/Value)[1]', 'bigint') AS ContactID,
	--x.item.value('(AdditionalInterests/Entry/PolicyAddlInterest/AccountContactRole/ID/Value)[1]', 'bigint') AS AccountContactRoleID,
	--dateadd(mi, datediff(mi,getutcdate(),getdate()),x.item.value('UpdateTime[1]', 'datetime2(7)')) AS UpdateTime
	--FROM @XML.nodes('//PolicyPeriod/PersonalAutoLine/Vehicles/Entry') AS x(item)
	--where x.item.value('(AdditionalInterests/Entry/ID/Value)[1]', 'bigint') is not null;

	WITH XMLNAMESPACES ( DEFAULT 'eis/pc/policyperiod') --PolicyPeriod/PersonalAutoLine/Vehicles/Entry'
	insert into AdditionalInterests
	(TransID, PolicyPeriodID, AccountID, VehID, VehFixedID, ID, FixedID, AddlInterestType, 
	CreateTime, AccountContactID, ContactID, AccountContactRoleID, UpdateTime )
	SELECT 
	@TransID as TransID,
	@PolPdID AS PolicyPeriodID,
	@AcctID AS AccountID,
	x.item.value('(//PolicyPeriod/PersonalAutoLine/Vehicles/Entry[AdditionalInterests/Entry/ID/Value=
						//PolicyPeriod/PersonalAutoLine/Vehicles/Entry/AdditionalInterests/Entry/ID/Value]/ID/Value)[1]', 'bigint') AS VehID,
	x.item.value('(//PolicyPeriod/PersonalAutoLine/Vehicles/Entry[AdditionalInterests/Entry/ID/Value=
						//PolicyPeriod/PersonalAutoLine/Vehicles/Entry/AdditionalInterests/Entry/ID/Value]/FixedId/Value)[1]', 'bigint') AS VehFixedID,
	x.item.value('(ID/Value)[1]', 'bigint') AS ID,
	x.item.value('(FixedId/Value)[1]', 'bigint') AS FixedID,
	x.item.value('(AdditionalInterestType/Code)[1]', 'varchar(50)') AS AddlInterestType,
    dateadd(mi, datediff(mi,getutcdate(),getdate()),x.item.value('(//PolicyPeriod/PersonalAutoLine/Vehicles/Entry[AdditionalInterests/Entry/ID/Value=
						//PolicyPeriod/PersonalAutoLine/Vehicles/Entry/AdditionalInterests/Entry/ID/Value]/CreateTime)[1]', 'datetime2(7)')) AS CreateTime,
	x.item.value('(PolicyAddlInterest/AccountContactRole/AccountContact/ID/Value)[1]', 'bigint') AS AccountContactID,
	x.item.value('(PolicyAddlInterest/AccountContactRole/AccountContact/Contact/ID/Value)[1]', 'bigint') AS ContactID,
	x.item.value('(PolicyAddlInterest/AccountContactRole/ID/Value)[1]', 'bigint') AS AccountContactRoleID,
	dateadd(mi, datediff(mi,getutcdate(),getdate()),x.item.value('(//PolicyPeriod/PersonalAutoLine/Vehicles/Entry[AdditionalInterests/Entry/ID/Value=
						//PolicyPeriod/PersonalAutoLine/Vehicles/Entry/AdditionalInterests/Entry/ID/Value]/UpdateTime)[1]', 'datetime2(7)')) AS UpdateTime
	FROM @xml.nodes('//PolicyPeriod/PersonalAutoLine/Vehicles/Entry/AdditionalInterests/Entry') AS x(item);

	WITH XMLNAMESPACES ( DEFAULT 'eis/pc/policyperiod')  ---PolicyPeriod/PersonalAutoLine/PolicyDrivers/Entry
	INSERT INTO [Quotes].PolicyDrivers        
	(TransID, PolicyPeriodID, AccountID, PolDriverID, BasedOnID, FixedID, CreateTime, Age, AgeFirstLicensed, 
	 AssocPrimVehID, BIPointsExt, CMPPointsExt, ColPointsExt, creditTierExt, CustPointsExt, LoanPointsExt, 
	 MedPointsExt, PDPointsExt, PIPMDPointsExt, PIPTXPointsExt, RentPointsExt, UMBIPointsExt, UMPDPointsExt, 
	 DriverClassFactor, Excluded, DiscGoodStudent, Education, FR19, FR44, LicenseNumber, LicenseState, LicenseStatus, 
	 MVROrdered, MaritalExt, OccupationGroup, OccupationStatus, OccupationTitle, Operator, PrimDrivPublicID, 
	 RatedCreditTierExt, RatingStatus, Relationship, SR22, TrainingClassType, YearsLicensed, UpdateTime, FirstName, 
	 Gender, MiddleName, LastName, Suffix, MaritalStatus
	)
	select 
	@TransID as TransID,
	@PolPdID AS PolicyPeriodID,
	@AcctID AS AccountID,
	x.item.value('(ID/Value)[1]', 'bigint') AS PolDriverID,
	x.item.value('(BasedOn/ID/Value)[1]', 'bigint') AS BasedOnID,
	x.item.value('(FixedId/Value)[1]', 'bigint') AS FixedID,
	dateadd(mi, datediff(mi,getutcdate(),getdate()),x.item.value('CreateTime[1]', 'datetime2(7)')) AS CreateTime,
	x.item.value('Age[1]', 'int') AS Age,
	x.item.value('AgeFirstLicensedExt[1]', 'int') AS AgeFirstLicensed,
	x.item.value('(AssociatedPrimaryVehicleExt/ID/Value)[1]', 'varchar(10)') AS AssocPrimVehID,
	x.item.value('BIPointsExt[1]', 'int') AS BIPointsExt,
	x.item.value('CMPPointsExt[1]', 'int') AS CMPPointsExt,
	x.item.value('ColPointsExt[1]', 'int') AS ColPointsExt,
	x.item.value('CreditTierExt[1]', 'VARCHAR(50)') AS creditTierExt,
	x.item.value('CustPointsExt[1]', 'int') AS CustPointsExt,
	x.item.value('LoanPointsExt[1]', 'int') AS LoanPointsExt,
	x.item.value('MedPointsExt[1]', 'int') AS MedPointsExt,
	x.item.value('PDPointsExt[1]', 'int') AS PDPointsExt,
	x.item.value('PIPMDPointsExt[1]', 'int') AS PIPMDPointsExt,
	x.item.value('PIPTXPointsExt[1]', 'int') AS PIPTXPointsExt,
	x.item.value('RentPointsExt[1]', 'int') AS RentPointsExt,
	x.item.value('UMBIPointsExt[1]', 'int') AS UMBIPointsExt,
	x.item.value('UMPDPointsExt[1]', 'int') AS UMPDPointsExt,
	x.item.value('DriverClassFactorExt[1]', 'varchar(50)') AS DriverClassFactor, 
	x.item.value('Excluded[1]', 'bit') AS Excluded,
	x.item.value('GoodStudentDiscountExt[1]', 'bit') AS DiscGoodStudent,
	x.item.value('HighestLevelOfEducationExt[1]', 'varchar(50)') AS Education,
	x.item.value('Fr19Ext[1]', 'bit') AS FR19,
	x.item.value('Fr44Ext[1]', 'bit') AS FR44,
	x.item.value('LicenseNumber[1]', 'varchar(20)') AS LicenseNumber, 
	x.item.value('LicenseState[1]', 'varchar(20)') AS LicenseState,
	x.item.value('LicenseStatusExt[1]', 'varchar(50)') AS LicenseStatus,
	x.item.value('MVROrderStatus[1]', 'varchar(50)') AS MVROrdered,
	x.item.value('MaritalExt[1]', 'varchar(50)') AS MaritalExt,
	--x.item.value('OccupationExt[1]', 'varchar(50)') AS Occupation,
	x.item.value('occupationGroupExt[1]', 'varchar(50)') AS OccupationGroup,
	x.item.value('OccupationStatusListExt[1]', 'varchar(50)') AS OccupationStatus,
	x.item.value('OccupationTitleExt[1]', 'varchar(300)') AS OccupationTitle,
	x.item.value('Operator[1]', 'varchar(50)') AS Operator,
	x.item.value('PublicID[1]', 'varchar(64)') AS PrimDrivPublicID,
	x.item.value('RatedCreditTierExt[1]', 'VARCHAR(50)') AS RatedCreditTierExt,
	x.item.value('RatingStatusExt[1]', 'varchar(50)') AS RatingStatus,
	x.item.value('RelationshipToInsuredExt[1]', 'varchar(50)') AS Relationship,
	x.item.value('Sr22Ext[1]', 'bit') AS SR22,
	x.item.value('TrainingClassType[1]', 'varchar(50)') AS TrainingClassType,
	x.item.value('YearsLicensedExt[1]', 'varchar(50)') AS YearsLicensed,
	dateadd(mi, datediff(mi,getutcdate(),getdate()),x.item.value('UpdateTime[1]', 'datetime2(7)')) AS UpdateTime,
		x.item.value('(FirstName)[1]', 'VARCHAR(30)') AS FirstName,
	x.item.value('(Gender)[1]', 'VARCHAR(50)') AS Gender,
	x.item.value('(MiddleName)[1]', 'VARCHAR(30)') AS MiddleName,
	x.item.value('(LastName)[1]', 'VARCHAR(30)') AS LastName,
	x.item.value('(Suffix)[1]', 'VARCHAR(50)') AS Suffix,
	x.item.value('(MaritalStatus)[1]', 'VARCHAR(50)') AS MaritalStatus 
	FROM @XML.nodes('//PolicyPeriod/PersonalAutoLine/PolicyDrivers/Entry') AS x(item);

	WITH XMLNAMESPACES (DEFAULT 'eis/pc/policyperiod' ) --PolicyPeriod/PersonalAutoLine/Vehicles/Entry/AccountLocation
	INSERT INTO [Quotes].AccountLocation
	(TransID, PolicyPeriodID, AccountID, VehID, FixedVehID, AcctLocationID, AddressLine1, AddressLine2, 
	 AddressType, City, County, Country, CreateTime, PhoneNumber, PostalCode, AcctAddPublicID, State, UpdateTime
	)
	SELECT
	@TransID as TransID,
	@PolPdID AS PolicyPeriodID,
	@AcctID AS AccountID,
	x.item.value('(ID/Value)[1]', 'bigint') AS VehID,
	x.item.value('(FixedId/Value)[1]', 'bigint') AS FixedVehID,
	x.item.value('(AccountLocation/ID/Value)[1]', 'bigint') AS AcctLocationID,
	x.item.value('(AccountLocation/AddressLine1)[1]', 'VARCHAR(60)') AS AddressLine1,
	x.item.value('(AccountLocation/AddressLine2)[1]', 'VARCHAR(60)') AS AddressLine2,
	x.item.value('(AddressType)[1]', 'VARCHAR(50)') AS AddressType,
	x.item.value('(AccountLocation/City)[1]', 'VARCHAR(60)') AS City,
	x.item.value('(AccountLocation/County)[1]', 'VARCHAR(60)') AS County,
	x.item.value('(AccountLocation/Country)[1]', 'VARCHAR(60)') AS Country,
	dateadd(mi, datediff(mi,getutcdate(),getdate()),x.item.value('(AccountLocation/CreateTime)[1]', 'datetime2(7)')) AS CreateTime,
	x.item.value('(AccountLocation/Phone)[1]', 'VARCHAR(60)') AS PhoneNumber,
	x.item.value('(AccountLocation/PostalCode)[1]', 'VARCHAR(60)') AS PostalCode,
	x.item.value('(AccountLocation/PublicID)[1]', 'VARCHAR(64)') AS AcctAddPublicID,
	x.item.value('(AccountLocation/State)[1]', 'VARCHAR(4)') AS State,
	dateadd(mi, datediff(mi,getutcdate(),getdate()),x.item.value('(AccountLocation/UpdateTime)[1]', 'datetime2(7)')) AS UpdateTime
	FROM @XML.nodes('//PolicyPeriod/PersonalAutoLine/Vehicles/Entry') AS x(item);
 
  
 	WITH XMLNAMESPACES (DEFAULT 'eis/pc/policyperiod' ) --PolicyAddress
	insert iNTO [Quotes].PolicyAddress
	(TransID, PolicyPeriodID, AccountID, PolAddressID, CreateTime, PolAddPublicID, UpdateTime
	--, AddressID
	, AddressLine1, AddressLine2, AddressLine3, City, County, PostalCode, State)
	SELECT
	@TransID as TransID,
	@PolPdID AS PolicyPeriodID,
	@AcctID AS AccountID,
	x.item.value('(ID/Value)[1]', 'bigint') AS PolAddressID,
	dateadd(mi, datediff(mi,getutcdate(),getdate()),x.item.value('(CreateTime)[1]', 'datetime2(7)')) AS CreateTime,
	x.item.value('(PublicID)[1]', 'varchar(64)') AS PolAddPublicID,
	dateadd(mi, datediff(mi,getutcdate(),getdate()),x.item.value('(UpdateTime)[1]', 'datetime2(7)')) AS UpdateTime,
	x.item.value('(AddressLine1)[1]', 'VARCHAR(60)') AS AddressLine1,
	x.item.value('(AddressLine2)[1]', 'VARCHAR(60)') AS AddressLine2,
	x.item.value('(AddressLine3)[1]', 'VARCHAR(60)') AS AddressLine3,
	x.item.value('(City)[1]', 'VARCHAR(60)') AS City,
	x.item.value('(County)[1]', 'VARCHAR(60)') AS County,
	x.item.value('(PostalCode)[1]', 'VARCHAR(10)') AS PostalCode,
	x.item.value('(State)[1]', 'VARCHAR(4)') AS State
	FROM @XML.nodes('//PolicyPeriod/PolicyAddress') AS x(item);


	WITH XMLNAMESPACES (DEFAULT 'eis/pc/policyperiod' ) --PolicyAddress/PolicyContactRoles/Entry
	INSERT into [Quotes].PolicyContact
	(TransID, PolicyPeriodID, AccountID, PolicyContactRoleID, AcctContactRoleID, CreateTime, AcctContactRolePublicID, 
	 UpdateTime, AcctContactID, AddressLine1, AddressLine2, City, PostalCode, State, ContactID, ContactCreateTime, 
	 HomePhone, ContactPublicID, SSNOfficialID, EmailAddress1, LienholderName, WorkPhone, ContactUpdateTime, DateOfBirth, 
	 FirstName, Gender, MiddleName, LastName, Suffix, CellPhone, MaritalStatus
	)
	SELECT
	@TransID as TransID,
	@PolPdID AS PolicyPeriodID,
	@AcctID AS AccountID,
	x.item.value('(ID/Value)[1]', 'bigint') AS PolicyContactRoleID,
	x.item.value('(AccountContactRole/ID/Value)[1]', 'bigint') AS AcctContactRoleID,
	dateadd(mi, datediff(mi,getutcdate(),getdate()), x.item.value('(AccountContactRole/CreateTime)[1]', 'datetime2(7)')) AS CreateTime,
	x.item.value('(AccountContactRole/PublicID)[1]', 'VARCHAR(64)') AS AcctContactRolePublicID,
	dateadd(mi, datediff(mi,getutcdate(),getdate()),x.item.value('(AccountContactRole/UpdateTime)[1]', 'datetime2(7)')) AS UpdateTime,
	x.item.value('(AccountContactRole/AccountContact/ID/Value)[1]', 'bigint') AS AcctContactID,
	x.item.value('(AccountContactRole/AccountContact/Contact/PrimaryAddress/AddressLine1)[1]', 'VARCHAR(60)') AS AddressLine1,
	x.item.value('(AccountContactRole/AccountContact/Contact/PrimaryAddress/AddressLine2)[1]', 'VARCHAR(60)') AS AddressLine2,
	x.item.value('(AccountContactRole/AccountContact/Contact/PrimaryAddress/City)[1]', 'VARCHAR(60)') AS City,
	x.item.value('(AccountContactRole/AccountContact/Contact/PrimaryAddress/PostalCode)[1]', 'VARCHAR(10)') AS PostalCode,
	x.item.value('(AccountContactRole/AccountContact/Contact/PrimaryAddress/State)[1]', 'VARCHAR(4)') AS State,
	x.item.value('(AccountContactRole/AccountContact/Contact/ID/Value)[1]', 'bigint') AS ContactID,
	dateadd(mi, datediff(mi,getutcdate(),getdate()),x.item.value('(AccountContactRole/AccountContact/Contact/CreateTime)[1]', 'datetime2(7)')) AS ContactCreateTime,
	x.item.value('(AccountContactRole/AccountContact/Contact/HomePhone)[1]', 'VARCHAR(30)') AS HomePhone,
	x.item.value('(AccountContactRole/AccountContact/Contact/PublicID)[1]', 'VARCHAR(64)') AS ContactPublicID,
	x.item.value('(AccountContactRole/AccountContact/Contact/SSNOfficialID)[1]', 'VARCHAR(255)') AS SSNOfficialID,
	x.item.value('(AccountContactRole/AccountContact/Contact/EmailAddress1)[1]', 'VARCHAR(60)') AS EmailAddress1,
	--x.item.value('(AccountContactRole/AccountContact/Contact/EmailAddress2)[1]', 'VARCHAR(60)') AS EmailAddress2,
	x.item.value('(AccountContactRole/AccountContact/Contact/Name)[1]', 'VARCHAR(255)') AS LienholderName,
	x.item.value('(AccountContactRole/AccountContact/Contact/WorkPhone)[1]', 'VARCHAR(30)') AS WorkPhone,
	--x.item.value('(AccountContactRole/AccountContact/Contact/PrimaryPhone)[1]', 'VARCHAR(50)') AS PrimaryPhone,
	dateadd(mi, datediff(mi,getutcdate(),getdate()),x.item.value('(AccountContactRole/AccountContact/Contact/UpdateTime)[1]', 'datetime2(7)')) AS ContactUpdateTime,
	dateadd(mi, datediff(mi,getutcdate(),getdate()),x.item.value('(AccountContactRole/AccountContact/Contact/entity-Person/DateOfBirth)[1]', 'datetime2(7)')) AS DateOfBirth,
	x.item.value('(AccountContactRole/AccountContact/Contact/entity-Person/FirstName)[1]', 'VARCHAR(30)') AS FirstName,
	x.item.value('(AccountContactRole/AccountContact/Contact/entity-Person/Gender)[1]', 'VARCHAR(50)') AS Gender,
	x.item.value('(AccountContactRole/AccountContact/Contact/entity-Person/MiddleName)[1]', 'VARCHAR(30)') AS MiddleName,
	x.item.value('(AccountContactRole/AccountContact/Contact/entity-Person/LastName)[1]', 'VARCHAR(30)') AS LastName,
	x.item.value('(AccountContactRole/AccountContact/Contact/entity-Person/Suffix)[1]', 'VARCHAR(50)') AS Suffix,
	x.item.value('(AccountContactRole/AccountContact/Contact/entity-Person/CellPhone)[1]', 'VARCHAR(30)') AS CellPhone,
	x.item.value('(AccountContactRole/AccountContact/Contact/entity-Person/MaritalStatus)[1]', 'VARCHAR(50)') AS MaritalStatus
	FROM @XML.nodes('//PolicyPeriod/PolicyContactRoles/Entry') AS x(item);
COMMIT

	--EXEC dbo.usp_ScrubDateTimes
END TRY
begin CATCH

INSERT INTO [ErrorTracking].[dbo].[ErrorXMLTransactions]
        ( XMLPayload, ID, Type, DataLoadDate )
VALUES  ( @XML,
         @TransID,
          'Error Loading table',
          GETDATE()
          )
ROLLBACK
END CATCH
END
	


END
/*ChangeCMD*/
