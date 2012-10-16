# data services

Data Services group provide RESTful interface for accessing business data.

## Tomcat - Local deployment

### Requirements
- Tomcat 7
- settings.xml with the following contents:
```xml
<settings xmlns="http://maven.apache.org/SETTINGS/1.1.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.1.0 http://maven.apache.org/xsd/settings-1.1.0.xsd">

    <servers>
	    <server>
			<id>tomcat</id>
			<username>tomcat</username>
			<password>tomcat</password>
			<url>http://localhost:8080/manager</url>
		</server>
	</servers>
</settings>
```
Then in the data-rest subproject run:
> $ mvn clean install tomcat7:deploy

In order to redeploy just run:
> $ mvn clean install tomcat7:deploy -Dmaven.tomcat.update=true

## Jetty - Local deployment

### Run from maven
> $ mvn clean install jetty:run

This will start an embedded Jetty on localhost:9090.

## API
See [Sharepoint site](http://emirates/DataSer/SitePages/Home.aspx "DataServices Sharepoint site").