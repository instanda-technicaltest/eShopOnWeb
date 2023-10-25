Feature: Checkout tests
  As a Customer, I want to checkout the cart menu and make payment.

  Scenario Outline: I can review cart
    Given I am on the Catalog page
    When I click on Add to basket for a product
    And I see the cart Page
    Then I can checkout
    And I input "<EMAIL>" and "<PASSWORD>" to login page
    And I click on login button
    And I see review Page
    And I make payment

    Examples:
      | EMAIL                  | PASSWORD   |
      | demouser@microsoft.com | Pass@word1 |