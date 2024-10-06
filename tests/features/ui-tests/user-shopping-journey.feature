@UIRegression
Feature: As a user I expect to test the core actions of shopping journey.

  @user-shopping-journey
  Scenario Outline: User logs in, filters products, adds to cart, checkout, pay and logs out
    Given the user is logged into the application
    When the user selects "<brand>" brand and "<type>" type
    And the user adds "<product>" to the cart
    And the user proceeds to checkout
    And the user clicks the "Pay Now" button
    Then the user should see the order confirmation message
    And the user logs out

    Examples:
      | brand | type    | product                    |
      | All   | All     | .NET BOT BLACK SWEATSHIRT  |
      | All   | Mug     | .NET BLACK & WHITE MUG     |
      | .NET  | T-Shirt | .NET FOUNDATION SWEATSHIRT |
