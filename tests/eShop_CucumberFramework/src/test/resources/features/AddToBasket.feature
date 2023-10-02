
Feature: To Verify adding products to basket

@Smoke @Regression
  Scenario: Validate adding products to basket
    Given I Open the Browser
    And I Navigate to the URL
    When I add below products to basket
    	| 		Product 						| count | 
      | "Prism White T-Shirt" 	|     2 | 
      | "OSLYN RED SHEET" 			|     1 |
    Then verify all products count in cart
   
      
