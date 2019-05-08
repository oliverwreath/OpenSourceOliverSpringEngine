# Oliver Spring Engine -  KickStart your project in 10 mins
* Welcome! 
* Why the project? No Java Developer love building same authentication over and over again. 
* Open Source Oliver Spring Engine to the rescue! 
* EASY to use - 10 mins and you have user login in place. With blazingly fast h2 memory db for fast PoC (Proof of Concept). 
* Then optionally switch to profile prod with MySQL, mvn clean, mvn test, mvn package and deploy, Viola! 
* Lets you focus on the money-making business logic. 
* Rich optional features. Including User entity, RESTful APIs, beautiful CRUD ag-grid and optional PayPal checkout button. 
* Like the Spring Boot slogan - Build any thing! But this time - build it fast and easy! 

# Preview
* Beautiful RESTful ag-grid Integration Seemlessly - Masterial design! High performance! With subtle animation! 
![Data_Grid](https://raw.githubusercontent.com/oliverwreath/OpenSourceOliverSpringEngine/7c55fa802c8186e04261ec7274273e3c4bd613dd/src/main/resources/static/data-grid.png)
* Build anything! - Based On your favourite& popular Spring Boot!
![Based On the Versatile Spring Boot!](https://raw.githubusercontent.com/oliverwreath/OpenSourceOliverSpringEngine/8eac34c187e74a1fd31bbc5f5238e41e5cb2aa92/src/main/resources/static/ReadmePictures/BuildAnyThing.JPG)

# Open Source License 
* GNU Affero General Public License v3.0

# Versions
* Spring Boot v2.1.2.RELEASE, Spring v5.1.4.RELEASE
* jdk1.8.0_201
* Hibernate Core {5.3.7.Final}
* org.hibernate.dialect.MySQL57Dialect

# Major Features 
* BootStrap Responsive Design. 
* RESTful APIs. Optional Read: ag-Grid 101 — Material Design — RESTful https://link.medium.com/oS0E5fvXbW
* Highest quality TDD tests. 
* Efficient Cache. 
* Accept payments with PayPal button. 
* FormLogin and 3rd party Log in with Google/ Github. 
* Reports and Anylytics. 
* Crazy handy data grid interaction. 
* i18n Localization. 
* Scheduling tasks. Optional Read: https://link.medium.com/5KDrJgC14V
* utf8mb4 internationalization! Optional Read: The utf8mb4 magic https://link.medium.com/FZxI5ZoXbW

# Minor Features 
* Login Audit + Edit Audit.  
* Send SMTP gmail. 
* Your favourite LomBok Integration! 
* @Enumerated(EnumType.STRING)
* Cron to cleanup excessive audit data 3 a.m. everyday. 

# Kick Start with Quick Configurations 
* 1 rename src/main/resources/*.properties.bak TO *.properties

* 2 It is EASY to use - simply run the dev environment - it SIMPLY WORKS! I put in an embedded h2 and tested, so you don't have to! 
![Run All Test](https://raw.githubusercontent.com/oliverwreath/OpenSourceOliverSpringEngine/08c13116f7d63fdf1fd591a8e80cef575ac051e4/src/main/resources/static/ReadmePictures/KickStart.JPG)
Just tell Spring to use the JVM options (-Dspring.profiles.active=dev) as shown in the picture. 

* 3 [Optional]for test environment - change the following: 
spring.datasource.username=YOUR_DB_INSTANCE_USERNAME
spring.datasource.password=YOUR_DB_INSTANCE_PASSWORD
oliver.jpa.properties.hibernate.hostname=YOUR_DB_INSTANCE_HOSTNAME

* Optional read of Switching Spring Boot environments Made Easy (dev, prod) - https://link.medium.com/f7LppJ7SbW

# Quick Test 
* 1 Then Run Your Tests right away to make sure everything adapts comfortably. 
![Run All Test](https://github.com/oliverwreath/OpenSourceOliverSpringEngine/blob/master/src/main/resources/static/ReadmePictures/Run_All_Tests.png?raw=true)

* 2 Then You should see something like this. 
![All Test Passed](https://github.com/oliverwreath/OpenSourceOliverSpringEngine/blob/master/src/main/resources/static/ReadmePictures/Tests_All_Passed.png?raw=true)

# Spin it up! 
![Quick_Start](https://github.com/oliverwreath/OpenSourceOliverSpringEngine/blob/master/src/main/resources/static/ReadmePictures/Quick_Start.png?raw=true)

# Features In a Glance
* i18n_Localization
![i18n_Localization](https://github.com/oliverwreath/OpenSourceOliverSpringEngine/blob/master/src/main/resources/static/ReadmePictures/i18n.JPG?raw=true
)
* Audit
![Audit](https://github.com/oliverwreath/OpenSourceOliverSpringEngine/blob/master/src/main/resources/static/ReadmePictures/Audit.JPG?raw=true)
