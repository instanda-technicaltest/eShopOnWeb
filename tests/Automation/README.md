# In this project I have followed Page object model, using Cucumber, Junit framework with Java language

# Smoke Test for eShopOnWeb

This is an automated smoke test scenario for the eShopOnWeb application. It verifies the basic functionality of user login and navigation to different pages.

## Scenario Overview

### User able to login successfully and go to different pages

- All Test Steps are present under
  1. ( tests\Automation\demo\src\main\resources\features\SmokeTest.feature )
  2. ( tests\Automation\demo\src\main\resources\features\UserTest.feature )

## Prerequisites

- Java should be installed in the machine.
- Access to the eShopOnWeb application.
- Application should be up and able to access through browser
- Valid user credentials (email and password).
- Proper configuration of the testing environment.

## Running the Test Cases

1. Clone the project repository to your local machine.

   ```bash
   git clone https://github.com/yourusername/eshop-onweb-automation.git
   ```

2. Run the eShop application
   You can configure Visual Studio to start multiple projects, or just go to the PublicApi folder in a terminal window and run dotnet run from there. After that from the Web folder you should run dotnet run --launch-profile Web. Now you should be able to browse to https://localhost:5001/. Note that if you use this approach, you'll need to stop the application manually in order to build the solution (otherwise you'll get file locking errors).

3. Once eShop application is started
   - Expand to test directory
   - expand src/test folder under tests\Automation\demo\src folder
   - open CucumberRun.java class
   - Click on Run button to execute
