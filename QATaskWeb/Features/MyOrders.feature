Feature: MyOrder

As a user
I want access my orders
So that I can view my orders


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
Scenario: Verify user can check order detail
    When user hovers on login email on the top menu
    And user clicks my orders button
    Then my order history page displayed
    When user clicks detail button
    Then order number should displayed

