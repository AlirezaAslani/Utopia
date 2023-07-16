
# Introduction
The project is a Maven-based application following a layering model, which consists of two main layers: the Entity Layer and the Application Layer.

## Layers Overview
1. Entity Layer
In the Entity Layer, the focus is on implementing entities and a general facade. This layer primarily deals with data and business logic related to the core entities of the application.

2. Application Layer
The Application Layer is responsible for implementing various components that interact with the outside world and handle different aspects of the application.

#### Components in the Application Layer
- ##### REST/SOAP API
  The Application Layer exposes REST and SOAP APIs, allowing external systems to communicate with the application and perform CRUD (Create, Read, Update, Delete) operations.

- ##### JSF Presentation
  The Application Layer utilizes <abbr title="JavaServer Faces">JSF</abbr> along with PrimeFaces, a popular UI component library, to create a modern and interactive web-based interface for the application.

- ##### SOAP Web Service Integration
  This layer also handles the integration with other SOAP web services to fetch data from external applications.


- ##### Three-Layer vs. Single-Layer Approach
  While the project is designed based on a three-layer architecture (Entity, Application, and WAR Layer), it also caters to users who prefer a single-layer approach. The WAR Layer encompasses the main part of the application and can be used without any modifications or additional effort, making it convenient for those who prefer a simpler architecture.

# Features
The application offers the following features:

#### Offline Captcha REST API
The application provides an offline CAPTCHA REST API that enables users to validate and secure their interactions.
It internally uses the "SimpleCaptcha" library to generate the captcha image ,spring-boot for rest services and to provide the captcha ID + captcha expiry mechanisum it uses "expiringmap" from "net.jodah" package. library.

The SimpleCaptcha library is installed as alocal maven repo in the project.

#### Filters
###### 1. Cross Filter
Cross-origin resource sharing (CORS) is a mechanism that allows JavaScript on a web page to make AJAX requests to another domain, different from the domain from where it originated. By default, such web requests are forbidden in browsers, and they will result in same-origin security policy errors. Using the Java CORS filter, you may allow the webpage to make requests from other domains as well (known as cross-domain requests).

###### 2. Security Filter
There are lots of ways to implement security with user authentication and authorization in the RESTful web services.
The main types of security we are going to talk about are the following:

API key
Basic authentication
Oauth2
Oauth2 + JWT
In order to make our discussion more specific, let’s assume we build a simple microservice backend application and the client users make requests to our backend services throw the REST-APIs. We will explain the use cases on how to secure the APIs with the context above.

API Key
It is a very easiest and simplest way to apply security and protect the API.

When to use: It fits designing APIs for 3rd parties services integration and limited access, not public access purpose. For instance, your company provides SMS gateway as web services and other companies would like to use your services.

How to use: validate the api-key either request param or Request Header.

How it works: Create Servlet Filter Security and validation either looking at the request param api_key and X-API-Key as HEADER and whitelist IPs address (optional). So every user makes a request, the Filter Security will check and validate first before it continues into the API controller part.

## Documentation
The doc directory in the repository contains important documentation and additional information that might be helpful:

Sample JSON Objects

The doc directory includes sample JSON objects for REST and SOAP API calls, making it easier for developers to understand the required data format for different endpoints.
Usage Guidelines

Detailed instructions on how to use the offline CAPTCHA REST API, cross filter, and security filter are available in the documentation. It provides insights into each component's functionality and how they can be effectively integrated into other projects.


## How to run? ##
Samples are tested on WebLogic Server 12cR2 (12.2.1.4) on Eclipse.

#### Important things to note :
#### 1. install JSF on weblogic:
Make sure install JSF 2 on weblogic server

Prerequisites:
  You would need a weblogic instance installed and have a domain created. If you do not have one, you can download the server from http://www.oracle.com/technetwork/middleware/weblogic/downloads/wls-main-097127.html. Then you would need to create a domain, this can be done from Configuration Wizard, where you would choose to create a new domain.

  I am using eclipse for this tutorial, with a simple java project (the reason why i choose not to use the web app project type is that it will give you a better sence of what the application is made of).
My weblogic is instaled in the following directory, this is the directory i will be refering throughout the tutorial:

	C:\Oracle\Middleware\Oracle_Home

The directory of the created domain in weblogic i have as:

	C:\Oracle\Middleware\Oracle_Home\user_projects\domains\technokon

Install JSF 2 libraries in Weblogic
  First thing we need to do is install the jsf2 library on weblogic (this isn't something you would have to do on JBoss or Glassfish servers for example).
  To do this you need to start-up your weblogic domain, so let's do that and go through the process of installing the library. To start the domain i will launch this file:


	startWebLogic.cmd

which is right in the domain directory. You would know that the server is running, when you see something like the following in the cmd window log, the the server is using:

	<Aug 7, 2014 8:05:22 PM EDT> <Notice> <WebLogicServer> <BEA-000331> <Started the
	 WebLogic Server Administration Server "AdminServer" for domain "technokon" running in development mode.>
	<Aug 7, 2014 8:05:22 PM EDT> <Notice> <WebLogicServer> <BEA-000360> <The server started in RUNNING mode.>
	<Aug 7, 2014 8:05:22 PM EDT> <Notice> <WebLogicServer> <BEA-000365> <Server state changed to RUNNING.>

Now navigate your browser to:

http://localhost:7001/console

and login to console. Note that i am using the default port 7001, that was configured during the domain creation process.

  Now, on the left menu choose:

Deployments, then click on the Install button. Now, navigate to the directory where the jfs-2.0.war is, which i have at:


	C:\Oracle\Middleware\Oracle_Home\wlserver\common\deployable-libraries

You will see a file there called:

	jsf-2.0.war

  Pick this file and click on the  Next button. Then on the next page pick Install this deployment as a library.
And click next again on the following 2 pages and Finish after, this will take you to the configuration page.
Now on this page, set the Restart may be required. Deployment Order: to 99 (default is 100), so it is deployed before other auto-deployable apps. adn click Save .

#### 2.Maven - External Dependencies
Need to add captcha lib to your Maven by command:

    mvn install:install-file -Dfile=simplecaptcha-1.2.1.jar -DgroupId=nl.captcha -DartifactId=kaptcha -Dversion=1.2.1 -Dpackaging=jar

#### 3.Configure a JDBC Data Source in Oracle WebLogic Server
To configure a JDBC data source using the WebLogic Server Administration Console:

1. After the Administration Server is up and running, access the WebLogic Server Administration Console. Open a web browser and enter the Console's URL.

	  http://localhost:7001/console
	  
Optionally, specify the host name and port of your domain's Administration Server in place of 	localhost:7001 .

2. On the Welcome screen, enter the domain administrator credentials, and then click Login.

3. In the Change Center, click **Lock & Edit**.
Note: To create a data source, or change anything in the domain configuration, you must first lock the domain configuration (to prevent other accounts from making changes during your edit session) and enable an edit session.

4. In the left panel, under Domain Structure, expand **Services** and select **Data Sources**.

5. On the **Summary of JDBC Data Sources** page, click **New** and select Generic Data Source.

6. On the **JDBC Data Source Properties** page:
Enter the **Name** of the data source as **myNewDS**.
Enter the **JNDI Name **of the data source as **myNewDS**.
Note: There is no requirement for the data source and JNDI names to be the same.
Choose **Oracle** as the **Database Type** and click **Next**.

7. For **Database Driver**, select ***Oracle's Driver (Thin)** for **Instance connections; Versions:Any** ,and then click Next.

8. On the **Transaction Options** page, retain all default values and click **Next**.

9. On the **Connection Properties** page:

| Field  | Value |
| ------------- | ------------- |
| **Database Name**  |  XE (your database name may be different)  |
| **Host Name** |  localhost (use the host name where you have configured your Administration Server)  |
| **Port** | 1521 (enter your database port) |
| **Database User Name** |  DBTESTER (this is the user defined in the SQL script)|
| **Password** | Database user's password (the password defined in the SQL script for the above user) |
| **Confirm Password** |Database user's password |

10. Click **Next**

11. On the **Test Database Connection** page, review the connection parameters and click **Test Configuration**.

12. If the message Connection test succeeded displays, click **Next**.

If the test is unsuccessful, click **Back** and review the entries made for the data source, correct any errors and then retry the test. If there are no errors in the entries and the test still fails, make sure your database is running.

13. On the **Select Targets** page, select **AdminServer** as the data source target.

14. Click **Finish** to save the JDBC data source configuration and deploy the data source to the AdminServer (target).

15. In the Change Center, click **Activate Changes**.
- The Console displays the message: All changes have been activated. No restarts are necessary
- In the Summary of JDBC Data Sources page, the new data source, **myNewDS**, is now listed in the Sources table.
  
16. To modify the configuration of the new data source, select the data source name, myNewDS.
  
17. On the **Settings for myNewDS** page, select **Configuration** and then select **Connection Pool**, and scroll down to find the capacity fields and change the existing values to:
  
| Field  | Value |
| ------------- | ------------- |
|**Initial Capacity**|  2 |
|**Maximum Capacity**|  20 |
|**Minimum capacity**|  2 |

18. Click Save.
19. In the Domain Structure tree, expand **Environment** and select **Servers**.
20. In the Servers table, select **AdminServer(admin)**.
21. To validate the configuration and target of the data source, view **myNewDS** in the JNDI Tree. To view the JNDI Tree, on the **Settings for AdminServer** page:

- Select Configuration and then select General.
- Select View JNDI Tree.

The JNDI tree opens in a new window and myNewDS appears in the JNDI tree. Other entries in the JNDI tree of your server may vary depending on the available resources in your server.

## Getting Started
To set up the project and run it on your local machine, follow these steps:

Clone the repository: git clone https://github.com/your_username/your_project.git
Navigate to the project directory: cd your_project
Build the project using Maven: mvn clean install
Deploy the WAR Layer to your application server of choice (e.g., Weblogic, Glassfish).


## Version Compatibility

| Java  | Maven | Weblogic|
| ------------- | ------------- | ------------- |
| 1.8.0_321  | 3.8.6  |12cR2 (12.2.1.4)|

## Contributing
With your help we can improve this set of samples, learn from each other and grow the community full of passionate people who care about the technology, innovation and code quality. Every contribution matters!

#### Some coding principles
- When creating new source file do not put (or copy) any license header, as we use top-level license (MIT) for each and every file in this repository.

- Please follow Weblogic Community code formatting profile as defined in the [Oracle  Doc](https://www.oracle.com/middleware/technologies/weblogic-server-downloads.html)  repository. The details are explained there, as well as configurations for Eclipse, IntelliJ and NetBeans.

There is just a bunch of things you should keep in mind before sending a pull request, so we can easily get all the new things incorporated into the master branch.
## Contact
For any inquiries or support, please contact alireza.a.eng@gmail.com.

This is just a template, so make sure to customize it with the appropriate details specific to your project.




