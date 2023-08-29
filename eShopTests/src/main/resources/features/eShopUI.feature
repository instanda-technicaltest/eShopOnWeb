@regression
Feature: E2E journey for eShop website

  Background: Login user to eShop website
    Given User is on eShop landing page
    When User clicks on LOGIN button
    And User enters "demouser@microsoft.com", "Pass@word1"
    And User clicks on Log in button
    Then User is on eShop home page

  Scenario Outline: User places the order
    Given User selects "<brand>", "<type>"
    And User clicks on search button
    When User select the "<productName>"
    And User checkouts the order
    And User is ready to pay for the order
    Then User should see the success message
    Examples:
      | brand | type | productName            |
      | .NET  | Mug  | .NET BLACK & WHITE MUG |