# java-ee-booking-system
Java EE web application (MVC, JDBC, MySQL): A booking system with seperate user roles (Admin, Teacher, Student) using a common login. Featuring a shopping cart functionality and other CRUD operations.

 - Changed laptop; migrated from Netbeans 8.2 to Netbeans 13

 - Migrated from JDK 8 to JDK 17

 - Had some difficulty getting the site to work with the newer MySQL Driver from Netbeans, so this commit includes the old MySQL driver from Netbeans 8.2. This driver is included in the new 'external-libs' folder to prevent this type of issue recurring

How to use this application:

Create database using file ‘trainingCentre.sql’

Copy ‘Tcweb.war’, located at:

..\TCweb\TCweb\dist

to the web server of your choice

Once the site is running on the server, you can login using the following credentials:

Admin

email:admin@trainingcentre.com

password:
P12345678#

Teacher

email:
terrence@trainingcentre.com
password:
P12345678#

Student
email:
tobytertiary@university.com

password:
P123456#

Dependencies to run the project in IDE:

JUnit 4.12

jsp-api-2.1-6.0.2.jar

jstl-1.2.jar

servletapi-2.3.jar

MySQL JDBC Driver

joda-time-2.10.13.jar

JDK 17


CSS/JS dependencies (all hotlinked from CDNs):

Bootstrap 4.1.3

jQuery 3.6

JS 1.14.3

jQuery Datatables 1.10.20
