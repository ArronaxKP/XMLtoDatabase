Content
=======
1. XMLtoDatabase  - Overview
2. How do you use this application
3. Logs & Debugging
4. Mapping.xml - The basic structure
4.1. Database (Main Insert Database)
4.2. Error Database
4.3. Source Database
5. Example XML


1. XMLtoDatabase
================

This is a project to create a dynamic XML parser that will output the XML into tables using the VTD-XML library for parsing XML and Log4j2 for logging.

This was build by Karl Parry and Gareth Edwards from Admiral UK for the use by Elephant.com.

The main aim is to try and parse XML returned by GuideWire and insert it into the Data warehouse staging environment.

2. How do you use this application
==================================

Run the command as follows: -
java -jar Shredder.jar

If you want to run it in Debug you run it with the -X flag: -

java -jar Shredder.jar -X

Mapping.xml controls what it parses and where it gets inserted into.

Currently mapping.xml must be in the same directory as Shredder.jar file for it to find it and parse. 

3. Logs & Debugging
===================

Curently the system is setup to log as follows: -
> If any issue occurs when inserting or updating a specific XML it will write an error to the error database with an error description. 
> It will also log informaton to the log file in the logs directory (same location as the Shredder.jar file).

If an error occurs with a specific XML and you are unable to find the issue the best idea would be to follow these steps: -
1. Wait until the application finishes running and shuts down. 
2. Once it has down, change the clause in the mapping.xml file to something similar to WHERE ID ='TRANSID'. 
3. Then run the application in debug mode (-X flag) this will make the application output all the insert statements and more detailed reporting. 
4. Using this output you can then find the cause of the issue. (In the early days this may require some code changes).  


The full debug command with the -X flag: -

java -jar Shredder.jar -X

4. Mapping.xml - The basic structure
====================================
Key:
(1) - Number of allowable elements


<shredder> The root element. (1)

<sourcedatabase> This is the information for the source database (the XML pay load locations). (1)
<errordatabase> This is the information for the error database (where the database errors will be written to). (1)
<database> This is the information for the core database (where the inserts will put the information from the XML values). (1)

All three databases also have the following Route Elements: -
<servername> The name of the server. (1)
<databasename> The name of the database on the server (without []). (1)
<schema> The schema where the tables exist (without []). (1)
<username> The username to access the database. (1)
<password> The password for the username to access the database. (1)


4.1. Database (Main Insert Database)
------------------------------------

The Core Database also has: -
<table> This is the table for the XML values to be written into (1..*)
<lookupfield> Defines a look up value that can be use for a column value.. You can none or many look ups with the recommended maximum of 45. (0..*)

Look up fields have the following elements: -
<key> This is the look up key that will be referenced when this value is to be used in the column look up. (1)
<variable> This corresponds the variable name defined from table select from the source database (1)

Tables have the following Elements
<name> The name of the table (without [])
<rootxpath> The root Xpath for the table. e.g. //PolicyPeriod for PolicyPeriod. (1)
<column> The column element will contain everything relating to a single column from a single table (1..*)

The Column has the following elements: -
<name> The name for the column (without []) (1)
<xpath> The Xpath value to get the details from the tables Root Xpath location. (0..1)
<lookup> This is the key that is used to get the column's value from the look up list e.g. TransID. (0..1)
<type> Is the SQL used to cast the object to the correct data type. It will replace the ? in the String with the value from the xpath. e.g. CAST ('?' AS DATETIME) will be written in the SQL as CAST('01 01 01T12345' AS DATETME) (0..1)


4.2. Error Database
-------------------

The Error database has: -
<table> This is the table for the error's to be written into (1)

The Table element has the following Elements
<name> The name of the table (without [])
<column> he column element will contain everything relating to a single column from a single table (1..*)

The Column has the following elements: -
<name> The name for the column (without []) (1)
<lookup> This is the key that is used to get the column's value from the look up list e.g. TransID. (0..1)
<sql> Is the Specific SQL command that will be inserted as the value for that column
<specialvalue> This is a special value that corresponds specifically with internal working. XML is the XML pay load and ERROR is the error message. These will be written as the value of the table. (0..1)


4.3. Source Database
--------------------

The Source database has the following elements: -
<table> The table name for the XML pay load to be retrieved from (1)

The table element has: -
<name> The name of the table containing the XML (1)
<column> The columns in the table to select(1..*)

Each column can have the following values: -
<name> The name of the column (1)
<variablelookupkey> The variable look up key that can be used later to map this value to the insert statements (0..1)
<subset> To subset the value to a specific set using e.g. TOP 10. This is inserted in the Select statement between SELECT and Column names e.g. SELECT ** HERE ** ColumnName FROM TableName
<clause> The add a clause to the end of the statemtne e.g. WHERE ID = '1'. This is inserted at the end of the select statement e.g. SELECT ColumnName FROM TableName ** HERE ** 


5. Example XML
==============

<?xml version="1.0" encoding="UTF-8"?>
<shredder>
	<sourcedatabase>
		<servername>ServerName</servername>
		<databasename>DatabaseName</databasename>
		<schema>Schema</schema>
		<username>USERNAME</username>
		<password>PASSW0RD</password>
		<table>
			<name>Table Name</name>
			<column>
				<name>Column Name</name>
				<variablelookupkey>The variable key that will be referenced in database</variablelookupkey>
			</column>
			<subset>Subset the select statements result e.g. TOP 1</subset>
			<clause>The clause used to select values e.g. WHERE ID = '505243'</clause>
		</table>
	</sourcedatabase>
	<errordatabase>
		<servername>ServerName</servername>
		<databasename>DatabaseName</databasename>
		<schema>Schema</schema>
		<username>USERNAME</username>
		<password>PASSW0RD</password>
		<table>
			<name>Table Name</name>
			<column>
				<name>Column Name</name>
				<specialvalue>XML</specialvalue> <!-- Used to place the XML pay load into a column -->
			</column>
			<column>
				<name>Column Name</name>
				<lookup>LookUp Key</lookup>
			</column>
			<column>
				<name>Column Name</name>
				<specialvalue>ERROR</specialvalue> <!-- Used to place the error message into a column` -->
			</column>
			<column>
				<name>Column Name</name>
				<sql>SQL Command for column value</sql>
			</column>
		</table>
	</errordatabase>
	<database>
		<servername>ServerName</servername>
		<databasename>DatabaseName</databasename>
		<schema>Schema</schema>
		<username>USERNAME</username>
		<password>PASSW0RD</password>
		<lookupfield>
			<key>LookUp Key</key>
			<variable>Variable name defined in Source Database</variable>
		</lookupfield>
		<lookupfield>
			<key>LookUp Key</key>
			<xpath>Xpath From Document ROOT</xpath>
		</lookupfield>
		<table>
			<name>Table Name</name>
			<rootxpath>//XPATH</rootxpath>
			<column>
				<name>ColumnName</name>
				<lookup>LookUp Key</lookup>
			</column>
			<column>
				<name>Column Name</name>
				<xpath>(Xpath From Table ROOT)[1]</xpath>
			</column>
		</table>
	</database>
</shredder>
