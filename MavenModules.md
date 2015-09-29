# Introduction #

http://sannotations.sourceforge.net/examples.html


# Details #

mvn archetype:create -DarchetypeGroupId=org.apache.maven.archetypes -DgroupId=org.jtpd -DartifactId=jtpd -Dpackaging=pom

mvn archetype:create -DarchetypeGroupId=org.apache.maven.archetypes -DgroupId=org.jtpd -DartifactId=jtpd.model -Dpackaging=jar

mvn archetype:create -DarchetypeGroupId=org.apache.maven.archetypes -DgroupId=org.jtpd -DartifactId=jtpd.web -Dpackaging=war


mvn archetype:create -DarchetypeGroupId=org.apache.maven.archetypes -DgroupId=org.jtpd -DartifactId=jtpd.conf -Dpackaging=jar

|Module|Desc|Packaging|Depends|
|:-----|:---|:--------|:------|
|jtpd.admin|Adminn Module|War      |jtpd.core|
|jtpd.core|Models + DAOs + Services + Business Logic|jar      |       |
|jtpd.web|Web Framework (includes jsp, controllers)|war      |jtpd.core|