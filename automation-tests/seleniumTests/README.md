# Selenium Test Automation Project

## Project Overview
This project contains automated UI tests for the eShopOnWeb project using Selenium WebDriver.  

## Prerequisites
- Java JDK 11+
- Maven 3+
- Eclipse IDE (optional, but recommended)
- Git

## Setup Instructions
1. Clone the repository: git clone https://github.com/instanda-technicaltest/eShopOnWeb.git


## Test Cases
- **LoginPage.java**: Tests user login functionality.
- **ProductPage.java**: Tests adding items to the basket.
- **CheckoutPage.java**: Tests the checkout process.
- **EshopEndToEndTest.java**: Runs the full end-to-end test flow.

## How to Run Tests
1. Import the project into Eclipse IDE.
2. Run the `EshopEndToEndTest.java` file as a TestNG test.
3. Verify test results in the test report generated in the `/test-output` folder.

## Build the project using Maven.
Run the eShopOnWeb application by navigating to: http://localhost:8000/

I have used the Page Object Model (POM) pattern in the scripts, as it offers several advantages.
###Maintainability: 
By using POM, I have ensured that any changes in the UI, such as locator changes, are isolated within their respective page classes. This makes it easier to update and maintain the test suite.
###Reusability: 
I can reuse the methods defined in the page classes across various test cases, reducing duplication and making the test suite more efficient.
###Separation of Concerns: 
Each page class focuses solely on the functionality of its respective web page, which results in clearer and more modular code.

###Challenges Faced:
I specifically developed these scripts for Safari browser, as I encountered multiple issues with Google Chrome, particularly with identifying locators. After extensive trial and error, I resolved locator issues by focusing on Safari's unique handling of web elements. While this required additional effort, I chose Safari because it was the most reliable option for this project.

###Docker Configuration:
I made some changes to the docker-compose.yml and docker-compose.override.yml files to ensure the proper execution of the tests in my environment. Even though the original project documentation suggests using port 5100, I ran the eShopOnWeb application at: http://localhost:8000/
This setup allowed me to test the eShopOnWeb project locally without issues.

###How to Run Tests:
Import the project into Eclipse IDE.
Run the EshopEndToEndTest.java file as a TestNG test.
Verify test results in the test report generated in the /test-output folder.

###Test Automation Flow:
The test flow follows a typical end-to-end scenario:

Login: The script logs into the application by navigating to the login page and entering the required credentials.
Add Items to Basket: The script simulates selecting a product and adding it to the basket.
Checkout: The script performs the checkout process and verifies the order has been placed successfully.

###Why I Chose Safari:
Due to ongoing issues with Google Chrome in identifying locators, I decided to run the tests using Safari. Safari provided better compatibility with the test scripts, and I was able to resolve locator-related challenges by switching browsers. This required extra effort in adapting to Safari's element-handling quirks, but it resulted in a more stable testing environment.

##API Testing Using REST Assured (Future Work):
Once I complete the UI tests, I plan to extend the test suite by using REST Assured to verify the application's API endpoints. This will allow me to directly interact with the APIs, ensuring they function as expected and return the correct responses under different conditions.