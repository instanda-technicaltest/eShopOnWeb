
QATaskWeb - README
eShop - End-to-End Testing
Overview
eShop is a fictitious E-commerce website that offers merchandise to customers. This platform allows users to sign in, browse products, add items to their basket, and place orders. The repository is organized into two main projects:
•	QATaskWeb
•	QATaskAPI
These projects are maintained under the branch Instanda-TechnicalTask.

## Project Goals
The primary objective of this project is to perform end-to-end testing for critical user journeys on the eShop platform. This testing includes:
•	User login
•	Adding items to the basket
•	Placing orders
•	Account management (e.g., viewing orders, managing settings)
•	Additional functionalities such as logout and password recovery

The focus was on the following functionalities:
1.	Login: Users can log in to their accounts and place orders.
2.	Logout: Users must log out after using their account to prevent unauthorized access.
3.	Forgot Password: Users can reset their password if forgotten.
4.	Place Order: Users can successfully place an order after adding items to the basket.
5.	My Account: Users can manage their account settings.
6.	My Orders: Users can view their order history.

## Technologies and Tools
The project leverages the following technologies and tools:
•	Programming Language: C#
•	Testing Framework: Selenium with .NET 6
•	Test Automation Framework: SpecFlow with NUnit
•	Containerization: Docker
•	Version Control: GitHub
•	Command Line: Windows PowerShell

## Key Dependencies
•	Selenium.WebDriver (including ChromeDriver, FirefoxDriver, EdgeDriver)
•	SpecFlow.Plus.LivingDocPlugin
•	NUnit & NUnit3TestAdapter
•	Serilog (for logging)
•	ExtentReports (for reporting)
•	FluentAssertions
•	DotNetSeleniumExtras.WaitHelpers
•	Microsoft.Extensions.Configuration

## Project Structure
1.	Features: Contains feature files describing user stories and test scenarios.
2.	Hooks: Manages setup and teardown actions for tests.
3.	Step Definitions: Maps Gherkin steps to C# code for each feature.
4.	 Page Objects: Create an object-oriented class model for each web page.
5.	AppSettings: Holds configuration values like browser type and application URL
6.	Implicit Usings: Automatically includes common namespaces, reducing code repetition.

## Running the Tests
To run the tests locally:
1.	Clone the repository:
   bash
Copy code
git clone https://github.com/nnennaozioko/eShopOnWeb.git
2.	Clean and build the project in your IDE.
3.	Open Test Explorer (in Visual Studio or Rider).
4.	Select the project e.g., (QATaskWeb), and execute tests by right clicking and then clicking run.



## Test Selection Rationale
Why These Tests?
The user stories and tests were selected based on their relevance to key functionalities critical for the end-user experience in an e-commerce platform. Here’s a breakdown of the reasoning behind each test chosen and why they were prioritized over others:

•	Login and Logout: Security is paramount in any e-commerce system, so ensuring that users can log in and out securely was prioritized.
•	Password Reset: Users frequently forget their passwords, so testing password recovery is essential.
•	Placing Orders: The ability to add items to the basket and successfully place orders is the core functionality of the eShop, making it critical to test thoroughly. 
•	Account Management: Users must be able to manage their personal details and order history, as it directly impacts the user experience.

## Not Tested

Registration: This user story is not needed for this demo purpose.
Admin: This has not been tested because is an internal component as the demo aims to get customer feedback.

## Test Data Considerations:
The test data used for this demo includes a username (email address) and password provided
by Instanda as part of the technical exercise. Additionally, other test data scenarios involve the use of invalid email addresses and passwords, as well as cases where the email address and password 
fields are left empty to test edge cases and error handling.

Reusability and Maintenance
The Page Object Model promotes code reuse, and test data was selected with this in mind:
•	Login Data: Email and password combinations are reused in multiple scenarios, such as login, password recovery, and account management.
•	Product Data: Items selected for orders are reused in other test cases, like viewing orders or adding products to the cart.
Data for Cross-Browser Testing
Given that Selenium is used with multiple drivers (ChromeDriver, Firefox, Edge), the test data was designed to work consistently across different browsers. Data for login, orders, and account management ensured consistent UI behavior and validation across all supported browsers.

## Identified Risks and Mitigations
1.	Risk: Incomplete test coverage of critical user flows (e.g., placing orders, managing account details).
•	Mitigation: Prioritized high-risk functionalities (e.g., login, order placement) and ran comprehensive end-to-end tests on these areas.

2.	Risk: Browser compatibility issues (since users could access the platform from different browsers).
•	Mitigation: Cross-browser testing was implemented using Selenium WebDriver across Chrome, Firefox, and Edge.

3.	Risk: Poor error handling or inconsistent UI behavior.
•	Mitigation: Extensive use of assertions and waits (using DotNetSeleniumExtras.WaitHelpers) to handle dynamic elements and ensure UI stability.

4.	Risk: Failing to capture logs and test reports for debugging purposes.
•	Mitigation: Integrated Serilog for detailed logging and ExtentReports for comprehensive test reports, ensuring that any issues during test runs can be easily traced and debugged.

Conclusion
The eShop Task provided a robust framework to automate key user journeys within an e-commerce platform using C# and Selenium. By focusing on security, user experience, and system reliability, the testing process ensures that the core functionalities of the platform work smoothly, offering a seamless experience for end-users. The setup with Docker, GitHub, and modern testing tools makes the project scalable and easy to maintain for future iterations.


QATaskAPI - README
Overview
QATaskAPI is a test automation suite developed to validate a set of RESTful API calls, ensuring proper functionality across operations like GET, POST, PUT, and DELETE. The suite is built using tools like Swagger, Postman, and Visual Studio, with tests structured using NUnit and SpecFlow for Behavior-Driven Development (BDD).

## Tools & Technologies Used
•	Swagger: API documentation and testing.
•	Postman: Manual API testing and request validation.
•	Visual Studio: IDE for development and debugging.
•	C#: Programming language.
•	Newtonsoft.Json: JSON serialization/deserialization.
•	NUnit: Unit testing framework.
•	NUnit3TestAdapter: For running NUnit tests inside Visual Studio.
•	RestSharp: HTTP client for interacting with APIs.
•	SpecFlow: BDD framework for creating feature files.
•	SpecFlow.Plus.LivingDocPlugin: Generates living documentation from feature files.

## API Requests Implemented
1.	POST Request
Endpoint: /catalog-types
Description: Adds new catalog types.

2.	GET Requests
	Endpoint: /catalog-brands
Fetches all catalog brands.
	Endpoint: /catalog-items
Fetches all catalog items.
    Endpoint: /catalog-items/{id}
Fetches a specific catalog item by ID.
	Endpoint: /catalog-types
Fetches all catalog types.

3.	PUT Request
Endpoint: /catalog-items
Description: Updates existing catalog items.

4.	DELETE Request
Endpoint: /catalog-item/{id}
Description: Deletes a catalog item by ID.

## Setup Instructions
Pre-Requisites
•	Visual Studio 2019 or later.
•	Postman for manual testing.
•	Swagger for API exploration.
•	.NET Core SDK for project execution.
•	SpecFlow.Plus.LivingDocPlugin for generating living documentation.

## Key Dependencies:
              
•	Newtonsoft.Json
•	NUnit
•	NUnit3TestAdapter
•	RestSharp
•	SpecFlow
•	SpecFlow.Plus.LivingDocPlugin

## Running the Tests
To run the tests locally:
1.	Clone the repository:
   bash
Copy code
git clone https://github.com/nnennaozioko/eShopOnWeb.git
2.	Clean and build the project in your IDE.
3.	Open Test Explorer (in Visual Studio or Rider).
4.	Select the project QATaskAPI and execute tests by right clicking and then clicking run.

## Testing with Postman
1.	Import API Collection:
Use Swagger documentation to import requests into Postman.
2.	Test Endpoints:
Use HTTP methods (GET, POST, PUT, DELETE) to test endpoints like /catalog-items, /catalog-brands, etc.
3.	Environment Variables:
Set up environment variables in Postman for API URLs or authentication tokens.

## Logging and Reporting
•	NUnit: Logs test case results and provides detailed output.
•	SpecFlow.Plus.LivingDocPlugin: Generates interactive reports from feature files, serving as living documentation.

## Endpoints Covered
HTTP Method	Endpoint	Description
POST	/authenticate	Authenticate a user
GET	/catalog-brands	Fetch all catalog brands
GET	/catalog-items	Fetch all catalog items
GET	/catalog-items/{id}	Fetch a specific catalog item by ID
POST	/catalog-items	Add new catalog items
PUT	/catalog-items	Update existing catalog items
DELETE	/catalog-item/{id}	Delete a catalog item
GET	/catalog-types	Fetch all catalog types

## Why Test:
Testing ensures the reliability and performance of the APIs by:
•	Achieving 100% coverage of critical endpoints.
•	Providing quick regression testing.
•	Ensuring minimal false positives.
•	Monitoring response times.

##Test Selection Rationale & Risk Mitigation

1.	Test Selection: Prioritized tests for critical API functionality like creating and fetching catalog items.
2.	Test Data Considerations: Realistic and edge-case data was used to simulate typical scenarios, ensuring coverage for valid and invalid data inputs.
3.	Risk Mitigation:
•	API Downtime: Implemented retry logic.
•	Data Integrity: Used mock data for consistent environments.
•	Security: Authentication checks were added for protected endpoints.

## PUT Request:
The PUT call made did not pass due to an Internal Server Error.
    "StatusCode": 500,
    "Message": "Object reference not set to an instance of an object."

Conclusion
The QATaskAPI suite provides a robust framework to automate API testing using C# and SpecFlow, focusing on key functionalities like catalog management. By leveraging modern tools and technologies, this project ensures that the API is reliable and scalable for future updates

