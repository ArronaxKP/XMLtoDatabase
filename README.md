XMLtoDatabase
=============

This is a project to create a dynamic XML parser that will output the XML into tables using the VTD-XML library


How do you use this application
===============================

Run the command as follows: -
java -jar Shredder.jar

If you want to run it in Debug you run it with the -X flag: -

java -jar Shredder.jar -X

Mapping XML controls what it parses and where it gets inserted into.

Mapping.xml - The basic structure
=================================


<shredder> The root element

<sourcedatabase> This is the information for the source database (the XML pay load locations)
<errordatabase> This is the information for the error database (where the database errors will be written to)
<database> This is the information for the core database (where the inserts will put the information from the XML values)

All three databases also have the following Route Elements: -
<servername> The name of the server
<databasename> The name of the database on the server (without [])
<schema> The schema where the tables exist (without [])
<username> The username to access the database
<password> THe password for the username to access the database

Each databse has the following elements
<table> The table element will contain everything or a single table entry

Tables have the following Elements
<name> The name of the table (without [])
<column> The column element will contain everything relating to a single column from a single table

The Column has the following elements: -
<name>
<variablelookupkey>
