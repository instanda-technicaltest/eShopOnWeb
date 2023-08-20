Feature: Validate the UI user journey

  @Smoke
  Scenario Outline: To validate the shopping cart
    Given user navigate to the eShopOnWeb site
    And user login with "Email" and "password"
    When user select the "<product>" to Add to Basket
    Then user validate the shopping cart values
    Examples:
      | product                   |
      | .NET Bot Black Sweatshirt |
      | Prism White T-Shirt       |


  @Smoke
  Scenario Outline: To validate the checkout process
    Given user navigate to the eShopOnWeb site
    And user login with "Email" and "password"
    When user select the "<product>" to Add to Basket
    And user validate the checkout page
    Examples:
      | product             |
      | Prism White T-Shirt |


  @Smoke
  Scenario Outline: To validate the order completion
    Given user navigate to the eShopOnWeb site
    And user login with "Email" and "password"
    When user select the "<product>" to Add to Basket
    And user proceed to checkout
    Then the order is placed successfully
    Examples:
      | product             |
      | Prism White T-Shirt |


  @Smoke
  Scenario Outline: To validate continue shopping option and add more product
    Given user navigate to the eShopOnWeb site
    And user login with "Email" and "password"
    When user select the "<product1>" to Add to Basket
    And user select continue shopping
    And user select the "<product2>" to Add to Basket
    And user proceed to checkout
    Then the order is placed successfully
    Examples:
      | product1            | product2                  |
      | Prism White T-Shirt | .NET Bot Black Sweatshirt |

