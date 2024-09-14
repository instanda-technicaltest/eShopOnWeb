Feature: full Operation

   Background: login
    Given I open the website
    When I click on login button
    And I enter the email "admin@microsoft.com" and password "Pass@word1"
    And I click on Log in button

    Scenario: Journey of product to cart
      Given I select T-Shirt from dropdown menu
      When I add .NET FOUNDATION SWEATSHIRT into cart
      And I make quantity as "1"
      And I click on update button and verify the amount
      And I click On checkout button
      And I click on Pay Now Button
      Then I verify the Success message