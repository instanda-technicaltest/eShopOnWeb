@UIRegression
Feature: As a user I expect to test log in functionality of eshoponweb application.

  @login
  Scenario: Successful login
    Given the user is on the login page
    When the user logs in with valid credentials
    Then the user should be redirected to home page
    And the user logs out

  @invalid-login
  Scenario: UnSuccessful login
    Given the user is on the login page
    When the user logs in with invalid credentials
    Then the user should see invalid login message

