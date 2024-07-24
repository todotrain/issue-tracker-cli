Issue tracker cli

call directly from console
    `mvn clean package`
    `java -jar .\target\cli-0.0.1-SNAPSHOT.jar parameters`


Parameters
-------------------------------------------
For creating a new issue use the following parameters
`-p,--parent <arg>   Parent issue ID`
`-d,--desc <arg>     Description`
`-l,--link <arg>     URL to the log`

For closing an existing issue 
`-c,--close <arg>    Issue to be closed`