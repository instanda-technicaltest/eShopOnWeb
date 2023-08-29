# Please write some Automated Tests!

## pre-requisites
I would suggest using
- IntelliJ IDE and extract eShopTest folder to a maven project
- java 11
- maven
- add Cucumber with java plugin
- add Gherkin plugin
- add cucumber java, cucumber junit, cucumber core with same version
- add selenium.java jar
- add rest-assured jar

## UI Automated Tests

### purpose
The purpose of tests is to perform E2E journey for eShopOnWeb website

### running UI tests using IntelliJ IDE
- find runner via path eShopTest/src/main/java/org/example/runners/RunnerUI.java
- select features as "src/main/resources/features/eShopUI.feature"
- select glue as {"org.example.UI"}
- click on green play button to run tests

### tests are
- E2E journey for eShop website
- Try registering an existing user
- Try registering a new user
- Success Login journey with user details
- InValid login details
- Search product details by selecting brands with different types
- Search results for Number of products can be more than or equal to zero
- Checkout and pay products journey
- Success order placement

Scenario: Login to eShop website
Given User is on eShop landing page
When User clicks on LOGIN button
And User enters "demouser@microsoft.com", "Pass@word1"
And User clicks on Log in button
Then User is on eShop home page

Scenario: Login with invalid details to eShop website
Given User is on eShop landing page
When User clicks on LOGIN button
And User enters "blahblah", "blah@12"
And User clicks on Log in button
Then User sees error message

Scenario: Registration new user to eShop website
Given User is on eShop landing page
When User clicks on LOGIN button
And User clicks on Register new user button
And User enters valid username, password
And User clicks on register button
Then User is on eShop home page

Scenario: User places the order
Given User successfully logins to app
And User selects "brand", "type"
And User clicks on search button
When User select the "productName"
And User checkouts the order
And User is ready to pay for the order
Then User should see the success message

Scenario: User deletes placed orders
Given User selects "<brand>", "<type>"
And User clicks on search button
When User select the "<productName>"
And User checkouts the order
And User deletes all placed order
Then User should see the basket empty message

## API Automated Tests

## purpose
The purpose of tests is to perform API automation for eShopOnWeb website

### running API tests using IntelliJ IDE
- find runner via path eShopTest/src/main/java/org/example/runners/RunnerAPI.java
- select features as "src/main/resources/features/eShopAPI.feature"
- select glue as {"org.example.API"}
- click on green play button to run tests

### tests are
- Authenticating an user and generate auth-token
- Get catalog-brands details using auth-token
- Perform CRUD operations for catalog-items endpoints using auth-token
- POST,PUT catalog-items request gives 403 Forbidden after submitting auth-token

Scenario: User Authentication
Given User authenticates with "demouser@microsoft.com", "Pass@word1"

Scenario: Send get request for catalog brands
When User sends get request for catalog brands api
Then User receives 200 status

Scenario: Send post request for catalog items
When User sends post request for catalog items api
Then User receives 200 status

Scenario: Send edits request for catalog items
When User sends put request for catalog items api
Then User receives 200 status

Scenario: Send deletes request for catalog items
When User sends delete request for catalog items api
Then User receives 200 status

Scenario: Send get request for catalog types
When User sends get request for catalog types api
Then User receives 200 status

