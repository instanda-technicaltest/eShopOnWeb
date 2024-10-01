#Features to be tested
# eShopOnWeb home page loads successfully - Done
# Add to basket functionality works for demouser and guest user - Done
  # Filter products By Category and Type
# Demo User can login to eShopOnWeb - Login from Checkout when Products have been added to the basket
# Demo User can view the basket
# User can remove a product from the basket - set qty to 0
# Demo User can update the quantity of a product in the basket
# Demo User can checkout the basket


@productcatalog
Feature: eShopOnWeb front-end UI features testing
  @productcatalog
  Scenario: eShopOnWeb home page loads successfully
    Given the user visits eShopOnWeb website
    Then Logo is present on the home page
    And Atleast one product is displayed on the home page
    And Product image, name, price, add to basket button are displayed on the home page
    And Login link is displayed on the home page

  @productcatalog
  Scenario: Add to basket functionality works
    Given the user visits eShopOnWeb website
    And Atleast one product is displayed on the home page
    When the user clicks on Add to basket button for first product
    Then the user is on the "Basket" page
    And basket in header displays 1
    And the product added to the basket is displayed with image, name, price, quantity, total price


















