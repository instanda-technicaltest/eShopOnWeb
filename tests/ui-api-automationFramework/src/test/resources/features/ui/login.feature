@loginUi
Feature: Login functionality

  @ui
  Scenario:  verify that user should login successfully with valid credentials
    Given I am on homepage
    When I click on login link
    And I enter email "demouser@microsoft.com"
    And I enter password "Pass@word1"
    And I click on login button
    Then email "demouser@microsoft.com" should be displayed

  @ui
  Scenario:  Verify the error message with invalid credentials
    Given I am on homepage
    When I click on login link
    And I enter email "test@microsoft.com"
    And I enter password "Pass@test"
    And I click on login button
    Then user should not login and "Invalid login attempt." should display