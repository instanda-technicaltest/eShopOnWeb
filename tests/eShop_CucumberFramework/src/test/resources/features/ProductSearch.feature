Feature: To Test Product Search functionality on eShop Page
  
  @Smoke @Regression
  Scenario Outline: Validate Type Search functionality
    Given I Open the Browser
    And I Navigate to the URL
    When I select <Type> and click search
    Then all <Type> search products are resulted
    

    Examples: 
      |  Type  |
     	| "Sheet"|
      | "Mug"  |
  
  @Regression
  Scenario Outline: Validate Type search products display count
    Given I Open the Browser
    And I Navigate to the URL
    When I select <Type> and click search
    Then all <Type> search products are resulted
    And verify the all products count
    

    Examples: 
      |  Type    |
     	| "T-Shirt"|