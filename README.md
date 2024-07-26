Issue tracker cli

Download Requirements
- Java 17 sdk
- Maven

call directly from console
    `mvn clean package`
    `java -jar .\target\cli.jar parameters`


Usage:
- `cli.jar -a [-p <parent>] [-d <desc>] [-l <link>]`
- `cli.jar -c [-i <issue_id>]`

usage: cli-0.0.1-SNAPSHOT.jar
- `-a,--add            add record operation`
- `-c,--close          close record operation`
- `-d,--desc <arg>     Description`
- `-i,--id <arg>       Issue to be closed`
- `-l,--link <arg>     URL to the log`
- `-p,--parent <arg>   Parent issue ID`






*** SWE Home Assignemnt in Java ***
A Java CLI application that will track system bugs in a Google's Spreadsheet (or any other spreadsheet).
User is able to enter on CLI the:
* create a new issue with params:
    * parent issue id - string
    * description - string
    * link - url to the log
    - will return the new issue id (auto increment)
* close the existing issue:
    * by entering the existing issue id
I.e. the spreadsheet table
| ID  | Description                                          | ParentId | Status | CreationTimestamp | Link      |
|-----|------------------------------------------------------|----------|--------|-------------------|-----------|
| I-1 | Customer 360 Job is not ingesting into Search Engine |          | Closed | 2024-05-01T11:02  | yahoo.com |
| I-2 | Databricks Job is failing on parsing DoB             |          | Open   | 2024-05-07T11:02  | yahoo.com |
Use:
- git (github, gitlab, anything personal)
- java 17+
- spring-boot
- docker - the cli is executable from docker (https://github.com/GoogleContainerTools/jib)
Nice to see:
- use the facade layer so the app is no directly coupled to Google Cloud / Spreadsheet (i.e. can be later Office365, Jira, ...)
- functional programming
- understanding of SOLID Principle
- understanding of Design patterns
- understanding of TDD and BDD
 
 