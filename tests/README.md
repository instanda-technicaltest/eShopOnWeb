# BDD Framework of Cucumber, Selenium and TestNG

This is a sample application to demonstrate how to setup and run Selenium, Cucumber tests with TestNG in Web application.

This framework consists of:
````
Cucumber – 7.15.0
Java 17
TestNG – 7.9.0
Maven – 3.9.6
Selenium - 4.16.1
Extent Report - 5.0.4
Extent Report Adapter - 1.14.0
Maven Compiler Plugin - 3.12.1
Maven Surefire Plugin - 3.2.3
````

To run the tests through options.addArguments("--headless"); command line, use the command
````
run testng.xml file
````

The tests are running in headless mode. To run the tests in normal mode, comment or delete this command
````
 options.addArguments("--headless");
````
Reports are generating in folder :
````
 test-output/Repoprt/cucumber.html
````

To run the tests in GitHub pipeline, we need to run the tests in the headless mode.

The TestNG Reports are generated in target folder. Go to surefire-reports and will see a list of reports.