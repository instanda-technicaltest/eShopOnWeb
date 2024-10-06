# eShopOnWeb Automation Test Challenge

## Overview

For this challenge, automated tests were implemented for the eShopOnWeb application using a Behavior-Driven Development (BDD) approach with Cucumber for both UI and API testing.

## Technologies Used
- **Programming Language**: `Java`
- **Build Tool**: `Maven`
- **BDD Framework**: `Cucumber` 
- **Test Runner**: `TestNG` integrated with Cucumber BDD
- **UI Automation**: `Selenium WebDriver` 
- **API Automation**: `REST Assured` 
- **Test Reporting**: `Extent Reports` 

## Test Approach

- **API Testing**:
  - Covered authentication API scenarios, including both successful and failed login attempts.
  - Validated the retrieval of catalog brands using the CatalogBrand API.

- **UI Testing**:
  - Tested the login functionality with valid and invalid credentials.
  - Verified the full user flow of adding products to the basket and placing an order.

## Test Execution

To execute the tests, run them directly from the Test Runner files located in `src/test/java/com/eShopOnWeb/runners` using an IDE.

There are three runner files:
  - **TestRunnerUi**: For UI tests.
  - **TestRunnerApi**: For API tests.
  - **TestRunnerUiApi**: For running both API and UI tests.

### Running Tests from IDE

1. Open the project in the IDE (e.g., IntelliJ IDEA, Eclipse).
2. Navigate to the `src/test/java/com/eShopOnWeb/runners` package.
3. Locate the runner file to execute:
    - **TestRunnerUi**: For UI tests.
    - **TestRunnerApi**: For API tests.
    - **TestRunnerUiApi**: For running both API and UI tests.
4. Right-click on the runner file and select **Run** or click the **Run** button in the IDE.
5. The selected tests will be executed.

### Running Tests via Maven

Alternatively, run the tests using Maven by navigating to the project directory: `tests/ui-api-automationFramework` and executing the following commands:

- **Running UI Tests**:
  - mvn test -Dtest=TestRunnerUi

- **Running API Tests**:
  - mvn test -Dtest=TestRunnerApi

- **Running Both API and UI Tests Together**:
  - mvn test -Dtest=TestRunnerUiApi

## Test Report
- Once the tests are executed, a report will be generated automatically. 
- To view the test reports: Navigate to the `tests/ui-api-automationFramework/test-output/` folder.