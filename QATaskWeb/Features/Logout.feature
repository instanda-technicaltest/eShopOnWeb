Feature: Logout
 As a user
 I want to logout
 So that no one can have access to my account


Background:
    Given user navigates to eShopOnWeb
    Then eShopOnWeb page displayed successfully
    When user clicks login button
    Then Login page displayed
    When user enters email address "admin@microsoft.com"
    And user enters password "Pass@word1"
    And user ticks remember me checkbox
    And user clicks Log in


@tag1
Scenario: Verify user can logout
    When user hovers on login email on the top menu
    And user clicks logout button
    Then login page displayed successfully
