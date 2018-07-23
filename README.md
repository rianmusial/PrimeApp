# PrimeApp

## Running this application
### As a web application

Due to issues with Tomcat, this application is not yet ready for deployment to a server.  Should you wish to to attempt to debug the issue yourself, please follow the following steps:

1. Clone this repository to your computer
2. Import the project into Eclipse as a Maven project
3. Set up a Tomcat 7 Server in your Eclipse.  Tomcat 7 can be downloaded from [here](https://tomcat.apache.org/download-70.cgi).
4. Add the project to Tomcat and deploy.
..* The hello world will be located at 'http://localhost:8080/PrimeApp/index.jsp'.
..* The controller for the page which needs to be set up to connect with a JSP to act as a web form can be accessed at 'http://localhost:8080/PrimeApp/ctrl/primes.do'.

### As a console application
1. Clone this repository to your computer
2. Import the project into Eclipse as a Maven project
3. Run com.rianmusial.PrimeApp.PrimeListGenerator.java as a java application