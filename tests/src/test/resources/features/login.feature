@login
Feature: Login page UI and functionality

  Scenario: Login page should be displayed
    Given I am on the "Login" page
    Then I should see the email field
    And I should see the password field
    And I should see the Login Link
    And I should see the remember me checkbox
    And I should see the forgot your password link
    And I should see the new user registration link

  Scenario Outline: Login with valid credentials
    Given I am on the "Login" page
    When I enter the "<email>" and "<password>"
    And I click on the "Login" button
    Then I should see the "<email>" in the header

    Examples:
      | email                  | password   |
      | demouser@microsoft.com | Pass@word1 |
      | admin@microsoft.com    | Pass@word1 |








