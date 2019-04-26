# OliverSpringEngine

Welcome! I always dream of a Spring Boot Engine to kick-start projects since they really share a lot of commons. 

# Quick configurations: 
db configs in file - src/main/resources/application-dev.properties.bak

* 1rename application-dev.properties.bak 2 application-dev.properties

* 2Then change the following: 
spring.datasource.username=YOUR_DB_INSTANCE_USERNAME
spring.datasource.password=YOUR_DB_INSTANCE_PASSWORD
oliver.jpa.properties.hibernate.hostname=YOUR_DB_INSTANCE_HOSTNAME

* Then Run Your Tests right away to make sure everything adapts comfortably. 
![Run All Test](https://github.com/oliverwreath/OpenSourceOliverSpringEngine/blob/master/src/main/resources/static/ReadmePictures/Run_All_Tests.png?raw=true)

* Then You should see something like this. 
![All Test Passed](https://github.com/oliverwreath/OpenSourceOliverSpringEngine/blob/master/src/main/resources/static/ReadmePictures/Tests_All_Passed.png?raw=true)

