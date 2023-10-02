# eShopAutomationTask
Automation Script for Instanda

# eShop Test Plan!

# Purpose
To verify that users can successfully sign in, add items to their basket, and place orders without issues.

# Test Objectives and Scope
## In-Scope:
  * User registration and sign-in.
  * Product Details page and Search feature.
  * Adding/Removing items to the basket.
  * Placing orders.
  * Order History
  * Managing Accounts
  * Sign out
## Out of Scope:
  * Payment processing

# Automation Testing Approach
I have chosen to employ the Selenium framework in combination with the Cucumber BDD (Behavior Driven Development) Framework for automating tests on a web-based application. The chosen programming language for this task is Java. This framework has been selected due to its utilization of feature files that contain comprehensive automation steps written in plain English, making them easily comprehensible to non-technical team members.

Used Existing Libraries such as Lombok for getter, Setters, Log4j statement injection, Owner API for Configuration Loading and Mapping, Google Guice for Dependency Injection, AssertJ library for Assertion and Extent Report-Grasshoper Cucumber 6 Adapter for Reporting

# Automation Test Cases and Steps
Please consult the "src/test/resources/feature" directory, which houses feature files containing comprehensive scenarios and detailed steps. The following functionalities have been chosen for automation:
* 		RegistrationLogin.feature - refer file for automation steps
* 		ProductSearch.feature - refer file for automation steps
* 		AddtoBasket.feature - refer file for automation steps
I've considered these functionalities for automation because they allow me to achieve the following:
* Generate dynamic test data for the registration process, where a new random email address is provided as data for each run.
* Incorporate multiple sets of test data to execute the same scenario using a scenario outline.
* Handle dynamic xpaths effectively.
* Implement scripted loops and utilize collections concepts as needed.

# Automation Script
https://github.com/Soundarya-Sundaram/eShopAutomationTask.git

# Run the Test
For Triggering the execution use below command:
* mvn clean install -Dbrowser=chrome -DexecType=local


Can use tags in cucumber such as @Smoke @Regression to run those scenarios
* Example: mvn clean isntall -Pchrome-grid -Dcucumber.filter.tags="@Smoke"






