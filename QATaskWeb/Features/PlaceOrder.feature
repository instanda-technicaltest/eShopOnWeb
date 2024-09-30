Feature: PlaceOrder

As a user on eShopOnWeb
I want to login
So that I can place, filter, remove, and see next and previous order

Background:
    Given user navigates to eShopOnWeb
    Then eShopOnWeb page displayed successfully
    When user clicks login button
    Then Login page displayed
    When user enters email address "admin@microsoft.com"
    And user enters password "Pass@word1"
    And user ticks remember me checkbox
    And user clicks Log in


@tag
Scenario: Verify user can add order to the bascket
    Then eShopOnWeb page displayed successfully
    When user clicks add to basket to add dot net black & white mug
    Then product displayed
    When user clicks continue shopping
    And user clicks add to basket to add prism white T-Shirt
    And user clicks update button
    And user clicks checkout button
    And user clicks back button
    When user clicks continue shopping
    And user clicks add to basket to dot net black sweatshirt
    And user clicks checkout button
    When user clicks pay now
    Then Thanks for your order! displayed

@tag
Scenario: Verify user can filter order
    When user clicks on brand dropdown to select other
    And user clicks on type dropdown to select sheet
    And user clicks on forward arrow button
    Then roslyn red sheet should display


@tag
Scenario: Verify user can remove from basket
    When user clicks add to basket to add prism white T-Shirt
    Then product displayed
    When user clicks on the down arrow to remove a product
    And user clicks update button
    Then basket is empty displayed


@tag
Scenario: Verify user can see the next and previous product
    When user clicks next button
    Then page two to two should displayed
    When user clicks previous button
    Then page one to two should diaplayed




