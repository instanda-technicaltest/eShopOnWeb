Feature: To Test Registration and Login functionality on eShop Page

  @Smoke @Regression
  Scenario: Validate Registration Functionality
    Given I Open the Browser
    And I Navigate to the URL
    When I click on Login
    Then Login page is displayed
    When I click on Register as a new user
    And I enter email and password
    And I click Register
    Then I login to eShopOnWeb Page
    And I logout
    When I login back with registered user
    Then I login to eShopOnWeb Page
    
  @Smoke @Regression
  Scenario: Validate Login Functionality with valid credentials
    Given I Open the Browser
    And I Navigate to the URL
    When I click on Login
    And I enter "demouser@microsoft.com" and "Pass@word1"
    Then Login page is displayed
    
  @Regression
  Scenario Outline: Validate Login Functionality with invalid credentials
    Given I Open the Browser
    And I Navigate to the URL
    When I click on Login
    And I enter invalid <email> or <password>
   	Then Login failed error is displayed

    Examples: 
      | 				email 				  |   password 	|
      | "demouse@microsoft.com" | "Pass@word1" |
      | "demouser@microsoft.com"| "Pass@word"  |
  