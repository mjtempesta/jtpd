# Introduction #

**Liquibase Kullanımı**GenericPK ve GenericModel
**System Models**


# Details #
## Liquibase ##

Liquibase veritabanı değişikliklerini versiyonlamak için kullanılan bir araçtır. Maven, Command Line, Ant pluginleri sürekli veritabanı güncellemelerinin takibini kolaylaştırmaktadır.

Maven Modülleri içerisindeki **jtpd.model** modülü içerisindeki pom.xml içerisinde ilgili liquibase maven konfigurasyonu yapılmıştır.

jtpd.model/src/main/resources içerisindeki liquibase-changelog.xml içerisinde veritabanı değişikliklerini sql ve xml elementleri olarak tanımlanabilir.

Kullanımı hakkından bilgi için : http://www.liquibase.org/manual/home

örnek tanımlama :

```
<databaseChangeLog
  xmlns="http://www.liquibase.org/xml/ns/dbchangelog/1.7"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://www.liquibase.org/xml/ns/dbchangelog/1.7
         http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-1.7.xsd">
    <changeSet id="1" author="bob">
        <comment>A sample change log</comment>
        <createTable/>
    </changeSet>
    <changeSet id="2" author="bob" runAlways="true">
        <alterTable/>
    </changeSet>
    <changeSet id="1" author="alice" failOnError="false" dbms="oracle">
        <alterTable/>
    </changeSet>
</databaseChangeLog>
```

Maven üzerinden veritabanı güncellemesi için :

**mvn liquibase:update**

komutu kullanılmalıdır.


Local makinada kullanımı için pom.xml'deki

```
			<plugin>
				<groupId>org.liquibase</groupId>
				<artifactId>liquibase-plugin</artifactId>
				<version>1.9.3.0</version>
				<configuration>
					<changeLogFile>src/main/resources/liquibase-changelog.xml</changeLogFile>
					<driver>com.mysql.jdbc.Driver</driver>
					<url>jdbc:mysql://db.jtpd.com:3306/JTPD</url>
					<username>root</username>
					<password>****</password>
				</configuration>
				<executions>
					<execution>
						<phase>process-resources</phase>
						<goals>
							<goal>update</goal>
						</goals>
					</execution>
				</executions>
				<dependencies>
					<dependency>
						<groupId>mysql</groupId>
						<artifactId>mysql-connector-java</artifactId>
						<version>5.1.6</version>
					</dependency>
				</dependencies>
			</plugin>
```

tanımlamaların değiştirilmesi gerekmektedir.

## JTPD MODEL TASARIMLARI ##

### User ve Story Modelleri ###
|USER|
|:---|
|Column Name|Type|
|ID  |Integer|PK  |
|USERNAME|VARCHAR(45)|    |
|PASSWORD|VARCHAR(45)|    |
|FIRSTNAME|VARCHAR(45)|    |
|LASTNAME|VARCHAR(45)|    |
|EMAIL|VARCHAR(45)|    |
|PHONE|VARCHAR(45)|    |

|USER\_EXTRA|
|:----------|
|Column Name|Type       |           |
|CREATEDTIME|DATETIME   |           |
|APPROVALMESSAGE|VARCHAR(255)|           |
|APPROVEDTIME|DATETIME   |           |
|APPROVEDBY |INT        |FK(USER-->ID)|

|STORY|
|:----|
|Column Name|Type |
|ID   |Integer|PK   |
|AUTHOR\_ID|INT  |FK(USER-->ID)|
|TITLE|VARCHAR(150)|     |
|BODY |MEDIUMTEXT|     |
|TYPE |ENUM('ARTICLE','PRESENTATION','NEWS','ADVERTISE','ANNOUNCEMENT')|     |
|CREATEDTIME|DATETIME|     |
|STATUS|ENUM('ONWAITING','ONCHECKING','PUBLISHED','REJECTED')|DEFAULT('ONWAITING')|
|LOOKEDBY|INT  |FK(USER-->ID)|




Add your content here.  Format your content with:
  * Text in **bold** or _italic_
  * Headings, paragraphs, and lists
  * Automatic links to other wiki pages