# eShopAutomation
eShop Test automation from Mehul Bhimani


eShop Test Automation
Overview
This document contains automated test scripts for the eShop application using Cypress-Jacascript. The tests cover various scenarios including user login, adding items to the cart, and completing the checkout process.


Prerequisites
Before running the Cypress tests, ensure you have the following installed:
- Node.js (version 14 or higher)
- npm (Node package manager)


Installation
1. Clone the repository:
git clone https://github.com/MB190/eShopAutomation-url
cd <your-repo-folder>

2. Install the dependencies:
npm install 


3. Install Cypress (if not already included in the package.json):
it’s in package.json already so no need to run. 
npm install cypress --save-dev


Running Cypress Tests
Open Cypress GUI


To open the Cypress Test Runner, use the following command:
npx cypress open


This command will launch the Cypress GUI where you can select and run your tests interactively.
Run Cypress Tests in Headless Mode

To run the tests in headless mode (without the GUI), use the following command:
npx cypress run

This will execute all tests in the `cypress/integration` folder.
Running Specific Tests


If you want to run a specific test file, you can do so by providing the path to the file:
npx cypress run --spec "cypress/integration/examples/eshopTest.js"
Writing Tests


The test files are located in the `cypress/integration/examples` directory. You can create new test files with a `.spec.js` extension and follow the Cypress syntax to write your tests.
Fixture Data


You can store test data in JSON format within the `cypress/fixtures` directory. The default fixture file is `example.json`, which contains test data for login.
Example Fixture Data
json
{
  "email": "demouser@microsoft.com",
  "password": "Pass@word1"
}



![image](https://github.com/user-attachments/assets/184697d4-9055-48e7-9385-faffc90b6c37)
