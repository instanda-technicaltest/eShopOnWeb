Feature: Catalog Page
  As a online customer,
  I want to be able to view products, add them to my cart and search for specific products,
  So that I can make my shopping experience easier and faster.

  Scenario: View a product from the product lists on Catalog page
    Given I am on the Catalog page
    Then I should see a list of products

  Scenario: Add a product to the cart from the Catalog page
    Given I am on the Catalog page
    And I should see a list of products
    When I click on Add to basket for a product
    Then the product should be added to my cart

  Scenario Outline: Search for a product by brand and type
    Given I am on the Catalog page
    When I select the brand "<BrandName>" from the dropdown
    And I select the type "<ProductType>" from the dropdown
    And I click on the Search button
    Then I should see a list of filtered product
    Examples:
      | BrandName | ProductType |
      | .NET      | Mug         |