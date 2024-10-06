@addToBasketAndCheckoutUi
Feature: User add product to basket and place order

  @ui
  Scenario: Verify That User Place Order Successfully
    Given I am on homepage
    When I click on login link
    And I enter email "demouser@microsoft.com"
    And I enter password "Pass@word1"
    And I click on login button
    And I add product "Roslyn Red Sheet" to the basket
    Then I verify the product name is "Roslyn Red Sheet"
    And I update the quantity to "2"
    And I click on the update button
    Then I verify the total is "$ 17.00"
    And I click on the checkout button
    Then Page title should be "Checkout - Microsoft.eShopOnWeb"
    And I click on the Pay Now button
    Then I verify the message "Thanks for your Order!"