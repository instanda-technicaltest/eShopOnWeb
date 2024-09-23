Feature: APITestPOST
  As a user
  I want to validate the API response POST Call

@tag1
Scenario: Validate POST API to create catalog items
	Given I have the POST API endpoint "https://localhost:44339/api/catalog-items"  
    And I have the following catalog items data
      | id | name                          | description                | price | pictureUri             | catalogTypeId | catalogBrandId |
      | 5  | Roslyn Red Sheet               | Roslyn Red Sheet          | 8.5   | /images/products/5.png | 3             | 5              |
      | 4  | .NET Foundation Sweatshirt     | .NET Foundation Sweatshirt| 12.0  | /images/products/4.png | 2             | 2              |
      | 3  | Prism White T-Shirt            | Prism White T-Shirt       | 12.0  | /images/products/3.png | 2             | 5              |           
   	When I send a POST request to the API 
	Then  the POST response status code should be 201
    And the response should contain "catalogItems"



