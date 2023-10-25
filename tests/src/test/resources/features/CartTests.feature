Feature: Cart tests
  As a Customer, I want to view cart menu and add product to cart

  Scenario: View the cart after adding the product to basket
    Given I am on the Catalog page
    When I click on Add to basket for a product
    Then I see the cart Page

  Scenario Outline: Update the quantity and Navigate to checkout
    Given I am on the Catalog page
    When I click on Add to basket for a product
    Then I see the cart Page
    And I can update the quantity of the product added to "<Quantity>"

    Examples:
      | Quantity |
      |   2      |

  Scenario: I can checkout the product after adding product to basket
    Given I am on the Catalog page
    When I click on Add to basket for a product
    Then I see the cart Page
    And I can checkout
