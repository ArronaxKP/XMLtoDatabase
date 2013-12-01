import java.util.ArrayList;

public class Shredder {

	public static void main(String args[]) {
		long start = System.currentTimeMillis();
		int i = 0;
		ArrayList<XMLObject> xmls = new ArrayList<XMLObject>();
		do{
			Parser parser = new Parser("<PolicyPeriod xmlns=\"eis/pc/policyperiod\"><AllCosts><Entry><ActualAdjRate>469.6920</ActualAdjRate><ActualTermAmount>469.69</ActualTermAmount><ChargePattern><Code>Premium</Code></ChargePattern><CoverageIDExt>2303720</CoverageIDExt><CreateTime>2013-11-21T14:12:00.703-05:00</CreateTime><FixedId><Value>6609752</Value></FixedId><ID><Value>6609752</Value></ID><Prorated>false</Prorated><Proration>1.0</Proration><PublicID>prod:6609752</PublicID><UpdateTime>2013-11-21T14:12:00.703-05:00</UpdateTime></Entry><Entry><ActualAdjRate>24.7618</ActualAdjRate><ActualTermAmount>24.76</ActualTermAmount><ChargePattern><Code>Premium</Code></ChargePattern><CoverageIDExt>2303728</CoverageIDExt><CreateTime>2013-11-21T14:12:00.703-05:00</CreateTime><FixedId><Value>6609758</Value></FixedId><ID><Value>6609758</Value></ID><Prorated>false</Prorated><Proration>1.0</Proration><PublicID>prod:6609758</PublicID><UpdateTime>2013-11-21T14:12:00.703-05:00</UpdateTime></Entry><Entry><ActualAdjRate>376.9342</ActualAdjRate><ActualTermAmount>376.93</ActualTermAmount><ChargePattern><Code>Premium</Code></ChargePattern><CoverageIDExt>2303721</CoverageIDExt><CreateTime>2013-11-21T14:12:00.703-05:00</CreateTime><FixedId><Value>6609753</Value></FixedId><ID><Value>6609753</Value></ID><Prorated>false</Prorated><Proration>1.0</Proration><PublicID>prod:6609753</PublicID><UpdateTime>2013-11-21T14:12:00.703-05:00</UpdateTime></Entry><Entry><ActualAdjRate>44.7303</ActualAdjRate><ActualTermAmount>44.73</ActualTermAmount><ChargePattern><Code>Premium</Code></ChargePattern><CoverageIDExt>2303727</CoverageIDExt><CreateTime>2013-11-21T14:12:00.703-05:00</CreateTime><FixedId><Value>6609755</Value></FixedId><ID><Value>6609755</Value></ID><Prorated>false</Prorated><Proration>1.0</Proration><PublicID>prod:6609755</PublicID><UpdateTime>2013-11-21T14:12:00.703-05:00</UpdateTime></Entry><Entry><ActualAdjRate>34.3274</ActualAdjRate><ActualTermAmount>34.33</ActualTermAmount><ChargePattern><Code>Premium</Code></ChargePattern><CoverageIDExt>2303724</CoverageIDExt><CreateTime>2013-11-21T14:12:00.703-05:00</CreateTime><FixedId><Value>6609756</Value></FixedId><ID><Value>6609756</Value></ID><Prorated>false</Prorated><Proration>1.0</Proration><PublicID>prod:6609756</PublicID><UpdateTime>2013-11-21T14:12:00.703-05:00</UpdateTime></Entry><Entry><ActualAdjRate>206.3666</ActualAdjRate><ActualTermAmount>206.37</ActualTermAmount><ChargePattern><Code>Premium</Code></ChargePattern><CoverageIDExt>2303720</CoverageIDExt><CreateTime>2013-11-21T14:12:00.703-05:00</CreateTime><FixedId><Value>6609757</Value></FixedId><ID><Value>6609757</Value></ID><Prorated>false</Prorated><Proration>1.0</Proration><PublicID>prod:6609757</PublicID><UpdateTime>2013-11-21T14:12:00.703-05:00</UpdateTime></Entry><Entry><ActualAdjRate>698.3258</ActualAdjRate><ActualTermAmount>698.33</ActualTermAmount><ChargePattern><Code>Premium</Code></ChargePattern><CoverageIDExt>2772340</CoverageIDExt><CreateTime>2013-11-21T14:12:00.703-05:00</CreateTime><FixedId><Value>6609754</Value></FixedId><ID><Value>6609754</Value></ID><Prorated>false</Prorated><Proration>1.0</Proration><PublicID>prod:6609754</PublicID><UpdateTime>2013-11-21T14:12:00.703-05:00</UpdateTime></Entry><Entry><ActualAdjRate>182.7659</ActualAdjRate><ActualTermAmount>182.77</ActualTermAmount><ChargePattern><Code>Premium</Code></ChargePattern><CoverageIDExt>2772339</CoverageIDExt><CreateTime>2013-11-21T14:12:00.703-05:00</CreateTime><FixedId><Value>6609751</Value></FixedId><ID><Value>6609751</Value></ID><Prorated>false</Prorated><Proration>1.0</Proration><PublicID>prod:6609751</PublicID><UpdateTime>2013-11-21T14:12:00.703-05:00</UpdateTime></Entry></AllCosts><CreateTime>2013-10-15T20:25:43.617-04:00</CreateTime><CreateUser><Contact><CreateTime>2013-04-08T10:09:28.316-04:00</CreateTime><FirstName>Allison</FirstName><ID><Value>57382</Value></ID><LastName>Embrey</LastName><PublicID>prod:57382</PublicID><UpdateTime>2013-10-07T09:29:11.61-04:00</UpdateTime></Contact><CreateTime>2013-04-08T10:09:28.316-04:00</CreateTime><ID><Value>3403</Value></ID><PublicID>prod:3403</PublicID><Roles><Entry><ID><Value>3401</Value></ID><Role><CreateTime>2012-09-07T16:41:54.273-04:00</CreateTime><ID><Value>30</Value></ID><Name>Sales Rep</Name><PublicID>sales_rep</PublicID><UpdateTime>2013-11-05T14:05:22.921-05:00</UpdateTime></Role></Entry></Roles><UpdateTime>2013-04-08T10:09:28.316-04:00</UpdateTime></CreateUser><CreditScore>checkScore</CreditScore><EISEditEffectiveDateExt>2013-11-22T00:01:00-05:00</EISEditEffectiveDateExt><EISPolicyTermNumber>1</EISPolicyTermNumber><FcraNotice>true</FcraNotice><ID><Value>271095</Value></ID><Job><CreateTime>2013-10-15T20:21:03.002-04:00</CreateTime><ID><Value>199687</Value></ID><OOSJob>false</OOSJob><PublicID>prod:199687</PublicID><UpdateTime>2013-10-15T20:48:41.153-04:00</UpdateTime><UpdateUser><Contact><FirstName>Allison</FirstName><LastName>Embrey</LastName></Contact><CreateTime>2013-04-08T10:09:28.316-04:00</CreateTime><ID><Value>3403</Value></ID><PublicID>prod:3403</PublicID><UpdateTime>2013-04-08T10:09:28.316-04:00</UpdateTime></UpdateUser></Job><MostRecentModel>false</MostRecentModel><PATransactions><Entry><Amount>34.33</Amount><Charged>true</Charged><CreateTime>2013-11-21T14:12:00.703-05:00</CreateTime><EffDate>2013-11-22T00:00:00-05:00</EffDate><ExpDate>2014-11-22T00:00:00-05:00</ExpDate><FixedId><Value>5328928</Value></FixedId><ID><Value>5328928</Value></ID><PACost><ActualAdjRate>34.3274</ActualAdjRate><ActualTermAmount>34.33</ActualTermAmount><ChargePattern><Code>Premium</Code></ChargePattern><Coverage><CovTerms><Entry><DisplayValue>$2,500</DisplayValue><PatternCode>PAPIPMD_LIMIT</PatternCode><ValueTypeName>Option</ValueTypeName></Entry><Entry><DisplayValue>Yes</DisplayValue><PatternCode>PAPIPMD_GUEST</PatternCode><ValueTypeName>bit</ValueTypeName></Entry></CovTerms><CreateTime>2013-10-15T20:25:43.617-04:00</CreateTime><ID><Value>2303724</Value></ID><PatternCode>PAPIP_MD</PatternCode><PublicID>prod:2303724</PublicID><UpdateTime>2013-10-15T20:25:43.617-04:00</UpdateTime></Coverage><CreateTime>2013-11-21T14:12:00.703-05:00</CreateTime><FixedId><Value>6609756</Value></FixedId><ID><Value>6609756</Value></ID><Prorated>false</Prorated><Proration>1.0</Proration><PublicID>prod:6609756</PublicID><Subtype><Code>PersonalAutoCovCost</Code></Subtype><UpdateTime>2013-11-21T14:12:00.703-05:00</UpdateTime><Vehicle><CreateTime>2013-11-21T14:10:30.563-05:00</CreateTime><FixedId><Value>1062257</Value></FixedId><ID><Value>1062257</Value></ID><PublicID>prod:1062257</PublicID><UpdateTime>2013-11-21T14:12:00.703-05:00</UpdateTime></Vehicle></PACost><PublicID>prod:5328928</PublicID><ToBeAccrued>true</ToBeAccrued><UpdateTime>2013-11-21T14:12:00.703-05:00</UpdateTime><UpdateUser><Contact><CreateTime>2013-06-02T15:21:23.643-04:00</CreateTime><FirstName>Julianna</FirstName><ID><Value>112784</Value></ID><LastName>Cookus</LastName><PublicID>prod:112784</PublicID><UpdateTime>2013-06-02T15:21:23.643-04:00</UpdateTime></Contact><CreateTime>2013-06-02T15:21:23.643-04:00</CreateTime><ID><Value>3708</Value></ID><PublicID>prod:3708</PublicID><Roles><Entry><ID><Value>4106</Value></ID><PublicID>prod:4106</PublicID><Role><CreateTime>2012-09-07T16:41:54.273-04:00</CreateTime><ID><Value>30</Value></ID><Name>Sales Rep</Name><PublicID>sales_rep</PublicID><UpdateTime>2013-11-05T14:05:22.921-05:00</UpdateTime></Role></Entry></Roles><UpdateTime>2013-06-02T15:21:23.643-04:00</UpdateTime></UpdateUser></Entry><Entry><Amount>44.73</Amount><Charged>true</Charged><CreateTime>2013-11-21T14:12:00.703-05:00</CreateTime><EffDate>2013-11-22T00:00:00-05:00</EffDate><ExpDate>2014-11-22T00:00:00-05:00</ExpDate><FixedId><Value>5328929</Value></FixedId><ID><Value>5328929</Value></ID><PACost><ActualAdjRate>44.7303</ActualAdjRate><ActualTermAmount>44.73</ActualTermAmount><ChargePattern><Code>Premium</Code></ChargePattern><Coverage><CovTerms><Entry><DisplayValue>$30,000/$60,000</DisplayValue><PatternCode>PAUMBI</PatternCode><ValueTypeName>Package</ValueTypeName></Entry></CovTerms><CreateTime>2013-10-15T20:25:44.148-04:00</CreateTime><ID><Value>2303727</Value></ID><PatternCode>PAUMBICov</PatternCode><PublicID>prod:2303727</PublicID><UpdateTime>2013-10-15T20:25:44.148-04:00</UpdateTime></Coverage><CreateTime>2013-11-21T14:12:00.703-05:00</CreateTime><FixedId><Value>6609755</Value></FixedId><ID><Value>6609755</Value></ID><Prorated>false</Prorated><Proration>1.0</Proration><PublicID>prod:6609755</PublicID><Subtype><Code>PersonalAutoCovCost</Code></Subtype><UpdateTime>2013-11-21T14:12:00.703-05:00</UpdateTime><Vehicle><CreateTime>2013-11-21T14:10:30.563-05:00</CreateTime><FixedId><Value>1062257</Value></FixedId><ID><Value>1062257</Value></ID><PublicID>prod:1062257</PublicID><UpdateTime>2013-11-21T14:12:00.703-05:00</UpdateTime></Vehicle></PACost><PublicID>prod:5328929</PublicID><ToBeAccrued>true</ToBeAccrued><UpdateTime>2013-11-21T14:12:00.703-05:00</UpdateTime><UpdateUser><Contact><CreateTime>2013-06-02T15:21:23.643-04:00</CreateTime><FirstName>Julianna</FirstName><ID><Value>112784</Value></ID><LastName>Cookus</LastName><PublicID>prod:112784</PublicID><UpdateTime>2013-06-02T15:21:23.643-04:00</UpdateTime></Contact><CreateTime>2013-06-02T15:21:23.643-04:00</CreateTime><ID><Value>3708</Value></ID><PublicID>prod:3708</PublicID><Roles><Entry><ID><Value>4106</Value></ID><PublicID>prod:4106</PublicID><Role><CreateTime>2012-09-07T16:41:54.273-04:00</CreateTime><ID><Value>30</Value></ID><Name>Sales Rep</Name><PublicID>sales_rep</PublicID><UpdateTime>2013-11-05T14:05:22.921-05:00</UpdateTime></Role></Entry></Roles><UpdateTime>2013-06-02T15:21:23.643-04:00</UpdateTime></UpdateUser></Entry><Entry><Amount>206.37</Amount><Charged>true</Charged><CreateTime>2013-11-21T14:12:00.703-05:00</CreateTime><EffDate>2013-11-22T00:00:00-05:00</EffDate><ExpDate>2014-11-22T00:00:00-05:00</ExpDate><FixedId><Value>5328930</Value></FixedId><ID><Value>5328930</Value></ID><PACost><ActualAdjRate>206.3666</ActualAdjRate><ActualTermAmount>206.37</ActualTermAmount><ChargePattern><Code>Premium</Code></ChargePattern><Coverage><CovTerms><Entry><DisplayValue>$30,000/$60,000</DisplayValue><PatternCode>PABodilyInjury</PatternCode><ValueTypeName>Package</ValueTypeName></Entry></CovTerms><CreateTime>2013-10-15T20:25:43.617-04:00</CreateTime><ID><Value>2303720</Value></ID><PatternCode>EISPABodilyInjuryCov</PatternCode><PublicID>prod:2303720</PublicID><UpdateTime>2013-10-15T20:25:43.617-04:00</UpdateTime></Coverage><CreateTime>2013-11-21T14:12:00.703-05:00</CreateTime><FixedId><Value>6609757</Value></FixedId><ID><Value>6609757</Value></ID><Prorated>false</Prorated><Proration>1.0</Proration><PublicID>prod:6609757</PublicID><Subtype><Code>PersonalAutoExpenseFeeCostExt</Code></Subtype><UpdateTime>2013-11-21T14:12:00.703-05:00</UpdateTime><Vehicle><CreateTime>2013-11-21T14:10:30.563-05:00</CreateTime><FixedId><Value>1062257</Value></FixedId><ID><Value>1062257</Value></ID><PublicID>prod:1062257</PublicID><UpdateTime>2013-11-21T14:12:00.703-05:00</UpdateTime></Vehicle></PACost><PublicID>prod:5328930</PublicID><ToBeAccrued>true</ToBeAccrued><UpdateTime>2013-11-21T14:12:00.703-05:00</UpdateTime><UpdateUser><Contact><CreateTime>2013-06-02T15:21:23.643-04:00</CreateTime><FirstName>Julianna</FirstName><ID><Value>112784</Value></ID><LastName>Cookus</LastName><PublicID>prod:112784</PublicID><UpdateTime>2013-06-02T15:21:23.643-04:00</UpdateTime></Contact><CreateTime>2013-06-02T15:21:23.643-04:00</CreateTime><ID><Value>3708</Value></ID><PublicID>prod:3708</PublicID><Roles><Entry><ID><Value>4106</Value></ID><PublicID>prod:4106</PublicID><Role><CreateTime>2012-09-07T16:41:54.273-04:00</CreateTime><ID><Value>30</Value></ID><Name>Sales Rep</Name><PublicID>sales_rep</PublicID><UpdateTime>2013-11-05T14:05:22.921-05:00</UpdateTime></Role></Entry></Roles><UpdateTime>2013-06-02T15:21:23.643-04:00</UpdateTime></UpdateUser></Entry><Entry><Amount>376.93</Amount><Charged>true</Charged><CreateTime>2013-11-21T14:12:00.703-05:00</CreateTime><EffDate>2013-11-22T00:00:00-05:00</EffDate><ExpDate>2014-11-22T00:00:00-05:00</ExpDate><FixedId><Value>5328931</Value></FixedId><ID><Value>5328931</Value></ID><PACost><ActualAdjRate>376.9342</ActualAdjRate><ActualTermAmount>376.93</ActualTermAmount><ChargePattern><Code>Premium</Code></ChargePattern><Coverage><CovTerms><Entry><DisplayValue>$15,000</DisplayValue><PatternCode>PAPropertyDamage</PatternCode><ValueTypeName>Option</ValueTypeName></Entry></CovTerms><CreateTime>2013-10-15T20:25:43.617-04:00</CreateTime><ID><Value>2303721</Value></ID><PatternCode>EISPAPropertyDamageCov</PatternCode><PublicID>prod:2303721</PublicID><UpdateTime>2013-10-15T20:25:43.617-04:00</UpdateTime></Coverage><CreateTime>2013-11-21T14:12:00.703-05:00</CreateTime><FixedId><Value>6609753</Value></FixedId><ID><Value>6609753</Value></ID><Prorated>false</Prorated><Proration>1.0</Proration><PublicID>prod:6609753</PublicID><Subtype><Code>PersonalAutoCovCost</Code></Subtype><UpdateTime>2013-11-21T14:12:00.703-05:00</UpdateTime><Vehicle><CreateTime>2013-11-21T14:10:30.563-05:00</CreateTime><FixedId><Value>1062257</Value></FixedId><ID><Value>1062257</Value></ID><PublicID>prod:1062257</PublicID><UpdateTime>2013-11-21T14:12:00.703-05:00</UpdateTime></Vehicle></PACost><PublicID>prod:5328931</PublicID><ToBeAccrued>true</ToBeAccrued><UpdateTime>2013-11-21T14:12:00.703-05:00</UpdateTime><UpdateUser><Contact><CreateTime>2013-06-02T15:21:23.643-04:00</CreateTime><FirstName>Julianna</FirstName><ID><Value>112784</Value></ID><LastName>Cookus</LastName><PublicID>prod:112784</PublicID><UpdateTime>2013-06-02T15:21:23.643-04:00</UpdateTime></Contact><CreateTime>2013-06-02T15:21:23.643-04:00</CreateTime><ID><Value>3708</Value></ID><PublicID>prod:3708</PublicID><Roles><Entry><ID><Value>4106</Value></ID><PublicID>prod:4106</PublicID><Role><CreateTime>2012-09-07T16:41:54.273-04:00</CreateTime><ID><Value>30</Value></ID><Name>Sales Rep</Name><PublicID>sales_rep</PublicID><UpdateTime>2013-11-05T14:05:22.921-05:00</UpdateTime></Role></Entry></Roles><UpdateTime>2013-06-02T15:21:23.643-04:00</UpdateTime></UpdateUser></Entry><Entry><Amount>469.69</Amount><Charged>true</Charged><CreateTime>2013-11-21T14:12:00.703-05:00</CreateTime><EffDate>2013-11-22T00:00:00-05:00</EffDate><ExpDate>2014-11-22T00:00:00-05:00</ExpDate><FixedId><Value>5328932</Value></FixedId><ID><Value>5328932</Value></ID><PACost><ActualAdjRate>469.6920</ActualAdjRate><ActualTermAmount>469.69</ActualTermAmount><ChargePattern><Code>Premium</Code></ChargePattern><Coverage><CovTerms><Entry><DisplayValue>$30,000/$60,000</DisplayValue><PatternCode>PABodilyInjury</PatternCode><ValueTypeName>Package</ValueTypeName></Entry></CovTerms><CreateTime>2013-10-15T20:25:43.617-04:00</CreateTime><ID><Value>2303720</Value></ID><PatternCode>EISPABodilyInjuryCov</PatternCode><PublicID>prod:2303720</PublicID><UpdateTime>2013-10-15T20:25:43.617-04:00</UpdateTime></Coverage><CreateTime>2013-11-21T14:12:00.703-05:00</CreateTime><FixedId><Value>6609752</Value></FixedId><ID><Value>6609752</Value></ID><Prorated>false</Prorated><Proration>1.0</Proration><PublicID>prod:6609752</PublicID><Subtype><Code>PersonalAutoCovCost</Code></Subtype><UpdateTime>2013-11-21T14:12:00.703-05:00</UpdateTime><Vehicle><CreateTime>2013-11-21T14:10:30.563-05:00</CreateTime><FixedId><Value>1062257</Value></FixedId><ID><Value>1062257</Value></ID><PublicID>prod:1062257</PublicID><UpdateTime>2013-11-21T14:12:00.703-05:00</UpdateTime></Vehicle></PACost><PublicID>prod:5328932</PublicID><ToBeAccrued>true</ToBeAccrued><UpdateTime>2013-11-21T14:12:00.703-05:00</UpdateTime><UpdateUser><Contact><CreateTime>2013-06-02T15:21:23.643-04:00</CreateTime><FirstName>Julianna</FirstName><ID><Value>112784</Value></ID><LastName>Cookus</LastName><PublicID>prod:112784</PublicID><UpdateTime>2013-06-02T15:21:23.643-04:00</UpdateTime></Contact><CreateTime>2013-06-02T15:21:23.643-04:00</CreateTime><ID><Value>3708</Value></ID><PublicID>prod:3708</PublicID><Roles><Entry><ID><Value>4106</Value></ID><PublicID>prod:4106</PublicID><Role><CreateTime>2012-09-07T16:41:54.273-04:00</CreateTime><ID><Value>30</Value></ID><Name>Sales Rep</Name><PublicID>sales_rep</PublicID><UpdateTime>2013-11-05T14:05:22.921-05:00</UpdateTime></Role></Entry></Roles><UpdateTime>2013-06-02T15:21:23.643-04:00</UpdateTime></UpdateUser></Entry><Entry><Amount>698.33</Amount><Charged>true</Charged><CreateTime>2013-11-21T14:12:00.703-05:00</CreateTime><EffDate>2013-11-22T00:00:00-05:00</EffDate><ExpDate>2014-11-22T00:00:00-05:00</ExpDate><FixedId><Value>5328933</Value></FixedId><ID><Value>5328933</Value></ID><PACost><ActualAdjRate>698.3258</ActualAdjRate><ActualTermAmount>698.33</ActualTermAmount><ChargePattern><Code>Premium</Code></ChargePattern><Coverage><CovTerms><Entry><DisplayValue>$500</DisplayValue><PatternCode>PACollDeductible</PatternCode><ValueTypeName>Option</ValueTypeName></Entry></CovTerms><CreateTime>2013-11-21T14:11:58-05:00</CreateTime><ID><Value>2772340</Value></ID><PatternCode>PACollisionCov</PatternCode><PublicID>prod:2772340</PublicID><UpdateTime>2013-11-21T14:11:58-05:00</UpdateTime></Coverage><CreateTime>2013-11-21T14:12:00.703-05:00</CreateTime><FixedId><Value>6609754</Value></FixedId><ID><Value>6609754</Value></ID><Prorated>false</Prorated><Proration>1.0</Proration><PublicID>prod:6609754</PublicID><Subtype><Code>PersonalVehicleCovCost</Code></Subtype><UpdateTime>2013-11-21T14:12:00.703-05:00</UpdateTime><Vehicle><CreateTime>2013-11-21T14:10:30.563-05:00</CreateTime><FixedId><Value>1062257</Value></FixedId><ID><Value>1062257</Value></ID><PublicID>prod:1062257</PublicID><UpdateTime>2013-11-21T14:12:00.703-05:00</UpdateTime></Vehicle></PACost><PublicID>prod:5328933</PublicID><ToBeAccrued>true</ToBeAccrued><UpdateTime>2013-11-21T14:12:00.703-05:00</UpdateTime><UpdateUser><Contact><CreateTime>2013-06-02T15:21:23.643-04:00</CreateTime><FirstName>Julianna</FirstName><ID><Value>112784</Value></ID><LastName>Cookus</LastName><PublicID>prod:112784</PublicID><UpdateTime>2013-06-02T15:21:23.643-04:00</UpdateTime></Contact><CreateTime>2013-06-02T15:21:23.643-04:00</CreateTime><ID><Value>3708</Value></ID><PublicID>prod:3708</PublicID><Roles><Entry><ID><Value>4106</Value></ID><PublicID>prod:4106</PublicID><Role><CreateTime>2012-09-07T16:41:54.273-04:00</CreateTime><ID><Value>30</Value></ID><Name>Sales Rep</Name><PublicID>sales_rep</PublicID><UpdateTime>2013-11-05T14:05:22.921-05:00</UpdateTime></Role></Entry></Roles><UpdateTime>2013-06-02T15:21:23.643-04:00</UpdateTime></UpdateUser></Entry><Entry><Amount>24.76</Amount><Charged>true</Charged><CreateTime>2013-11-21T14:12:00.703-05:00</CreateTime><EffDate>2013-11-22T00:00:00-05:00</EffDate><ExpDate>2014-11-22T00:00:00-05:00</ExpDate><FixedId><Value>5328934</Value></FixedId><ID><Value>5328934</Value></ID><PACost><ActualAdjRate>24.7618</ActualAdjRate><ActualTermAmount>24.76</ActualTermAmount><ChargePattern><Code>Premium</Code></ChargePattern><Coverage><CovTerms><Entry><DisplayValue>15,000</DisplayValue><PatternCode>PAUMPDLimit</PatternCode><ValueTypeName>Option</ValueTypeName></Entry></CovTerms><CreateTime>2013-10-15T20:25:44.148-04:00</CreateTime><ID><Value>2303728</Value></ID><PatternCode>PAUMPDCov</PatternCode><PublicID>prod:2303728</PublicID><UpdateTime>2013-10-15T20:25:44.148-04:00</UpdateTime></Coverage><CreateTime>2013-11-21T14:12:00.703-05:00</CreateTime><FixedId><Value>6609758</Value></FixedId><ID><Value>6609758</Value></ID><Prorated>false</Prorated><Proration>1.0</Proration><PublicID>prod:6609758</PublicID><Subtype><Code>PersonalAutoCovCost</Code></Subtype><UpdateTime>2013-11-21T14:12:00.703-05:00</UpdateTime><Vehicle><CreateTime>2013-11-21T14:10:30.563-05:00</CreateTime><FixedId><Value>1062257</Value></FixedId><ID><Value>1062257</Value></ID><PublicID>prod:1062257</PublicID><UpdateTime>2013-11-21T14:12:00.703-05:00</UpdateTime></Vehicle></PACost><PublicID>prod:5328934</PublicID><ToBeAccrued>true</ToBeAccrued><UpdateTime>2013-11-21T14:12:00.703-05:00</UpdateTime><UpdateUser><Contact><CreateTime>2013-06-02T15:21:23.643-04:00</CreateTime><FirstName>Julianna</FirstName><ID><Value>112784</Value></ID><LastName>Cookus</LastName><PublicID>prod:112784</PublicID><UpdateTime>2013-06-02T15:21:23.643-04:00</UpdateTime></Contact><CreateTime>2013-06-02T15:21:23.643-04:00</CreateTime><ID><Value>3708</Value></ID><PublicID>prod:3708</PublicID><Roles><Entry><ID><Value>4106</Value></ID><PublicID>prod:4106</PublicID><Role><CreateTime>2012-09-07T16:41:54.273-04:00</CreateTime><ID><Value>30</Value></ID><Name>Sales Rep</Name><PublicID>sales_rep</PublicID><UpdateTime>2013-11-05T14:05:22.921-05:00</UpdateTime></Role></Entry></Roles><UpdateTime>2013-06-02T15:21:23.643-04:00</UpdateTime></UpdateUser></Entry><Entry><Amount>182.77</Amount><Charged>true</Charged><CreateTime>2013-11-21T14:12:00.703-05:00</CreateTime><EffDate>2013-11-22T00:00:00-05:00</EffDate><ExpDate>2014-11-22T00:00:00-05:00</ExpDate><FixedId><Value>5328935</Value></FixedId><ID><Value>5328935</Value></ID><PACost><ActualAdjRate>182.7659</ActualAdjRate><ActualTermAmount>182.77</ActualTermAmount><ChargePattern><Code>Premium</Code></ChargePattern><Coverage><CovTerms><Entry><DisplayValue>$500</DisplayValue><PatternCode>PACompDeductible</PatternCode><ValueTypeName>Option</ValueTypeName></Entry></CovTerms><CreateTime>2013-11-21T14:11:58-05:00</CreateTime><ID><Value>2772339</Value></ID><PatternCode>PAComprehensiveCov</PatternCode><PublicID>prod:2772339</PublicID><UpdateTime>2013-11-21T14:11:58-05:00</UpdateTime></Coverage><CreateTime>2013-11-21T14:12:00.703-05:00</CreateTime><FixedId><Value>6609751</Value></FixedId><ID><Value>6609751</Value></ID><Prorated>false</Prorated><Proration>1.0</Proration><PublicID>prod:6609751</PublicID><Subtype><Code>PersonalVehicleCovCost</Code></Subtype><UpdateTime>2013-11-21T14:12:00.703-05:00</UpdateTime><Vehicle><CreateTime>2013-11-21T14:10:30.563-05:00</CreateTime><FixedId><Value>1062257</Value></FixedId><ID><Value>1062257</Value></ID><PublicID>prod:1062257</PublicID><UpdateTime>2013-11-21T14:12:00.703-05:00</UpdateTime></Vehicle></PACost><PublicID>prod:5328935</PublicID><ToBeAccrued>true</ToBeAccrued><UpdateTime>2013-11-21T14:12:00.703-05:00</UpdateTime><UpdateUser><Contact><CreateTime>2013-06-02T15:21:23.643-04:00</CreateTime><FirstName>Julianna</FirstName><ID><Value>112784</Value></ID><LastName>Cookus</LastName><PublicID>prod:112784</PublicID><UpdateTime>2013-06-02T15:21:23.643-04:00</UpdateTime></Contact><CreateTime>2013-06-02T15:21:23.643-04:00</CreateTime><ID><Value>3708</Value></ID><PublicID>prod:3708</PublicID><Roles><Entry><ID><Value>4106</Value></ID><PublicID>prod:4106</PublicID><Role><CreateTime>2012-09-07T16:41:54.273-04:00</CreateTime><ID><Value>30</Value></ID><Name>Sales Rep</Name><PublicID>sales_rep</PublicID><UpdateTime>2013-11-05T14:05:22.921-05:00</UpdateTime></Role></Entry></Roles><UpdateTime>2013-06-02T15:21:23.643-04:00</UpdateTime></UpdateUser></Entry></PATransactions><PeriodDisplayStatus>Quoted</PeriodDisplayStatus><PeriodEnd>2014-11-22T00:01:00-05:00</PeriodEnd><PeriodStart>2013-11-22T00:01:00-05:00</PeriodStart><PersonalAutoLine><AffinityGroupExt /><AllCoverages><Entry><CovTerms><Entry><DisplayValue>$30,000/$60,000</DisplayValue><PatternCode>PABodilyInjury</PatternCode><ValueTypeName>Package</ValueTypeName></Entry></CovTerms><CreateTime>2013-10-15T20:25:43.617-04:00</CreateTime><ID><Value>2303720</Value></ID><PatternCode>EISPABodilyInjuryCov</PatternCode><PublicID>prod:2303720</PublicID><UpdateTime>2013-10-15T20:25:43.617-04:00</UpdateTime></Entry><Entry><CovTerms><Entry><DisplayValue>$15,000</DisplayValue><PatternCode>PAPropertyDamage</PatternCode><ValueTypeName>Option</ValueTypeName></Entry></CovTerms><CreateTime>2013-10-15T20:25:43.617-04:00</CreateTime><ID><Value>2303721</Value></ID><PatternCode>EISPAPropertyDamageCov</PatternCode><PublicID>prod:2303721</PublicID><UpdateTime>2013-10-15T20:25:43.617-04:00</UpdateTime></Entry><Entry><CovTerms><Entry><DisplayValue>$30,000/$60,000</DisplayValue><PatternCode>PAUMBI</PatternCode><ValueTypeName>Package</ValueTypeName></Entry></CovTerms><CreateTime>2013-10-15T20:25:44.148-04:00</CreateTime><ID><Value>2303727</Value></ID><PatternCode>PAUMBICov</PatternCode><PublicID>prod:2303727</PublicID><UpdateTime>2013-10-15T20:25:44.148-04:00</UpdateTime></Entry><Entry><CovTerms><Entry><DisplayValue>$2,500</DisplayValue><PatternCode>PAPIPMD_LIMIT</PatternCode><ValueTypeName>Option</ValueTypeName></Entry><Entry><DisplayValue>Yes</DisplayValue><PatternCode>PAPIPMD_GUEST</PatternCode><ValueTypeName>bit</ValueTypeName></Entry></CovTerms><CreateTime>2013-10-15T20:25:43.617-04:00</CreateTime><ID><Value>2303724</Value></ID><PatternCode>PAPIP_MD</PatternCode><PublicID>prod:2303724</PublicID><UpdateTime>2013-10-15T20:25:43.617-04:00</UpdateTime></Entry><Entry><CovTerms><Entry><DisplayValue>15,000</DisplayValue><PatternCode>PAUMPDLimit</PatternCode><ValueTypeName>Option</ValueTypeName></Entry></CovTerms><CreateTime>2013-10-15T20:25:44.148-04:00</CreateTime><ID><Value>2303728</Value></ID><PatternCode>PAUMPDCov</PatternCode><PublicID>prod:2303728</PublicID><UpdateTime>2013-10-15T20:25:44.148-04:00</UpdateTime></Entry><Entry><CovTerms><Entry><DisplayValue>$500</DisplayValue><PatternCode>PACollDeductible</PatternCode><ValueTypeName>Option</ValueTypeName></Entry></CovTerms><CreateTime>2013-11-21T14:11:58-05:00</CreateTime><ID><Value>2772340</Value></ID><PatternCode>PACollisionCov</PatternCode><PublicID>prod:2772340</PublicID><UpdateTime>2013-11-21T14:11:58-05:00</UpdateTime></Entry><Entry><CovTerms><Entry><DisplayValue>$500</DisplayValue><PatternCode>PACompDeductible</PatternCode><ValueTypeName>Option</ValueTypeName></Entry></CovTerms><CreateTime>2013-11-21T14:11:58-05:00</CreateTime><ID><Value>2772339</Value></ID><PatternCode>PAComprehensiveCov</PatternCode><PublicID>prod:2772339</PublicID><UpdateTime>2013-11-21T14:11:58-05:00</UpdateTime></Entry></AllCoverages><AllowTextExt>false</AllowTextExt><ApplicantExt><CreateTime>2013-10-15T20:25:43.617-04:00</CreateTime><CreditScoreExt>505</CreditScoreExt><FixedId><Value>1681251</Value></FixedId><ID><Value>1681292</Value></ID><PublicID>prod:1681292</PublicID><UpdateTime>2013-11-21T14:12:00.703-05:00</UpdateTime></ApplicantExt><AtFaultExt>0</AtFaultExt><BaseState>MD</BaseState><ChannelEXT>webToPhone</ChannelEXT><ChildOnPolicyExt>false</ChildOnPolicyExt><CreateTime>2013-10-15T20:25:43.617-04:00</CreateTime><CurrentCarrier_Ext>Geico</CurrentCarrier_Ext><CurrentCoverageFlagExt>false</CurrentCoverageFlagExt><EmailAddress1>joemoma365@gmail.com</EmailAddress1><ESignatureExt>true</ESignatureExt><FixedId><Value>569166</Value></FixedId><GarageLocations><Entry><PostalCode>21703</PostalCode></Entry></GarageLocations><HasAutoInsuranceExt>policy_expired_over_30days</HasAutoInsuranceExt><hasThreeYearCleanRenewalExt>false</hasThreeYearCleanRenewalExt><homeownerExt>false</homeownerExt><ID><Value>569177</Value></ID><LapseInCoverageExt>lapse_more_than_30days</LapseInCoverageExt><MinorChildExt>false</MinorChildExt><MultiCarExt>false</MultiCarExt><nCIncidents>0</nCIncidents><NumDriversExt>1</NumDriversExt><OnlineDiscountExt>true</OnlineDiscountExt><PaidInFullExt>false</PaidInFullExt><PaperlessDiscountExt>true</PaperlessDiscountExt><PAPIP_MD><CovTerms><Entry><DisplayValue>$2,500</DisplayValue></Entry><Entry><DisplayValue>Yes</DisplayValue></Entry></CovTerms><CreateTime>2013-10-15T20:25:43.617-04:00</CreateTime><FixedId><Value>2303681</Value></FixedId><ID><Value>2303724</Value></ID><PublicID>prod:2303724</PublicID><UpdateTime>2013-10-15T20:25:43.617-04:00</UpdateTime></PAPIP_MD><PolicyDrivers><Entry><AccountContactRole><CreateTime>2013-10-15T20:21:03.002-04:00</CreateTime><ID><Value>469167</Value></ID><PublicID>prod:469167</PublicID><UpdateTime>2013-10-15T20:21:03.002-04:00</UpdateTime></AccountContactRole><Age>24</Age><AgeFirstLicensedExt>18</AgeFirstLicensedExt><AssociatedPrimaryVehicleExt><CreateTime>2013-11-21T14:10:30.563-05:00</CreateTime><ID><Value>1062257</Value></ID><PublicID>prod:1062257</PublicID><UpdateTime>2013-11-21T14:12:00.703-05:00</UpdateTime></AssociatedPrimaryVehicleExt><BIPointsExt>0</BIPointsExt><CMPPointsExt>0</CMPPointsExt><ColPointsExt>0</ColPointsExt><CreateTime>2013-10-15T20:25:43.617-04:00</CreateTime><CreditTierExt>P1</CreditTierExt><CustPointsExt /><DateOfBirth>1989-01-12T00:00:00-05:00</DateOfBirth><DriverClassFactorExt>1.2232000</DriverClassFactorExt><Excluded>false</Excluded><FirstName>Joseph</FirstName><FixedId><Value>1681251</Value></FixedId><Fr19Ext>false</Fr19Ext><Fr44Ext>false</Fr44Ext><Gender>M</Gender><GoodStudentDiscountExt>true</GoodStudentDiscountExt><HighestLevelOfEducationExt>associatevocational</HighestLevelOfEducationExt><ID><Value>1681292</Value></ID><LastName>Carrafa</LastName><LicenseState>MD</LicenseState><LicenseStatusExt>valid</LicenseStatusExt><LoanPointsExt /><MaritalExt>single</MaritalExt><MaritalStatus>S</MaritalStatus><MedPointsExt /><MVROrderStatus>Not Ordered</MVROrderStatus><occupationGroupExt>O06</occupationGroupExt><OccupationStatusListExt>EmployedPrivately</OccupationStatusListExt><OccupationTitleExt>Nurse - CNA (Certified Nursing Assistant)</OccupationTitleExt><Operator>Occasional</Operator><PDPointsExt>0</PDPointsExt><PIPMDPointsExt>0</PIPMDPointsExt><PIPTXPointsExt /><PublicID>prod:1681292</PublicID><RatedCreditTierExt>P1</RatedCreditTierExt><RatingStatusExt>rated</RatingStatusExt><RelationshipToInsuredExt>applicant</RelationshipToInsuredExt><RentPointsExt /><Sr22Ext>false</Sr22Ext><UMBIPointsExt>0</UMBIPointsExt><UMPDPointsExt>0</UMPDPointsExt><UpdateTime>2013-11-21T14:12:00.703-05:00</UpdateTime><YearsLicensedExt>6</YearsLicensedExt></Entry></PolicyDrivers><PublicID>prod:569177</PublicID><ReapplicationDiscountExt>false</ReapplicationDiscountExt><ResidencyExt>rent</ResidencyExt><responsibleDriverExt>true</responsibleDriverExt><SourceOfBusinessExt>tv</SourceOfBusinessExt><TierExt /><TierScoreExt /><UpdateTime>2013-11-13T21:17:22.304-05:00</UpdateTime><Vehicles><Entry><AccountLocation><AddressLine1>621 himes ave</AddressLine1><AddressLine2>107</AddressLine2><City>Frederick</City><Country>US</Country><County>Frederick</County><CreateTime>2013-10-15T20:21:02.377-04:00</CreateTime><ID><Value>501955</Value></ID><Phone>609-402-9712</Phone><PostalCode>21703</PostalCode><PublicID>prod:501955</PublicID><State>MD</State><UpdateTime>2013-10-15T20:21:02.377-04:00</UpdateTime></AccountLocation><Age>7</Age><AssociatedPrimaryDriverExt><AccountContactRole><CreateTime>2013-10-15T20:21:03.002-04:00</CreateTime><ID><Value>469167</Value></ID><PublicID>prod:469167</PublicID><UpdateTime>2013-10-15T20:21:03.002-04:00</UpdateTime></AccountContactRole><CreateTime>2013-10-15T20:25:43.617-04:00</CreateTime><ID><Value>1681292</Value></ID><PublicID>prod:1681292</PublicID><UpdateTime>2013-11-21T14:12:00.703-05:00</UpdateTime></AssociatedPrimaryDriverExt><CoverableReferenceDate>2013-10-15T00:00:00-04:00</CoverableReferenceDate><CreateTime>2013-11-21T14:10:30.563-05:00</CreateTime><DMAExt>410</DMAExt><ExistingDamageExt>false</ExistingDamageExt><ExoticVehicleExt>false</ExoticVehicleExt><ExpirationDate>2014-11-22T00:01:00-05:00</ExpirationDate><FixedId><Value>1062257</Value></FixedId><GarageLocation><County>Frederick</County><PostalCode>21703</PostalCode></GarageLocation><ID><Value>1062257</Value></ID><IgnoreVINExt>false</IgnoreVINExt><LeaseOrRent>true</LeaseOrRent><LicenseState>MD</LicenseState><Make>FORD</Make><Model>FREESTYLE</Model><PrimaryUse>commuting</PrimaryUse><PublicID>prod:1062257</PublicID><TerritoryExt>904</TerritoryExt><UpdateTime>2013-11-21T14:12:00.703-05:00</UpdateTime><VehicleNumber>1</VehicleNumber><VehicleType>auto</VehicleType><Year>2006</Year></Entry></Vehicles><WebCustomerNubmerExt>7I4CH7</WebCustomerNubmerExt><WebToPhoneDiscountExt>true</WebToPhoneDiscountExt><YearsWithProviderExt>less_1year</YearsWithProviderExt><YouthfulEXT>false</YouthfulEXT><YouthfulPNIExt>false</YouthfulPNIExt></PersonalAutoLine><Policy><Account><AccountNumber>10114611</AccountNumber><CreateTime>2013-10-15T20:21:02.377-04:00</CreateTime><CreateUser><Contact><CreateTime>2012-12-28T16:49:04.702-05:00</CreateTime><FirstName>ConsumerQuote</FirstName><ID><Value>14795</Value></ID><LastName>Integration</LastName><PublicID>prod:14795</PublicID><UpdateTime>2012-12-28T16:49:04.702-05:00</UpdateTime></Contact><CreateTime>2012-12-28T16:49:04.702-05:00</CreateTime><ID><Value>1403</Value></ID><PublicID>prod:1403</PublicID><Roles><Entry><ID><Value>1401</Value></ID><PublicID>prod:1401</PublicID><Role><CreateTime>2012-09-07T11:32:11.907-04:00</CreateTime><ID><Value>1</Value></ID><Name>Superuser</Name><PublicID>superuser</PublicID><UpdateTime>2013-10-02T12:08:23.384-04:00</UpdateTime></Role></Entry></Roles><UpdateTime>2012-12-28T16:49:04.702-05:00</UpdateTime></CreateUser><ID><Value>119485</Value></ID><PublicID>prod:119485</PublicID><UpdateTime>2013-10-15T20:21:03.002-04:00</UpdateTime></Account><CreateTime>2013-10-15T20:21:03.002-04:00</CreateTime><ID><Value>122492</Value></ID><Periods><Entry><CreateTime>2013-10-15T20:25:43.617-04:00</CreateTime><ID><Value>271095</Value></ID><Preempted>false</Preempted><PublicID>prod:271095</PublicID><UpdateTime>2013-11-21T14:12:00.703-05:00</UpdateTime></Entry><Entry><CreateTime>2013-10-15T20:21:03.002-04:00</CreateTime><ID><Value>271092</Value></ID><Preempted>false</Preempted><PublicID>prod:271092</PublicID><UpdateTime>2013-11-21T14:11:59.906-05:00</UpdateTime></Entry></Periods><PublicID>prod:122492</PublicID><UpdateTime>2013-10-15T20:48:41.153-04:00</UpdateTime></Policy><PolicyAddress><Address><AddressLine1>621 Himes Ave Apt 107</AddressLine1><City>Frederick</City><County>Frederick</County><CreateTime>2013-10-15T20:21:02.377-04:00</CreateTime><ID><Value>501954</Value></ID><PostalCode>21703</PostalCode><PublicID>prod:501954</PublicID><State>MD</State><UpdateTime>2013-10-15T20:23:45.302-04:00</UpdateTime></Address><AddressLine1>621 Himes Ave Apt 107</AddressLine1><City>Frederick</City><County>Frederick</County><CreateTime>2013-10-15T20:25:43.617-04:00</CreateTime><ID><Value>565193</Value></ID><PostalCode>21703</PostalCode><PublicID>prod:565193</PublicID><State>MD</State><UpdateTime>2013-10-15T20:25:43.617-04:00</UpdateTime></PolicyAddress><PolicyContactRoles><Entry><AccountContactRole><AccountContact><Contact><entity-Person><CellPhone>609-402-9126</CellPhone><DateOfBirth>1989-01-12T00:00:00-05:00</DateOfBirth><FirstName>Joseph</FirstName><Gender>M</Gender><LastName>Carrafa</LastName><MiddleName>J</MiddleName><Suffix>jr</Suffix></entity-Person><CreateTime>2013-10-15T20:21:02.377-04:00</CreateTime><ID><Value>287448</Value></ID><PrimaryAddress><AddressLine1>621 Himes Ave Apt 107</AddressLine1><AddressType>home</AddressType><City>Frederick</City><Country>US</Country><County>Frederick</County><CreateTime>2013-10-15T20:21:02.377-04:00</CreateTime><ID><Value>501954</Value></ID><PostalCode>21703</PostalCode><PublicID>prod:501954</PublicID><State>MD</State><UpdateTime>2013-10-15T20:23:45.302-04:00</UpdateTime></PrimaryAddress><PrimaryPhone>home</PrimaryPhone><PublicID>prod:287448</PublicID><UpdateTime>2013-10-15T20:23:45.302-04:00</UpdateTime></Contact><CreateTime>2013-10-15T20:21:02.377-04:00</CreateTime><ID><Value>286745</Value></ID><PublicID>prod:286745</PublicID><UpdateTime>2013-10-15T20:21:03.002-04:00</UpdateTime></AccountContact><CreateTime>2013-10-15T20:21:03.002-04:00</CreateTime><ID><Value>469166</Value></ID><PublicID>prod:469166</PublicID><UpdateTime>2013-10-15T20:21:03.002-04:00</UpdateTime></AccountContactRole><CreateTime>2013-10-15T20:25:43.617-04:00</CreateTime><ID><Value>1681291</Value></ID><PublicID>prod:1681291</PublicID><UpdateTime>2013-10-15T20:25:43.617-04:00</UpdateTime></Entry><Entry><AccountContactRole><AccountContact><Contact><entity-Person><CellPhone>609-402-9712</CellPhone><DateOfBirth>1989-01-12T00:00:00-05:00</DateOfBirth><FirstName>Joseph</FirstName><Gender>M</Gender><LastName>Carrafa</LastName><MaritalStatus>S</MaritalStatus><MiddleName>J</MiddleName><Suffix>jr</Suffix></entity-Person><CreateTime>2013-10-15T20:21:03.002-04:00</CreateTime><HomePhone>609-402-9712</HomePhone><ID><Value>287449</Value></ID><PrimaryAddress><AddressLine1>621 Himes Ave Apt 107</AddressLine1><AddressType>home</AddressType><City>Frederick</City><Country>US</Country><County>Frederick</County><CreateTime>2013-10-15T20:21:02.377-04:00</CreateTime><ID><Value>501954</Value></ID><PostalCode>21703</PostalCode><PublicID>prod:501954</PublicID><State>MD</State><UpdateTime>2013-10-15T20:23:45.302-04:00</UpdateTime></PrimaryAddress><PrimaryPhone>mobile</PrimaryPhone><PublicID>prod:287449</PublicID><UpdateTime>2013-11-13T21:12:10.976-05:00</UpdateTime><WorkPhone>609-402-9712</WorkPhone></Contact><CreateTime>2013-10-15T20:21:03.002-04:00</CreateTime><ID><Value>286746</Value></ID><PublicID>prod:286746</PublicID><UpdateTime>2013-11-13T21:14:11.398-05:00</UpdateTime></AccountContact><CreateTime>2013-10-15T20:21:03.002-04:00</CreateTime><ID><Value>469167</Value></ID><PublicID>prod:469167</PublicID><UpdateTime>2013-10-15T20:21:03.002-04:00</UpdateTime></AccountContactRole><CreateTime>2013-10-15T20:25:43.617-04:00</CreateTime><ID><Value>1681292</Value></ID><PublicID>prod:1681292</PublicID><UpdateTime>2013-11-21T14:12:00.703-05:00</UpdateTime></Entry></PolicyContactRoles><PolicyNumber>Unassigned</PolicyNumber><PublicID>prod:271095</PublicID><QuoteTierGroupExt>10</QuoteTierGroupExt><QuoteTierScoreExt>462</QuoteTierScoreExt><RateAsOfDate>2013-11-21T14:11:58.109-05:00</RateAsOfDate><Status>Quoted</Status><TermPremiumAmountExt>2037.91</TermPremiumAmountExt><TotalPremiumRPT>2037.91</TotalPremiumRPT><TransactionCostRPT>2037.91</TransactionCostRPT><TransactionFeeAmountExt>0.00</TransactionFeeAmountExt><TransactionPremiumRPT>2037.91</TransactionPremiumRPT><UpdateTime>2013-11-21T14:12:00.703-05:00</UpdateTime><UpdateUser><Contact><CreateTime>2013-06-02T15:21:23.643-04:00</CreateTime><FirstName>Julianna</FirstName><ID><Value>112784</Value></ID><LastName>Cookus</LastName><PublicID>prod:112784</PublicID><UpdateTime>2013-06-02T15:21:23.643-04:00</UpdateTime></Contact><CreateTime>2013-06-02T15:21:23.643-04:00</CreateTime><ID><Value>3708</Value></ID><PublicID>prod:3708</PublicID><Roles><Entry><Role><CreateTime>2012-09-07T16:41:54.273-04:00</CreateTime><ID><Value>30</Value></ID><Name>Sales Rep</Name><PublicID>sales_rep</PublicID><UpdateTime>2013-11-05T14:05:22.921-05:00</UpdateTime></Role></Entry></Roles><UpdateTime>2013-06-02T15:21:23.643-04:00</UpdateTime></UpdateUser><ValidQuote>true</ValidQuote><WrittenDate>2013-10-15T00:00:00-04:00</WrittenDate></PolicyPeriod>");
			xmls.add(parser.parseIt());
			i++;
		} while (i<1000);
		long end = System.currentTimeMillis();
		System.out.println(end-start);
	}
	
	public static PolicyPeriod getPolicyPeriod(Parser parser) {
		PolicyPeriod pp = new PolicyPeriod();
		pp.TransID = null;
		pp.TransType = null;
		pp.PPID = parser.getValue("ID/Value[1]");
		pp.BasedOnID = parser.getValue("(BasedOn/ID/Value)[1]");
		pp.CeateTime = parser.getValue("CreateTime[1]");
		pp.CreditScore = parser.getValue("CreditScore[1]");
		pp.CurrentPremExt = parser.getValue("CurrentPremExt[1]");
		pp.EISEditEffDate = parser.getValue("EISEditEffectiveDateExt[1]");
		pp.FcraNotice = parser.getValue("FcraNotice[1]");
		pp.JobID = parser.getValue("(Job/ID/Value)[1]");
		pp.MostRecentModel = parser.getValue("(MostRecentModel)[1]");
		pp.PolPerPublicID = parser.getValue("(PublicID)[1]");
		pp.PolicyID = parser.getValue("(Policy/ID/Value)[1]");
		pp.PolicyNumber = parser.getValue("PolicyNumber[1]");
		pp.PolExpDate = parser.getValue("(PeriodEnd)[1]");
		pp.PolEffDate = parser.getValue("(PeriodStart)[1]");
		pp.RateAsOfDate = parser.getValue("(RateAsOfDate)[1]");
		pp.Status = parser.getValue("(Status)[1]");
		pp.TermNumber = parser.getValue("(TermNumber)[1]");
		pp.TermPremiumExt = parser.getValue("(TermPremiumAmountExt)[1]");
		pp.TotalPremiumRPT = parser.getValue("(TotalPremiumRPT)[1]");
		pp.TransactionCostRPT = parser.getValue("(TransactionCostRPT)[1]");
		pp.TransactionFeeAmt = parser.getValue("(TransactionFeeAmountExt)[1]");
		pp.TransactionCostRPT = parser.getValue("(TransactionPremiumRPT)[1]");
		pp.UpdateTime = parser.getValue("(UpdateTime)[1]");
		pp.ValidQuote = parser.getValue("(ValidQuote)[1]");
		pp.WrittenDate = parser.getValue("(WrittenDate)[1]");
		pp.AccountID = parser.getValue("(Policy/Account/ID/Value)[1]");
		pp.AccountNumber = parser.getValue("(Policy/Account/AccountNumber)[1]");
		pp.UpdateUserID = parser.getValue("(Policy/Account/AccountNumber)[1]");
		pp.UpdateUserID = parser.getValue("(UpdateUser/ID/Value)[1]");
		pp.UpdateUserContactFirstName = parser.getValue("(UpdateUser/Contact/FirstName)[1]");
		pp.UpdateUserContactLastName = parser.getValue("(UpdateUser/Contact/LastName)[1]");
		pp.UpdateUserRoleID = parser.getValue("(UpdateUser/Roles/Entry/Role/ID/Value)[1]");
		pp.RoleName = parser.getValue("(UpdateUser/Roles/Entry/Role/Name)[1]");
		pp.CreateUserID = parser.getValue("(CreateUser/ID/Value)[1]");
		pp.CreateUserContactID = parser.getValue("(CreateUser/Contact/ID/Value)[1]");
		pp.CreateUserContactFirstName = parser.getValue("(CreateUser/Contact/FirstName)[1]");
		pp.CreateUserContactLastName = parser.getValue("(CreateUser/Contact/LastName)[1]");
		pp.EmailAddress1 = parser.getValue("(EmailAddress1)[1]");
		pp.AccountCreateUserFirstName = parser.getValue("(Policy/Account/CreateUser/Contact/FirstName)[1]");
		pp.AccountCreateUserLastName = parser.getValue("(Policy/Account/CreateUser/Contact/LastName)[1]");
		pp.QuoteTierGroupExt = parser.getValue("QuoteTierGroupExt[1]");
		pp.QuoteTierScoreExt = parser.getValue("QuoteTierScoreExt[1]");
		return pp;
	}
	
	public static Coverage getCoverage(Parser parser) {
		Coverage cov = new Coverage();
		cov.TransID = null;
		cov.PolicyPeriodID = null;
		cov.AccountID = null;
		cov.PATransID = parser.getValue("(ID/Value)[1]");
		cov.FixedID = parser.getValue("(FixedId/Value)[1]");
		cov.Amount = parser.getValue("(Amount)[1]");
		cov.Charged = parser.getValue("(Charged)[1]");
		cov.CreateTime = parser.getValue("(CreateTime)[1]");
		cov.EffDate = parser.getValue("(EffDate)[1]");
		cov.ExpDate = parser.getValue("(ExpDate)[1]");
		cov.PostedDate = parser.getValue("(PostedDate)[1]");
		cov.PATransPublicID = parser.getValue("(PublicID)[1]");
		cov.ToBeAccrued = parser.getValue("(ToBeAccrued)[1]");
		cov.UpdateTime = parser.getValue("(UpdateTime)[1]");
		cov.WrittenDate = parser.getValue("(WrittenDate)[1]");
		cov.PACostID = parser.getValue("(PACost/ID/Value)[1]");
		cov.PACostFixedID = parser.getValue("(PACost/FixedId/Value)[1]");
		cov.PACostBasedOnID = parser.getValue("(PACost/BasedOn/ID/Value)[1]");
		cov.PACostBasedOnFixedID = parser.getValue("(PACost/BasedOn/FixedId/Value)[1]");
		cov.PACostCreateTime = parser.getValue("(PACost/CreateTime)[1]");
		cov.ActualAdjRate = parser.getValue("(PACost/ActualAdjRate)[1]");
		cov.ActualTermAmount = parser.getValue("(PACost/ActualTermAmount)[1]");
		cov.ChargePattern = parser.getValue("(PACost/ChargePattern/Code)[1]");
		cov.PACostPublicID = parser.getValue("(PACost/PublicID)[1]");
		cov.Prorated = parser.getValue("(PACost/Prorated)[1]");
		cov.Proration = parser.getValue("(PACost/Proration)[1]");
		cov.Subtype = parser.getValue("(PACost/Subtype/Code)[1]");
		cov.PACostUpdateTime = parser.getValue("(PACost/UpdateTime)[1]");
		cov.CovID = parser.getValue("(PACost/Coverage/ID/Value)[1]");
		cov.CovPatternCode = parser.getValue("(PACost/Coverage/PatternCode)[1]");
		cov.DisplayValue = parser.getValue("(PACost/Coverage/CovTerms/Entry/DisplayValue)[1]");
		cov.PatternCode = parser.getValue("(PACost/Coverage/CovTerms/Entry/PatternCode)[1]");
		cov.ValueTypeName = parser.getValue("(PACost/Coverage/CovTerms/Entry/ValueTypeName)[1]");
		cov.VehID = parser.getValue("(PACost/Vehicle/ID/Value)[1]");
		cov.FixedVehID = parser.getValue("(PACost/Vehicle/FixedId/Value)[1]");
		cov.UpdateUserID = parser.getValue("(UpdateUser/ID/Value)[1]");
		cov.ContactID = parser.getValue("(UpdateUser/Contact/ID/Value)[1]");
		cov.RoleID = parser.getValue("(UpdateUser/Roles/Entry/Role/ID/Value)[1]");
		cov.RoleName = parser.getValue("(UpdateUser/Roles/Entry/Role/Name)[1]");
		return cov;
	}
	
	public static PersonalAutoLine getPersonalAutoLine(Parser parser) {
		PersonalAutoLine pal = new PersonalAutoLine();
		pal.TransID = null;
		pal.PolicyPeriodID = null;
		pal.AccountID = null;
		pal.PALineID = parser.getValue("(ID/Value)[1]");
		pal.BasedOnID = parser.getValue("(BasedOn/ID/Value)[1]");
		pal.affinityExt = parser.getValue("AffinityExt[1]");
		pal.affinityGroupExt = parser.getValue("AffinityGroupExt[1]");
		pal.AllowTextExt = parser.getValue("AllowTextExt[1]");
		pal.ApplicantID = parser.getValue("(ApplicantExt/ID/Value)[1]");
		pal.AppFixedID = parser.getValue("(ApplicantExt/FixedId/Value)[1]");
		pal.atFaultExt = parser.getValue("AtFaultExt[1]");
		pal.BaseState = parser.getValue("(BaseState)[1]");
		pal.ChannelExt = parser.getValue("(ChannelEXT)[1]");
		pal.ChildOnPolicyExt = parser.getValue("ChildOnPolicyExt[1]");
		pal.CreateTime = parser.getValue("(CreateTime)[1]");
		pal.CreditScoreExt = parser.getValue("(ApplicantExt/CreditScoreExt)[1]");
		pal.CurrentBILimits = parser.getValue("(CurrentInjuryLimitsExt)[1]");
		pal.CurrentCarrierExt = parser.getValue("(CurrentCarrier_Ext)[1]");
		pal.CurrentPremExt = parser.getValue("CurrentPremiumExt[1]");
		pal.EmailAddress1 = parser.getValue("(EmailAddress1)[1]");
		pal.ESignatureExt = parser.getValue("(ESignatureExt)[1]");
		pal.Guest_PIP = parser.getValue("(PAPIP_MD/CovTerms/Entry/DisplayValue)[2]");
		pal.HasCurrInsurExt = parser.getValue("(HasAutoInsuranceExt)[1]");
		pal.HomeownerExt = parser.getValue("(homeownerExt)[1]");
		pal.LapseInCoverageExt = parser.getValue("(LapseInCoverageExt)[1]");
		pal.minorChildExt = parser.getValue("(MinorChildExt)[1]");
		pal.MultiCarExt = parser.getValue("(MultiCarExt)[1]");
		pal.NonChargIncidents = parser.getValue("(nCIncidents)[1]");
		pal.numDriversExt = parser.getValue("(NumDriversExt)[1]");
		pal.OnlineDiscountExt = parser.getValue("(OnlineDiscountExt)[1]");
		pal.PaidInFullExt = parser.getValue("(PaidInFullExt)[1]");
		pal.PaperlessDiscountExt = parser.getValue("(PaperlessDiscountExt)[1]");
		pal.PolicyCapRatioExt = parser.getValue("(PolicyCapRatioExt)[1]");
		pal.PromoCode = parser.getValue("(PromoCode)[1]");
		pal.PALinePublicID = parser.getValue("(PublicID)[1]");
		pal.ReapplicationDiscountExt = parser.getValue("(ReapplicationDiscountExt)[1]");
		pal.ResidencyExt = parser.getValue("(ResidencyExt)[1]");
		pal.ResponsibleDriverExt = parser.getValue("(responsibleDriverExt)[1]");
		pal.SourceOfBusinessExt = parser.getValue("(SourceOfBusinessExt)[1]");
		pal.TierExt = parser.getValue("(TierExt)[1]");
		pal.TierScoreExt = parser.getValue("(TierScoreExt)[1]");
		pal.UpdateTime = parser.getValue("(UpdateTime)[1]");
		pal.WebToPhoneDiscountExt = parser.getValue("(WebToPhoneDiscountExt)[1]");
		pal.YearsWithProviderExt = parser.getValue("(YearsWithProviderExt)[1]");
		pal.youthfulExt = parser.getValue("(YouthfulEXT)[1]");
		pal.youthfulPNIExt = parser.getValue("(YouthfulPNIExt)[1]");
		return pal;
	}
	
	public static Incidents getIncidents(Parser parser) {
		Incidents inc = new Incidents();
		inc.TransID = null;
		inc.PolicyPeriodID = null;
		inc.AccountID = null;
		inc.IncidentID = parser.getValue("(ID/Value)[1]");
		inc.CreateTime = parser.getValue("(CreateTime)[1]");
		inc.IncidentDescription = parser.getValue("(IncidentDescription)[1]");
		inc.OccurrenceDate = parser.getValue("(OccurenceDate)[1]");
		inc.OtherDriver = parser.getValue("(OtherDriver)[1]");
		inc.IncidentPublicID = parser.getValue("(PublicID)[1]");
		inc.Remarks = parser.getValue("(Remarks)[1]");
		inc.Subtype = parser.getValue("(Subtype)[1]");
		inc.UpdateTime = parser.getValue("(UpdateTime)[1]");
		inc.PolicyDriverID = parser.getValue("(PolicyDriver/ID/Value)[1]");
		inc.AccountContactRoleID = parser.getValue("(PolicyDriver/AccountContactRole/ID/Value)[1]");
		inc.AccountContactID = parser.getValue("(PolicyDriver/AccountContactRole/AccountContact/ID/Value)[1]");
		inc.ContactID = parser.getValue("(PolicyDriver/AccountContactRole/AccountContact/Contact/ID/Value)[1]");
		return inc;
	}
		
	public static Vehicle getVehicle(Parser parser) {
		Vehicle veh = new Vehicle();
		veh.TransID = null;
		veh.PolicyPeriodID = null;
		veh.AccountID = null;
		veh.VehID = parser.getValue("(ID/Value)[1]");
		veh.FixedVehID = parser.getValue("(FixedId/Value)[1]");
		veh.VehAge = parser.getValue("Age[1]");
		veh.BodyType = parser.getValue("(BodyType)[1]");
		veh.AssocPrimDriverID = parser.getValue("(AssociatedPrimaryDriverExt/ID/Value)[1]");
		veh.AcctContRoleID = parser.getValue("(AssociatedPrimaryDriverExt/AccountContactRole/ID/Value)[1]");
		veh.CostNew = parser.getValue("CostNew[1]");
		veh.CoverableDate = parser.getValue("CoverableReferenceDate[1]");
		veh.CreateTime = parser.getValue("CreateTime[1]");
		veh.DMAExt = parser.getValue("(DMAExt)[1]");
		veh.ExistingDamageExt = parser.getValue("ExistingDamageExt[1]");
		veh.ExoticVehicleExt = parser.getValue("ExoticVehicleExt[1]");
		veh.ExpDate = parser.getValue("ExpirationDate[1]");
		veh.GarageLocationCounty = parser.getValue("(GarageLocation/County)[1]");
		veh.GarageLocationPostalCode = parser.getValue("(GarageLocation/PostalCode)[1]");
		veh.IgnoreVINExt = parser.getValue("IgnoreVINExt[1]");
		veh.LeaseOrRent = parser.getValue("LeaseOrRent[1]");
		veh.LicenseState = parser.getValue("LicenseState[1]");
		veh.Make = parser.getValue("Make[1]");
		veh.Model = parser.getValue("Model[1]");
		veh.PrimaryUse = parser.getValue("PrimaryUse[1]");
		veh.TerritoryExt = parser.getValue("(TerritoryExt)[1]");
		veh.VehPublicID = parser.getValue("PublicID[1]");
		veh.VehNum = parser.getValue("VehicleNumber[1]");
		veh.VehType = parser.getValue("VehicleType[1]");
		veh.VehYear = parser.getValue("Year[1]");
		veh.UpdateTime = parser.getValue("UpdateTime[1]");
		veh.VIN = parser.getValue("(Vin)[1]");
		return veh;
	}
		
	public static AdditionalInterests getAdditionalInterests(Parser parser) {
		AdditionalInterests ai = new AdditionalInterests();
		ai.TransID = null;
		ai.PolicyPeriodID = null;
		ai.AccountID = null;
		ai.VehID = parser.getValue("(//PolicyPeriod/PersonalAutoLine/Vehicles/Entry[AdditionalInterests/Entry/ID/Value=//PolicyPeriod/PersonalAutoLine/Vehicles/Entry/AdditionalInterests/Entry/ID/Value]/ID/Value)[1]");
		ai.VehFixedID = parser.getValue("(//PolicyPeriod/PersonalAutoLine/Vehicles/Entry[AdditionalInterests/Entry/ID/Value=//PolicyPeriod/PersonalAutoLine/Vehicles/Entry/AdditionalInterests/Entry/ID/Value]/FixedId/Value)[1]");
		ai.ID = parser.getValue("(ID/Value)[1]");
		ai.FixedID = parser.getValue("(FixedId/Value)[1]");
		ai.AddlInterestType = parser.getValue("(AdditionalInterestType/Code)[1]");
		ai.CreateTime = parser.getValue("(//PolicyPeriod/PersonalAutoLine/Vehicles/Entry[AdditionalInterests/Entry/ID/Value=//PolicyPeriod/PersonalAutoLine/Vehicles/Entry/AdditionalInterests/Entry/ID/Value]/CreateTime)[1]");
		ai.AccountContactID = parser.getValue("(PolicyAddlInterest/AccountContactRole/ID/Value)[1]");
		ai.ContactID = parser.getValue("(PolicyAddlInterest/AccountContactRole/AccountContact/Contact/ID/Value)[1]");
		ai.AccountContactRoleID = parser.getValue("(PolicyAddlInterest/AccountContactRole/ID/Value)[1]");
		ai.UpdateTime = parser.getValue("(//PolicyPeriod/PersonalAutoLine/Vehicles/Entry[AdditionalInterests/Entry/ID/Value=//PolicyPeriod/PersonalAutoLine/Vehicles/Entry/AdditionalInterests/Entry/ID/Value]/UpdateTime)[1]");
		return ai;
	}
		
	public static PolicyDrivers getPolicyDrivers(Parser parser) {
		PolicyDrivers pd = new PolicyDrivers();
		pd.TransID = null;
		pd.PolicyPeriodID = null;
		pd.AccountID = null;
		pd.PolDriverID = parser.getValue("(ID/Value)[1]");
		pd.BasedOnID = parser.getValue("(BasedOn/ID/Value)[1]");
		pd.FixedID = parser.getValue("(FixedId/Value)[1]");
		pd.CreateTime = parser.getValue("CreateTime[1]");
		pd.Age = parser.getValue("Age[1]");
		pd.AgeFirstLicensed = parser.getValue("AgeFirstLicensedExt[1]");
		pd.AssocPrimVehID = parser.getValue("(AssociatedPrimaryVehicleExt/ID/Value)[1]");
		pd.BIPointsExt = parser.getValue("BIPointsExt[1]");
		pd.CMPPointsExt = parser.getValue("CMPPointsExt[1]");
		pd.ColPointsExt = parser.getValue("ColPointsExt[1]");
		pd.creditTierExt = parser.getValue("CreditTierExt[1]");
		pd.CustPointsExt = parser.getValue("CustPointsExt[1]");
		pd.LoanPointsExt = parser.getValue("LoanPointsExt[1]");
		pd.MedPointsExt = parser.getValue("MedPointsExt[1]");
		pd.PDPointsExt = parser.getValue("PDPointsExt[1]");
		pd.PIPMDPointsExt = parser.getValue("PIPMDPointsExt[1]");
		pd.PIPTXPointsExt = parser.getValue("PIPTXPointsExt[1]");
		pd.RentPointsExt = parser.getValue("RentPointsExt[1]");
		pd.UMBIPointsExt = parser.getValue("UMBIPointsExt[1]");
		pd.UMPDPointsExt = parser.getValue("UMPDPointsExt[1]");
		pd.DriverClassFactor = parser.getValue("DriverClassFactorExt[1]");
		pd.Excluded = parser.getValue("Excluded[1]");
		pd.DiscGoodStudent = parser.getValue("GoodStudentDiscountExt[1]");
		pd.Education = parser.getValue("HighestLevelOfEducationExt[1]");
		pd.FR19 = parser.getValue("Fr19Ext[1]");
		pd.FR44 = parser.getValue("Fr44Ext[1]");
		pd.LicenseNumber = parser.getValue("LicenseNumber[1]");
		pd.LicenseState = parser.getValue("LicenseState[1]");
		pd.LicenseStatus = parser.getValue("LicenseStatusExt[1]");
		pd.MVROrdered = parser.getValue("MVROrderStatus[1]");
		pd.MaritalExt = parser.getValue("MaritalExt[1]");
		pd.OccupationGroup = parser.getValue("occupationGroupExt[1]");
		pd.OccupationStatus = parser.getValue("OccupationStatusListExt[1]");
		pd.OccupationTitle = parser.getValue("OccupationTitleExt[1]");
		pd.Operator = parser.getValue("Operator[1]");
		pd.PrimDrivPublicID = parser.getValue("PublicID[1]");
		pd.RatedCreditTierExt = parser.getValue("RatedCreditTierExt[1]");
		pd.RatingStatus = parser.getValue("RatingStatusExt[1]");
		pd.Relationship = parser.getValue("RelationshipToInsuredExt[1]");
		pd.SR22 = parser.getValue("Sr22Ext[1]");
		pd.TrainingClassType = parser.getValue("TrainingClassType[1]");
		pd.YearsLicensed = parser.getValue("YearsLicensedExt[1]");
		pd.UpdateTime = parser.getValue("UpdateTime[1]");
		pd.FirstName = parser.getValue("(FirstName)[1]");
		pd.Gender = parser.getValue("(Gender)[1]");
		pd.MiddleName = parser.getValue("(MiddleName)[1]");
		pd.LastName = parser.getValue("(LastName)[1]");
		pd.Suffix = parser.getValue("(Suffix)[1]");
		pd.MaritalStatus = parser.getValue("(MaritalStatus)[1]");
		return pd;
	}
		
	public static AccountLocation getAccountLocation(Parser parser) {
		AccountLocation al = new AccountLocation();
		al.TransID = null;
		al.PolicyPeriodID = null;
		al.AccountID = null;
		al.VehID = parser.getValue("(ID/Value)[1]");
		al.FixedVehID = parser.getValue("(FixedId/Value)[1]");
		al.AcctLocationID = parser.getValue("(AccountLocation/ID/Value)[1]");
		al.AddressLine1 = parser.getValue("(AccountLocation/AddressLine1)[1]");
		al.AddressLine2 = parser.getValue("(AccountLocation/AddressLine2)[1]");
		al.AddressType = parser.getValue("(AddressType)[1]");
		al.City = parser.getValue("(AccountLocation/City)[1]");
		al.County = parser.getValue("(AccountLocation/County)[1]");
		al.Country = parser.getValue("(AccountLocation/Country)[1]");
		al.CreateTime = parser.getValue("(AccountLocation/CreateTime)[1]");
		al.PhoneNumber = parser.getValue("(AccountLocation/Phone)[1]");
		al.PostalCode = parser.getValue("(AccountLocation/PostalCode)[1]");
		al.AcctAddPublicID = parser.getValue("(AccountLocation/PublicID)[1]");
		al.State = parser.getValue("(AccountLocation/State)[1]");
		al.UpdateTime = parser.getValue("(AccountLocation/UpdateTime)[1]");
		return al;
	}
		
	public static PolicyAddress getPolicyAddress(Parser parser) {
		PolicyAddress pa = new PolicyAddress();
		pa.TransID = null;
		pa.PolicyPeriodID = null;
		pa.AccountID = null;
		pa.PolAddressID = parser.getValue("(ID/Value)[1]");
		pa.CreateTime = parser.getValue("(CreateTime)[1]");
		pa.PolAddPublicID = parser.getValue("(PublicID)[1]");
		pa.UpdateTime = parser.getValue("(UpdateTime)[1]");
		pa.AddressLine1 = parser.getValue("(AddressLine1)[1]");
		pa.AddressLine2 = parser.getValue("(AddressLine2)[1]");
		pa.AddressLine3 = parser.getValue("(AddressLine3)[1]");
		pa.City = parser.getValue("(City)[1]");
		pa.County = parser.getValue("(County)[1]");
		pa.PostalCode = parser.getValue("(PostalCode)[1]");
		pa.State = parser.getValue("(State)[1]");
		return pa;
	}
		
	public static PolicyContact getPolicyContact(Parser parser) {
		PolicyContact pc = new PolicyContact();
		pc.TransID = null;
		pc.PolicyPeriodID = null;
		pc.AccountID = null;
		pc.PolicyContactRoleID = parser.getValue("(ID/Value)[1]");
		pc.AcctContactRoleID = parser.getValue("(AccountContactRole/ID/Value)[1]");
		pc.CreateTime = parser.getValue("(AccountContactRole/CreateTime)[1]");
		pc.AcctContactRolePublicID = parser.getValue("(AccountContactRole/PublicID)[1]");
		pc.UpdateTime = parser.getValue("(AccountContactRole/UpdateTime)[1]");
		pc.AcctContactID = parser.getValue("(AccountContactRole/AccountContact/ID/Value)[1]");
		pc.AddressLine1 = parser.getValue("(AccountContactRole/AccountContact/Contact/PrimaryAddress/AddressLine1)[1]");
		pc.AddressLine2 = parser.getValue("(AccountContactRole/AccountContact/Contact/PrimaryAddress/AddressLine2)[1]");
		pc.City = parser.getValue("(AccountContactRole/AccountContact/Contact/PrimaryAddress/City)[1]");
		pc.PostalCode = parser.getValue("(AccountContactRole/AccountContact/Contact/PrimaryAddress/PostalCode)[1]");
		pc.State = parser.getValue("(AccountContactRole/AccountContact/Contact/PrimaryAddress/State)[1]");
		pc.ContactID = parser.getValue("(AccountContactRole/AccountContact/Contact/ID/Value)[1]");
		pc.ContactCreateTime = parser.getValue("(AccountContactRole/AccountContact/Contact/CreateTime)[1]");
		pc.HomePhone = parser.getValue("(AccountContactRole/AccountContact/Contact/HomePhone)[1]");
		pc.ContactPublicID = parser.getValue("(AccountContactRole/AccountContact/Contact/PublicID)[1]");
		pc.SSNOfficialID = parser.getValue("(AccountContactRole/AccountContact/Contact/SSNOfficialID)[1]");
		pc.EmailAddress1 = parser.getValue("(AccountContactRole/AccountContact/Contact/EmailAddress1)[1]");
		pc.LienholderName = parser.getValue("(AccountContactRole/AccountContact/Contact/Name)[1]");
		pc.WorkPhone = parser.getValue("(AccountContactRole/AccountContact/Contact/WorkPhone)[1]");
		pc.ContactUpdateTime = parser.getValue("(AccountContactRole/AccountContact/Contact/UpdateTime)[1]");
		pc.DateOfBirth = parser.getValue("(AccountContactRole/AccountContact/Contact/entity-Person/DateOfBirth)[1]");
		pc.FirstName = parser.getValue("(AccountContactRole/AccountContact/Contact/entity-Person/FirstName)[1]");
		pc.Gender = parser.getValue("(AccountContactRole/AccountContact/Contact/entity-Person/Gender)[1]");
		pc.MiddleName = parser.getValue("(AccountContactRole/AccountContact/Contact/entity-Person/MiddleName)[1]");
		pc.LastName = parser.getValue("(AccountContactRole/AccountContact/Contact/entity-Person/LastName)[1]");
		pc.Suffix = parser.getValue("(AccountContactRole/AccountContact/Contact/entity-Person/Suffix)[1]");
		pc.CellPhone = parser.getValue("(AccountContactRole/AccountContact/Contact/entity-Person/CellPhone)[1]");
		pc.MaritalStatus = parser.getValue("(AccountContactRole/AccountContact/Contact/entity-Person/MaritalStatus)[1]");
		return pc;
	}
}
