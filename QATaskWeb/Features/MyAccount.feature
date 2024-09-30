Feature: MyAccount

As a user on eShopOnWeb
I want to accass my account
So that I can manage it.

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
Scenario: Verify user can view My account page
    When user hovers on login email on the top menu
    And user clicks my account button
    Then manage your account page displayed
