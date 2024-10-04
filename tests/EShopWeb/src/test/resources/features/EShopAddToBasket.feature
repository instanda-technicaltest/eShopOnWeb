Feature: Validate various products in good build application

  Background:
    Given User is on EShop Home page "http://localhost:5106/"

  @Regresion @AutomationTest @Login
  Scenario: Validate user is able to login to eshop application
    Given User clicks on login button
    And User enters username as "demouser@microsoft.com"
    And User enters password as "Pass@word1"
    And User clicks on login button on login page
    And User validates the home page

  @Regresion @AutomationTest @HomePage
  Scenario: Validate user is able to filter the products on Home Page
    Given User clicks on login button
    And User enters username as "demouser@microsoft.com"
    And User enters password as "Pass@word1"
    And User clicks on login button on login page
    And User validates the home page
    And User selects ".NET" from brand dropdown list
    And User selects "Mug" from type dropdown list
    And User clicks on arrow button
    And User validates the filter results

  @Regresion @AutomationTest @HomePage @AddToBasket
  Scenario: Validate user is able to add product to basket and able to placed the order
    Given User clicks on login button
    And User enters username as "demouser@microsoft.com"
    And User enters password as "Pass@word1"
    And User clicks on login button on login page
    And User validates the home page
    And User selects ".NET" from brand dropdown list
    And User selects "Mug" from type dropdown list
    And User clicks on arrow button
    And User validates the filter results
    And User clicks on Add to Basket button
    And User validates product is added to the basket
    And User click on basket link
    And User select quantity as "2"
    And User validates the total price of the product
    And User click on checkout button
    And User validates the total price of the product on pay now page
    And User click on Pay now button
    And User validates "Thanks for your Order!" message